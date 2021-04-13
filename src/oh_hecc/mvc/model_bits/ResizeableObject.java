package oh_hecc.mvc.model_bits;

/**
 * An interface for objects that can be resized
 */
public interface ResizeableObject {

    /**
     * Model should call this for all buttons when it's resized,
     * so the buttons will remain the same size (relative to the viewable model itself)
     * and at the the bottom of the model
     * @param w new width of the model
     * @param h new height of the model
     */
    void resize(int w, int h);
}
