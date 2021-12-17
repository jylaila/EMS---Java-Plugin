/**
 * 
 */
package compiler.sepia.ast;

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

	public String gen() {

		StringBuilder builder = new StringBuilder();
		builder.append("source<** " + String.valueOf(sourceCode)+ " **>").append("\n");

		return builder.toString();
	}
	
	public String get() {
		
		return String.valueOf(sourceCode).replace("\0", "");
	}
	
	private char []sourceCode;
	

}
