package arbres.expr;

/**
 *
 * @author Jonathan Moze
 */
public class ExprVariable implements Expr {

    /**
     * nom de la variable
     */
    private final String name;
    
    /**
     * valeur de la variable
     */
    private final int valeur;
    
    /**
     * Constructeur de l'expression variable
     * @param name le nom de la variable
     * @param valeur valeur de la variable
     */
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
