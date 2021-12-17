package ems.sepia.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import ems.management.ErrorList;
import ems.management.FileManagement;




public class sepiaManagement {

	FileManagement objDir = new FileManagement();
	ErrorList errorList = new ErrorList();

	public ArrayList<String> openFile(String ErrorName, String File)
			throws FileNotFoundException {

		ArrayList<String> content = new ArrayList<String>();
		String returnPath = errorList.returnPath(ErrorName);

		// Procura por arquivos dentro do diretório
		for (String path : objDir.FilePath(returnPath).list()) {
			if (path.startsWith(File) && path.endsWith(".sepia")) {
				try {
					content = objDir.ReadFile(objDir.FilePath(returnPath + "\\"
							+ path));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return content;
	}

	public void Delete(String ErrorName, String File) {

		String returnPath = errorList.returnPath(ErrorName);

		// Procura por arquivos dentro do diretório
		for (String path : objDir.FilePath(returnPath).list()) {

			if (path.startsWith(File) && path.endsWith(".sepia")) {
				objDir.DeleteFile(objDir.FilePath(returnPath + "\\" + path)
						.toString());

			}
		}
	}

	public String FindFolder (String ErrorName, String File)
			throws FileNotFoundException {

		File Folder = null;
		String returnPath = errorList.returnPath(ErrorName);

		// Procura por arquivos dentro do diretório
		for (String path : objDir.FilePath(returnPath).list()) {
			if (path.startsWith(File) && path.endsWith(".sepia")) {
				Folder = objDir.FilePath(returnPath + "\\"
						+ path);
			}
		}

		return Folder.toString();
	}
	
}
