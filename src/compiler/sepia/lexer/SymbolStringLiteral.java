/**
 * 
 */
package compiler.sepia.lexer;

/** This class represents a literal string 
 * @author José
 *
 */
public class SymbolStringLiteral extends Symbol {

	/**
	 * @param token
	 * @param symbolString
	 * @param lineNumber
	 * @param columnNumber
	 */
	public SymbolStringLiteral(Token token, String symbolString,
			int startLine, int lineNumber, int columnNumber) {
		super(token, symbolString, startLine, lineNumber, columnNumber);
		// TODO Auto-generated constructor stub
	}

}
