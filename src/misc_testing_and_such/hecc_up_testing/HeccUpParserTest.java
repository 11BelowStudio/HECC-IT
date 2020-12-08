package misc_testing_and_such.hecc_up_testing;


import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
27/08/2020

HECC-UP parser test

this basically is a demonstration of the parser bit of HECC-UP

it's basically being given the HECC code from the HECC-SPECC v0.1, and parses it into a HECC game.

 @author Rachel Lowe
 @deprecated use HECC-UP instead lmao
 */
@Deprecated
public class HeccUpParserTest {

    String dataToParse;

    String fileLocation;

    ArrayList<String> heccedData;

    File heccedFile;

    boolean heccedFileExists;


    public HeccUpParserTest(String input, String heccedFileLocation) {

        heccedData = new ArrayList<>();

        dataToParse = input.concat("\n");
        /*
        you might be asking 'why am I concatting a "\n" to the end of the input?'
        well, long story short, if there isn't an "\n" at the end of the last line of the input, that last line doesn't get looked at.
        so I may as well make sure that it does get looked at, y'know?
         */

        fileLocation = heccedFileLocation;

    }

    public boolean parseTheData() throws ParserException{

        heccedData.clear();

        heccedData.add("//HECC UP parser test output (as of 27/08/2020) (R. Lowe, 2020)\n\n");

        boolean notDone = true;

        Set<String> passageNames = new TreeSet<String>();

        Map<String, String> parsedPassages = new TreeMap<String, String>();

        String metadata = "no metadata";
        boolean hasMetadata = false;



        // STUFF THAT OMITS ANY METADATA STUFF THAT'S BEFORE THE FIRST PASSAGE DECLARATION

        Matcher firstDeclarationMatcher = Pattern.compile("(^::)", Pattern.MULTILINE).matcher(dataToParse);

        if(firstDeclarationMatcher.find()){
            int startIndex = firstDeclarationMatcher.start();
            if (startIndex > 1){
                metadata = dataToParse.substring(0,startIndex-1);
                hasMetadata = true;
                dataToParse = dataToParse.substring(startIndex);
            }
        }

        //TODO (after MVP): the metadata stuff
        //System.out.println(dataToParse);
        //System.out.println(metadata);

        //declaration of starting passage name is added to heccedData
        heccedData.add("var startingPassageName = \"Start\";\n\r\n\r");

        //starts the declaration of the getHECCED function in heccedData
        heccedData.add("function getHECCED(){\n\r");


        // DOUBLE-ESCAPING ALL SPEECHMARKS IN THE TEXT (so they'll be escaped when outputting them in hecced.js)

        Matcher speechMatcher = Pattern.compile("(?<speechmarks>\\\")",Pattern.MULTILINE).matcher(dataToParse);

        dataToParse = speechMatcher.replaceAll("\\\\\"");

        //System.out.println(dataToParse);

        // KEEPING TRACK OF ALL THE PASSAGE NAMES

        Matcher passageNameMatcher = Pattern.compile("(?<names>(?<=^::)[\\w- ]*[\\w]+)", Pattern.MULTILINE).matcher(dataToParse);


        while (passageNameMatcher.find()){
            String currentName = passageNameMatcher.group(0);
            //System.out.println(currentName);
            if (!passageNames.add(currentName)){
                //THROW EXCEPTION HERE
                throw new DuplicatePassageNameException(currentName);
                // System.out.println("uh oh looks like we got a duplicate passage!");
                //} else{
                //System.out.println("passage added successfully");
            }
        }

        if (passageNames.size() == 0){
            System.out.println("no passages found!");
            //THROW EXCEPTION HERE
            throw new NoPassagesException();
        } else{
            System.out.println(passageNames.size());
        }

        //System.out.println(passageNames);


        // CONVERTING ALL DIRECT LINKS TO passageLinks

        Matcher directLinkMatcher = Pattern.compile("(?<directLinks>\\[\\[[\\w]+[\\w- ]*[\\w]+\\]\\])", Pattern.MULTILINE).matcher(dataToParse);

        notDone = true;

        do{
            notDone = directLinkMatcher.find();
            if (notDone){
                String temp = directLinkMatcher.group(0);
                temp = temp.substring(2, temp.length()-2);
                if (!passageNames.contains(temp)){
                    System.out.println("looks like an undefined passage called " + temp + " is being linked!");
                    //THROW EXCEPTION HERE
                    throw new UndefinedPassageException(temp);
                }
                String link = "<a class=\\\\\"passageLink\\\\\" onclick='theHeccer.goToPassage(\\\\\"" +temp+ "\\\\\")'>" +temp+"</a>";
                dataToParse = directLinkMatcher.replaceFirst(link);
                directLinkMatcher.reset(dataToParse);
            }
        } while(notDone);

        //System.out.println(dataToParse);

        // CONVERTING ALL INDIRECT LINKS TO PassageLinks

        Matcher indirectLinkMatcher = Pattern.compile("(?<indirectLinks>\\[\\[[^\\[\\]\\|]+\\|[\\w]+[\\w- ]*[\\w]+\\]\\])", Pattern.MULTILINE).matcher(dataToParse);

        notDone = true;

        do{
            notDone = indirectLinkMatcher.find();
            if (notDone){
                String temp = indirectLinkMatcher.group(0);
                //System.out.println(temp);
                temp = temp.substring(2, temp.length()-2);
                //System.out.println(temp);
                String[] linkParts = temp.split("\\|");
                //System.out.println(linkParts[0] + "," + linkParts[1]);
                if (!passageNames.contains(linkParts[1])){
                    System.out.println("looks like an undefined passage called " + linkParts[1] + " is being linked!");
                    //THROW EXCEPTION HERE
                    throw new UndefinedPassageException(linkParts[1]);
                }
                String link = "<a class=\\\\\"passageLink\\\\\" onclick='theHeccer.goToPassage(\\\\\"" +linkParts[1]+ "\\\\\")'>" +linkParts[0]+"</a>";
                //System.out.println(link);
                dataToParse = indirectLinkMatcher.replaceFirst(link);
                indirectLinkMatcher.reset(dataToParse);
            }
        } while(notDone);

        //System.out.println(dataToParse);



        Matcher declarationMatcher = Pattern.compile("(?<declarations>^::[\\w- ]*[\\w]+)", Pattern.MULTILINE).matcher(dataToParse);
        //"(?<declarations>^::[\\w- ]*[\\w]+)"
        //passageNameMatcher.reset(dataToParse);

        //will give this the everythingAfterDeclaration
        Matcher passageContentMatcher = Pattern.compile("(?<content>(?<=\\r\\n|\\r|\\n)(?!^::).*\\n(?!^::)|\\r(?!^::)|\\n\\r(?!^::)*.+)", Pattern.MULTILINE).matcher("");

        //will use this to crop leading whitespace lines
        Matcher entirelyWhitespaceMatcher = Pattern.compile("^\\s*$", Pattern.MULTILINE).matcher("");

        Matcher lineEndWhitespaceMatcher = Pattern.compile("\\s*\\R$", Pattern.MULTILINE).matcher("");

        notDone = true;

        String currentPassageName;
        String nextPassageName = "";
        String everythingAfterDeclaration;
        String currentContent;

        int nextDeclarationStart = 0;
        int thisDeclarationStart;
        //int thisContentStart = 0;

        boolean foundFirst = false;
        boolean contentFound;

        do{
            notDone = declarationMatcher.find();
            //notDone = passageNameMatcher.find();
            thisDeclarationStart = nextDeclarationStart;
            currentPassageName = nextPassageName;
            if (notDone){
                String tempDeclaration = declarationMatcher.group(0);
                //String tempDeclaration = passageNameMatcher.group(0);
                nextPassageName = tempDeclaration.substring(2);
                nextDeclarationStart = declarationMatcher.start();
                //nextDeclarationStart = passageNameMatcher.start();
                if (!foundFirst){
                    foundFirst = true;
                    continue;
                }
            } else{
                //everything to the end of dataToParse will be considered if there's no next declaration
                nextDeclarationStart = dataToParse.length();

            }
            everythingAfterDeclaration = dataToParse.substring(thisDeclarationStart, nextDeclarationStart);
            passageContentMatcher.reset(everythingAfterDeclaration);
            contentFound = false;
            currentContent = "\"<p>";
            String temp;
            while(passageContentMatcher.find()){
                temp = passageContentMatcher.group(0);
                if (!contentFound){ //if no content has been found
                    //check if this current line is entirely whitespace
                    if (entirelyWhitespaceMatcher.reset(temp).matches()){
                        continue;
                        //skip this line if it's entirely whitespace
                    } else{
                        contentFound = true;
                        //content has been found once first not-entirely-whitespace line has been reached
                    }
                }
                lineEndWhitespaceMatcher.reset(temp);
                temp = lineEndWhitespaceMatcher.replaceAll("</br>");
                currentContent = currentContent.concat(temp);
            }
            if (!contentFound){
                System.out.println("No content found for passage " + currentPassageName);
                throw new EmptyPassageException(currentPassageName);
            }
            boolean stillHasTrailingLinebreaks = false;
            do{
                int lengthMinus5 = currentContent.length()-5;
                temp = (currentContent.substring(lengthMinus5));
                stillHasTrailingLinebreaks = temp.equals("</br>");
                if (stillHasTrailingLinebreaks){
                    currentContent = currentContent.substring(0,lengthMinus5);
                }
            } while (stillHasTrailingLinebreaks);
            currentContent = currentContent.concat("</p>\"");
            //System.out.println(currentContent);
            parsedPassages.put(currentPassageName, currentContent);

        } while(notDone);

        for (Map.Entry<String, String> e: parsedPassages.entrySet()){
            String heccedFunction = "\ttheHeccer.addPassageToMap(\n\t\tnew Passage(\n\t\t\t\"";
            //System.out.println("Passage name: " + e.getKey());
            //System.out.println("Passage content: " + e.getValue());

            heccedFunction = heccedFunction.concat(e.getKey());
            heccedFunction = heccedFunction.concat("\",\n\t\t\t");
            heccedFunction = heccedFunction.concat(e.getValue());
            heccedFunction = heccedFunction.concat("\n\t\t)\n\t);\n\n\n");

            heccedData.add(heccedFunction);
        }

        heccedData.add("\n\ttheHeccer.printPassages();\n");

        heccedData.add("\n\ttheHeccer.loadCurrentPassage();\n\n");

        heccedData.add("}\n");

        heccedData.add("\n//that's all, folks!\n\n");

        for(String h: heccedData){
            System.out.println(h);
        }

        return true;

    }

