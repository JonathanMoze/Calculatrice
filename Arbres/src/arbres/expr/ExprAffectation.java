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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String description() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
