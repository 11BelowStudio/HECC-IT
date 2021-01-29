package oh_hecc.game_parts.passage;

import utilities.Vector2D;

import java.util.*;

public abstract class AbstractPassage implements PassageReadingInterface {

    /**
     * The name of this passage
     */
    String passageName;

    /**
     * The actual raw contents of this passage
     */
    String passageContent;


    /**
     * The set with the names of the other passages this passage links to
     */
    final Set<String> linkedPassages;


    /**
     * The list of tags that this passage has
     */
    final List<String> passageTags;


    /**
     * Something to let the user know about the status of this passage.
     */
    PassageStatus status;

    AbstractPassage(){
        passageName = UUID.randomUUID().toString();
        linkedPassages = new HashSet<>();
        passageTags = new ArrayList<>();
        passageContent = "";
        updatePassageStatus();
    }

    AbstractPassage(String passageName){
        this();
        this.passageName = passageName.trim();
        updatePassageStatus();
    }

    AbstractPassage(String passageName, String unparsedContent){
        this(passageName);
        this.passageContent = unparsedContent.trim();
        updatePassageStatus();
    }

    AbstractPassage(String passageName, String unparsedContent, String lineEndMetadata){
        this(passageName, unparsedContent);
        passageTags.addAll(PassageReadingInterface.readTagMetadata(lineEndMetadata));
        linkedPassages.addAll(SharedPassage.findLinks(unparsedContent));
        updatePassageStatus();
    }

    /**
     * This method is used to update the 'status' of this passage.
     * If the passage contents (still) contains a reference to a deleted passage, this is a 'DELETED_LINK' passage.
     * If the passage contains no links to other notes, this passage is an END_NODE
     * otherwise, it's NORMAL.
     */
    void updatePassageStatus() {
        if (passageContent.isEmpty()){
            status = PassageStatus.EMPTY_CONTENT;
        }else if(SharedPassage.doesPassageContentContainDeletedLinks(passageContent)){
            status = PassageStatus.DELETED_LINK;
        } else if (linkedPassages.isEmpty()){
            status = PassageStatus.END_NODE;
        } else{
            status = PassageStatus.NORMAL;
        }
    }


    /**
     * Obtains the passage content
     * @return passage content
     */
    @Override
    public String getPassageContent(){
        return this.passageContent;
    }

    /**
     * Obtains the passage name
     * @return passage name
     */
    @Override
    public String getPassageName(){
        return this.passageName;
    }


    /**
     * Obtains the list of passage tags
     * @return passageTags
     */
    @Override
    public List<String> getPassageTags(){
        return passageTags;
    }

    /**
     * gets the set of linked passages
     * @return linkedPassages
     */
    @Override
    public Set<String> getLinkedPassages(){
        return linkedPassages;
    }

    /**
     * Obtain an enum representing the current status of the passage
     *
     * @return a passageStatus value for this passage
     */
    @Override
    public PassageStatus getPassageStatus() {
        return status;
    }


}
