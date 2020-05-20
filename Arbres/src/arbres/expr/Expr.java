package arbres.expr;

import arbres.TableVariables;

public interface Expr {
    /**
     * Evalue l'expression dans un environnement
     * @param env
     * @return la valeur de l'expression
     */
    int valeur(Environnement env);
    
    // "factories" pour fabriquer des expression.
    
    public static Expr constante(int valeur) {
        return new ExprConstante(valeur);
    }
    public static Expr binaire(Expr gauche, OpBinaire op, Expr droite) {
        return new ExprBinaire(gauche, op, droite);
    }
    public static Expr variable(String name, TableVariables vars){
        return new ExprVariable(name, vars.valeur(name));
    }
    public static Expr affectation(String name, Expr expr, TableVariables vars){
        vars.affecter(name, expr.valeur(vars));
        return new ExprAffectation(name, expr);
    }

    public String description();
    
}
