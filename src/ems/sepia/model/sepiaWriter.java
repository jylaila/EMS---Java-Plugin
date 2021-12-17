package ems.sepia.model;

//@author Janaina

import java.io.IOException;
import java.util.ArrayList;



import compiler.sepia.ast.Cause;
import compiler.sepia.ast.Explanation;
import compiler.sepia.ast.Explanation2;
import compiler.sepia.ast.SourceCode;
import ems.management.ErrorList;
import ems.management.FileManagement;
import ems.management.frmEMS_new;


public class sepiaWriter {

	FileManagement objDir = new FileManagement();
	ErrorList errorList = new ErrorList();

	public sepiaWriter(Boolean Use, String errorName, String FileName,
			ArrayList<String> parametersarray,
			ArrayList<Explanation> explanation, ArrayList<Cause> cause,
			ArrayList<Explanation2> explanationArray2,
			ArrayList<SourceCode> sourceCodearray) {
	
		this.useThis = Use;
		this.parametersArray = parametersarray;
		this.explanationArray = explanation;
		this.causeArray = cause;
		this.explanationArray2 = explanationArray2;
		this.sourceCodeArray = sourceCodearray;
		this.errorName = errorName;
		this.fileName = FileName;
			}

	public sepiaWriter() {
	}

	public void set() {
		frmEMS_new frEdit = new frmEMS_new();
	
		frEdit.setValuesSepia(errorName, useThis, fileName, sourceCodeArray, causeArray, explanationArray, explanationArray2);

	}

	public void Save2(String ErrorName, sepiaWriter objsepia) {

		String Source, Explanation, Cause, Explanation2 = null;
		String Parameters = null;
		StringBuilder builder = new StringBuilder();

		builder.append("Use: " + objsepia.useThis.toString()).append("\n");
		builder.append("parameters(");

		for (String t : objsepia.parametersArray) {

			if (Parameters != null)
				Parameters = Parameters + t + ", ";

			else
				Parameters = t + ", ";

		}
		if (Parameters != null) {
			Parameters = Parameters.substring(0, Parameters.length() - 5);
			Parameters = Parameters.replace("\n", "");
		}

		builder.append(Parameters).append("\n");
		builder.append(")").append("\n");

		for (Explanation e : objsepia.explanationArray) {
			Explanation = String.valueOf(e.gen().toString());
			builder.append(Explanation);
		}

		//builder.append("cause " + objsepia.causeLabel + " {").append("\n");
		//
		// for (Cause c : objsepia.causeArray) {
		// Cause = c.gen().toString();
		// builder.append(Cause);
		// }
		//
		for (Explanation2 s : objsepia.explanationArray2) {
			Explanation2 = s.gen().toString();
			builder.append(Explanation2);
		}

		for (SourceCode s : objsepia.sourceCodeArray) {
			Source = s.gen().toString();
			builder.append(Source);
		}

		builder.append("}").append("\n");

		String returnPath = errorList.returnPath(ErrorName);

		objDir.setDirName(returnPath);
		objDir.setExtension("sepia");

		objDir.setFileName(objsepia.fileName + "_" + objDir.genFileName());

		objDir.setContent(builder.toString());

		try {

			objDir.GenerateFileFolder(objDir);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	ArrayList<Explanation> explanationArray;
	private String errorName, causeLabel, fileName;
	ArrayList<Explanation2> explanationArray2;
	private Boolean useThis;
	private ArrayList<SourceCode> sourceCodeArray;
	private ArrayList<String> parametersArray;
	ArrayList<Cause> causeArray;

	public ArrayList<Explanation2> getExplanation2() {
		return explanationArray2;
	}

	public void setExplanation2(ArrayList<Explanation2> explanationArray2) {
		this.explanationArray2 = explanationArray2;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public ArrayList<String> getParametersArray() {
		return parametersArray;
	}

	public void setParametersArray(ArrayList<String> parametersArray) {
		this.parametersArray = parametersArray;
	}

	public String getCauseLabel() {
		return causeLabel;
	}

	public void setCauseLabel(String causeLabel) {
		this.causeLabel = causeLabel;
	}

	public Boolean getUseThis() {
		return useThis;
	}

	public void setUseThis(Boolean useThis) {
		this.useThis = useThis;
	}

	public ArrayList<Cause> getCause() {
		return causeArray;
	}

	public void setCause(ArrayList<Cause> cause) {
		this.causeArray = cause;
	}

	public ArrayList<Explanation> getText() {
		return explanationArray;
	}

	public void setText(ArrayList<Explanation> text) {
		this.explanationArray = text;
	}

	public ArrayList<SourceCode> getSourceCodeArray() {
		return sourceCodeArray;
	}

	public void setSourceCodeArray(ArrayList<SourceCode> sourceCodeArray) {
		this.sourceCodeArray = sourceCodeArray;
	}

}
