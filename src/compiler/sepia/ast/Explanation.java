/**
 * 
 */
package compiler.sepia.ast;

/**
 * Keeps the source code of examples
 * 
 * @author José
 * 
 */
public class Explanation {

	public Explanation(char[] explanation) {
		this.explanation = explanation;
	}

	public char[] getExplanation() {
		return explanation;
	}

	public String get() {
		
		return String.valueOf(explanation).replace("\0", "");
	}

	
	public String gen() {

		StringBuilder builder = new StringBuilder();
		builder.append("explanation<<*{"  + String.valueOf(explanation)+ "}*>>").append("\n");

		return builder.toString();
	}
	
	private char[] explanation;
}
