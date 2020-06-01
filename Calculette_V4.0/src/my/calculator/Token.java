/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.calculator;

/**
 *
 * @author Jonathan Moze
 */
public class Token {

    /**
     * type du token
     */
    final TokenType type;
    
    /**
     * valeur du token
     */
    final String string;

    /**
     * Constructeur de token
     * @param type tyoe du token
     * @param string valeur du token
     */
    public Token(TokenType type, String string) {
        this.type = type;
        this.string = string;
    }

    /**
     * Test si un token est un nombre
     * @return vrai si c'es un nombre, faux sinon
     */
    public boolean isNumber() {
        return this.type == TokenType.NUMBER;
    }

    
    /**
     * test si un token est bien un symbol particulier
     * @param symbol le symbole que l'on veut tester
     * @return vrai si c'est le symbole, faux sinon
     */
    public boolean isSymbol(String symbol) {
        return this.type == TokenType.SYMBOL && this.string.equals(symbol);
    }
    
    /**
     * Test si le token est un mot
     * @return vrai si c'est un mot, faux sinon
     */
    public boolean isWord(){
        return this.type == TokenType.WORD;
    }
    
    /**
     * Test si le token est invalide
     * @return vrai si il est invalide, faux sinon
     */
    public boolean isInvalid() {
        return this.type == TokenType.INVALID;
    }

    /**
     * test si le token est un token de fin
     * @return vrai si c'est un token de fin, faux sinon
     */
    public boolean isFinish() {
        return this.type == TokenType.END;
    }

    /**
     * Fonction permettant de récuperer la valeur numérique du token
     * @return la valeur
     */
    public int value() {
        return Integer.parseInt(this.string);
    }

    /**
     * Fonction permettant de récuperer le symbole d'un token
     * 
     * @return le symbole 
     */
    public char symbol() {
        return this.string.charAt(0);
    }
    
    /**
     * Fonction permettant de récupérer le mot d'un token
     * @return le mot
     */
    public String word(){
        return this.string;
    }
    
}
