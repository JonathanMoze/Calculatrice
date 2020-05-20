package arbres;

import arbres.expr.Expr;
import arbres.expr.OpBinaire;

/**
 *
 * @author billaud
 */
public class Arbres {

    public static void main(String[] args) {

        testsSansVariables();
        testsAvecVariables();
        testsAffectation();

    }

    private static void testsSansVariables() {

        TableVariables vars = new TableVariables();

        // 3 = 3
        afficherAvecValeur(Expr.constante(3), vars);

        // 3 + 4 = 7
        afficherAvecValeur(
                Expr.binaire(
                        Expr.constante(3),
                        OpBinaire.PLUS,
                        Expr.constante(4)
                ), vars);

        // 10 - (2 + 5) = 3
        // Ne s'affiche pas correctement 
        // Résultat incorrect
        afficherAvecValeur(
                Expr.binaire(
                        Expr.constante(10),
                        OpBinaire.MOINS,
                        Expr.binaire(
                                Expr.constante(2),
                                OpBinaire.PLUS,
                                Expr.constante(5)
                        )
                ), vars);

        // (2 + 3) * 4 = 12
        afficherAvecValeur(
                Expr.binaire(
                        Expr.binaire(
                                Expr.constante(2),
                                OpBinaire.PLUS,
                                Expr.constante(3)
                        ),
                        OpBinaire.MUL,
                        Expr.constante(4)
        ), vars);
        
        
    }

    private static void afficherAvecValeur(Expr n, TableVariables vars) {
        System.out.format("Valeur de %s = %d\n",
                n.description(),
                n.valeur(vars));
    }

    private static void testsAvecVariables() {
        TableVariables vars = new TableVariables();
        vars.affecter("a", 100);
        vars.affecter("b", 20);
        vars.affecter("c", 3);

        // tester  expressions
        // a             100
        // a + b         120
        // a + b * c     160
        
        afficherAvecValeur(Expr.variable("a", vars), vars);
        afficherAvecValeur(
                Expr.binaire(
                    Expr.variable("a", vars),
                    OpBinaire.PLUS,
                    Expr.variable("b", vars)
                ), vars);
        afficherAvecValeur(
                Expr.binaire(
                        Expr.variable("a", vars),
                        OpBinaire.PLUS,
                        Expr.binaire(
                                Expr.variable("b", vars),
                                OpBinaire.MUL,
                                Expr.variable("c", vars))
                ), vars);
        }
    

    private static void testsAffectation() {
        TableVariables vars = new TableVariables();
        // tester affectation
        // a = 10             10
        // a + 1              11
        // b = a * a         121
        // b                 121
        // a = b = 33         33
        // b                  33
        
        
        afficherAvecValeur(Expr.affectation("a", Expr.constante(10), vars), vars);
        afficherAvecValeur(Expr.affectation("a", Expr.binaire(
                                                    Expr.variable("a", vars),
                                                    OpBinaire.PLUS,
                                                    Expr.constante(1)), vars), vars);
        afficherAvecValeur(Expr.affectation("b",
                           Expr.binaire(Expr.variable("a", vars),
                                        OpBinaire.MUL,
                                        Expr.variable("a", vars)), vars), vars);
        afficherAvecValeur(Expr.variable("b", vars), vars);
        afficherAvecValeur(Expr.affectation("a",
                            Expr.affectation("b",
                                    Expr.constante(33), vars), vars), vars);
        afficherAvecValeur(Expr.variable("b", vars), vars);
        


    }

}
