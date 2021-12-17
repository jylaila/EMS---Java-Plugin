package ems.Siel.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ems.management.ErrorList;
import ems.management.FileManagement;





public class sielWriter {

	private String key, value;

	FileManagement objDir = new FileManagement();
	ErrorList errorList = new ErrorList();


	public String getkey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void Save(String ErrorName, sielWriter objSiel) {

		String returnPath = errorList.returnPath(ErrorName);

		objDir.setDirName(returnPath);
		objDir.setExtension("siel");
		objDir.setFileName("Parameters");
		objDir.setContent(objSiel.getkey());
		//objDir.setContent(objSiel.getkey() + ">>;");

		try {

			objDir.GenerateFileFolder(objDir);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void Update(String ErrorName, sielWriter objSiel) throws FileNotFoundException {

		ArrayList<String> content = null;

		String returnPath = errorList.returnPath(ErrorName);

		File path = objDir.FilePath(returnPath + "/Parameters.siel");

		try {
			content = objDir.ReadFile(path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < content.size(); i++) {
			String[] parameter = content.get(i).toString().split(" ");
			if (parameter[0].toString().equals(objSiel.getkey())) {
				content.set(i, objSiel.getkey());
			}
		}

		objDir.setDirName(returnPath);
		objDir.setExtension("siel");
		objDir.setFileName("Parameters");
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < content.size(); i++) {
			builder.append(content.get(i).toString() + "\n");
		}

		objDir.setContent(builder.toString() + ">>;");

		try {
			objDir.DeleteFile(path.toString());
			objDir.GenerateFileFolder(objDir);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void Delete(String ErrorName, String Parameter) throws FileNotFoundException {

		ArrayList<String> content = null;

		String returnPath = errorList.returnPath(ErrorName);

		File path = objDir.FilePath(returnPath + "/Parameters.siel");

		try {
			content = objDir.ReadFile(path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < content.size(); i++) {
			String[] parameter = content.get(i).toString().split(" ");
			if (parameter[0].toString().equals(Parameter)) {
				if (JOptionPane.showConfirmDialog(null,
						"Are you sure you want to delete " + Parameter,
						"Confirm", JOptionPane.YES_NO_OPTION) == 0) {
					content.remove(i);
				}

			}
		}

		objDir.setDirName(returnPath);
		objDir.setExtension("siel");
		objDir.setFileName("Parameters");
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < content.size(); i++) {
			if (content.get(i).toString() != null) {
				builder.append(content.get(i).toString() + "\n");
			}
		}
		objDir.setContent(builder.toString());

		try {

			objDir.DeleteFile(path.toString());
			objDir.GenerateFileFolder(objDir);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public boolean checkParameter(String ErrorName, sielWriter objSiel) {
		ArrayList<String> content = null;
		FileManagement objDir = new FileManagement();
		boolean value = true;

		String returnPath = errorList.returnPath(ErrorName);

		File path = objDir.FilePath(returnPath + "/Parameters.siel");

		if (path.exists()) {
			try {
				content = objDir.ReadFile(path);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			for (int i = 0; i < content.size(); i++) {
				String[] parameter = content.get(i).toString().split(" ");
				if (parameter[0].toString().equals(objSiel.getkey())) {

					JOptionPane.showMessageDialog(null,
							"This parameter already exists.", "Warning",
							JOptionPane.WARNING_MESSAGE);

					value = false;
				}
			}
		}
		return value;
	}

}
