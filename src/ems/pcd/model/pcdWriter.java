package ems.pcd.model;

//@author Janaina

import java.io.IOException;
import ems.management.ErrorList;
import ems.management.FileManagement;

public class pcdWriter {

	FileManagement objDir = new FileManagement();
	ErrorList errorList = new ErrorList();

	public pcdWriter() {

	}

	public void Save(String ErrorName, String Code) {

		StringBuilder builder = new StringBuilder();

		builder.append(Code + "\n");

		String returnPath = errorList.returnPath(ErrorName);

		objDir.setDirName(returnPath);
		objDir.setExtension("pcd");
		objDir.setFileName("previousCodes");
		objDir.setContent(builder.toString());

		try {

			objDir.GenerateFileFolder(objDir);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
