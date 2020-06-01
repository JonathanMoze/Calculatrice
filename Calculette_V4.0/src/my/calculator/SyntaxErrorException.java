package my.calculator;

/**
 *
 * @author Jonathan Moze
 */
public class SyntaxErrorException extends Exception{
    
    /**
     * COnstructeur d'une exeption de syntaxe
     * @param message le message d'erreur 
     */
    public SyntaxErrorException (String message){
        super(message);
    }
}
