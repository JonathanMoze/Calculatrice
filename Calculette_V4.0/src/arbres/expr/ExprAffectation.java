/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbres.expr;


/**
 *
 * @author jonat
 */
public class ExprAffectation implements Expr {

    private final String name;
    private final Expr expr;
    
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
