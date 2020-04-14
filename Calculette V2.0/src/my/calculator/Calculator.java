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

    public int evaluation(String line)
            throws SyntaxErrorException, EvaluationErrorException {
        Tokenizer tockenizer = new Tokenizer(line);
        Token token = tockenizer.get();
        checkSyntax(token.isNumber(), "Number expected");
        int total = token.value();

        token = tockenizer.get();
        while (token.isSymbol("+")) {
            token = tockenizer.get();
            checkSyntax(token.isNumber(), "Number expected");
            total += token.value();
            token = tockenizer.get();
        }

        while (token.isSymbol("-")) {
            token = tockenizer.get();
            checkSyntax(token.isNumber(), "Number expected");
            total -= token.value();
            token = tockenizer.get();
        }
        
        checkSyntax(token.isFinish(), String.format("End of expression expected, %s found", token));
        return total;
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
            if (line.equals("\\q")) {
                break;
            }
            try {
                int value = evaluation(line);
                System.out.format("> %d\n", value);
            } catch (SyntaxErrorException ex) {
                System.out.format(" ! Incorrect syntax %s\n",
                        ex.getMessage());
            } catch (EvaluationErrorException ex) {
                System.out.format(" ! Evaluation failed %s\n",
                        ex.getMessage());
            }
        }
        System.out.println("Bye.");
    }
}
