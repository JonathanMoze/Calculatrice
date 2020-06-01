package testPackage;

import static junit.framework.Assert.assertEquals;
import my.calculator.Calculator;
import my.calculator.EvaluationErrorException;
import my.calculator.SyntaxErrorException;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

/**
 *
 * @author Jonathan Moze
 */
public class testCalculator {

    
    /**
     * Test général de la fonction évaluation
     * @throws SyntaxErrorException
     * @throws EvaluationErrorException 
     */
    @Test
    public void testEvaluation() throws SyntaxErrorException, EvaluationErrorException {
        Calculator calc = new Calculator();
        assertEquals(123, calc.evaluation("  123 "));
        assertEquals(4, calc.evaluation("2+2"));
        assertEquals(1, calc.evaluation("421-20-400"));
        assertEquals(6, calc.evaluation("2*3"));
        assertEquals(2, calc.evaluation("7/3"));
        assertFalse(calc.evaluation("23 + 45") == 26);
        assertEquals(18, calc.evaluation("2*(4+5)"));
        assertEquals(2, calc.evaluation("6/(2*3 - 3)"));
        assertEquals(-12, calc.evaluation("3 * -(3+1)"));
    }

    
    /**
     * Test pour le cas de la division par 0
     * @throws SyntaxErrorException 
     */
    @Test
    public void testDivisionZero() throws SyntaxErrorException {
        Calculator calc = new Calculator();
        try {
            calc.evaluation("1 + 2/0 + 3");
        } catch (EvaluationErrorException ex) {
        }
    }

    
    /**
     * Test pour l'assignation de valeurs a des variables
     * @throws SyntaxErrorException
     * @throws EvaluationErrorException 
     */
    @Test
    public void testAssignments() throws SyntaxErrorException, EvaluationErrorException {
        Calculator c = new Calculator();
        assertEquals(12, c.evaluation("num = 3*4"));
        assertEquals(12, c.evaluation("num"));
        assertEquals(2, c.evaluation("den = 2"));
        assertEquals(2, c.evaluation("den"));
        assertEquals(6, c.evaluation("num / den"));

        assertEquals(10, c.evaluation("(a = 2+1) + (b = 2*3 + 1)"));
        assertEquals(3, c.evaluation("a"));
        assertEquals(7, c.evaluation("b"));
    }
}
