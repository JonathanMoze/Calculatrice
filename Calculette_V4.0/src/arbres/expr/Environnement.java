package arbres.expr;


public interface Environnement {
    void affecter(String name, int value);
    int  valeur(String name);
}
