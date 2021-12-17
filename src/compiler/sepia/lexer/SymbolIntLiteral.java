/**
 * 
 */
package compiler.sepia.lexer;

/** This class represents int literals
 * @author José
 *
 */
public class SymbolIntLiteral extends Symbol {

	/**
	 * @param symbolString
	 */
	public SymbolIntLiteral(Token token, String symbolString, int intLiteral,
            int startLine, int lineNumber, int columnNumber) {
		super(token, symbolString, startLine, lineNumber, columnNumber);
		this.intLiteral = intLiteral;
		// TODO Auto-generated constructor stub
	}
	
	public void setIntValue(int intValue) {
		this.intLiteral = intValue;
	}

	public int getIntValue() {
		return intLiteral;
	}

	private int intLiteral;

}
