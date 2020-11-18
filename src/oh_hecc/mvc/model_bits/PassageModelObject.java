package oh_hecc.mvc.model_bits;

import oh_hecc.mvc.PassageModel;
import utilities.Vector2D;

/**
 * This is basically the same as AbstractObject.
 * But with a reference to a PassageModel object as well.
 */
public abstract class PassageModelObject extends AbstractObject {


    /**
     * The overarching PassageModel object that holds this object
     */
    final PassageModel theModel;

    /**
     * The constructor for the PassageModelObject
     * @param pos initial position for this PassageModelObject
     * @param model the actual PassageModel that this needs a reference to
     */
    PassageModelObject(Vector2D pos, PassageModel model){
        super(pos);
        theModel = model;
    }




}
