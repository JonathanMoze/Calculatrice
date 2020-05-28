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
 * @author jonat
 */
public class Calculator {

    private Tokenizer tokenizer;
    private Token token;
    private final TableVariables table = new TableVariables();

    public int evaluation(String line)
            throws SyntaxErrorException, EvaluationErrorException {

        tokenizer = new Tokenizer(line);
        token = tokenizer.get();

        int value = arbreExpr().valeur(table);
        checkSyntax(token.isFinish(), String.format("End of expression expected, %s found", token));

        return value;
    }

    private void checkSyntax(boolean condition, String message) throws SyntaxErrorException {
        if (!condition) {
            throw new SyntaxErrorException(message);
        }
    }

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

    private Expr arbreNumber() throws SyntaxErrorException {
        checkSyntax(token.isNumber(), "Number expected");
        Expr value = Expr.constante(token.value());
        token = tokenizer.get();

        return value;
    }

    private Expr arbreTerm() throws SyntaxErrorException, EvaluationErrorException {
        Expr value = arbreFactor();
        while (token.isSymbol("*") || token.isSymbol("/")) {
            if (token.isSymbol("*")) {
                token = tokenizer.get();
                Expr tmp = arbreFactor();
                value = Expr.binaire(value, OpBinaire.MUL, tmp);
            } else {
                token = tokenizer.get();
                try {
                    Expr tmp = arbreFactor();
                    value = Expr.binaire(value, OpBinaire.DIV, tmp);
                } catch (ArithmeticException ex) {
                    throw new EvaluationErrorException(ex.getMessage());
                }
            }
        }

        return value;
    }

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
