package ufscar.ems.actions.quickfix;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolution2;
import org.eclipse.ui.IMarkerResolutionGenerator;

public class MarkerResolutionGenerator implements IMarkerResolutionGenerator {
	
	public String getLabel() {
		return "More help are avaliable";
	}

	public IMarkerResolution[] getResolutions(IMarker marker) {

		try {
			Object problem = marker.getAttribute("SOURCE_ID");

			return new IMarkerResolution[] { 
					new ShortMessage(marker),
					new Examples(marker), 
					new Explanation(marker),
					new DetailedMessage(marker), 
					new Causes(marker),
					new Codes(marker)

			};

		} catch (Exception e) {
			return new IMarkerResolution[0];
		}
	}

}
