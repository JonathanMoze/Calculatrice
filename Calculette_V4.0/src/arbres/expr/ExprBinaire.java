package arbres.expr;


/**
 * 
 * @author Jonathan Moze
 */
class ExprBinaire implements Expr {

    /**
     * Opération binaire de l'expression
     */
    private final  OpBinaire op;
    /**
     * Expression de gauche dans l'expression binaire
     */
    private final Expr gauche;
    
    /**
     * Expression de droite dans l'expression binaire
     */
    private final Expr droite;

    /***
     * COnstructeur de l'expression binaire
     * @param gauche expression de gauche
     * @param op opérateur binaire
     * @param droite expression de droite
     */
    public ExprBinaire( Expr gauche, OpBinaire op, Expr droite) {
        this.op = op;
        this.gauche = gauche;
        this.droite = droite;
    }

    @Override
    public int valeur(Environnement env) {
         return op.appliquer(gauche.valeur(env), droite.valeur(env));
    }
    
    @Override
    public String description() {
        return String.format("(%s %s %s)", 
                gauche.description(), op, droite.description());
    }
    
}
