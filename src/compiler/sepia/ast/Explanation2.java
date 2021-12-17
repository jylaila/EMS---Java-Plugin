/**
 * 
 */
package compiler.sepia.ast;

/** Keeps the source code of examples
 * @author José
 *
 */
public class Explanation2 {

	public Explanation2(char []explanation2) {
		this.explanation2 = explanation2;
	}

	public char [] getExplanation() {
		return explanation2;
	}
	
	public String get()
	{
		
		return String.valueOf(explanation2).replace("\0", "");
	}

	public String gen() {

		StringBuilder builder = new StringBuilder();
		int indice = String.valueOf(explanation2).indexOf("-");
		int lenght = String.valueOf(explanation2).length();
		builder.append("cause " + String.valueOf(explanation2).substring(0, indice) + " {").append("\n");
		builder.append("explanation<<*{"  + String.valueOf(explanation2).substring(indice+1, lenght)+ "}*>>").append("\n");

		return builder.toString();
	}
	private char []explanation2;
}
