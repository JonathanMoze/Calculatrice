
package my.calculator;

/**
 *
 * @author Jonathan Moze
 */
public class EvaluationErrorException extends Exception{
    
    /**
     * Constructeur d'une exeption de calcul
     * @param message message d'erreur
     */
    public EvaluationErrorException(String message){
        super(message);
    }
}
