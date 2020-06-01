package arbres.expr;

import my.calculator.TableVariables;

/**
 * 
 * @author Jonathan Moze
 */
public interface Expr {
    /**
     * Evalue l'expression dans un environnement
     * @param env
     * @return la valeur de l'expression
     */
    int valeur(Environnement env);
    
    // "factories" pour fabriquer des expression.
    
    /**
     * Fabrication d'une expression d'une constante
     * @param valeur la valeur de la constante
     * @return l'expression constante
     */
    public static Expr constante(int valeur) {
        return new ExprConstante(valeur);
    }
    
    /**
     * Fabrication d'un expression avec une opération binaire
     * @param gauche l'expression du coté gauche
     * @param op l'opération binaire
     * @param droite l'expression de droite
     * @return l'expression avec opération binaire
     */
    public static Expr binaire(Expr gauche, OpBinaire op, Expr droite) {
        return new ExprBinaire(gauche, op, droite);
    }
    
    /**
     * Fabriaction d'une expression avec une variable
     * @param name nom de la variable
     * @param vars la table contenant les variables
     * @return 
     */
    public static Expr variable(String name, TableVariables vars){
        return new ExprVariable(name, vars.valeur(name));
    }
    
    /**
     * Fabrication d'une expression avec une affectation de variable
     * @param name nom de la variable
     * @param expr expression affectée à la variable
     * @param vars la table conteant les variables
     * @return 
     */
    public static Expr affectation(String name, Expr expr, TableVariables vars){
        vars.affecter(name, expr.valeur(vars));
        return new ExprAffectation(name, expr);
    }

    /**
     * décris l'expression 
     * @return la description
     */
    public String description();
    
}
