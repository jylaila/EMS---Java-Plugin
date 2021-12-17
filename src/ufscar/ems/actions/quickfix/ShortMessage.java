package ufscar.ems.actions.quickfix;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IMarker;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMarkerResolution2;
import org.eclipse.ui.PlatformUI;

import ems.management.ErrorList;
import ems.management.FileManagement;
import ems.royal.model.royalManagement;

public class ShortMessage implements IMarkerResolution2 {
	IMarker marker;

	ShortMessage(IMarker marker) {
		this.marker = marker;
	}

	public String getLabel() {
		return "See a short message";
	}

	public void run(IMarker marker)
	{
		
	}

	@Override
	public String getDescription() {
		String message = null;
		try {
			message = shortMessage
					(marker.getAttribute
							(IMarker.SOURCE_ID).toString().toLowerCase());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return getFormatedString(message);
	}
	@Override
	public Image getImage() {
			return PlatformUI.getWorkbench().getSharedImages()
					.getImage(org.eclipse.ui.ISharedImages.IMG_OBJS_INFO_TSK);
	}
/**
 * Exibe uma mensagem curta, diferente da primeira mensagem
 * @param errorName
 * @return
 */
	
	static String shortMessage(String errorName) {

		ErrorList errorList = new ErrorList();
		FileManagement objDir = new FileManagement();
		royalManagement objroyal = new royalManagement();
		String folderName = null;
		ArrayList<String> content;	
		String shortmessage = "";
		int cont = 0;

		// Lista nome dos erros referente as pastas
		folderName = errorList.returnPath(errorName);
		
		// Procura os arquivos dentro do diretório
		for (String file : objDir.FilePath(folderName).list()) {

				if (file.endsWith(".royal")) {
			
					try {
						// carrego o arquivo
						content = objroyal.openFile(errorName, file);

						// verifico se o arquivo deve ser utilizado
						if (content.get(0).contains("true")) {

							// percorro o arraylist e atribuo o conteúdo a uma
							// String
							for (Iterator iterator = content.iterator(); iterator
									.hasNext();) {

								shortmessage = shortmessage
										+ iterator.next().toString();
							}

							// se tiver conteúdo na variável
							if (shortmessage.length() > 0) {
								// inicio e final da mensagem
								int start = shortmessage
										.indexOf("errorDescription<<<");
								int end = shortmessage.indexOf(">>>");

								// deixo só a mensagem curta na variável
								shortmessage = shortmessage.toString()
										.substring(start, end)
										.replace("errorDescription<<<", "");
							}

						}

					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
	
		}


		return shortmessage;

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
