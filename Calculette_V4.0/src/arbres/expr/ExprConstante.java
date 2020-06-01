package arbres.expr;

/**
 * 
 * @author Jonathan Moze
 */
class ExprConstante implements Expr {
    
    /**
     * valeur de l'expression
     */
    private final int valeur;

    /**
     * Constructeur de l'expression constante
     * @param valeur valeur de la constante
     */
    ExprConstante(int valeur) {
        this.valeur = valeur;
    }

    @Override
    public int valeur(Environnement env) {
        return valeur;
    }

    @Override
    public String description() {
        return String.format("la constante %d", valeur);
    }
    
    
}
