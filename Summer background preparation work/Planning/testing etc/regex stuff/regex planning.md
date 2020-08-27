Some stuff I've been testing on jDoodle

```
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

public class MyClass {
    public static void main(String args[]) {

        String regex = "(?<speechmarks>\\\")|(?<declarations>^::[\\w- ]*[\\w]+)|(?<names>(?<=^::)[\\w- ]*[\\w]+)|(?<content>(?<=\\r\\n|\\r|\\n)(?!^::).*\\n(?!^::)|\\r(?!^::)|\\n\\r(?!^::)*.+)|(?<directLinks>\\[\\[[\\w- ]*[\\w]+\\]\\])|(?<indirectLinks>\\[\\[[^\\[\\]\\|]+\\|[\\w- ]*[\\w]+\\]\\])";
        String input = "this line is before the first passage declaration, so, officially, this line doesn't exist! \n\r\n\r"
        	 + "::Start\n\r\n\r"
        	 + "starting passage content goes here.\n\r"
        	 + "The following line contains a link to \"Another passage.\"\n\r"
        	 + "[[Another passage]]\n\r\n\r"
        	 + "::Another passage [yes] <34,35>\n\r\n\r"
        	 + "congrats you clicked that link to get here, Another passage.\n\r"
        	 + "why not [[click this|Yet Another Passage]] as well?\n\r\n\r"
        	 + "::Yet Another Passage\n\r\n\r"
        	 + "woah you clicked that so you're now at Yet Another Passage.\n\r\n\r"
        	 + "Do you want to go [[Left]], [[Right]], [[Back to the start|Start]], or [[Skip this nonsense|dave]]?\n\r\n\r"
        	 + "::Left\n\r\n\r"
        	 + "You go to the left, but the path leads you back to [[dave]].\n\r\n\r"
        	 + "::Right\n\r\n\r"
        	 + "You went to the right, but the path leads you back to [[dave]].\n\r\n\r"
        	 + "::dave\n\r\n\r"
        	 + "This passage is called dave.\n\r"
        	 + "dave's content doesn't include any links to any other passages.\n\r"
        	 + "So I guess this counts as the end.";
        
        boolean notDone = true;
        
        Set<String> passageNames = new TreeSet<String>();
        
        Map<String, String> parsedPassages = new TreeMap<String, String>();
        
        ArrayList<String> heccedPassages = new ArrayList<>();
        
        String metadata = "no metadata";
        boolean hasMetadata = false;
        
        //Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        //Matcher matcher = pattern.matcher(input);
        
        
        // STUFF THAT OMITS ANY METADATA STUFF THAT'S BEFORE THE FIRST PASSAGE DECLARATION
        
        Matcher firstDeclarationMatcher = Pattern.compile("(^::)", Pattern.MULTILINE).matcher(input);
        
        if(firstDeclarationMatcher.find()){
            int startIndex = firstDeclarationMatcher.start();
            if (startIndex > 1){
                metadata = input.substring(0,startIndex-1);
                hasMetadata = true;
                input = input.substring(startIndex, input.length());
            }
        }
        
        //System.out.println(input);
        //System.out.println(metadata);
        
        
        // DOUBLE-ESCAPING ALL SPEECHMARKS IN THE TEXT (so they'll be escaped when outputting them in hecced.js)
        
        Matcher speechMatcher = Pattern.compile("(?<speechmarks>\\\")",Pattern.MULTILINE).matcher(input);
        
        input = speechMatcher.replaceAll("\\\\\"");
        
        //System.out.println(input);
        
        // KEEPING TRACK OF ALL THE PASSAGE NAMES
        
        Matcher passageNameMatcher = Pattern.compile("(?<names>(?<=^::)[\\w- ]*[\\w]+)", Pattern.MULTILINE).matcher(input);
        
        
        while (passageNameMatcher.find()){
            String currentName = passageNameMatcher.group(0);
            //System.out.println(currentName);
            if (!passageNames.add(currentName)){
                //THROW EXCEPTION HERE
                // System.out.println("uh oh looks like we got a duplicate passage!");
            //} else{
                //System.out.println("passage added successfully");
            }
        }
        
        if (passageNames.size() == 0){
            System.out.println("no passages found!");
            //THROW EXCEPTION HERE
        } else{
            System.out.println(passageNames.size());
        }
        
        //System.out.println(passageNames);
        
        
        // CONVERTING ALL DIRECT LINKS TO passageLinks
        
        Matcher directLinkMatcher = Pattern.compile("(?<directLinks>\\[\\[[\\w- ]*[\\w]+\\]\\])", Pattern.MULTILINE).matcher(input);
        
        notDone = true;
        
        do{
            notDone = directLinkMatcher.find();
            if (notDone){
                String temp = directLinkMatcher.group(0);
                temp = temp.substring(2, temp.length()-2);
                if (!passageNames.contains(temp)){
                    System.out.println("looks like an undefined passage called " + temp + " is being linked!");
                    //THROW EXCEPTION HERE
                } 
                String link = "<a class=\\\\\"passageLink\\\\\" onclick=\'theHeccer.goToPassage(\\\\\""+temp+"\\\\\")\'>"+temp+"</a>";
                input = directLinkMatcher.replaceFirst(link);
                directLinkMatcher.reset(input);
            }
        } while(notDone);
        
        //System.out.println(input);
        
        // CONVERTING ALL INDIRECT LINKS TO PassageLinks
        
        Matcher indirectLinkMatcher = Pattern.compile("(?<indirectLinks>\\[\\[[^\\[\\]\\|]+\\|[\\w- ]*[\\w]+\\]\\])", Pattern.MULTILINE).matcher(input);
        
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
                } 
                String link = "<a class=\\\\\"passageLink\\\\\" onclick=\'theHeccer.goToPassage(\\\\\""+linkParts[1]+"\\\\\")\'>"+linkParts[0]+"</a>";
                //System.out.println(link);
                input = indirectLinkMatcher.replaceFirst(link);
                indirectLinkMatcher.reset(input);
            }
        } while(notDone);
        
        //System.out.println(input);
        
        
        
        Matcher declarationMatcher = Pattern.compile("(?<declarations>^::[\\w- ]*[\\w]+)", Pattern.MULTILINE).matcher(input);
        //"(?<declarations>^::[\\w- ]*[\\w]+)"
        //passageNameMatcher.reset(input);
        
        //will give this the everythingAfterDeclaration
        Matcher passageContentMatcher = Pattern.compile("(?<content>(?<=\\r\\n|\\r|\\n)(?!^::).*\\n(?!^::)|\\r(?!^::)|\\n\\r(?!^::)*.+)", Pattern.MULTILINE).matcher("");
        
        //will use this to crop leading whitespace lines
        Matcher entirelyWhitespaceMatcher = Pattern.compile("^\\s*$", Pattern.MULTILINE).matcher("");
        
        Matcher lineEndWhitespaceMatcher = Pattern.compile("\\s*?$(\\n|\\r|\\n\\r)", Pattern.MULTILINE).matcher("");
        
        notDone = true;
        
        String currentPassageName = "";
        String nextPassageName = "";
        String everythingAfterDeclaration = "";
        String currentContent = "";
        
        int nextDeclarationStart = 0;
        int thisDeclarationStart = 0;
        //int thisContentStart = 0;
        
        boolean foundFirst = false;
        boolean contentFound = false;
        
        do{
            notDone = declarationMatcher.find();
            //notDone = passageNameMatcher.find();
            thisDeclarationStart = nextDeclarationStart;
            currentPassageName = nextPassageName;
            if (notDone){
                String tempDeclaration = declarationMatcher.group(0);
                //String tempDeclaration = passageNameMatcher.group(0);
                nextPassageName = tempDeclaration.substring(2,tempDeclaration.length());
                nextDeclarationStart = declarationMatcher.start();
                //nextDeclarationStart = passageNameMatcher.start();
                if (!foundFirst){
                    foundFirst = true;
                    continue;
                }
            } else{
                //everything to the end of input will be considered if there's no next declaration
                nextDeclarationStart = input.length();
                
            }
            everythingAfterDeclaration = input.substring(thisDeclarationStart, nextDeclarationStart);
            passageContentMatcher.reset(everythingAfterDeclaration);
            contentFound = false;
            currentContent = "\"<p>";
            while(passageContentMatcher.find()){
                String temp = passageContentMatcher.group(0);
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
                temp = lineEndWhitespaceMatcher.replaceAll("<br/>");
                currentContent = currentContent.concat(temp);
            }
            if (!contentFound){
                //throw exception here
                System.out.println("No content found for passage " + currentPassageName);
            }
            currentContent = currentContent.concat("</p>\"");
            //System.out.println(currentContent);
            parsedPassages.put(currentPassageName, currentContent);
            
        } while(notDone);
        
        for (Map.Entry<String, String> e: parsedPassages.entrySet()){
            String heccedFunction = "theHeccer.addPassageToMap(\n\tnew Passage(\n\t\t\"";
            //System.out.println("Passage name: " + e.getKey());
            //System.out.println("Passage content: " + e.getValue());
            
            heccedFunction = heccedFunction.concat(e.getKey());
            heccedFunction = heccedFunction.concat("\",\n\t\t");
            heccedFunction = heccedFunction.concat(e.getValue());
            heccedFunction = heccedFunction.concat("\n\t)\n);\n");
            
            heccedPassages.add(heccedFunction);
        }
        
        for(String h: heccedPassages){
            System.out.println(h);
        }
        
        
        //System.out.println("Full matcher results: ");
        //matcherOutput(matcher);
        
        //System.out.println("\n\nSpeechmark matcher results: ");
        //matcherOutput(speechMatcher);
        
        //System.out.println("\n\nDeclaration matcher results: ");
        //matcherOutput(declarationMatcher);
        
        //System.out.println("\n\nPassage name matcher results: ");
        //matcherOutput(passageNameMatcher);
        
        //System.out.println("\n\nPassage content matcher results: ");
        //matcherOutput(passageContentMatcher);
        
        //System.out.println("\n\nDirect link matcher results: ");
        //matcherOutput(directLinkMatcher);
        
        //System.out.println("\n\nIndirect link matcher results: ");
        //matcherOutput(indirectLinkMatcher);


    }
    
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
}
```