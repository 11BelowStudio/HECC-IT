package oh_hecc.game_parts;

import heccCeptions.*;
import oh_hecc.Heccable;
import oh_hecc.game_parts.component_editing_windows.EditorWindowInterface;
import oh_hecc.game_parts.component_editing_windows.GenericEditorWindow;
import oh_hecc.game_parts.component_editing_windows.MetadataEditorWindow;
import oh_hecc.game_parts.component_editing_windows.PassageEditorWindow;
import oh_hecc.game_parts.metadata.MetadataEditingInterface;
import oh_hecc.game_parts.passage.EditablePassage;
import oh_hecc.game_parts.passage.PassageEditingInterface;
import oh_hecc.game_parts.passage.PassageStatus;
import oh_hecc.game_parts.passage.SharedPassage;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A single class that can encapsulate all the game data stuff
 */
public class GameDataObject implements Heccable, EditWindowGameDataInterface, MVCGameDataInterface {

    /**
     * Map of all passages, mapped to their UUIDs
     */
    private final Map<UUID, PassageEditingInterface> passageMap;

    /**
     * The metadataEditingInterface object for this heccin game
     */
    private final MetadataEditingInterface theMetadata;

    /**
     * Where the .hecc file is actually saved
     */
    private final Path savePath;

    /**
     * A file to use as a backup for the last valid version of the .hecc file.
     */
    private final Path lastValidPath;

    /**
     * UUID of the start passage
     */
    private Optional<UUID> startUUID;

    /**
     * A singleton GenericEditorWindow
     */
    private final GenericEditorWindow editorWindow = null;


    //public GameDataObject()


    /**
     * Instantiates a new Game data object.
     *
     * @param pMap         the passage map
     * @param meta         the metadataeditinginterface object
     * @param saveLocation where the hecc file is saved
     */
    public GameDataObject(Map<UUID, PassageEditingInterface> pMap, MetadataEditingInterface meta, Path saveLocation) {
        passageMap = pMap;
        theMetadata = meta;
        savePath = saveLocation;
        startUUID = Optional.empty();
        getStartUUID(true);
        updateLinkedUUIDs();

        Path fpath = savePath.getFileName();

        String fname = fpath.toString();
        if (fname.endsWith(".hecc")) {
            fname = fname.substring(0, fname.length() - 5);
        }
        fname = fname + "_lastValidVersion.hecc";
        lastValidPath = savePath.getParent().resolve(fname);

    }

    /**
     * Constructor used for creating a completely new gamedataobject (when making a new .hecc file)
     * @param meta the MetadataEditingInterface containing metadata for the new game
     * @param saveLocation the save file location for this new game
     */
    public GameDataObject(MetadataEditingInterface meta, Path saveLocation) {
        this(new HashMap<>(), meta, saveLocation);
    }

    public void updateLinkedUUIDs(){
        for (PassageEditingInterface e: passageMap.values()){
            e.updateLinkedUUIDs(passageMap);
        }
    }

    /**
     * Obtains the UUID of the start passage. Will not forcibly create a start passage.
     * @return an Optional holding the UUID of the start passage (if it exists)
     */
    @Override
    public Optional<UUID> getStartUUID(){
        return getStartUUID(false);
    }

