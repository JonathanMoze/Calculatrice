package arbres.expr;



class ExprBinaire implements Expr {

    private final  OpBinaire op;
    private final Expr gauche;
    private final Expr droite;

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
