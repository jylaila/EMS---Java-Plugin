package compiler.siel.main;

//Parâmetros específicos para o erro

import java.io.PrintWriter;

import compiler.siel.ast.*;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if ( args.length < 1 ) {
			System.out.println("filename should be the parameter");
			System.exit(1);
		}

		String filename = args[0];
 		MyFile myFile = new MyFile(filename);

		char []input = myFile.readFile();
		if ( input == null ) {
			System.out.println("Error reading file '" + filename + "'");
		}
		else {
			Compiler compiler = new Compiler();
			Program p = compiler.compile(input,  new PrintWriter(System.out, true));
			p.gen();
		}
		
	}

}
