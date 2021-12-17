package ems.management;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.text.BadLocationException;

import org.eclipse.core.filebuffers.FileBuffers;
import org.eclipse.core.filebuffers.ITextFileBuffer;
import org.eclipse.core.filebuffers.ITextFileBufferManager;
import org.eclipse.core.filebuffers.LocationKind;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.texteditor.MarkerUtilities;

import ems.Siel.model.sielManagement;
import ems.royal.model.royalManagement;
import ems.sepia.model.sepiaManagement;
import ufscar.ems.actions.quickfix.MarkerResolutionGenerator;

public class SignalCompilerError {

	static sielManagement objsiel = new sielManagement();

	static String errorName;
	static String compilationText;
	static ArrayList<String> siel = new ArrayList<String>();
	static int cont = 0;
	static String file1;

	static compiler.siel.main.Main main = new compiler.siel.main.Main();

	/**
	 * Método para exibição das mensagens personalizadas *
	 * 
	 * @param compilationUnitText
	 *            - Todo o texto do arquivo de erro
	 * @param file
	 *            - Recebe o nome do arquivo que receberá as marcações
	 * @param line
	 *            - Linha que ocorreu o erro
	 * @param column
	 *            - Coluna que ocorreu o erro
	 * @param sielCode
	 *            - Código em Siel, parâmetros necessários para exibição da
	 *            mensagem
	 */

