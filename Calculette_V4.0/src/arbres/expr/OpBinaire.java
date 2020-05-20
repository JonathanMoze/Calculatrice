package arbres.expr;

import java.util.function.IntBinaryOperator;

/**
 * Les opérations binaires dans les expressions
 *
 */
public enum OpBinaire {

    PLUS((a, b) -> a + b),
    MOINS((a, b) -> a - b),
    MUL((a, b) -> a * b),
    DIV((a, b) -> a / b);
    
    final IntBinaryOperator op;
    
    OpBinaire(IntBinaryOperator op) {
        this.op = op;
    }
    
    int appliquer(int a, int b) {
        return op.applyAsInt(a, b);
    }
    
}
