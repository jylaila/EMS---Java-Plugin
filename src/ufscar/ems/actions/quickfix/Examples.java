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

import com.sun.xml.internal.bind.marshaller.Messages;

import ems.management.ErrorList;
import ems.management.FileManagement;
import ems.royal.model.royalManagement;

public class Examples implements IMarkerResolution2 {
	IMarker marker;

	Examples(IMarker marker) {
		this.marker = marker;
	}

	public String getLabel() {
		return "See some examples ";
	}

	public void run(IMarker marker) {

	}

	@Override
	public String getDescription() {
		String message = "";
		try {
			message = exampleMessage(marker.getAttribute(IMarker.SOURCE_ID)
					.toString());
			// MessageDialog.openInformation(null, "Examples", message);

		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getFormatedString(message.toString());
	}

	@Override
	public Image getImage() {
			return PlatformUI.getWorkbench().getSharedImages()
					.getImage(org.eclipse.ui.ISharedImages.IMG_OBJS_INFO_TSK);
	}

	/**
	 * Carrega os exemplos para o erro
	 * 
	 * @param errorName
	 * @return
	 */

	private String exampleMessage(String errorName) {

		ErrorList errorList = new ErrorList();
		FileManagement objDir = new FileManagement();
		royalManagement objroyal = new royalManagement();
		String folderName = null;
		ArrayList<String> content;
		StringBuilder examplemessage = new StringBuilder();
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
							StringBuilder example = new StringBuilder();

							// percorro o arraylist e atribuo o conteúdo a uma
							// String
							for (Iterator iterator = content.iterator(); iterator
									.hasNext();) {

								example.append(iterator.next().toString()
										+ "\n");
							}

							// se tiver conteúdo na variável
							if (example.length() > 0) {

								// verifico quantos exemplos tem
								String[] examples = example.toString().split(
										"source<");
								int size = examples.length;

								int start = 0;
								int end = 0;

								// monto a mensagem

								for (int i = 1; i < size; i++) {
									// inicio e final da mensagem
									start = example.indexOf("source<**", start);
									end = example.indexOf("**>", end);

									// deixo só o exemplo variável
									examplemessage.append("<bold>- Example " + i
											+ ":\n\n</bold>");
									examplemessage.append(example.toString()
											.substring(start, end)
											.replace("source<**", "")
											+ "\n\n");
								}
							}

						}

					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			//}
		}

		return getFormatedString(examplemessage.toString());

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
