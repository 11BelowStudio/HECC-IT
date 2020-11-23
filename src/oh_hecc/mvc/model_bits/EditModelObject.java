package oh_hecc.mvc.model_bits;

import oh_hecc.mvc.EditModelInterface;
import utilities.Vector2D;

/**
 * This is basically the same as AbstractObject.
 * But with a reference to a PassageModel object as well.
 */
public abstract class EditModelObject extends AbstractObject {


    /**
     * The overarching PassageModel object that holds this object
     */
    final EditModelInterface theModel;

    /**
     * The constructor for the PassageModelObject
     * @param pos initial position for this PassageModelObject
     * @param model the actual PassageModel that this needs a reference to
     */
    EditModelObject(Vector2D pos, EditModelInterface model){
        super(pos);
        theModel = model;
    }




}
