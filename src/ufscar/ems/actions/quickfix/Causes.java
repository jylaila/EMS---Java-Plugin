package ufscar.ems.actions.quickfix;

import java.util.StringTokenizer;

import org.eclipse.core.resources.IMarker;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMarkerResolution2;
import org.eclipse.ui.PlatformUI;

import ems.management.SignalCompilerError;

public class Causes implements IMarkerResolution2 {
	IMarker marker;

	Causes(IMarker marker) {
		this.marker = marker;
	}

	public String getLabel() {
		return "See some causes";
	}

	public void run(IMarker marker)
	{
		
	}
	public void run(IMarker marker, String error) {
		
	}

	@Override
	public String getDescription() {
		String message=null;
		StringBuilder causemessage = new StringBuilder();
		try {
			message = SignalCompilerError.loadSepia(marker.getAttribute(
					IMarker.SOURCE_ID).toString());
			
			// se tiver conteúdo na variável
			if (message.length() > 0) {

				// verifico quantos exemplos tem
				String[] messages = message.toString().split(
						"source<");
				int size = messages.length;

				int start = 0;
				int end = 0;

				// monto a mensagem

				for (int i = 1; i < size; i++) {
					// inicio e final da mensagem
					start = message.indexOf("*>>", start);
					end = message.indexOf("**>", end);

					// deixo só o exemplo variável			
					causemessage.append(message.toString()
							.substring(start, end)
							.replace("*>>", "")
							+ "\n\n");
				}
			}


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getFormatedString(message.toString());
	}

	@Override
	public Image getImage() {
		//Image i = javax.swing.ImageIcon("myimage.gif").getImage();
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
