package ems.royal.model;

//@author Janaina

import java.io.IOException;
import java.util.ArrayList;

import compiler.royal.ast.Explanation;
import compiler.royal.ast.SourceCode;

import ems.management.ErrorList;
import ems.management.FileManagement;
import ems.management.frmEMS_new;
import ems.royal.model.royalWriter;

public class royalWriter {

	FileManagement objDir = new FileManagement();
	ErrorList errorList = new ErrorList();

	public royalWriter(Boolean use, String errorName, String FileName,
			String language, String languageInEnglish,
			char[] errorDescription2, char[] text2,
			ArrayList<SourceCode> sourceCodeArray2) {
		this.FileName = FileName;
		this.useThis = use;
		this.language = language;
		this.languageInEnglish = languageInEnglish;
		this.errorDescription = String.valueOf(errorDescription2);
		this.text = String.valueOf(text2);
		this.sourceCodeArray = sourceCodeArray2;
		this.errorName = errorName;

	}

	public void set() {

		frmEMS_new frEdit = new frmEMS_new();

		frEdit.setValuesRoyal(errorName, language, languageInEnglish,
				errorDescription, text, FileName, useThis, sourceCodeArray);

	}

	public royalWriter() {
	}

	public void Save(String ErrorName, royalWriter objExl) {

		String Source = null;
		String Explanation = null;
		StringBuilder builder = new StringBuilder();

		builder.append("Use: " + objExl.useThis.toString()).append("\n");
		builder.append(
				"language: \"" + objExl.language + "\", " + "\""
						+ objExl.languageInEnglish + "\"").append("\n");
		builder.append("errorDescription<<< " + objExl.errorDescription + ">>>")
				.append("\n");

		for (Explanation x : objExl.explanationArray) {
			Explanation = x.gen();
			builder.append(Explanation);
		}

		// builder.append("text<<<* " + objExl.text + "*>>>").append("\n");

		for (SourceCode s : objExl.sourceCodeArray) {
			Source = s.gen();
			builder.append(Source);
		}

		String returnPath = errorList.returnPath(ErrorName);

		objDir.setDirName(returnPath);
		objDir.setExtension("royal");
		objDir.setFileName(objExl.FileName + "_" + objDir.genFileName());
		objDir.setContent(builder.toString());

		try {

			objDir.GenerateFileFolder(objDir);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	private String errorName, language, languageInEnglish, errorDescription,
			FileName, text;

	public String getFileName() {
		return FileName;
	}

	public void setFileName(String fileName) {
		FileName = fileName;
	}

	private Boolean useThis;

	public Boolean getUseThis() {
		return useThis;
	}

	public void setUseThis(Boolean useThis) {
		this.useThis = useThis;
	}

	private ArrayList<SourceCode> sourceCodeArray;
	private ArrayList<Explanation> explanationArray;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLanguageInEnglish() {
		return languageInEnglish;
	}

	public void setLanguageInEnglish(String languageInEnglish) {
		this.languageInEnglish = languageInEnglish;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public ArrayList<SourceCode> getSourceCodeArray() {
		return sourceCodeArray;
	}

	public void setSourceCodeArray(ArrayList<SourceCode> sourceCodeArray2) {
		this.sourceCodeArray = sourceCodeArray2;
	}

	public void setExplanationArray(ArrayList<Explanation> explanationArray) {
		this.explanationArray = explanationArray;
	}

	public ArrayList<Explanation> getExplanationArray() {
		return explanationArray;
	}

}
