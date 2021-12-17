package ufscar.ems.actions.quickfix;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMarkerResolution2;
import org.eclipse.ui.PlatformUI;

import ems.management.ErrorList;
import ems.management.FileManagement;
import ems.pcd.model.pcdManagement;
import ems.pcd.model.pcdWriter;

public class Codes implements IMarkerResolution2 {
	IMarker marker;

	Codes(IMarker marker) {
		this.marker = marker;
	}

	public String getLabel() {
		return "See other codes with the same error";
	}

	public void run(IMarker marker) {
		if (MessageDialog.openConfirm(null, "EMS - Confirm?",
				"Do you wish to save the current code?") == true) {
			try {
				pcdWriter obj = new pcdWriter();
				String errorName;

				errorName = marker.getAttribute(IMarker.SOURCE_ID).toString();

				String errorCode = marker.getAttribute(IMarker.TEXT).toString();
				obj.Save(errorName, errorCode);

				MessageDialog.openInformation(null, "EMS - File saved. ",
						"Code save successful!");

			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public String getDescription() {
		String message = null;
		try {
			message = previousCode(marker.getAttribute(IMarker.SOURCE_ID)
					.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getFormatedString(message);
	}

	@Override
	public Image getImage() {
		return PlatformUI.getWorkbench().getSharedImages()
				.getImage(org.eclipse.ui.ISharedImages.IMG_OBJ_ADD);
	}

	static String previousCode(String errorName) {

		ErrorList errorList = new ErrorList();
		FileManagement objDir = new FileManagement();
		pcdManagement objpcd = new pcdManagement();
		String folderName = null;
		ArrayList<String> content;
		String codemessage = "";

		// Lista nome dos erros referente as pastas
		folderName = errorList.returnPath(errorName);

		// Procura os arquivos dentro do diretório
		for (String file : objDir.FilePath(folderName).list()) {

			if (file.endsWith(".pcd")) {

				try {
					// carrego o arquivo
					content = objpcd.openFile(errorName, file);

					// percorro o arraylist e atribuo o conteúdo a uma
					// String
					for (Iterator iterator = content.iterator(); iterator
							.hasNext();) {

						codemessage = codemessage + iterator.next().toString();
					}
					
					if (codemessage == "") {
					
						codemessage = "You don´t have previous code to show.";

					}

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

		return codemessage;

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
