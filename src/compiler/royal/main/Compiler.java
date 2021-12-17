package compiler.royal.main;

// This class is the compiler for the language ExExl

//Program ::=  Language Description Text { Source }
//Language ::= "language" ":" Str "," Str
//Description ::= "errorDescription" ":" Str
// VER-->CTMOALL TEXTO DELIMITADO

//{Id e Str} são terminais, sendo {Id} um identificador composto por letras e {Str} uma string delimitada por aspas. 
//{DelimitedText} é um terminal composto por uma cadeia de símbolos {s}, seguido de quaisquer caracteres e terminado pelo reverso de {s}. Seguimos as convenções de Cyan para a delimitação de metaobjetos. 

import java.io.*;
import java.util.ArrayList;

import compiler.royal.ast.SourceCode;
import compiler.royal.auxComp.*;
import compiler.royal.lexer.*;
import ems.royal.model.royalWriter;

public class Compiler {

	private SymbolTable symbolTable;
	private Lexer lexer;
	private CompilerError error;
	static String errorName;
	private static String fileName;

	// compile must receive an input with an character less than
	// p_input.lenght
	public royalWriter compile(String errorName, char[] input,
			PrintWriter outError, String FileName) {

		symbolTable = new SymbolTable();
		error = new CompilerError(new PrintWriter(outError));
		lexer = new Lexer(input);
		Compiler.errorName = errorName;
		Compiler.fileName = FileName;
		// error.setLexer(lexer);

		royalWriter p = null;
		try {
			p = program();
		} catch (Exception e) {
			// the below statement prints the stack of called methods.
			// of course, it should be removed if the compiler were
			// a production compiler.
			e.printStackTrace();
		}
		/*
		 * if (error.wasAnErrorSignalled()) return null; else return p;
		 */
		return p;
	}

	private royalWriter program() {

		if (lexer.token != Token.USE)
			error.signal("'Use' expected");
		lexer.nextToken();

		if (lexer.token != Token.COLON)
			error.signal("':' expected");
		lexer.nextToken();

		if (lexer.token != Token.IDENT)
			error.signal("'True or False' expected");
		Boolean use = new Boolean(lexer.symbol.getSymbolString());
		lexer.nextToken();

		if (lexer.token != Token.LANGUAGE)
			error.signal("'language' expected");
		lexer.nextToken();

		if (lexer.token != Token.COLON)
			error.signal("':' expected");
		lexer.nextToken();

		if (lexer.token != Token.LITERALSTRING)
			error.signal("string expected");
		String language = lexer.symbol.getSymbolString();
		lexer.nextToken();

		if (lexer.token != Token.COMMA)
			error.signal("',' expected");
		lexer.nextToken();

		if (lexer.token != Token.LITERALSTRING)
			error.signal("string expected");
		String languageInEnglish = lexer.symbol.getSymbolString();
		lexer.nextToken();

		if (lexer.token != Token.CTMOCALL)
			error.signal("'errorDescription' expected");
		SymbolCTMOCall ctmoCall = (SymbolCTMOCall) lexer.symbol;
		char[] errorDescription = ctmoCall.getText();

		if (ctmoCall.getSymbolString().compareTo("errorDescription") != 0)
			error.signal("'errorDescription' expected");
		lexer.nextToken();

		if (lexer.token != Token.CTMOCALL)
			error.signal("'text' expected");
		ctmoCall = (SymbolCTMOCall) lexer.symbol;
		char[] text = ctmoCall.getText();

		if (ctmoCall.getSymbolString().compareTo("text") != 0)
			error.signal("'text' expected");
		lexer.nextToken();

		ArrayList<SourceCode> sourceCodeArray = new ArrayList<SourceCode>();

		while (lexer.token == Token.CTMOCALL) {
			ctmoCall = (SymbolCTMOCall) lexer.symbol;

			if (ctmoCall.getSymbolString().compareTo("source") != 0)
				error.signal("'source' expected");
			sourceCodeArray.add(new SourceCode(ctmoCall.getText()));
			lexer.nextToken();
		}

		if (lexer.token != Token.EOF)
			error.signal("End of file expected");

		return new royalWriter(use, errorName, fileName, language,
				languageInEnglish, errorDescription, text, (sourceCodeArray));

	}

}
