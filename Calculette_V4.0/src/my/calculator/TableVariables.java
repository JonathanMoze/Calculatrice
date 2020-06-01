package my.calculator;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Jonathan Moze
 */
public class TableVariables implements arbres.expr.Environnement {

    
    /**
     * Map ou sont stokées les variables
     */
    Map<String, Integer> variables = new HashMap<>();

    
    /**
     * Fonction permettant d'affecter une variable à la table de variable
     * @param name nom de la variable
     * @param value valeur de la variable
     */
    @Override
    public void affecter(String name, int value) {
        variables.put(name, value);
    }

    
    /**
     * Fonction permettant de récuperer la valeur d'un variable dans la table
     * @param name nom de la variable
     * @return valeur de la variable
     */
    @Override
    public int valeur(String name) {
        return variables.get(name);
    }
}
