/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testPackage;

import static junit.framework.Assert.assertEquals;
import my.calculator.Calculator;
import my.calculator.EvaluationErrorException;
import my.calculator.SyntaxErrorException;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author jonat
 */
public class testCalculator {

    @Test
    public void testSums() throws SyntaxErrorException, EvaluationErrorException {
        Calculator calc = new Calculator();
        assertEquals(123, calc.evaluation("  123 "));
        assertEquals(4, calc.evaluation("2+2"));
        assertEquals(1, calc.evaluation("421-20-400"));
        assertEquals(6, calc.evaluation("2*3"));
        assertEquals(3, calc.evaluation("7/3"));
        assertFalse(calc.evaluation("23 + 45") == 26);
    }

    @Test
    public void testDivisionZero() throws SyntaxErrorException {
        Calculator calc = new Calculator();
        try {
            calc.evaluation("1 + 2/0 + 3");
            fail();
        } catch (EvaluationErrorException ex) {
        }
    }
}
