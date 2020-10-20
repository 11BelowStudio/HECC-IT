package hecc_up;

public class HeccUpHandler {
    private PassageParser passageParser;

    private FolderOutputter outputter;

    private LoggerInterface logger;

    public HeccUpHandler(LoggerInterface parent, String inputHecc, String outputPath){
        logger = parent;
        passageParser = new PassageParser(inputHecc,parent);
        outputter = new FolderOutputter();
    }
}
