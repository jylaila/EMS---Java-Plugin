package compiler.sepia.main;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import compiler.sepia.ast.Program;
import ems.sepia.model.sepiaWriter;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String args, String errorName, String FileName)throws JAXBException, IOException  {
		// TODO Auto-generated method stub
		
				
		if ( args.length() < 1 ) {
			System.out.println("filename should be the parameter");
			System.exit(1);
		}

		String filename = args;
		
		MyFile myFile = new MyFile(filename);

		char []input = myFile.readFile();
		if ( input == null ) {
			System.out.println("Error reading file '" + filename + "'");
		}
		else {
								
			Compiler compiler = new Compiler();
			sepiaWriter p = compiler.compile(errorName, input,  new PrintWriter(System.out, true), FileName);
			p.set();
				
		}
		
		
	}

}
