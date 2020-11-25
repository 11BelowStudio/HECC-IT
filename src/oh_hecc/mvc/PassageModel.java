package oh_hecc.mvc;

import oh_hecc.game_parts.metadata.EditableMetadata;
import oh_hecc.game_parts.metadata.MetadataEditingInterface;
import oh_hecc.game_parts.passage.PassageEditingInterface;
import oh_hecc.mvc.model_bits.PassageObject;
import utilities.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.*;

/**
 * Okay so this is the Model of the actual network of passages and such
 */
public class PassageModel extends Model implements EditModelInterface {




    final Map<UUID, PassageEditingInterface> passageMap;
    final Map<UUID, PassageObject> visibleNetwork;

    final MetadataEditingInterface theMetadata;

    final Vector2D topRightCorner;

    final Vector2D scrollMovementVector;

    public PassageModel(Controller c, EditableMetadata metadata, Map<UUID, PassageEditingInterface> allPassages){
        super(c);

        theMetadata = metadata;
        passageMap = allPassages;

        visibleNetwork = new HashMap<>();

        for (PassageEditingInterface p: passageMap.values()) {
            p.updateLinkedUUIDs(allPassages);
            visibleNetwork.put(p.getPassageUUID(), new PassageObject(this,p));
        }

        topRightCorner = new Vector2D(0,0);

        scrollMovementVector = new Vector2D(0,0);

    }


    public void update(){
        //if (theController.action.)
    }


    public void drawModel(Graphics2D g){
        super.drawModel(g);

        //backup of the original lack of a transform
        AffineTransform unscrolled = g.getTransform();

        //translates everything in the negative direction to where the top-right corner currently is
        g.translate(-topRightCorner.x,-topRightCorner.y);

        //the objects representing links between passages are drawn first
        for (PassageObject p: visibleNetwork.values()) {
            p.drawLinks(g);
        }

        //then the passage objects themselves are drawn
        for (PassageObject p: visibleNetwork.values()){
            p.draw(g);
        }

        //now it goes back to where it was before it was scrolled
        g.setTransform(unscrolled);
    }



    @Override
    public PassageEditingInterface getPassageFromUUID(UUID uuidOfPassageToGet){
        return passageMap.get(uuidOfPassageToGet);
    }

    @Override
    public PassageObject getPassageObjectFromUUID(UUID uuidOfPassageObjectToGet){
        return visibleNetwork.get(uuidOfPassageObjectToGet);
    }

    @Override
    public Set<UUID> getUUIDsOfPassagesLinkedToParticularPassageFromUUID(UUID sourcePassageUUID){
        return passageMap.get(sourcePassageUUID).getLinkedPassageUUIDs();
    }

    @Override
    public Set<PassageEditingInterface> getPassagesFromSetOfUUIDs(Set<UUID> getThesePassages){
        Set<PassageEditingInterface> thePassages = new HashSet<>();
        if (!getThesePassages.isEmpty()){
            for(UUID u: getThesePassages){
                thePassages.add(passageMap.get(u));
            }
        }
        return thePassages;
    }

    @Override
    public Set<PassageEditingInterface> getPassageEditingInterfaceObjectsConnectedToGivenObject(UUID uuidOfSourceObject){
        Set<PassageEditingInterface> theLinkedPassages = new HashSet<>();
        Set<UUID> linkedUUIDs = passageMap.get(uuidOfSourceObject).getLinkedPassageUUIDs();
        if (!linkedUUIDs.isEmpty()){
            for (UUID u: linkedUUIDs) {
                theLinkedPassages.add(passageMap.get(u));
            }
        }
        return theLinkedPassages;
    }

    @Override
    public Map<UUID, PassageEditingInterface> getThePassageMap(){
        return passageMap;
    }

}
