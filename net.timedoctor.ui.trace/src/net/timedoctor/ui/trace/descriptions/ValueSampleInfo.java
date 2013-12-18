/*******************************************************************************
 * Copyright (c) 2006-2013 TimeDoctor contributors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License version 1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Royal Philips Electronics NV. - initial API and implementation
 *******************************************************************************/
package net.timedoctor.ui.trace.descriptions;

import net.timedoctor.core.model.SampleLine;
import net.timedoctor.core.model.ZoomModel;

public class ValueSampleInfo extends AbstractSampleInfo {
	private SampleLine line;
	private ZoomModel zoom;
	
	public ValueSampleInfo(final SampleLine line, final ZoomModel zoom) {
		super(line, zoom);
		this.zoom = zoom;
		this.line = line;	
	}
	
	@Override
	protected void fillInfoString(StringBuilder sb, int index) {
		double startTime = line.getSample(index).time;
		double endTime = line.getSample(index + 1).time;
		double valueDifference = line.getSample(index + 1).val
				- line.getSample(index).val;

		// Average values
		double[] result = line.getCounterDifference(zoom.getStartTime(), 
				zoom.getEndTime());
		double overallTimeInterval = result[0];
		double avgValueDifference = result[1];
		
		sb.append(timeBoundsToStr(startTime, endTime));
		sb.append(" (" + timeIntervalToStr(startTime, endTime) + ")\n");
		sb.append(doubleToIntStr(valueDifference));
		sb.append("\nAvg: " + doubleToIntStr(avgValueDifference)  + " #");
		sb.append(" / ");
		sb.append(doubleToIntStr(avgValueDifference/overallTimeInterval) + " #/s");
				
		String description = line.descrString(startTime);
		if (description != null) {
			sb.append(description);
		}
	}
}
