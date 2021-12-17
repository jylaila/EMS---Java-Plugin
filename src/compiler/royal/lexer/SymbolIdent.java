/**
 * 
 */
package compiler.royal.lexer;

/** This class represents an identifier. Inherited field name is the 
 * identifier name
 * @author José
 *
 */
public class SymbolIdent extends Symbol {

	/**
	 * @param symbolString
	 * @param lineNumber 
	 * @param columnNumber 
	 */
	public SymbolIdent(Token token, String symbolString, int startLine, 
			 int lineNumber, int columnNumber) {
		super(token, symbolString, startLine, lineNumber, columnNumber);
		// TODO Auto-generated constructor stub
	}

}