    /**
     * Attempts to update the 'startPassage' field of the metadata,
     * along with the startUUID field of this object, and also renaming the existing start passage if needed
     * @param newStartPassage the name of the new intended start passage
     * @return true if the start passage was updated successfully (or if the current start passage was given as an argument)
     * @throws InvalidPassageNameException if the new start passage isn't valid
     */
    @Override
    public boolean updateStartPassage(String newStartPassage) throws InvalidPassageNameException {
        boolean result = false;
        String currentStartPassage = theMetadata.getStartPassage();

        if (newStartPassage.equals(currentStartPassage)){
            // if the start passage isn't changing, we just let it change.
            return true;
        }

        Optional<PassageEditingInterface> pWithOldName = (startUUID.map(passageMap::get));
        Optional<PassageEditingInterface> pWithNewName = passageMap.values().stream().filter(p -> p.getPassageName().equals(newStartPassage)).findAny();
        if (pWithOldName.isPresent()){
            if (pWithNewName.isPresent()){
                if (JOptionPane.showConfirmDialog(
                        null,
                        "<html><p>Did you want your game to start from<br>"+
                                newStartPassage + " instead of <br>" +
                                currentStartPassage + "?</p></html>",
                        "Changing start passage to other existing passage",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                ) != JOptionPane.YES_OPTION){
                    return false;
                }
                result = theMetadata.updateStartPassage(newStartPassage);
                startUUID = Optional.of(pWithNewName.get().getPassageUUID());
            } else{
                result = theMetadata.updateStartPassage(newStartPassage);
                if (JOptionPane.showConfirmDialog(
                        null,
                        "<html><p>Did you want to rename your existing start passage<br>"+
                                currentStartPassage + " to be called<br>" +
                                newStartPassage + " instead?</p></html>",
                        "Did you want to rename the existing start passage?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                ) == JOptionPane.YES_OPTION){
                    try {
                        pWithOldName.get().renameThisPassage(newStartPassage,passageMap);
                    } catch (DuplicatePassageNameException ignored) {}
                } else {
                    getStartUUID(true);
                }
            }
        } else {
            result = theMetadata.updateStartPassage(newStartPassage);
            if (pWithNewName.isPresent()){
                startUUID = Optional.of(pWithNewName.get().getPassageUUID());
            } else {
                getStartUUID(true);
            }
        }
        return result;
    }

    /**
     * Checks to see if the start passage was renamed, and, if so, it'll rename the reference to it in the metadata appropriately.
     * And if there is no start passage, it'll forcibly create one.
     */
    public void checkForStartRename(){
        if (startUUID.isPresent()){
            try {
                theMetadata.updateStartPassage(passageMap.get(startUUID.get()).getPassageName());
            } catch (Exception ignored){}
        } else {
            getStartUUID();
        }
    }

    /**
     * Obtains the UUID of the start passage
     * @param forceCreateStart if this is true, if the start passage doesn't exist yet, it will forcibly create the start passage.
     * @return an Optional holding the UUID of the start passage (if it exists)
     */
    public Optional<UUID> getStartUUID(boolean forceCreateStart){
        Optional<PassageEditingInterface> start;
        String startName = theMetadata.getStartPassage();
        if (startUUID.isPresent()){
            start = (startUUID.map(passageMap::get));
            if (start.isPresent() && (start.get().getPassageName()).equals(startName)){
                return startUUID;
            }
        }
        start = passageMap.values().stream().filter( e -> e.getPassageName().equals(startName)).findAny();
        if (start.isPresent()){
            startUUID = Optional.of(start.get().getPassageUUID());
        } else if (forceCreateStart){
            PassageEditingInterface p = new EditablePassage(theMetadata.getStartPassage());
            passageMap.put(p.getPassageUUID(),p);
            startUUID = Optional.of(p.getPassageUUID());
        } else {
            startUUID = Optional.empty();
        }
        return startUUID;
    }

    /**
     * Informs this object that the passage with the specified UUID has been deleted.
     * If aforementioned passage was the start passage, this object should set the start passage UUID to be that of another arbitrary passage which still exists.
     * @param uuid UUID of deleted passage.
     */
    public void thisPassageHasBeenDeleted(UUID uuid){
        passageMap.remove(uuid);
        if (startUUID.isPresent() && startUUID.get().equals(uuid)){
            String start = "Start";
            if (!passageMap.isEmpty()){
                start = passageMap.values().iterator().next().getPassageName();
            }
            try {
                theMetadata.updateStartPassage(start);
            } catch (Exception ignored){}
            getStartUUID(true);
        }
    }

    /**
     * Gets passage map.
     *
     * @return the passage map
     */
    @Override
    public Map<UUID, PassageEditingInterface> getPassageMap() {
        return passageMap;
    }

    /**
     * Gets the metadata.
     *
     * @return the the metadata
     */
    @Override
    public MetadataEditingInterface getTheMetadata() {
        return theMetadata;
    }

    /**
     * Gets the path to where this object is saved
     * @return savePath
     */
    @Override
    public Path getSavePath(){
        return savePath;
    }

    /**
     * Creates a PassageEditorWindow for the given passage (and the entire gamedata)
     * @param passage the UUID of the passage in question which is having its PassageEditorWindow opened
     * @return a {@link PassageEditorWindow} which allows a user to edit the passage in question
     */
    @Override
    public EditorWindowInterface openPassageEditWindow(UUID passage){
        EditorWindowInterface pw = new PassageEditorWindow(passageMap.get(passage),this);
        addClosedListener(pw);
        return pw;
    }

    /**
     * Creates a MetadataEditorWindow for this game
     * @return the MetadataEditorWindow allowing the user to edit the metadata
     */
    @Override
    public EditorWindowInterface openMetadataEditWindow(){
        EditorWindowInterface ew = new MetadataEditorWindow(this);
        addClosedListener(ew);
        return ew;
    }

    /**
     * Adds some window closed listeners (for cleanup and such) to the given EditorWindowInterface object
     * @param w the EditorWindowInterface that the listeners are being added to
     * @return the EditorWindowInterface but with the listeners added to it
     */
    private EditorWindowInterface addClosedListener(EditorWindowInterface w){
        w.addWindowClosedListener(
                e -> {
                    getStartUUID(true);
                    updateLinkedUUIDs();
                }
        );
        return w;
    }

    /**
     * Puts the specified PassageEditingInterface object onto the PassageMap.
     * @param p the passage to put on the map.
     */
    @Override
    public void putPassageOntoMap(PassageEditingInterface p) {
        passageMap.put(p.getPassageUUID(), p);
    }

    /**
     * Obtains the PassageEditingInterface object for the passage with the given UUID
     * @param uuidOfPassageToGet the UUID of the PassageEditingInterface object which is needed.
     * @return the appropriate PassageEditingInterface object.
     */
    @Override
    public PassageEditingInterface getPassageFromUUID(UUID uuidOfPassageToGet){
        return passageMap.get(uuidOfPassageToGet);
    }


    /**
     * Obtains the PassageEditingInterface objects of all the passages which the given passage links to
     *
     * @param uuidOfSourceObject the UUID of the passage that we're trying to find the 'child' passages of
     * @return the UUIDs of all the 'child' passages
     */
    @Override
    public Set<PassageEditingInterface> getPassageEditingInterfaceObjectsConnectedToGivenObject(UUID uuidOfSourceObject) {
        Set<PassageEditingInterface> theLinkedPassages = new HashSet<>();
        passageMap.get(uuidOfSourceObject).getLinkedPassageUUIDs().forEach(
                u -> theLinkedPassages.add(passageMap.get(u))
        );
        return theLinkedPassages;
    }

    /**
     * Obtains the UUIDs of the passages that link to the destination passage
     *
     * @param destination the UUID of the passage that we're trying to find the 'parent' passages of
     * @return the UUIDs of all the 'parent' passages
     */
    @Override
    public Set<UUID> getThePassageObjectsWhichLinkToGivenPassageFromUUID(UUID destination) {
        return passageMap.keySet().stream().filter(
                p -> passageMap.get(p).getLinkedPassageUUIDs().contains(destination)
        ).collect(Collectors.toSet());
    }

    /**
     * Saves the .hecc
     *
     * @throws IOException if it couldn't be saved.
     */
    public void saveTheHecc() throws IOException {
        Files.write(savePath, Collections.singleton(toHecc()));
    }

    /**
     * Saves the .hecc, but also checks to see if it's valid or not (and, if it's valid, it overwrites the '_lastValidVersion' backup)
     *
     * @throws IOException if there's an IO problem preventing it from being saved.
     * @throws HeccCeption if there's a problem with the .hecc file that renders it invalid.
     */
    @Override
    public void saveTheHeccCheckingValidity() throws IOException, HeccCeption {
        Files.write(savePath, Collections.singleton(toHecc()));
        if (checkForValidity()) {
            Files.write(lastValidPath, Collections.singleton(toHecc()));
        }
    }

    /**
     * Get the .hecc version of the data held in this object
     *
     * @return The data held within theMetadata and in passageMap but in .hecc form
     */
    @Override
    public String toHecc() {

        StringBuilder heccBuilder = new StringBuilder();
        heccBuilder.append(theMetadata.toHecc());
        heccBuilder.append("\n");
        heccBuilder.append(startDepthFirstHeccBuilder());
        return heccBuilder.toString();
    }


    /**
     * Returns the entire GameDataObject in .hecc form using streams.
     * But the passages aren't in any particular order.
     * @return a string containing all the .hecc code.
     */
    @Deprecated
    private String quickAndDirtyHecc(){
        return theMetadata.toHecc().concat("\n").concat(
                passageMap.values().stream().map(Heccable::toHecc)
                        .collect(Collectors.joining("\n")));
    }

    /**
     * An iterative method for building the .hecc code for the passages, (breadth first)
     * @return a string containing all the .hecc code for the passages
     */
    @Deprecated
    private String breadthFirstHeccBuilder(){
        StringBuilder sb = new StringBuilder();
        Set<UUID> keys = new HashSet<>(passageMap.keySet());
        Set<UUID> nextChildren = new HashSet<>();
        Set<UUID> current = new HashSet<>();
        if (startUUID.isPresent()){
            nextChildren.add(startUUID.get());
        } else {
            nextChildren.add(keys.iterator().next());
        }
        do{
            do{
                current.addAll(nextChildren);
                keys.removeAll(current);
                current.forEach(
                        c -> {
                            SharedPassage sp = passageMap.get(c);
                            sb.append(sp.toHecc());
                            sb.append("\n");
                            nextChildren.addAll(sp.getLinkedPassageUUIDs());
                        }
                );
                nextChildren.retainAll(keys);
                current.clear();
            } while(!nextChildren.isEmpty());
            if (!keys.isEmpty()){
                nextChildren.add(keys.iterator().next());
            }
        } while(!keys.isEmpty());
        return sb.toString();
    }

    /**
     * A method which starts to build the .hecc file recursively (depth first, prefix traversal)
     * @return a string containing all the .hecc code for the passages
     */
    private String startDepthFirstHeccBuilder(){
        StringBuilder sb = new StringBuilder();
        Set<UUID> keys = new HashSet<>(passageMap.keySet());
        if (!keys.isEmpty()){
            UUID start = startUUID.orElse(keys.iterator().next());
            keys.remove(start);
            sb.append(depthFirstHeccBuilder(keys,start));

            while(!keys.isEmpty()){
                start = keys.iterator().next();
                keys.remove(start);
                sb.append(depthFirstHeccBuilder(keys,start));
            }

        }
        return sb.toString();
    }

    /**
     * Only call this via startDepthFirstHeccBuilder. This actually does the recursion and the depth first (prefix traversal) stuff
     * @param allKeys set of all the keys which haven't been found yet
     * @param current UUID of the current key being looked at
     * @return a string containing the .hecc code for the current passage and all its children, constructed depth-first.
     */
    private String depthFirstHeccBuilder(Set<UUID> allKeys, UUID current) {
        StringBuilder sb = new StringBuilder();
        SharedPassage sp = passageMap.get(current);
        sb.append(sp.toHecc());
        /*
        we explicitly do a deep clone here, because not doing a deep clone leads to the actual
        set of linked passage UUIDs belonging to the current object getting messed with.

        Which, as you can imagine, is kinda awkward.
         */
        Set<UUID> kids = new HashSet<>(sp.getLinkedPassageUUIDs());
        kids.retainAll(allKeys);
        if (!kids.isEmpty()) {
            allKeys.removeAll(kids);
            for (UUID u : kids) {
                sb.append(depthFirstHeccBuilder(allKeys, u));
            }
        }
        return sb.toString();
    }

    /**
     * Call this to work out if the current game is valid or not.
     *
     * @return true if it's valid, false otherwise.
     * @throws HeccCeption with details of the problem if it's invalid.
     */
    public boolean checkForValidity() throws HeccCeption {

        if (passageMap.isEmpty()) {
            throw new NoPassagesException();
        }
        if (!startUUID.isPresent()) {
            throw new MissingStartingPassageException(theMetadata.getStartPassage());
        }
        for (PassageEditingInterface p : passageMap.values()) {
            if (!p.isThisValid()) {
                String thePassage = p.getPassageName();
                switch (p.getPassageStatus()) {
                    case DELETED_LINK:
                        throw new heccCeptions.DeletedLinkPresentException(thePassage);
                    case EMPTY_CONTENT:
                        throw new heccCeptions.EmptyPassageException(thePassage);
                    default:
                        break;
                }
            }
        }

        return true;


    }


}
