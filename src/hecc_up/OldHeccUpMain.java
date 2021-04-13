package hecc_up;

import java.nio.file.Paths;

/**
 * This used to be the main class for HECC UP
 * but now it's mainly used for testing the creation of a HECCIN' Game when I'm too lazy to use HeccUpGUI
 *
 * @see HeccUpGUI
 * @deprecated just use HeccUpGUI instead lmao
 */
@Deprecated
public class OldHeccUpMain implements LoggerInterface {
    //The former main class for HeccUp

    /**
     * the HeccUpHandler object
     */
    private final HeccUpHandler heccUpHandler;

    /**
     * Constructor
     * <br>
     * bottom text
     */
    public OldHeccUpMain(){

        heccUpHandler = new HeccUpHandler(this);
    }


    /**
     * Attempts to hecc up the game stored as src/assets/textAssets/HeccSample.hecc,
     * and attempts to output it to outputs/hecc_up_testing_v3/heccin_game
     */
    public void heccUpTheGame(){

        heccUpHandler.attemptToHeccUpTheGame(
                Paths.get("src/assets/textAssets/HeccSample2.hecc"),
                Paths.get("old outputs/hecc_up_testing_v3/heccin_game")
        );

    }


    /**
     * main method bottom text
     * @param args le command line arguments have arrived (and will promptly be ignored)
     */
    public static void main(String[] args) {
        final OldHeccUpMain heccUp = new OldHeccUpMain();
        heccUp.heccUpTheGame();
    }

}

