package gameParts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class VariableTests {

    @Test
    void testName(){
        String input = "sampleVariable";
        Variable output = new Variable("sampleVariable","0","");
        assertTrue(
                new Variable(input).equals(output)
        );
    }

    @Test
    void testNameValue(){
        String input = "sampleVariable = 42";
        Variable output = new Variable("sampleVariable", "42","");
        assertTrue(
                new Variable(input).equals(output)
        );
    }

    @Test
    void testNameComment(){
        String input = "sampleVariable //DEEZ NUTS";
        Variable output = new Variable("sampleVariable","0","DEEZ NUTS");
        assertTrue(
                output.equals(new Variable(input))
        );
    }

    @Test
    void testNameValueComment(){
        String input = "sampleVariable = 42 //DEEZ NUTS";
        Variable output = new Variable("sampleVariable","42","DEEZ NUTS");
        assertTrue(
                output.equals(new Variable(input))
        );
    }
}
