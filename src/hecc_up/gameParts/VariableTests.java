package hecc_up.gameParts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Some tests for the Variable class (basically all testing the constructor)
 */
public class VariableTests {

    /**
     * tests the name of a variable
     */
    @Test
    void testName(){
        final String input = "sampleVariable";
        final Variable output = new Variable("sampleVariable","0","");
        assertTrue(
                new Variable(input).equals(output)
        );
    }

    /**
     * tests variable with name and value
     */
    @Test
    void testNameValue(){
        final String input = "sampleVariable = 42";
        final Variable output = new Variable("sampleVariable", "42","");
        assertTrue(
                new Variable(input).equals(output)
        );
    }

    /**
     * tests variable with name and comment
     */
    @Test
    void testNameComment(){
        final String input = "sampleVariable //DEEZ NUTS";
        final Variable output = new Variable("sampleVariable","0","DEEZ NUTS");
        assertTrue(
                output.equals(new Variable(input))
        );
    }

    /**
     * tests variable with name, value, and comment
     */
    @Test
    void testNameValueComment(){
        final String input = "sampleVariable = 42 //DEEZ NUTS";
        final Variable output = new Variable("sampleVariable","42","DEEZ NUTS");
        assertTrue(
                output.equals(new Variable(input))
        );
    }
}
