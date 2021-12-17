package compiler.royal.auxComp;

import java.io.*;

import javax.swing.JOptionPane;

import compiler.royal.lexer.*;


public class CompilerError {

    public CompilerError( PrintWriter out ) {
          // output of an error is done in out
        this.out = out;
        thereWasAnError = false;
    }

    public void setLexer( Lexer lexer ) {
        this.lexer = lexer;
    }

    public boolean wasAnErrorSignalled() {
        return thereWasAnError;
    }

    public void show( String strMessage ) {
    	JOptionPane.showMessageDialog(null, strMessage +"." + "\n Please verify the file and try again", "There´s an error while compiling the file.", JOptionPane.ERROR_MESSAGE);    

        show( strMessage, false );
    }

    public void show( String strMessage, boolean goPreviousToken ) {
        // is goPreviousToken is true, the error is signalled at the line of the
        // previous token, not the last one.
        if ( goPreviousToken ) {
          out.println(" error at line " + lexer.getLineNumberBeforeLastToken() + ": ");
          out.println( lexer.getLineBeforeLastToken() );
        }
        else {
          out.println(" error at line " + lexer.getLineNumber() + ": ");
          out.println(lexer.getCurrentLine());
        }

        out.println( strMessage );
        out.flush();
        if ( out.checkError() )
          System.out.println("error in signaling an error");
        thereWasAnError = true;
    }


	public void showErrorFile(String string) {
		
		out.println(string);
		
	}



    public void signal( String strMessage ) {
        show( strMessage );
        out.flush();
        thereWasAnError = true;
        throw new RuntimeException();
    }

    static public void crash(String message) {
    	System.out.println(message);
    	System.exit(1);
    }
    
    private Lexer lexer;
    private PrintWriter out;
    private boolean thereWasAnError;
}

