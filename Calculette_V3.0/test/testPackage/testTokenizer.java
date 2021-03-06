package testPackage;

import my.calculator.Token;
import my.calculator.Tokenizer;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class testTokenizer {

    @Test
    public void testEnd() {
        String line = "    ";
        Tokenizer tokenizer = new Tokenizer(line);

        Token token = tokenizer.get();
        assertTrue(token.isFinish());
        // détection de confusions
        assertFalse(token.isNumber());
        assertFalse(token.isSymbol("+"));
    }

    @Test
    public void testNumber() {
        String line = " 123   ";
        Tokenizer tokenizer = new Tokenizer(line);

        Token token = tokenizer.get();
        assertTrue(token.isNumber());
        assertEquals(123, token.value());
        //
        assertFalse(token.isFinish());
        assertFalse(token.isSymbol("+"));
    }

    @Test
    public void testSymbol() {
        String line = " *   ";
        Tokenizer tokenizer = new Tokenizer(line);

        Token token = tokenizer.get();
        assertTrue(token.isSymbol("*"));
        // 
        assertFalse(token.isNumber());
        assertFalse(token.isFinish());
    }

    @Test
    public void testSequence() {
        String line = "12+(34)";
        Tokenizer tokenizer = new Tokenizer(line);

        Token token = tokenizer.get();
        assertTrue(token.isNumber());
        assertEquals(12, token.value());

        token = tokenizer.get();
        assertTrue(token.isSymbol("+"));

        token = tokenizer.get();
        assertTrue(token.isSymbol("("));

        token = tokenizer.get();
        assertTrue(token.isNumber());
        assertEquals(34, token.value());

        token = tokenizer.get();
        assertTrue(token.isSymbol(")"));

        token = tokenizer.get();
        assertTrue(token.isFinish());

    }
    
    
    @Test
    public void testWord() {
        String line = " hello  ";
        Tokenizer tokenizer = new Tokenizer(line);

        Token token = tokenizer.get();
        assertTrue(token.isWord());
        assertEquals("hello", token.word());
        
        // etc.
    }
    
    @Test
    public void testSequenceWords() {
        // pour vérifier qu'on ne consomme pas un
        // caractère en trop
        String line = "abc=def";
        Tokenizer tokenizer = new Tokenizer(line);

        Token token = tokenizer.get();
        assertTrue(token.isWord());
        assertEquals("abc", token.word());

        token = tokenizer.get();
        assertTrue(token.isSymbol("="));
        
        token = tokenizer.get();
        assertTrue(token.isWord());
        assertEquals("def", token.word());
        
        token = tokenizer.get();
        assertTrue(token.isFinish());
    }
}