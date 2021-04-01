package oh_hecc.game_parts.component_editing_windows;

/**
 * Lighterweight interface for GenericEditorWindow, for purposes of passing it to the main program
 */
public interface EditorWindowInterface {

    /**
     * Adding a WindowClosed event listener via a Runnable functional interface
     * @param closeEvent the functional interface holding the function that needs to be called when this window is closed.
     * @see java.lang.Runnable
     */
    void addWindowClosedListener(Runnable closeEvent);
}
