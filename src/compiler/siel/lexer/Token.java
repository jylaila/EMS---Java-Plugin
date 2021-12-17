package compiler.siel.lexer;

public enum Token {

	ASSIGN("="),
	CHARLITERAL("~charConst"),
	COLON(":"),
	COMMA(","),
	CTMOCALL("~CTMOCALL"),
	EOF("~EOF"),
	IDENT("~Ident"),
	IDENTCOLON("~Ident:"),
	INTLITERAL("~intConst"),
	LANGUAGE("language"),
	LEFTPAR("("),
	LEFTSB("["),
	LEFTCB("{"),
	LITERALSTRING("~literalString"),
	RIGHTPAR(")"),
	RIGHTCB("}"),
	RIGHTSB("]"),
	SEMICOLON(";");


	Token(String name) {
		this.name = name;
	}

	@Override public String toString() {
		return name;
	}


	private String name;



}

