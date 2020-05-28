/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.calculator;

/**
 *
 * @author jonat
 */
public class Token {

    final TokenType type;
    final String string;

    public Token(TokenType type, String string) {
        this.type = type;
        this.string = string;
    }

    public boolean isNumber() {
        return this.type == TokenType.NUMBER;
    }

    public boolean isSymbol(String symbol) {
        return this.type == TokenType.SYMBOL && this.string.equals(symbol);
    }
    
    public boolean isWord(){
        return this.type == TokenType.WORD;
    }
    

    public boolean isInvalid() {
        return this.type == TokenType.INVALID;
    }

    public boolean isFinish() {
        return this.type == TokenType.END;
    }

    public int value() {
        return Integer.parseInt(this.string);
    }

    public char symbol() {
        return this.string.charAt(0);
    }
    
    public String word(){
        return this.string;
    }
    
}
