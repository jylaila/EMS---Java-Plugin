package ufscar.ems.actions.quickfix;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMarkerResolution2;
import org.eclipse.ui.PlatformUI;

import ems.management.ErrorList;
import ems.management.FileManagement;
import ems.royal.model.royalManagement;

public class Explanation implements IMarkerResolution2 {

	IMarker marker;

	Explanation(IMarker marker) {
		this.marker = marker;
	}

	// define o label da mensagem
	public String getLabel() {
		return "See an explanation";
	}

	public void run(IMarker marker) {

	}

	// mostra a mensagem na janela
	@Override
	public String getDescription() {
		String message = null;
		try {
			message = explanationMessage(marker.getAttribute(IMarker.SOURCE_ID)
					.toString());
			// MessageDialog.openInformation(null, "Explanation", message);

		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getFormatedString(message);
	}

	// imagem que vai aparecer no label
	@Override
	public Image getImage() {
		return PlatformUI.getWorkbench().getSharedImages()
				.getImage(org.eclipse.ui.ISharedImages.IMG_OBJS_INFO_TSK);
	}
	
	/**
	 * Carrega as explicações para o erro
	 * 
	 * @param errorName
	 * @return Explicação do erro
	 */
	
	private String explanationMessage(String errorName) {

		ErrorList errorList = new ErrorList();
		FileManagement objDir = new FileManagement();
		royalManagement objroyal = new royalManagement();
		String folderName = null;
		ArrayList<String> content;
		String explanation = null;
		int first = 0;
	

		// Lista nome dos erros referente as pastas
		folderName = errorList.returnPath(errorName);

		// Procura os arquivos dentro do diretório
		for (String file : objDir.FilePath(folderName).list()) {

			//if (first == 0) {
				if (file.endsWith(".royal")) {
					// só vou utilizar o primeiro arquivo
					first++;

					try {
						// carrego o arquivo
						content = objroyal.openFile(errorName, file);

						// verifico se o arquivo deve ser utilizado
						if (content.get(0).contains("true")) {

							// percorro o arraylist e atribuo o conteúdo a uma
							// String
							for (Iterator iterator = content.iterator(); iterator
									.hasNext();) {

								explanation = explanation
										+ iterator.next().toString();
							}

							// se tiver conteúdo na variável
							if (explanation.length() > 0) {
								// inicio e final da mensagem
								int start = explanation.indexOf("text<<<*");
								int end = explanation.indexOf("*>>>");

								// deixo só a mensagem curta na variável
								explanation = explanation.toString()
										.substring(start, end)
										.replace("text<<<*", "");
							}

						}

					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			//}
		}
		
		return explanation;

	}
	
	/**
	 * Gets the formatted description.
	 * 
	 * @param description
	 *            the description.
	 * @return the formatted description.
	 */
	private String getFormatedString(String description) {
		StringTokenizer token = new StringTokenizer(description, "\r");
		StringBuffer buffer = new StringBuffer();
		for (; token.hasMoreTokens();) {
			buffer.append("<pre>").append(token.nextToken()).append("</pre>");
		}
		return buffer.toString();
	}

}
