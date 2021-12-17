package compiler.royal.lexer;

import java.util.*;




public class Lexer {


	public Lexer(char[] in) {

		this.in = in;
		lineNumber = 1;
		k = 0;
		commentLevel = 0;
		startLine = 0;
		beforeLastTokenPos = 0;
		lastTokenPos = 0;

		nextToken();
	}


	public Token token;
	public String ident;
	public String tokenString;

	public byte byteValue;
	public short shortValue;
	public int intValue;
	public long longValue;
	public float floatValue;
	public double doubleValue;
	public char charValue;
	public boolean booleanValue;
	static private Hashtable<String, Token> keywordsTable;
	static private Hashtable<String, Token> specialCharTable;
	static private Set<String> keywordTextSet;

	private char[] in;
	private int commentLevel;
	// index for the input. in[k] is the next character to be analyzed
	private int k;
	public int startToken;
	private int lineNumber;
	private int beforeLastTokenPos, lastTokenPos;

	public Symbol symbol;

	/*
	 * /** keeps all the tokens of the current file. private Symbol
	 * []tokenArray; /** index of array tokenArray. private int indexTokenArray;
	 * 
	 * 
	 * /** size of array tokenArray (the real number of elements in it, which
	 * may be different from tokenArray.length). private int sizeTokenArray;
	 */
	
	private int startLineComment;
	/**
	 * in[startLine] is the first character of the line in which the symbol is
	 */
	private int startLine;


	private static final long MaxInteger = 2147483647;

	/**
	 * Maximum number of characters of a sequence of symbols that ends a
	 * metaobject. Example:
	 *
	 * @javacode[*= .... =*] Here =*] has three characters. It could have at
	 *            most MaxChEndSymbolString.
	 */
	private static final int MaxChEndSymbolString = 30;

	// static private Set<String> illegalOperatorSymbolSequence;
	static private String[] setOfIllegalOperatorSymbolSequence;

	static {
		String v[] = { "><", "((", "))", "[|", "([", "])", "[[", "]]", "[:", ":]",
				"|:", "|->", "(:", "<:", ">(", ":[", ":(", "[^", "::", ");",
				">;", ")>", ")*", "*)", ")*)", ">>", ")+", "+)", ")?", "?)",
				")?)", ">)", "^(" };
		setOfIllegalOperatorSymbolSequence = v;
		initSymbols();
	}

