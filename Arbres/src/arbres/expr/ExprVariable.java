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
public class ExprVariable implements Expr {

    private final String name;
    private final int valeur;
    
    
    public ExprVariable(String name, int valeur) {
        this.name = name;
        this.valeur = valeur;
    }

    @Override
    public int valeur(Environnement env) {
        return this.valeur;
    }

    @Override
    public String description() {
        return String.format("la variable %s", name);
    }
    
}
