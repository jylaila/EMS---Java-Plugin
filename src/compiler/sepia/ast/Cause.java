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
public class Cause {

	public Cause(char[] cause) {
		this.cause = cause;
	}

	public char[] getCause() {
		return cause;
	}

	public String get() {
		return String.valueOf(cause).replace("\0", "");
	}

	public String gen() {
		
		StringBuilder builder = new StringBuilder();
		builder.append("cause " + String.valueOf(cause)+ " {").append("\n");

		return builder.toString();
	}

	private char[] cause;
}