	public static void signalCompilerError(char[] compilationUnitText, String file, int line, int column,
			ArrayList<String> sielCode) {

		int start = column;
		// errorName = sielCode.get(0).substring(start,
		// sielCode.get(0).length() - 1);
		errorName = "variable was not declared";

		// armazeno o código com erro do usuário
		compilationText = new String(compilationUnitText);

		// armazeno o código em Siel
		siel = sielCode;

		// armezeno o projeto que está sendo usado
		IProject[] prj = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		IResource resource = null;

		for (int i = 0; i < prj.length; i++) {

			resource = prj[i].getProject().getFile(file);

			if (resource.exists()) {

				try {
					// if (checkParameters(errorName, sielCode)) {
					file1 = file;

					// gero a mensagem curta
					String message = shortMessage(errorName);

					// crio o marker com a mensagem
					createProblemMarker(resource, message, line, column);

					// crio o marker com a mensagem
					// createInformationMarker(resource, message, line, column);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Verifica se os parâmetros fornecidos pelo compilador estão corretos
	 * 
	 * @param errorName
	 * @param sielCode
	 * @return True if the parameters are ok
	 */
	public static boolean checkParameters(String errorName, ArrayList<String> sielCode) {
		int cont = 0;

		for (int x = 0; x < sielCode.size(); x++) {

			String[] variable = sielCode.get(x).toString().split("=");
			String aux = variable[0].toString().toLowerCase().trim();

			for (int i = 0; i < sielCode.size(); i++) {

				String Parameter = sielCode.get(i);
				Parameter = Parameter.replace("/n", "").trim().toLowerCase();

				if (aux.equals(Parameter)) {
					cont = cont + 1;
					break;
				}

			}
		}

		// verifico se algum parâmetro não existe
		if (cont == sielCode.size() - 1)
			return true;
		else
			return false;

	}

	/**
	 * Delete all error Markers before compile the code
	 * 
	 * @throws CoreException
	 */
	private static void DeleteMarkes() throws CoreException {
		// Delete all markers from the projects in the workspace
		IProject[] prj = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		for (int i = 0; i < prj.length; i++) {
			prj[i].deleteMarkers("org.eclipse.core.resources.problemmarker", true, IResource.DEPTH_INFINITE);
			prj[i].deleteMarkers("org.eclipse.core.resources.textmarker", true, IResource.DEPTH_INFINITE);
			prj[i].deleteMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
		}
	}

	/**
	 * Creates an error mark that indicates where the error occurred
	 * 
	 * @throws BadLocationException
	 * 
	 * @throws CoreException
	 */
	static void createProblemMarker(IResource resource, String message, int line, int start)
			throws BadLocationException {

		try {

			String location = "line " + line + " char start " + start;

			// delete all markers before create new ones
			DeleteMarkes();

			// Create a marker to indicates the problem

			IMarker marker = resource.createMarker(IMarker.PROBLEM);

			// configura o marker com os resolutions
			marker.setAttribute(IMarker.PRIORITY, IMarker.PRIORITY_HIGH); // prioridade
			marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR); // severidade
			marker.setAttribute(IMarker.LOCATION, location); // mensagem de onde
																// o marker está
																// localizado
			marker.setAttribute(IMarker.MESSAGE, "A variável não foi declarada."); // mensagem
																					// de
																					// erro
			marker.setAttribute(IMarker.LINE_NUMBER, line);// linha que o marker
															// irá ser colocado

			marker.setAttribute(IMarker.SOURCE_ID, errorName); // id do marker
			marker.setAttribute(IMarker.TEXT, compilationText); // atribuo o
																// código com
																// erro ao Text
			ensureRanges(marker, line, resource);

			// mostra as opções das mensagens
			MarkerResolutionGenerator markerres = new MarkerResolutionGenerator();
			markerres.getResolutions(marker);

		} catch (CoreException ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * Carrega uma mensagem simples
	 * 
	 * @param errorName
	 */

	private static String shortMessage(String errorName) {

		royalManagement objroyal = new royalManagement();
		FileManagement objDir = new FileManagement();
		ErrorList errorList = new ErrorList();

		// Lista nome dos erros referente as pastas

		String folderName = null;
		ArrayList<String> content;
		folderName = errorList.returnPath(errorName);
		String shortmessage = "";
		int cont = 0;

		// Procura os arquivos dentro do diretório
		for (String file : objDir.FilePath(folderName).list()) {

			// if (cont == 0) {
			if (file.endsWith(".royal")) {

				// só vou utilizar o primeiro arquivo
				cont++;

				try {
					// carrego o arquivo
					content = objroyal.openFile(errorName, file);

					// verifico se o arquivo deve ser utilizado
					if (content.get(0).contains("true")) {

						// percorro o arraylist e atribuo o conteúdo a uma
						// String
						for (Iterator iterator = content.iterator(); iterator.hasNext();) {

							shortmessage = shortmessage + iterator.next().toString();
						}

						// se tiver conteúdo na variável
						if (shortmessage.length() > 0) {
							// inicio e final da mensagem
							int start = shortmessage.indexOf("errorDescription<<<");
							int end = shortmessage.indexOf(">>>");

							// deixo só a mensagem curta na variável
							shortmessage = shortmessage.toString().substring(start, end).replace("errorDescription<<<",
									"");
						}

					}

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			// }
		}

		return shortmessage;
	}

	/**
	 * Carrega uma mensagem de erro atribuindo os parâmetros fornecidos pelo
	 * compilador
	 * 
	 * @param errorName
	 * @param sielCode
	 */

	public static String loadSepia(String errorName) {
		// Lista nome dos erros referente as pastas
		FileManagement objDir = new FileManagement();
		ErrorList errorList = new ErrorList();
		sepiaManagement objsepia = new sepiaManagement();
		String message = "";
		String folderName, aux, auxValue = null;
		String[] variable = null;
		ArrayList<String> content = null;
		folderName = errorList.returnPath(errorName);

		// Procura os arquivos dentro do diretório
		for (String file : objDir.FilePath(folderName).list()) {

			if (file.endsWith(".sepia")) {

				try {

					content = objsepia.openFile(errorName, file.toString());
					if (content.get(0).contains("true")) {

						// Atribuo o conteúdo do array em um StringBuilder
						for (Iterator iterator = content.iterator(); iterator.hasNext();) {

							message = message + (iterator.next().toString() + "\n");
						}

						// percorro o array separando os conjuntos de
						// pares/valores
						for (int x = 0; x < siel.size(); x++) {

							// separo os conjuntos
							variable = siel.get(x).toString().split("=");
							// armazeno o nome do parâmetro
							aux = variable[0].toString();
							auxValue = "'" + variable[1].toString() + "' ";
							aux = "#" + aux;

							// Verifico se o parâmetro existe na mensagem
							if (message.toString().toLowerCase().contains(aux)) {
								message = message.replace(aux, auxValue);
							}

						}
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		return message.toString();

	}

	/**
	 * Updates the charstart and charend ranges if necessary for the given line.
	 * Returns immediately if the line is not valid (< 0 or greater than the
	 * total line number count)
	 * 
	 * @param document
	 * @param marker
	 * @param line
	 * @throws BadLocationException
	 * @throws CoreException
	 */
	static void ensureRanges(final IMarker marker, final int line, IResource resource)
			throws BadLocationException, CoreException {

		ITextFileBufferManager iTextFileBufferManager = FileBuffers.getTextFileBufferManager();
		ITextFileBuffer iTextFileBuffer = null;
		IDocument document = null;

		try {
			iTextFileBufferManager.connect(resource.getFullPath(), LocationKind.IFILE, new NullProgressMonitor());
			iTextFileBuffer = iTextFileBufferManager.getTextFileBuffer(resource.getFullPath(), LocationKind.IFILE);
			document = iTextFileBuffer.getDocument();

			iTextFileBufferManager.disconnect(resource.getFullPath(), LocationKind.IFILE, new NullProgressMonitor());
			int start = document.getLineOffset(line - 1);
			int end = document.getLineLength(line - 1);
			

			// this next section was done to remove the leading white spaces
			while (document.getChar(start) == ' ' || document.getChar(start) == '\t') {
				start++;
				end--;
			}
		

			final int charStart = start;
			final int charEnd = start + end;			
			
			MarkerUtilities.setCharStart(marker, charStart);
			MarkerUtilities.setCharEnd(marker, charEnd);

		} catch (org.eclipse.jface.text.BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
