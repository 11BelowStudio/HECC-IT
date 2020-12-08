package hecc_up;

/**
 * This used to be the main class for HECC UP
 * but now it's mainly used for testing the creation of a HECCIN' Game when I'm too lazy to use HeccUpGUI
 * @deprecated just use HeccUpGUI instead lmao
 * @see HeccUpGUI
 */
@Deprecated
public class OldHeccUpMain implements LoggerInterface{
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
     * and attempts to output it to outputs/hecc_up_testing_v2/heccin_game
     */
    public void heccUpTheGame(){

        heccUpHandler.attemptToHeccUpTheGame(
                "src/assets/textAssets/HeccSample.hecc",
                "outputs/hecc_up_testing_v2/heccin_game"
        );

    }


    /**
     * main method bottom text
     * @param args
     */
    public static void main(String[] args) {
        OldHeccUpMain heccUp = new OldHeccUpMain();
        heccUp.heccUpTheGame();
    }

}


//TODO stuff for after MVP

//TODO (after MVP): further Metadata

//TODO (after MVP): OH-HECC (Optional Help for HECC)