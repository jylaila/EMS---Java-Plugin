package ems.management;

import java.util.ArrayList;
import java.util.Arrays;

//import org.eclipse.core.resources.IMarker;
//import org.eclipse.core.resources.IResource;
//import org.eclipse.core.runtime.CoreException;

public class CyanCompilerFake {

	
	/**
	 * @param args
	 */
//	
//	public static void main (String[] args)
//	{
	public static void main() {
	
		char[] errorName = "variable was not declared".toCharArray();
		ArrayList<String> array = new ArrayList<String> (Arrays.asList (new String [] { "identifier = x", 
				"statementText = self.n = x", "methodSignature = set:", "prototypeName = Box", "supertype = null", "implementedInterfaces = null", 
				"visibleLocalVariableList = null", "instanceVariableList = n", "methodList = set:, get:"}));
		
		SignalCompilerError.signalCompilerError(errorName, "src\\main\\Program1.java", 22,
				5,array);

//	}
	}
}