    //this was used for testing when making this
    static void matcherOutput(Matcher m){
        while(m.find()){
            System.out.println("Full match (group 0): " + m.group(0));
            if (m.groupCount() > 1){
                for (int i = 1; i <= m.groupCount(); i++) {
                    System.out.println("Group " + i + ": " + m.group(i));
                }
            }
        }
    }

    void outputHeccedData(){


        heccedFile = new File(fileLocation.concat("HECCED.js"));
        writeTheFile(heccedFile,heccedData);

        /*
        try{
            heccedFile.createNewFile();
            FileWriter heccedFileWriter = new FileWriter(heccedFile);
            for(String s: heccedData){
                heccedFileWriter.write(s);
            }
            heccedFileWriter.close();
        } catch(FileNotFoundException e){
            System.out.println("FileNotFoundException!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException!");
            e.printStackTrace();
        }*/
    }

    void outputHECCER(){
        File heccerFile = new File(fileLocation.concat("HECCER.js"));
        writeTheFile(heccerFile,TextAssetReader.getHECCER());
    }

    void outputIndex(){
        File indexFile = new File(fileLocation.concat("index.html"));
        writeTheFile(indexFile,TextAssetReader.getIndex());
    }

    private void writeTheFile(File f, ArrayList<String> dataToWrite){
        try{
            f.createNewFile();
            FileWriter heccedFileWriter = new FileWriter(f);
            for(String s: dataToWrite){
                heccedFileWriter.write(s);
            }
            heccedFileWriter.close();
        } catch(FileNotFoundException e){
            System.out.println("FileNotFoundException!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        /*
        String input = "this line is before the first passage declaration, so, officially, this line doesn't exist! \n\n"
                + "::Start\n\n"
                + "starting passage content goes here.\n"
                + "The following line contains a link to \"Another passage.\"\n"
                + "[[Another passage]]\n\n"
                + "::Another passage [yes] <34,35>\n\n"
                + "congrats you clicked that link to get here, Another passage.\n"
                + "why not [[click this|Yet Another Passage]] as well?\n\n"
                + "::Yet Another Passage\n\n"
                + "woah you clicked that so you're now at Yet Another Passage.\n\n"
                + "Do you want to go [[Left]], [[Right]], [[Back to the start|Start]], or [[Skip this nonsense|dave]]?\n\n"
                + "::Left\n\n"
                + "You go to the left, but the path leads you back to [[dave]].\n\n"
                + "::Right\n\n"
                + "You went to the right, but the path leads you back to [[dave]].\n\n"
                + "::dave\n\n"
                + "This passage is called dave.\n"
                + "dave's content doesn't include any links to any other passages.\n"
                + "So I guess this counts as the end.\n";
         */


        String input = TextAssetReader.getHeccString();

        System.out.println(input);

        //String saveFileLocation = "Summer background preparation work/Planning/testing etc/HECC UP/";

        String saveFileLocation = "src/misc_testing_and_such/hecc_up_testing/outputs/";

        HeccUpParserTest heccUp = new HeccUpParserTest(input, saveFileLocation);

        try {
            heccUp.parseTheData();

            heccUp.outputHeccedData();
            heccUp.outputHECCER();
            heccUp.outputIndex();
        } catch (ParserException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}

class ParserException extends Exception{
    ParserException(String s){ super(s);}
}

class NoPassagesException extends ParserException{
    NoPassagesException(){ super("How do you expect anyone to play this game, seeing as you forgot to define any passages?");}
}

class InvalidPassageNameException extends ParserException{
    InvalidPassageNameException(String passageName){ super(passageName + " is not a valid passage name!");}
}

class DuplicatePassageNameException extends ParserException{
    DuplicatePassageNameException(String passageName){ super("Multiple passages share the same name ("+ passageName + "), which is not allowed!");}
}

class EmptyPassageException extends ParserException{
    EmptyPassageException(String passageName){ super(passageName + " contains no content!");}
}

class UndefinedPassageException extends ParserException{
    UndefinedPassageException(String passageName){ super("There is a link to a passage called '" + passageName +"', which isn't defined!");}
}

class TextAssetReader {

    private final static String path = "/misc_testing_and_such/hecc_up_testing/resources/";

    private final static ArrayList<String> HECCER = fileToStringArrayList("HECCER.js");
    private final static ArrayList<String> INDEX = fileToStringArrayList("index.html");

    private final static ArrayList<String> HECC_ARRAYLIST = fileToStringArrayList("HeccSample.hecc");

    private static ArrayList<String> fileToStringArrayList(String filename){

        //if it wasn't obvious, yes, this was unceremoniously borrowed from my CE218 stuff
        ArrayList<String> output = new ArrayList<>();
        try{
            InputStream in = utilities.TextAssetReader.class.getResourceAsStream(path + filename);
            //This allows the specified text asset file to be packaged within the .jar ;)
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String currentString;
            //pretty much setting up the stuff for reading the file
            //until the end of the file is reached, it will add the current string to the file (even the empty ones)
            while ((currentString = br.readLine())!=null) {
                output.add(currentString.concat("\n"));
            }
            br.close(); //closes the bufferedReader
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    static String getHeccString(){
        String heccString = "";
        for(String s: HECC_ARRAYLIST){
            heccString = heccString.concat(s);
        }
        return heccString;
    }

    static ArrayList<String> getHECCER() {return HECCER;}
    static ArrayList<String> getIndex() {return INDEX;}
}
//TODO stuff for the MVP

//TODO: a version that doesn't have everything in the same Java file

//TODO: replace the map of parsed passages (passage name, passage content) with a map of ParsedPassage objects (passage name, ParsedPassage object)

//TODO: allow user to specify what .hecc file they want to read

//TODO: allow user to specify where they want to save their hecc game

//TODO: general improvements to index.html to make it look less crap

//TODO: GUI for HECC-UP (not talking about OH-HECC)

//TODO stuff for after MVP

//TODO (after MVP): Metadata

//TODO (after MVP): OH-HECC (Optional Help for HECC)