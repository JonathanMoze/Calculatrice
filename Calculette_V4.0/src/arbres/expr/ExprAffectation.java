package arbres.expr;


/**
 *
 * @author Jonathan Moze
 */
public class ExprAffectation implements Expr {
    /**
     * nom de la variable utilisée
     */
    private final String name;
    
    /**
     * Expression affectée
     */
    private final Expr expr;
    
    /**
     * Constructeur de l'expression
     * @param name le nom de variable
     * @param expr l'expresison à affecter
     */
    public ExprAffectation(String name, Expr expr) {
        this.name = name;
        this.expr = expr;
    }

    @Override
    public int valeur(Environnement env) {
        env.affecter(name, expr.valeur(env));
        return expr.valeur(env);
    }

    @Override
    public String description() {
        return String.format("L'affectaion de %s a la variable %s", expr.description(), name);
    }
    
}
