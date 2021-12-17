package ems.management;

//@author Janaina

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class FileManagement {

	String dirName, content, extension, fileName;

	// cria estrutura de pasta e o arquivo
	public void GenerateFileFolder(FileManagement objDir) throws IOException {

		File diretorio = FilePath(objDir.getDirName());

		if (!diretorio.exists()) {

			diretorio.mkdirs();
		}

		File filename = new File(diretorio + "/" + objDir.getFileName() + "." + objDir.getExtension());

		FileWriter x = new FileWriter(filename, true);
		content += "\n";
		x.write(content);
		x.close();

	}

	// Lista pastas do caminho fornecido
	public static String[] listDirectory(File dir) {
		String[] filhos = null;
		System.out.println(dir);
		if (dir != null) {
			if (dir.isDirectory()) {
				filhos = dir.list();
			}
		}
		return filhos;
	}

	// Lê o conteúdo do arquivo e carrega em um array
	public ArrayList<String> ReadFile(File arq) throws FileNotFoundException {

		ArrayList<String> linhas = new ArrayList<String>();
		StringBuilder builder = new StringBuilder();
		Scanner ler = new Scanner(arq);

		while (ler.hasNext()) {

			builder.append(ler.nextLine()).append("\n");
		}

		// String[] line = builder.toString().split(">>;");
		String[] line = builder.toString().split(",");

		for (int i = 0; i < line.length; i++)
			linhas.add(line[i]);

		ler.close();

		return linhas;
	}

	// Lê o conteúdo do arquivo e carrega em um array
	public ArrayList<String> ReadParameters(File arq) throws FileNotFoundException {

		ArrayList<String> linhas = new ArrayList<String>();
		StringBuilder builder = new StringBuilder();
		Scanner ler = new Scanner(arq);

		while (ler.hasNext()) {

			linhas.add(ler.nextLine());
		}

		return linhas;
	}

	// Deleta arquivo de determinada pasta
	public void DeleteFile(String filename) {
		File f = new File(filename);
		f.delete();
	}

	// Lista caminho a partir do nome do erro
	public File FilePath(String folderName) {

		ArrayList<String> directory = null;
		try {
		
			directory = ReadFile(new File(System.getProperty("user.home") + "/data/Configuration.conf"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String local = directory.get(0);
		local = local.replace("\n", "");

		return new File(local + folderName);
	}

	// Lista caminho padrão pastas erro
	public File DefaultPath() {
		ArrayList<String> directory = null;
		String local = null;
		try {
			directory = ReadFile(new File(System.getProperty("user.home") + "/data/Configuration.conf"));

			local = directory.get(0);
			local = local.replace("\n", "");
			return new File(local);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(directory);
		}
		return null;
	}

	// verifica se arquivo existe
	public Boolean checkFile(File fileName) {
		Boolean check = false;

		if (fileName.exists()) {
			check = true;
		}

		return check;
	}

	public String genFileName() {

		UUID uuid = UUID.randomUUID();
		String myRandom = uuid.toString();
		return myRandom.substring(0, 5);
	}

	public String getDirName() {
		return dirName;
	}

	public void setDirName(String dirName) {
		this.dirName = dirName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
