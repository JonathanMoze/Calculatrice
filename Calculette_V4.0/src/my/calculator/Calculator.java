/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.calculator;

import arbres.expr.Expr;
import arbres.expr.OpBinaire;
import java.util.Scanner;

/**
 *
 * @author Jonathan Moze
 */
public class Calculator {

    /**
     * tokenizer pour découper l'expression
     */
    private Tokenizer tokenizer;

    /**
     * le token courant
     */
    private Token token;

    /**
     * la table des variables
     */
    private final TableVariables table = new TableVariables();

    /**
     * Fonction prinpcipale de la calculatrice permettant d'analyser l'entrée de
     * l'utilisateur et d'envoyer la réponse
     *
     * @param line ligne d'entrée dans la calculatrice
     * @return value la valeur du résultat
     *
     * @throws SyntaxErrorException erreur de syntaxe dans la ligne
     * @throws EvaluationErrorException erreur de mathématiques
     */
    public int evaluation(String line)
            throws SyntaxErrorException, EvaluationErrorException {

        tokenizer = new Tokenizer(line);
        token = tokenizer.get();

        int value = arbreExpr().valeur(table);
        checkSyntax(token.isFinish(), String.format("End of expression expected, %s found", token));

        return value;
    }

    /**
     * fonction permettant de lever une excpetion si il y a une erreur de
     * syntaxe
     *
     * @param condition la condition de syntaxe
     * @param message message d'erreur si la condition n'est pas bonne
     * @throws SyntaxErrorException erreur de syntaxe levée
     */
    private void checkSyntax(boolean condition, String message) throws SyntaxErrorException {
        if (!condition) {
            throw new SyntaxErrorException(message);
        }
    }

    /**
     * Fonction permettant a la calculatrice de fonctionner plusieurs fois sans
     * redémarrer l'application et permettant d'interagir avec l'utilisateur
     */
    public void boucleInteraction() {
        Scanner in = new Scanner(System.in);
        System.out.println("Claculatrice : Entrez votre calcul");
        while (true) {
            System.out.print("> ");
            if (!in.hasNextLine()) {
                break;
            }
            String line = in.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            if (line.equals("exit")) {
                break;
            }
            try {
                int value = evaluation(line);
                System.out.format("> %d\n", value);
            } catch (SyntaxErrorException ex) {
                System.out.format(" ! Incorrect syntax : %s\n",
                        ex.getMessage());
            } catch (EvaluationErrorException ex) {
                System.out.format(" ! Evaluation failed : %s\n",
                        ex.getMessage());
            }
        }
        System.out.println("Bye.");
    }

    /**
     * Fonction permettant le calcul entre deux termes d'une l'expression
     *
     * @return expr l'expression resultat
     * @throws SyntaxErrorException erreur de syntaxe dans l'expression
     * @throws EvaluationErrorException erreur de calcul dans l'expression
     */
    private Expr arbreExpr() throws SyntaxErrorException, EvaluationErrorException {
        Expr expr = arbreTerm();
        while (token.isSymbol("+") || token.isSymbol("-")) {
            if (token.isSymbol("+")) {
                token = tokenizer.get();
                Expr tmp = arbreTerm();
                expr = Expr.binaire(expr, OpBinaire.PLUS, tmp);
            } else {
                token = tokenizer.get();
                Expr tmp = arbreTerm();
                expr = Expr.binaire(expr, OpBinaire.MOINS, tmp);
            }
        }
        return expr;
    }

    /**
     * fonction permettant de récuperer les valeurs numériques des nombres de
     * l'expression
     *
     * @return value expression de la valeur du nombre
     * @throws SyntaxErrorException erreur de syntaxe dans l'expression
     */
    private Expr arbreNumber() throws SyntaxErrorException {
        checkSyntax(token.isNumber(), "Number expected");
        Expr value = Expr.constante(token.value());
        token = tokenizer.get();

        return value;
    }

    /**
     * Fonction permettant de calculer un terme à partir de deux facteurs d'une
     * expression.
     *
     * @return value expression de la valeur résultat
     * @throws SyntaxErrorException erreur de syntaxe dans l'expression
     * @throws EvaluationErrorException erreur de mathématiques dans
     * l'expression
     */
    private Expr arbreTerm() throws SyntaxErrorException, EvaluationErrorException {
        Expr value = arbreFactor();
        while (token.isSymbol("*") || token.isSymbol("/")) {
            if (token.isSymbol("*")) {
                token = tokenizer.get();
                Expr tmp = arbreFactor();
                value = Expr.binaire(value, OpBinaire.MUL, tmp);
            } else {
                token = tokenizer.get();
                Expr tmp = arbreFactor();
                value = Expr.binaire(value, OpBinaire.DIV, tmp);
            }
        }

        return value;
    }

    /**
     * Fonction permettant de calculer un facteur a partir de nombres, de
     * variables.
     *
     * @return expr l'expression du résultat calculé
     * @throws SyntaxErrorException erreur de syntaxe
     * @throws EvaluationErrorException erreur de mathématiques
     */
    private Expr arbreFactor() throws SyntaxErrorException, EvaluationErrorException {

        Expr expr = Expr.constante(0);
        if (token.isNumber()) {
            expr = arbreNumber();
        } else if (token.isWord()) {
            String nom = token.word();
            token = tokenizer.get();
            if (token.isSymbol("=")) {
                token = tokenizer.get();
                expr = Expr.affectation(nom, arbreExpr(), table);
            } else {
                try {
                    expr = Expr.variable(nom, table);
                } catch (NullPointerException ex) {
                    throw new EvaluationErrorException(ex.getMessage());
                }
            }

        } else if (token.isSymbol("(")) {
            token = tokenizer.get();
            expr = arbreExpr();
            if (token.isSymbol(")")) {
                token = tokenizer.get();
            }
        } else if (token.isSymbol("-")) {
            token = tokenizer.get();
            expr = Expr.binaire(Expr.constante(0), OpBinaire.MOINS, arbreFactor());
        } else {
            throw new SyntaxErrorException("missing \")\"");
        }
        return expr;
    }
}
