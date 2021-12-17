package ems.Siel.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import ems.management.ErrorList;
import ems.management.FileManagement;





public class sielManagement {

	FileManagement objDir = new FileManagement();
	ErrorList errorList = new ErrorList();

	public ArrayList<String> openFile(String ErrorName)
			throws FileNotFoundException {

		ArrayList<String> content = new ArrayList<String>();
		String foldername = errorList.returnPath(ErrorName);
		if (foldername == null)
		{
		foldername = ErrorName.replace(" ", "_");	
		}
		File path = objDir.FilePath(foldername + "/Parameters.siel");
		
		if (path.exists()) {
			// abre o arquivo
			try {
				content = objDir.ReadFile(path);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return content;
	}
	
	public void deleteError(String ErrorName) {

				String returnPath = errorList.returnPath(ErrorName);

				File path = objDir.FilePath(returnPath + "/Parameters.siel");

				objDir.DeleteFile(path.toString());
	
	}

	

}
