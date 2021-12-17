/**
 * 
 */
package compiler.royal.ast;

/** Keeps the source code of examples
 * @author José
 *
 */
public class SourceCode {

	public SourceCode(char []sourceCode) {
		this.sourceCode = sourceCode;
	}

	public char [] getSourceCode() {
		return sourceCode;
	}
	
	public String get()
	{

		return String.valueOf(sourceCode).replace("\0", "");
	}

	public String gen() {
		StringBuilder builder = new StringBuilder();
		builder.append("source<** " + String.valueOf(sourceCode) + "**>")
				.append("\n");

		return builder.toString();	
	}
	private char []sourceCode;
}
