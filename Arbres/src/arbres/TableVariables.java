package arbres;

import java.util.HashMap;
import java.util.Map;


public class TableVariables implements arbres.expr.Environnement {
    
        Map<String,Integer> variables = new HashMap<>();
    
    public void affecter(String name, int value) {
        variables.put(name, value);
    }
    
    public int  valeur(String name) {
        return variables.get(name);
    }
}
