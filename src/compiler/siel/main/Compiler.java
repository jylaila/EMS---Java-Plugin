package compiler.siel.main;

// This class is the compiler for the language Siel

//Program ::=  Language Description Text { Source }
//Language ::= "language" ":" Str "," Str
//Description ::= "errorDescription" ":" Str
// VER-->CTMOALL TEXTO DELIMITADO

//{Id e Str} são terminais, sendo {Id} um identificador composto por letras e {Str} uma string delimitada por aspas. 
//{DelimitedText} é um terminal composto por uma cadeia de símbolos {s}, seguido de quaisquer caracteres e terminado pelo reverso de {s}. Seguimos as convenções de Cyan para a delimitação de metaobjetos. 


import java.io.*;
import java.util.ArrayList;

import compiler.siel.ast.*;
import compiler.siel.auxComp.*;
import compiler.siel.lexer.*;


public class Compiler {

	
	private SymbolTable symbolTable;
	private Lexer lexer;
	private CompilerError error;

	// compile must receive an input with an character less than
	// p_input.lenght
	public Program compile(char[] input, PrintWriter outError) {

		symbolTable = new SymbolTable();
	    error = new CompilerError(new PrintWriter(outError));
		lexer = new Lexer(input);

		Program p = null;
		try {
			p = program();
		} catch (Exception e) {
			// the below statement prints the stack of called methods.
			// of course, it should be removed if the compiler were
			// a production compiler.
			e.printStackTrace();
		}
	
		return p;
	}

	
	private Program program() {

		while (lexer.token == Token.IDENT)
		{
		// verifico o id do parâmetro
		if (lexer.token != Token.IDENT)	error.signal("id expected");
		String id = (String) lexer.symbol.getSymbolString();
		lexer.nextToken();

		// verifico se o parâmetro já foi declarado
		if (symbolTable.getInGlobal(id) != null)error.signal("Parameter " + id + " already exists");
	
		if (lexer.token != Token.ASSIGN) error.signal("'=' esperado");
		lexer.nextToken();

		if (lexer.token != Token.LITERALSTRING)	error.signal("string expected");
		String string1 = (String) lexer.symbol.getSymbolString();
		
		lexer.nextToken();

		//insiro o parâmetro na tbl de simbolos
		symbolTable.putInGlobal(id, string1);
		
		//System.out.println(id + " = " + string1);
		
		}		
	
		if ( lexer.token != Token.EOF )
			error.signal("End of file expected");

		return new Program (symbolTable); 

	}

}