	public int getCurrentChar() {
		// return the position of the current character in the input "in"
		return k;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public String getCurrentLine() {
		// return the current line
		int i = startLine;
		StringBuffer line = new StringBuffer();
		while (in[i] >= '\0' && in[i] != '\n' && in[i] != '\r')
			line.append(in[i++]);
		return line.toString();
	}

	public int getColumn() {

		return startToken - startLine + 1;
		/*
		 * // return the column of start of the last token int i = startToken;
		 * while ( i >= 0 && in[i] != '\n' ) i--; return startToken - i; int j =
		 * k - i; if ( token != Symbol.IDENT ) j = j - stringValue.length();
		 * else if ( ident != null ) j = j - ident.length(); return j;
		 */
	}

	/**
	 * Initialize the private variables of class lexer: - fill the keywords
	 * table with the keywords; - scan all the file and puts all symbols in
	 * array tokenArray
	 *
	 * all future calls to method next will only retrieve the symbols from array
	 * tokenArray
	 */
	static private void initSymbols() {


		// inserts the keywords in the symbol table
		
		keywordsTable = new Hashtable<String, Token>();
		for (Token s : Token.values())
			if (Character.isLetter(s.toString().charAt(0)))
				keywordsTable.put(s.toString(), s);
		specialCharTable = new Hashtable<String, Token>();

		
		specialCharTable.put("=", Token.ASSIGN);
		specialCharTable.put(":", Token.COLON);
		specialCharTable.put(",", Token.COMMA);
		specialCharTable.put("(", Token.LEFTPAR);
		specialCharTable.put("[", Token.LEFTSB);
		specialCharTable.put("{", Token.LEFTCB);
		specialCharTable.put(")", Token.RIGHTPAR);
		specialCharTable.put("}", Token.RIGHTCB);
		specialCharTable.put("]", Token.RIGHTSB);
		specialCharTable.put(";", Token.SEMICOLON);
		
		// see http://stackoverflow.com/questions/15496/hidden-features-of-java
		keywordTextSet = new HashSet<String>() {{
			add("source");
			add("text");
			add("errorDescription");
		}};
	}


	public void nextToken() {
		StringBuffer s;
		String ext;

		while (Character.isWhitespace(in[k])) {
			if (in[k] == '\n') {
				lineNumber++;
				if (in[k] != '\0')
					startLine = k + 1;
			}
			k++;
		}
		if (in[k] == '\0') {
			token = Token.EOF;
			symbol = new Symbol(token, "", startLine, lineNumber, getColumn());
			return;
		}

		if (in[k] == '/' && in[k + 1] == '/') {
			// comment till the end of the line
			k += 2;
			while (in[k] != '\n' && in[k] != '\0')
				k++;
			if (in[k] == '\n') {
				k++;
				lineNumber++;
				startLine = k;
			}
			nextToken();
		} else if (in[k] == '/' && in[k + 1] == '*') {
			// start comment
			commentLevel = 1;
			int lineStartComment = lineNumber;
			k += 2;
			while (commentLevel > 0)
				if (in[k] == '*' && in[k + 1] == '/') {
					commentLevel--;
					k += 2;
				} else if (in[k] == '/' && in[k + 1] == '*') {
					commentLevel++;
					k += 2;
				} else if (in[k] == '\0') {
					error("Comment started at line " + lineStartComment
							+ " does not terminate");
					token = Token.EOF;
					symbol = new Symbol(token, "", startLine, lineNumber,
							getColumn());
					return;
				} else {
					if (in[k] == '\n') {
						lineNumber++;
						startLine = k + 1;
					}
					k++;
				}
			nextToken();
		} else {
			startToken = k;
			// not a comment
			if (Character.isLetter(in[k]) || in[k] == '_') {
				s = new StringBuffer();
				while (Character.isLetterOrDigit(in[k]) || in[k] == '_') {
					s.append(in[k]);
					k++;
				}
				ident = s.toString();
				if (ident.compareTo("_") == 0) {
					error("'_' is not a valid identifier");
				} /*
				if (in[k] == ':') {
					ident = ident + ":";
					k++;
					token = Token.IDENTCOLON;
					symbol = new SymbolIdent(token, ident, startLine,
							lineNumber, getColumn());
				} else { */
					Token aSymbol = keywordsTable.get(ident);
					if ( aSymbol == null ) {
						// identifier
						
						if ( isSequenceChar(in[k]) ) {
							  /*
							   * something like
							   *       source<<*  .... *>>
							   *  or
							   *       text([ ... ])
							   */
						    if ( ! keywordTextSet.contains(ident) )
    							error("'source' or 'text' expected before char sequence");
    						else
							   getMetaobjectText(ident);
						}
						else {
							token = Token.IDENT;
                         	symbol = new SymbolIdent(token, ident, startLine,
								lineNumber, getColumn());
						}
					} else {
						// keyword
						token = aSymbol;
						symbol = new SymbolKeyword(token, ident, startLine,
								       lineNumber, getColumn());
					}

				//}
			} else if ( in[k] >= '0' && in[k] <= '9' ) {
				try {
					StringBuffer strnum = new StringBuffer();
					int state = 1;
					ext = "";
					while (state != 100)
						switch (state) {
						case 1:
							while ((in[k] >= '0' && in[k] <= '9')
									|| in[k] == '_') {

								if (in[k] != '_')
									strnum.append(in[k]);
								k++;
							}
							StringBuffer extension = new StringBuffer();
							while (Character.isLetter(in[k]))
								extension.append(in[k++]);
							ext = extension.toString();

							if (ext.compareTo("B") == 0)
								state = 3;
							else if (ext.compareTo("L") == 0)
								state = 4;
							else if (ext.compareTo("F") == 0)
								state = 5;
							else if (ext.compareTo("D") == 0)
								state = 6;
							else if (in[k] == '.') {
								strnum.append('.');
								state = 7;
							} else if (ext.compareTo("E") == 0
									|| ext.compareTo("e") == 0) {
								strnum.append(ext);
								state = 9;
							} else {
								if (ext.compareTo("I") == 0)
									k++;
								state = 2;
							}
							break;
						case 2:
							token = Token.INTLITERAL;
							tokenString = strnum + ext;
							intValue = Integer.valueOf(tokenString).intValue();
							symbol = new SymbolIntLiteral(token, tokenString,
									intValue, startLine, lineNumber,
									getColumn());
							long n = Long.valueOf(tokenString).longValue();
							if (n > MaxInteger)
								throw new NumberFormatException();
							state = 100;
							break;
						case 7:
							k++;
							if (in[k] < '0' || in[k] > '9') {
								if (!Character.isLetter(in[k]) && in[k] != '_')
									error("Illegal constant: . should be followed by a number");
								strnum = new StringBuffer(strnum.toString()
										.substring(0, strnum.length() - 1));
								token = Token.INTLITERAL;
								tokenString = strnum + ext;
								intValue = Integer.valueOf(tokenString)
								.intValue();
								symbol = new SymbolIntLiteral(token,
										tokenString, intValue, startLine,
										lineNumber, getColumn());
								k--;
								state = 100;
							} else
								state = 8;
							break;
						case 8:
							while (in[k] >= '0' && in[k] <= '9') {
								strnum.append(in[k]);
								k++;
							}
							if (in[k] == 'E' || in[k] == 'e') {
								strnum.append(in[k]);
								k++;
								state = 9;
							} else if (in[k] == 'd') {
								k++;
								state = 6;
							} else {
								if (in[k] == 'r')
									k++;
								state = 5;
							}
							break;
						case 10:
							while (in[k] >= '0' && in[k] <= '9') {
								strnum.append(in[k]);
								k++;
							}
							if (in[k] == 'd') {
								k++;
								state = 6;
							} else {
								if (in[k] == 'r')
									k++;
								state = 5;
							}
							break;
						}
				} catch (NumberFormatException e) {
					error("error in converting number");
					token = Token.INTLITERAL;
					symbol = new SymbolIntLiteral(token, "0", 0, startLine,
							lineNumber, getColumn());
				}
			} else {
				boolean foundToken = false;
                if ( in[k] == '@' && in[k+1] == '"' ) {
					foundToken = true;
					k += 2;
					tokenString = getRawLiteralString();
					token = Token.LITERALSTRING;
					symbol = new SymbolStringLiteral(token, tokenString,
							startLine, lineNumber, getColumn());

				}
				if ( ! foundToken )
					switch (in[k]) {
					case '"':
						k++;
						tokenString = getLiteralString();
						token = Token.LITERALSTRING;
						symbol = new SymbolStringLiteral(token, tokenString,
								startLine, lineNumber, getColumn());

						break;
					case '\'':
						s = new StringBuffer();
						k++;
						if (in[k] == '\\')
							while (in[k] != '\'' && in[k] != '\n' && in[k] != '\0') {
								s.append(in[k]);
								k++;
							}
						else {
							s.append(in[k]);
							k++;
						}
						if (in[k] != '\'') {
							if (in[k] == '\n' || in[k] == '\0')
								error("Non-terminated literal character");
							else
								error("\' expected");
						} else
							k++;
						tokenString = s.toString();
						charValue = tokenString.charAt(0);

						token = Token.CHARLITERAL;
						symbol = new SymbolCharLiteral(token, tokenString,
								charValue, startLine, lineNumber, getColumn());
						break;
					case ',':
						token = Token.COMMA;
						k++;
						symbol = new SymbolOperator(token, ",", startLine,
								lineNumber, getColumn());
						break;
					case ';':
						token = Token.SEMICOLON;
						k++;
						symbol = new SymbolOperator(token, ";", startLine,
								lineNumber, getColumn());
						break;
						/*
						 * ! ? @ # $ = % & * + / < - ^ ~ . : > | \ ( ) [ ] { }
						 */
					case '!':  case '?':  case '@':  case '#':  case '$':
					case '=':  case '%':  case '&':  case '*':  case '+':
					case '/':  case '<':  case '-':  case '^':  case '~':
					case '.':  case ':':  case '>':  case '|':  case '\\':
					case '(':  case ')':  case '[':  case ']':  case '{':
					case '}':
						s = new StringBuffer();
						while ( in[k] == '!' || in[k] == '?' || in[k] == '@'
    							|| in[k] == '#' || in[k] == '$'

								|| in[k] == '=' || in[k] == '%'	|| in[k] == '&'
								|| in[k] == '*' || in[k] == '+'

								|| in[k] == '/' || in[k] == '<' || in[k] == '-'
								|| in[k] == '^' || in[k] == '~'

								|| in[k] == '.' || in[k] == ':' || in[k] == '>'
								|| in[k] == '|' || in[k] == '\\'

								|| in[k] == '(' || in[k] == ')'
								|| in[k] == '[' || in[k] == ']' || in[k] == '{'
								|| in[k] == '}' )
							s.append(in[k++]);
						String ss = s.toString();
                        if ((token = specialCharTable.get(ss)) != null) {
							symbol = new SymbolOperator(token, ss, startLine,
									lineNumber, getColumn());
						}
						else
							error("Unidentified symbol sequence'" + ss + "'");
							symbol = new SymbolOperator(token, ss, startLine,
									lineNumber, getColumn());
							
						break;
					default:
						k++;
						error("Unidentified character '" + in[k - 1] + "'"
								+ " ASCII " + ((int) in[k - 1]));
						symbol = new SymbolOperator(token, "X", startLine,
								lineNumber, getColumn());
					}
			}
		}
		beforeLastTokenPos = lastTokenPos;
		lastTokenPos = k - 1;

	}


	/**
	 * name is "source" or "text". This method is called when we have
	 *        source<<* .... *>>
	 * or
	 *      text[<+   ....  +>] 
	 *      
	 * Of course, in other DSL´s the parameter name may assume other values.
	 * 
	 * @param name
	 * @return
	 */
	private Symbol getMetaobjectText(String name) {
		
		if ( ! isSequenceChar(in[k]) ) {
			return null;
		}
		else {
			char[] leftCharArray = new char[MaxChEndSymbolString + 1];
			  // size of leftCharArray and rightCharArray
			int size = 0;
			while ( isSequenceChar(in[k]) ) {
				if (size >= MaxChEndSymbolString) {
					error("Sequence of symbols in a metaobject that has more than "
							+ MaxChEndSymbolString + " symbols");
					token = Token.EOF;
					symbol = new Symbol(token, "", startLine, lineNumber,
							getColumn());
					return symbol;
				}
				leftCharArray[size++] = in[k++];
			}
			leftCharArray[size] = '\0';
			char[] rightCharArray = getRightSymbolSeq(leftCharArray, size);
			char []text = getTextTill(rightCharArray);

			if ( text != null ) {
				symbol = new SymbolCTMOCall(token = Token.CTMOCALL,
							name, fromCharArray(leftCharArray),
							fromCharArray(rightCharArray), text,
							startLine, lineNumber, getColumn());

			}
			else
				symbol = null;
			return symbol;
		}
	}
	
				
	
	/**
	 * Assumes that in[k] is in the first character after " in a string or Cyan
	 * Symbol. That is, in "olá" in[k] must be 'o' and in
	 * #"this is a cyan symbol" in[k] must be 't'
	 *
	 * @return
	 */
	private String getLiteralString() {
		String ret = null;
		StringBuffer s = new StringBuffer();
		while (in[k] != '\0' && in[k] != '\n')
			if (in[k] == '"')
				break;
			else if (in[k] == '\\') {
				if (in[k + 1] == '\\') {
					s.append("\\\\");
					k += 2;
				} else if (in[k + 1] != '\n' && in[k + 1] != '\0') {
					s.append(in[k]);
					k++;
					s.append(in[k]);
					k++;
				}
			} else {
				s.append(in[k]);
				k++;
			}

		if (in[k] == '\0' || in[k] == '\n') {
			error("Nonterminated string");
			ret = "";
		} else {
			k++;
			ret = s.toString();
		}
		return ret;
	}


	/**
	 * Assumes that in[k] is in the first character after " in a string.
	 * That is, in "olá" in[k] must be 'o'. This method does not consider
	 * escape characters --- it should be called when the string starts
	 * with @" as in
	 *       @"c:\t\f"
	 *  This string has six characters, including two '\\'.
	 *
	 * @return
	 */
	private String getRawLiteralString() {
		String ret = null;
		StringBuffer s = new StringBuffer();
		while (in[k] != '\0' && in[k] != '\n')
			if (in[k] == '"')
				break;
            else {
				s.append(in[k]);
				k++;
			}

		if (in[k] == '\0' || in[k] == '\n') {
			error("Nonterminated string");
			ret = "";
		} else {
			k++;
			ret = s.toString();
		}
		return ret;
	}


	public int getLineNumberBeforeLastToken() {
		return getLineNumber(beforeLastTokenPos);
	}

	private int getLineNumber(int index) {
		// return the line number in which the character input[index] is
		int i, n, size;
		n = 1;
		i = 0;
		size = in.length;
		while (i < size && i < index) {
			if (in[i] == '\n')
				n++;
			i++;
		}
		return n;
	}

	public String getLineBeforeLastToken() {
		return getLine(beforeLastTokenPos);
	}

	private String getLine(int index) {
		// get the line that contains input[index]. Assume input[index] is at a
		// token, not
		// a white space or newline

		int i = index;
		if (i == 0)
			i = 1;
		else if (i >= in.length)
			i = in.length;

		StringBuffer line = new StringBuffer();
		// go to the beginning of the line
		while (i >= 1 && in[i] != '\n')
			i--;
		if (in[i] == '\n')
			i++;
		// go to the end of the line putting it in variable line
		while (in[i] != '\0' && in[i] != '\n' && in[i] != '\r') {
			line.append(in[i]);
			i++;
		}
		return line.toString();
	}

	/**
	 * returns true if the parameter can be part of a sequence that delimits
	 * a literal object or a metaobject.
	 * @param ch
	 * @return
	 */
	private boolean isSequenceChar(char ch) {
		return ch == '!' ||  ch == '?' ||  ch == '@' ||  ch == '#' ||
		       ch == '$' ||  ch == '=' ||  ch == '%' ||  ch == '&' ||
		       ch == '*' ||  ch == '+' ||  ch == '/' ||  ch == '<' ||
		       ch == '-' ||  ch == '^' ||  ch == '~' ||  ch == '.' ||
		       ch == ':' ||  ch == '>' ||  ch == '|' ||  ch == '\\' ||
		       ch == '(' ||  ch == ')' ||  ch == '[' ||  ch == ']' ||
		       ch == '{' ||  ch == '}';

	}

	
	
	
	public void setStartLineComment(int startLineComment) {
		this.startLineComment = startLineComment;
	}

	public int getStartLineComment() {
		return startLineComment;
	}

	/**
	 * Janaína: change this method. As it is, it prints a message in the standard output, 
	 * messing with the IDE. 
	 * 
	 * @param message
	 */
	public void error(String message) {
		System.out.println("Error at line ");
		System.out.println(getCurrentLine());
		System.out.println("  " + message);
	}

	/**
	 * return a string with the contents of charArray (which may be
	 *  '\0' terminated).
	 * @param charArray
	 * @return
	 */
	private String fromCharArray(char[] charArray) {
		String s = "";
		int i = 0;
		while (i < charArray.length && charArray[i] != '\0') {
			s = s + charArray[i];
			i++;
		}
		return s;
	}


	/**
	 * gets a left symbol sequence of a literal object or metaobjec as input and returns
	 * the right symbol sequence:
	 *            (#  returns  #)
	 *            <(*   returns *)>
	 *
	 * @param leftCharArray should be a sequence of char´s terminated with '\0'
	 * @param size
	 * @return
	 */
	private char []getRightSymbolSeq(char []leftCharArray, int size) {

		/*
		 * realSize makes this method works whether leftCharArray terminates with '\0' or not.
		 */
		int realSize = size;
		if ( leftCharArray[size-1] == '\0' ) --realSize;
		char[] rightCharArray = new char[realSize + 1];
		int j;
		char ch;
		for (j = 0; j < realSize; j++) {
			ch = leftCharArray[realSize - j - 1];
			if (ch == ')' || ch == ']' || ch == '}' || ch == '>') {
				error("), ], }, or > cannot be used in starting a metaobject call");
				token = Token.EOF;
				symbol = new Symbol(token, "", startLine, lineNumber,
						getColumn());
			} else if (ch == '(')
				ch = ')';
			else if (ch == '[')
				ch = ']';
			else if (ch == '{')
				ch = '}';
			else if (ch == '<')
				ch = '>';
			rightCharArray[j] = ch;
		}
		rightCharArray[realSize] = '\0';
		return rightCharArray;
	}

	/**
	 * the current character, in[k], is the first one of a metaobject call
	 * or literal object. in[k] would be '1' in the examples below
	 *         @version<<+1+>>
	 *         ($$1  > 0  $$)
	 * This method returns the text between the current character (including it)
	 * and the sequence of symbols rightCharArray.
	 *
	 * rightCharArray should be terminated with '\0'
	 */
	private char []getTextTill(char[] rightCharArray) {
		/**
		 * an inefficient way of looking for rightCharArray, I know that
		 */
		  /*
		   * size of rightCharArray. The last character, '\0', does not count
		   */
		int size = rightCharArray.length - 1;
		  // this method should work even if rightCharArray does not end with '\0'
		if ( rightCharArray[size] != '\0' ) ++size;

		char ch = rightCharArray[0];
		int startText = k;
		int startLine = lineNumber;
		while (true) {
			if (in[k] == '\0') {
				error("Compile-Time Metaobject Call or literal object that started in line"
						+ startLine + " was not ended with "
						+ rightCharArray.toString() + " as expected");
				token = Token.EOF;
				symbol = new Symbol(token, "", startLine, lineNumber,
						getColumn());
				return null;
			} else {
				if (in[k] == ch) {
					int auxK = k;
					int j = 0;
					while (in[k] != '\0' && rightCharArray[j] != '\0'
							&& in[k] == rightCharArray[j]) {
						k++;
						j++;
					}
					// found rightCharArray in array "in" ?
					if (rightCharArray[j] == '\0') {
						// reached end of array rightString
						int sizeTextMetaobject = k - startText - size;
						char[] text = new char[sizeTextMetaobject+1];
						/*
						 * int m = 0; for (j = startText; j <= k - 2; j++) {
						 * text[m++] = in[j]; }
						 */
						j = startText;
						for (int m = 0; m < sizeTextMetaobject; m++) {
							text[m] = in[j];
							j++;
						}
						text[sizeTextMetaobject] = '\0';
						return text;
					} 
					else
						k = auxK + 1;

				} else {
					if (in[k] == '\n') {
						if (in[k] == '\r')
							k++;
						lineNumber++;
						startLine = k + 1;
					}
					k++;
				}
			}
		}

	}
}
