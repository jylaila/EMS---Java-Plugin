package compiler.sepia.main;

// This class is the compiler for the language Eel

//Program ::=  Parameters Explanation { Cause } 
//Parameters ::= "parameters" "("  { "," str } ")"
//Explanation ::= "explanation" TEXT
//Cause ::= "cause" Id "{" CauseExplanation { source } "} 
//CauseExplanation :: = "explanation" TEXT 
//Source :: = "source" Id TEXT

//{Id e Str} são terminais, sendo {Id} um identificador composto por letras e {Str} uma string delimitada por aspas. 
//{DelimitedText} é um terminal composto por uma cadeia de símbolos {s}, seguido de quaisquer caracteres e terminado pelo reverso de {s}. Seguimos as convenções de Cyan para a delimitação de metaobjetos. 

import java.io.*;
import java.util.ArrayList;



import compiler.sepia.ast.*;
import compiler.sepia.auxComp.*;
import compiler.sepia.lexer.*;
import ems.management.frmEMS_new;
import ems.sepia.model.sepiaWriter;

public class Compiler {

	private Lexer lexer;
	private CompilerError error;
	static String errorName;
	static String fileName;

	public sepiaWriter compile(String errorName, char[] input,
			PrintWriter outError, String FileName) {

		new SymbolTable();
		error = new CompilerError(new PrintWriter(outError));
		lexer = new Lexer(input);
		Compiler.errorName = errorName;
		Compiler.fileName = FileName;
		sepiaWriter p = null;

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

	public sepiaWriter program() {

		if (lexer.token != Token.USE)
			error.signal("'use' expected");
		lexer.nextToken();

		if (lexer.token != Token.COLON)
			error.signal("':' expected");
		lexer.nextToken();

		if (lexer.token != Token.IDENT)
			error.signal("'true or false' expected");
		use = new Boolean(lexer.symbol.getSymbolString());
		lexer.nextToken();

		if (lexer.token != Token.PARAMETERS)
			error.signal("'Parameters' expected");
		lexer.nextToken();

		if (lexer.token != Token.LEFTPAR)
			error.signal(" '(' expected");
		lexer.nextToken();

		while (lexer.token == Token.IDENT) {
			if (lexer.token != Token.IDENT)
				error.signal("Parameters expected");
			parametersarray.add(lexer.symbol.getSymbolString());
			lexer.nextToken();

			if (lexer.token != Token.RIGHTPAR) {
				if (lexer.token != Token.COMMA)
					error.signal(" ',' expected");
				lexer.nextToken();
			} else
				break;

		}

		lexer.nextToken();

		if (lexer.token != Token.CTMOCALL)
			error.signal("'explanation' expected");
		SymbolCTMOCall ctmoCall = (SymbolCTMOCall) lexer.symbol;

		if (ctmoCall.getSymbolString().compareTo("explanation") != 0)
			error.signal("'explanation' expected");
		explanation.add(new Explanation(ctmoCall.getText()));
		lexer.nextToken();

		while (lexer.token == Token.CAUSE) {
			if (lexer.token != Token.CAUSE)
				error.signal("'cause' expected");
			lexer.nextToken();

			if (lexer.token != Token.IDENT)
				error.signal("'indentifier' expected");
			cause.add(new Cause(lexer.symbol.getSymbolString().toCharArray()));
			String concat = String.valueOf( lexer.symbol.getSymbolString().toCharArray()) ;
			lexer.nextToken();

			if (lexer.token != Token.LEFTCB)
				error.signal("'{' expected");
			lexer.nextToken();

			if (lexer.token != Token.CTMOCALL)
				error.signal("'explanation' expected");
			ctmoCall = (SymbolCTMOCall) lexer.symbol;

			if (ctmoCall.getSymbolString().compareTo("explanation") != 0)
				error.signal("'explanation' expected");
			concat = concat + "-" + String.valueOf(ctmoCall.getText());
			explanation2.add(new Explanation2(concat.toCharArray()));
			lexer.nextToken();

			while (lexer.token == Token.CTMOCALL) {
				ctmoCall = (SymbolCTMOCall) lexer.symbol;
				if (ctmoCall.getSymbolString().compareTo("source") != 0)
					error.signal("'source' expected");
				sourceCodeArray.add(new SourceCode(ctmoCall.getText()));
				lexer.nextToken();
			}

		}

		if (lexer.token != Token.RIGHTCB)
			error.signal("'}' expected");

		return new sepiaWriter(use, errorName, fileName, parametersarray,
				explanation, cause, explanation2, sourceCodeArray);	

	}

	boolean use;
	ArrayList<String> parametersarray = new ArrayList<String>();
	ArrayList<Explanation> explanation = new ArrayList<Explanation>();
	ArrayList<Cause> cause = new ArrayList<Cause>();
	ArrayList<SourceCode> sourceCodeArray = new ArrayList<SourceCode>();
	ArrayList<Explanation2> explanation2 = new ArrayList<Explanation2>();

}
