/**
 * 
 */
package compiler.sepia.ast;
import java.util.ArrayList;


/**
 * This class represents a program in the DSL Eel
 * @author Janaina
 *
 */

public class Program {
	
	public Program(ArrayList<String> parametersarray, ArrayList<Explanation> explanation,  ArrayList<Cause> cause, ArrayList<Explanation2> explanation2, ArrayList<SourceCode> sourceCodearray){
		this.parametersarray = parametersarray;
		this.explanation = explanation;
		this.cause = cause;
		this.explanation2 = explanation2;
		this.sourceCodearray = sourceCodearray;
	}
	
	
	public void gen() {
		
		System.out.println("Parameters (" + parametersarray + ")");
		for (Explanation e : explanation) {
			e.gen();
		}
		
		for (Cause c : cause) {
			c.gen();
		}
				
		for (Explanation2 e2 : explanation2) {
			e2.gen();
		}
		
		for (SourceCode s : sourceCodearray ) {
			s.gen();
		}
		System.out.println("}");
	}

	public ArrayList<String> parametersarray;	
	private ArrayList<Explanation> explanation;
	private ArrayList<Cause> cause;
	private ArrayList<Explanation2> explanation2;
	private ArrayList<SourceCode> sourceCodearray;
}
