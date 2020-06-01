package arbres.expr;

import java.util.function.IntBinaryOperator;
import my.calculator.EvaluationErrorException;

/**
 * Les opérations binaires dans les expressions
 *
 */
public enum OpBinaire {

    /**
     * opération d'addition
     */
    PLUS((a, b) -> a + b),
    /**
     * opération de soustraction
     */
    MOINS((a, b) -> a - b),
    /**
     * opération de multiplication
     */
    MUL((a, b) -> a * b),
    /**
     * opération de division
     */
    DIV((a, b) -> a / b);

    /**
     * l'opérateur binaire
     */
    final IntBinaryOperator op;

    /**
     * Constructeur d'opérateur binaire
     *
     * @param op l'opérateur
     */
    OpBinaire(IntBinaryOperator op) {
        this.op = op;
    }

    /**
     * Fonction permettant d'aplliquer l'opérateur è deux entiers
     *
     * @param a premier entier
     * @param b deuxième entier
     * @return la valeur résultat
     * @throws my.calculator.EvaluationErrorException
     */
    int appliquer(int a, int b) throws EvaluationErrorException {
        try {
            return op.applyAsInt(a, b);
        } catch (ArithmeticException ex) {
            throw new EvaluationErrorException(ex.getMessage());
        }
    }

}
