/**
 * 
 */
package compiler.siel.ast;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import compiler.siel.auxComp.SymbolTable;


/**
 * This class represents a program in the DSL SIEL
 * @author José
 *
 */
public class Program {

	/**
	 * 
	 */
	public Program(SymbolTable symbolTable) {
	
		this.table = symbolTable;
		
	}
	
	public void gen() {
		Enumeration e = table.keys();
		Object key;
		Object valor;
		
		while( e.hasMoreElements() ){
		 key = e.nextElement();
		 valor = table.getInGlobal(key);
		 System.out.println( key + " = " + valor );
		}		}
			

	 private SymbolTable table;
	 
}
