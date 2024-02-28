package hecc_up.gameParts;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Some tests for the Metadata class
 */
public class MetadataTests {

    /**
     * don't construct this.
     */
    private MetadataTests(){}

    /**
     * Tests some valid metadata
     */
    @Test
    void validMetadataTests(){

        final String validMetadata = "!author: An Author\n" +
                "!title: This is a title\n" +
                "!ifid: 2052A3A1-17DB-4202-9753-81D8CF3CBC0E\n" +
                "!start: Kevin\n"+
                "!var: sampleVariable\n" +
                "!var: sampleVariable2 = 42\n"+
                "!var: sampleVariable3 = 42 // ok";

        final Metadata theMetadata = new Metadata(validMetadata);

        assertEquals("An Author", theMetadata.findAuthor());
        assertEquals("An Author", theMetadata.getAuthor());

        assertEquals("This is a title", theMetadata.findTitle());
        assertEquals("This is a title", theMetadata.getTitle());

        assertEquals("2052A3A1-17DB-4202-9753-81D8CF3CBC0E", theMetadata.findIFID());
        assertEquals("2052A3A1-17DB-4202-9753-81D8CF3CBC0E", theMetadata.getIfid());
        assertTrue(theMetadata.doesIfidExist());

        assertEquals("Kevin",theMetadata.findStartPassage());
        assertEquals("Kevin",theMetadata.getStartPassage());

        final List<Variable> someVars = new ArrayList<>();
        someVars.add(new Variable("sampleVariable","0",""));
        someVars.add(new Variable("sampleVariable2", "42",""));
        someVars.add(new Variable("sampleVariable3", "42","ok"));

        assertEquals(someVars.toString(), theMetadata.findVariables().toString());

        assertEquals(someVars.toString(), theMetadata.getVariables().toString());


        assertTrue(theMetadata.isAllOptionalMetadataDeclared());
        assertEquals("No metadata problems detected!", theMetadata.outputMetadataDefinitionInstructions());

        //theMetadata.printDebugData();

        assertEquals("<!-- UUID://2052A3A1-17DB-4202-9753-81D8CF3CBC0E// -->", theMetadata.getIfidButHtmlFormatted());

        final String ifictionMetadata = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<ifindex version=\"1.0\" xmlns=\"http://babel.ifarchive.org/protocol/iFiction/\">\n" +
                "\t<!-- Bibliographic data generated via HECC-UP -->\n" +
                "\t<story>\n" +
                "\t\t<identification>\n" +
                "\t\t\t<ifid>2052A3A1-17DB-4202-9753-81D8CF3CBC0E</ifid>\n" +
                "\t\t\t<format>html</format>\n" +
                "\t\t</identification>\n" +
                "\t\t<bibliographic>\n" +
                "\t\t\t<title>This is a title</title>\n" +
                "\t\t\t<author>An Author</author>\n" +
                "\t\t</bibliographic>\n" +
                "\t</story>\n" +
                "</ifindex>";

        assertEquals(ifictionMetadata, theMetadata.getIFictionMetadata());
    }

    /**
     * Tests some valid metadata via the ParseMetadata method
     */
    @Test
    void validMetadataTestParseMetadata(){

        final String validMetadata = "!author: An Author\n" +
                "!title: This is a title\n" +
                "!ifid: 2052A3A1-17DB-4202-9753-81D8CF3CBC0E\n" +
                "!start: Kevin\n"+
                "!var: sampleVariable\n" +
                "!var: sampleVariable2 = 42";

        final Metadata theMetadata = new Metadata(validMetadata);
        theMetadata.parseMetadata();


        assertEquals("An Author", theMetadata.getAuthor());

        assertEquals("This is a title", theMetadata.getTitle());

        assertEquals("2052A3A1-17DB-4202-9753-81D8CF3CBC0E", theMetadata.getIfid());
        assertTrue(theMetadata.doesIfidExist());

        assertEquals("Kevin",theMetadata.getStartPassage());

        final List<Variable> someVars = new ArrayList<>();
        someVars.add(new Variable("sampleVariable","0",""));
        someVars.add(new Variable("sampleVariable2", "42",""));

        assertEquals(someVars.toString(), theMetadata.findVariables().toString());

        assertEquals(someVars.toString(), theMetadata.getVariables().toString());


        assertTrue(theMetadata.isAllOptionalMetadataDeclared());
        assertEquals("No metadata problems detected!", theMetadata.outputMetadataDefinitionInstructions());

        //theMetadata.printDebugData();

        assertEquals("<!-- UUID://2052A3A1-17DB-4202-9753-81D8CF3CBC0E// -->", theMetadata.getIfidButHtmlFormatted());

        final String ifictionMetadata = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<ifindex version=\"1.0\" xmlns=\"http://babel.ifarchive.org/protocol/iFiction/\">\n" +
                "\t<!-- Bibliographic data generated via HECC-UP -->\n" +
                "\t<story>\n" +
                "\t\t<identification>\n" +
                "\t\t\t<ifid>2052A3A1-17DB-4202-9753-81D8CF3CBC0E</ifid>\n" +
                "\t\t\t<format>html</format>\n" +
                "\t\t</identification>\n" +
                "\t\t<bibliographic>\n" +
                "\t\t\t<title>This is a title</title>\n" +
                "\t\t\t<author>An Author</author>\n" +
                "\t\t</bibliographic>\n" +
                "\t</story>\n" +
                "</ifindex>";

        assertEquals(ifictionMetadata, theMetadata.getIFictionMetadata());
    }

