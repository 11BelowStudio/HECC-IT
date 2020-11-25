package oh_hecc.game_parts.component_editing_windows;

import java.awt.event.WindowEvent;

/**
 * Lighterweight interface for GenericEditorWindow, for purposes of passing it to the main program
 */
public interface EditorWindowInterface {

    /**
     * Adding a WindowClosed event listener via a Consumer<\WindowEvent\> functional interface
     * @param closeEvent the functional interface holding the function that needs to be called when this window is closed.
     * @see java.util.function.Consumer
     */
    void addWindowClosedListener(java.util.function.Consumer<WindowEvent> closeEvent);
}
