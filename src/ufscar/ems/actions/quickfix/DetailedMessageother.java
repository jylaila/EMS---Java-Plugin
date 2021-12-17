package ufscar.ems.actions.quickfix;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolution2;

import ems.management.SignalCompilerError;

public class DetailedMessageother implements IMarkerResolution2 {
	String label;

	DetailedMessageother(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void run(IMarker marker)
	{
		
	}
	public void run(IMarker marker, String error) {
		String message;
		try {
//			message = SignalCompilerError.loadExexlShortDescription(marker
//					.getAttribute(IMarker.SOURCE_ID).toString());
//			MessageDialog.openInformation(null, "Error Message", message);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}



}
