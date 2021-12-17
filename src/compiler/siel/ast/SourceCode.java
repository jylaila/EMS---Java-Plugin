/**
 * 
 */
package compiler.siel.ast;

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

	public void gen() {
		System.out.println();
		System.out.println("source<<*{");
		System.out.println(sourceCode);
		System.out.println("}*>>");		
	}
	private char []sourceCode;
}
