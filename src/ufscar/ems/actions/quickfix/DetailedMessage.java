package ufscar.ems.actions.quickfix;

import java.util.StringTokenizer;

import org.eclipse.core.resources.IMarker;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMarkerResolution2;
import org.eclipse.ui.PlatformUI;

import ems.management.SignalCompilerError;

public class DetailedMessage implements IMarkerResolution2 {

	IMarker marker;

	DetailedMessage(IMarker marker) {

		this.marker = marker;

	}

	public String getLabel() {
		return "See a detailed message";
	}

	public void run(IMarker marker) {

	
	}


	@Override
	public String getDescription() {
		String message = null;
		try {
			message = SignalCompilerError.loadSepia(marker.getAttribute(
					IMarker.SOURCE_ID).toString());
			
			// se tiver conteúdo na variável
			if (message.length() > 0) {
				// inicio e final da mensagem
				int start = message
						.indexOf("explanation<<*{");
				int end = message.indexOf("}*>>");

				// deixo só a mensagem curta na variável
				message = message.toString()
						.substring(start, end)
						.replace("explanation<<*{", "");
			}

		

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getFormatedString(message);

	}

	@Override
	public Image getImage() {
			return PlatformUI.getWorkbench().getSharedImages()
					.getImage(org.eclipse.ui.ISharedImages.IMG_OBJ_FILE);
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
