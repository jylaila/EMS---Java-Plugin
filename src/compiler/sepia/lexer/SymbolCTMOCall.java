package compiler.sepia.lexer;

/**
 * Describes an compile-time metaobject call.
 *
 * @author José
 *
 */

public class SymbolCTMOCall extends Symbol {

	/**
	 * @param token  refers to the '@' or '@@' preceding the metaobject call
	 * @param ctmoName  is the name of the metaobject, "color" in @@color(blue)
	 * @param leftSymbol is the symbol opening the metaobject call, "(" in @color(blue)
	 * @param rightSymbol is the symbol closing the call, ")" in @color(blue)
	 * @param text é the text between the opening and closing characters, "blue" in the
	 *        example.
	 * @param lineNumber
	 * @param columnNumber
	 */
	public SymbolCTMOCall(Token token, String ctmoName, String leftSymbol,
			String rightSymbol,
			char []text,
			int startLine, int lineNumber, int columnNumber) {
		super(token, ctmoName, startLine, lineNumber, columnNumber);
		// TODO Auto-generated constructor stub
		this.leftSymbol = leftSymbol;
		this.rightSymbol = rightSymbol;
		this.text = text;
	}

	public boolean isCodeg() {
		return false;
	}

	public void setText(char [] text) {
		this.text = text;
	}

	public char [] getText() {
		return text;
	}

	public void setLeftSymbol(String leftSymbol) {
		this.leftSymbol = leftSymbol;
	}

	public String getLeftSymbol() {
		return leftSymbol;
	}

	public void setRightSymbol(String rightSymbol) {
		this.rightSymbol = rightSymbol;
	}

	public String getRightSymbol() {
		return rightSymbol;
	}

	/**
	 * text between the ( and ) such as in
	 *     @AWK(  /[A-Z]/ [ print $0 ] )
	 *  here variable text contains "  /[A-Z]/ [ print $0 ] ".
	 */
	private char []text;
	/**
	 * left symbol just after the metaobject name. In the above example, "(".
	 * It may be (, [, or {. That will change later, allowing other symbols or
	 * removing the left symbol altogether.
	 * Idem for rightSymbol;
	 */
	private String leftSymbol, rightSymbol;

}