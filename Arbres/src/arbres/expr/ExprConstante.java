package arbres.expr;

class ExprConstante implements Expr {
    

    private final int valeur;

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
