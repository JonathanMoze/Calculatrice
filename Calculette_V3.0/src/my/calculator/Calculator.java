/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.calculator;

import java.util.Scanner;

/**
 *
 * @author jonat
 */
public class Calculator {

    private Tokenizer tokenizer;
    private Token token;

    public int evaluation(String line)
            throws SyntaxErrorException, EvaluationErrorException {

        tokenizer = new Tokenizer(line);
        token = tokenizer.get();

        int value = get_expr_value();
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
        System.out.println("Claculatriice : Entrez votre calcul");
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

    private int get_expr_value() throws SyntaxErrorException, EvaluationErrorException {
        int total = get_term_value();
        while (token.isSymbol("+")) {
            token = tokenizer.get();
            total += get_term_value();
        }
        while (token.isSymbol("-")) {
            token = tokenizer.get();
            total -= get_term_value();
        }
        return total;
    }

    private int get_number_value() throws SyntaxErrorException {
        checkSyntax(token.isNumber(), "Number expected");
        int value = token.value();
        token = tokenizer.get();

        return value;
    }

    private int get_term_value() throws SyntaxErrorException, EvaluationErrorException {
        int value = get_factor_value();
        while (token.isSymbol("*")) {
            token = tokenizer.get();
            value *= get_factor_value();
        }
        while (token.isSymbol("/")) {
            token = tokenizer.get();
            try {
                value /= get_factor_value();
            } catch (ArithmeticException ex) {
                throw new EvaluationErrorException(ex.getMessage());
            }
        }

        return value;
    }

    private int get_factor_value() throws SyntaxErrorException, EvaluationErrorException {

        if (token.isNumber()) {
            return get_number_value();
        } else if (token.isSymbol("(")) {
            token = tokenizer.get();
            int value = get_expr_value();
            if (token.isSymbol(")")) {
                token = tokenizer.get();
            }
            return value;
        } else if (token.isSymbol("-")) {
            token = tokenizer.get();
            return -get_factor_value();
        } else {
            throw new SyntaxErrorException("missing \")\"");
        }
    }
}
