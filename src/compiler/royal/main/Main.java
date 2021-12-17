package compiler.royal.main;

//Texto Simples com a explicação do erro

import java.awt.Component;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

import ems.royal.model.royalWriter;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String args, String errorName, String FileName) {
		// TODO Auto-generated method stub
		if ( args.length() < 1 ) {
			System.out.println("filename should be the parameter");
			System.exit(1);
		}

		String filename = args;
		
		String ErrorName = errorName;
		
		MyFile myFile = new MyFile(filename);

		char []input = myFile.readFile();
		if ( input == null ) {
	
			System.out.println("Error reading file '" + filename + "'");
		}
		else {
			Compiler compiler = new Compiler();
			royalWriter p = compiler.compile(ErrorName, input,  new PrintWriter(System.out, true), FileName);
			p.set();
		}
		
	}

}
