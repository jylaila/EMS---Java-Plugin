package compiler.royal.lexer;

public class Symbol {

	public Symbol(Token token, String symbolString, int startLine, int lineNumber, int columnNumber) {
		this.token = token;
		this.symbolString = symbolString;
		this.startLine = startLine;
		this.setLineNumber(lineNumber);
		this.setColumnNumber(columnNumber);
	}

	public void setSymbolString(String symbolString) {
		this.symbolString = symbolString;
	}
	public String getSymbolString() {
		return symbolString;
	}

	/**
	 * get the Java name corresponding to this selector. It is
	 * equal to symbolString except when there is a underscore.
	 * All underscore characters are duplicated. So,
	 *       Is_A_Number
	 * results in
	 *       Is__A__Number
	 * @param lineNumber

	public String getJavaName() {
		String s = "";
		for ( int i = 0; i < symbolString.length(); i++ ) {
			char ch = symbolString.charAt(i);
			if ( ch != ':' )
				s = s + ch;
			if ( ch == '_' )
				s = s + '_';
		}
		return s;

	}*/

	public int getStartLine() {
		return startLine;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setColumnNumber(int columnNumber) {
		this.columnNumber = columnNumber;
	}

	public int getColumnNumber() {
		return columnNumber;
	}

	public Token token;
	public String symbolString;
	/**
	 * if the file is in the char array arrayChar, then arrayChar[startLine] is the
	 * first character of the line in which this symbol is.
	 */
	public int startLine;

	private int lineNumber, columnNumber;

}