    /**
     * and some tests for invalid metadata
     */
    @Test
    void testInvalidMetadata(){

        final String invalidMetadata = "!author : Anne? Author.\n" +
                "!title: \n" +
                "title: is this a title?\n"+
                " !title: an title\n"+
                "! title: an tittle\n"+
                "!title :tiiitle\n"+
                "!ifid: deez-nutz\n" +
                "!start: Kevin!\n";

        final Metadata theMetadata = new Metadata(invalidMetadata);

        assertEquals("Anonymous", theMetadata.findAuthor());
        assertEquals("Anonymous", theMetadata.getAuthor());

        assertEquals("An Interactive Fiction", theMetadata.findTitle());
        assertEquals("An Interactive Fiction", theMetadata.getTitle());

        assertEquals("", theMetadata.findIFID());
        assertEquals("unspecified", theMetadata.getIfid());
        assertFalse(theMetadata.doesIfidExist());

        assertEquals("Start",theMetadata.findStartPassage());
        assertEquals("Start",theMetadata.getStartPassage());

        final String someVars = new ArrayList<>().toString();

        assertEquals(someVars, theMetadata.findVariables().toString());

        assertEquals(someVars, theMetadata.getVariables().toString());


        assertFalse(theMetadata.isAllOptionalMetadataDeclared());

        theMetadata.printDebugData();
    }

    /**
     * test multiple metadata declarations in the same file (should only read the first declaration)
     */
    @Test
    void testMultipleMetadataDeclarations(){

        final String dupedMetadata = "!author: An Author\n" +
                "!title: This is a title\n" +
                "!ifid: 2052A3A1-17DB-4202-9753-81D8CF3CBC0E\n" +
                "!start: Kevin\n"+
                "!author: fail\n" +
                "!title: fail\n" +
                "!ifid: BC8F7F4F-0A01-4EC8-8793-C2CDBD8C4C4F\n" +
                "!start: fail\n";

        final Metadata theMetadata = new Metadata(dupedMetadata);

        theMetadata.parseMetadata();

        assertEquals("An Author", theMetadata.getAuthor());

        assertEquals("This is a title", theMetadata.getTitle());

        assertEquals("2052A3A1-17DB-4202-9753-81D8CF3CBC0E", theMetadata.getIfid());
        assertTrue(theMetadata.doesIfidExist());

        assertEquals("Kevin",theMetadata.getStartPassage());

        assertTrue(theMetadata.isAllOptionalMetadataDeclared());
        assertEquals("No metadata problems detected!", theMetadata.outputMetadataDefinitionInstructions());


        assertEquals("<!-- UUID://2052A3A1-17DB-4202-9753-81D8CF3CBC0E// -->", theMetadata.getIfidButHtmlFormatted());

        final String ifictionMetadata = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<ifindex version=\"1.0\" xmlns=\"http://babel.ifarchive.org/protocol/iFiction/\">\n" +
                "\t<!-- Bibliographic data generated via HECC-UP -->\n" +
                "\t<story>\n" +
                "\t\t<identification>\n" +
                "\t\t\t<ifid>2052A3A1-17DB-4202-9753-81D8CF3CBC0E</ifid>\n" +
                "\t\t\t<format>html</format>\n" +
                "\t\t</identification>\n" +
                "\t\t<bibliographic>\n" +
                "\t\t\t<title>This is a title</title>\n" +
                "\t\t\t<author>An Author</author>\n" +
                "\t\t</bibliographic>\n" +
                "\t</story>\n" +
                "</ifindex>";

        assertEquals(ifictionMetadata, theMetadata.getIFictionMetadata());
    }
}
