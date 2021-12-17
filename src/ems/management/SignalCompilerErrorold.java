package ems.management;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import ems.Siel.model.*;
import ems.royal.model.*;
import ems.sepia.model.*;



public class SignalCompilerErrorold {

	static sielManagement objsiel = new sielManagement();
	static royalManagement objexl = new royalManagement();
	static sepiaManagement objeel = new sepiaManagement();
	static FileManagement objDir = new FileManagement();
	static ErrorList errorList = new ErrorList();

	public static void SignalCompilerError(String... info) {
		Scanner s = new Scanner(System.in);

		int start = info[0].indexOf("'") + 1;
		String errorName = info[0].substring(start, info[0].length() - 1);

		if (checkParameters(errorName, info)) {

			loadEel(errorName, info);
			System.out.println("Press enter if you need more help");
			if (s.nextLine() != null) {
				
				loadExexl(errorName);

			}
		}
	}

	public static boolean checkParameters(String errorName, String[] parameters) {
		int cont = 0;

		ArrayList<String> content = null;

		try {
			content = objsiel.openFile(errorName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int x = 0; x < parameters.length; x++) {

			String[] variable = parameters[x].toString().split("=");
			String aux = variable[0].toString().toLowerCase().trim();

			for (int i = 0; i < content.size(); i++) {

				String Parameter = content.get(i);
				Parameter = Parameter.replace("/n", "").trim().toLowerCase();

				if (aux.equals(Parameter)) {
					cont = cont + 1;
					break;
				}

			}
		}

		// verifico se algum parametro não existe
		if (cont == parameters.length - 1)
			return true;
		else
			return false;

	}

	public static void loadExexl(String errorName) {
		// Lista nome dos erros referente as pastas

		String folderName = null;
		String[] fileName;
		ArrayList<String> content;
		folderName = errorList.returnPath(errorName);

		// Procura os arquivos dentro do diretório
		for (String file : objDir.FilePath(folderName).list()) {

			if (file.endsWith(".exexl")) {

				fileName = file.split("_");
				try {
					content = objexl
							.openFile(errorName, fileName[0].toString());
					if (content.get(0).contains("true")) {

						for (int i = 1; i < content.size(); i++) {

							System.out.println(content.get(i));
						}
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

	}

	public static void loadEel(String errorName, String[] parameters) {
		// Lista nome dos erros referente as pastas

		String folderName, aux, auxValue = null;
		String[] variable = null;
		ArrayList<String> content = null;
		folderName = errorList.returnPath(errorName);

		// Procura os arquivos dentro do diretório
		for (String file : objDir.FilePath(folderName).list()) {

			if (file.endsWith(".eel")) {

				try {

					content = objeel.openFile(errorName, file.toString());
					if (content.get(0).contains("true")) {

						for (int x = 0; x < parameters.length; x++) {

							variable = parameters[x].toString().split("=");
							aux = variable[0].toString().toLowerCase().trim();
							

							auxValue = parameters[x].toString().substring(
									parameters[x].indexOf("'") + 1,
									parameters[x].length());
							auxValue = auxValue.replace("'", "");

							for (int i = 0; i < content.size(); i++) {

								String Parameter = content.get(i);
								Parameter = Parameter.replace("/n", "").trim()
										.toLowerCase();

								if (Parameter.contains("#" + aux)) {
									String newvalue = content.get(i)
											.toLowerCase();

									newvalue = newvalue.replace("#" + aux,
											auxValue);
									content.set(i, newvalue);

								}

							}
						}
						
						for (int i = 1; i < content.size(); i++) {
							System.out.println(content.get(i));
						}
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

	}
}
