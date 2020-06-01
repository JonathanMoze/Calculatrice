package my.calculator;

import java.util.function.Supplier;

/**
 *
 * @author Jonathan Moze
 */
public class Tokenizer implements Supplier<Token> {
    
    /**
     * les symbols pouvant être utilisés
     */
    final String SYMBOLS = "+-*/()=";
    
    /**
     * la ligne à découper en token
     */
    String line;
    
    /**
     * index du prochain token
     */
    int next;

    /**
     * Constructeur d'un tokenizer
     * @param line expression à découper
     */
    public Tokenizer(String line) {
        this.line = line;
        this.next = 0;
    }

    
    /**
     * Fonction permettant de récuperer le prochain token de l'expression
     * @return le prochain token
     */
    @Override
    public Token get() {
        while (next < line.length() && Character.isSpaceChar(line.charAt(next))) {
            next++;
        }
        if (next >= line.length()) {
            return new Token(TokenType.END, "");
        }

        char first = line.charAt(next);
        if (Character.isDigit(first)) {
            return getNumber();
        }
        else if(isValidFirstCharForWord(first)){
            return getWord();
        }
        else if(SYMBOLS.indexOf(first) >= 0){
            return getSymbol();
        }
        else{
            next++;
            return new Token(TokenType.INVALID, Character.toString(first));
        }
    }

    
    /**
     * Fonction permettant de récuperer un token de type nombre avec sa valeur
     * @return le token
     */
    private Token getNumber() {
        StringBuilder builder = new StringBuilder();
        do {
            builder.append(line.charAt(next));
            next++;
        } while (next < line.length() && Character.isDigit(line.charAt(next)));
        return new Token(TokenType.NUMBER, builder.toString());
    }

    
    /**
     * Fonction permettant de récuperer un token de type symbole avec le symbole
     * en question
     * @return le token
     */
    private Token getSymbol() {
        String string = Character.toString(line.charAt(next));
        next++;
        return new Token(TokenType.SYMBOL, string);
    }
    
    /**
     * Fonction permettant de récuperer un token de type mot, avec le mot en
     * question
     * 
     * @return le token 
     */
    private Token getWord(){
        StringBuilder builder = new StringBuilder();
        do{
            builder.append(line.charAt(next));
            next++;
        }while (next < line.length() && isValidCharForWord(line.charAt(next)));
        return new Token(TokenType.WORD, builder.toString());
    }

    /**
     * Test si un caratère est valide pour pouvoir être dans un mot
     * @param c le caractère
     * @return vrai si le caractère est valide, faux sinon
     */
    private boolean isValidCharForWord(char c) {
        return c == '_' || Character.isLetter(c) || Character.isDigit(c);
    }
    
    /**
     * Test si le premier caractère est bien une lettre pour pouvoir être le
     * premier caractère d'un mot
     * 
     * @param first le permier caractère
     * @return vrai si oui, faux sinon
     */
    private boolean isValidFirstCharForWord (char first){
        return Character.isLetter(first);
    }
}
