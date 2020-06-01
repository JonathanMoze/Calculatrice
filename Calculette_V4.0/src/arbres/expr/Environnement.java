package arbres.expr;

/**
 * L'environnement avec lequel sont construites les exoressions
 * @author Jonathan Moze
 */
public interface Environnement {
    
    /**
     * Affecter une variable à un environnement
     * @param name nom de la variable
     * @param value valeur associée
     */
    void affecter(String name, int value);
    
    /**
     * Récuperer la valeur d'une variable de l'environnement
     * @param name nom de la variable
     * @return la valeur
     */
    int  valeur(String name);
}
