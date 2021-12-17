/**
 * 
 */
package compiler.siel.lexer;

/** This class represents char literals
 * @author José
 *
 */
public class SymbolCharLiteral extends Symbol {

	/**
	 * @param symbolString
	 */
	public SymbolCharLiteral(Token token, String symbolString, char charLiteral,
            int startLine, int lineNumber, int columnNumber) {
		super(token, symbolString, startLine, lineNumber, columnNumber);
		this.charLiteral = charLiteral;
		// TODO Auto-generated constructor stub
	}

	public void setCharLiteral(char charLiteral) {
		this.charLiteral = charLiteral;
	}

	public char getCharLiteral() {
		return charLiteral;
	}

	private char charLiteral;
}
