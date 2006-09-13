/*******************************************************************************
 * Copyright (c) 2006 Royal Philips Electronics NV.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License version 1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Royal Philips Electronics NV. - initial API and implementation
 *******************************************************************************/
package com.nxp.timedoctor.core.model.lines;

import com.nxp.timedoctor.core.model.SampleCPU;
import com.nxp.timedoctor.core.model.SampleLine;
import com.nxp.timedoctor.core.model.Sample.SampleType;

/**
 * Sample line for notes.
 */
public class NoteSampleLine extends SampleLine {

	/**
	 * Constructs a note sample line using the given cpu and integer id. Adds it
	 * to the right section of the model.
	 * 
	 * @param cpu
	 *            the cpu associated with the line
	 * @param id
	 *            the integer id of the line
	 */
	public NoteSampleLine(final SampleCPU cpu, final int id) {
		super(cpu, id);
		setType(LineType.NOTES);
	}

	/**
	 * Implements inherited abstract method.
	 * 
	 * @param endTime
	 *            the time to which to perform calculation
	 */
	@Override
	public final void calculate(final double endTime) {
		if (getName() == null) {
			setName(String.format("Notes 0x%x", getID()));
		}
		if (getCount() > 0) {
			addSample(SampleType.END, endTime, getSample(getCount() - 1).val);
		} else {
			addSample(SampleType.END, endTime);
		}
	}

	/**
	 * Overrides superclass method for type-specific behavior.
	 * 
	 * @param from
	 *            the beginning time of the search window
	 * @param to
	 *            the end time of the search window
	 * @return whether or not the line has samples in the given window
	 */
	@Override
	public final boolean hasSamples(final double from, final double to) {
		int i = binarySearch(from);
		int j = binarySearch(to);
		if (i != j) {
			return true;
		} else if (getCount() <= 1) {
			return false;
		} else if (getSample(i).time > to || getSample(i).time < from) {
			return false;
		} else if (getSample(i).type == SampleType.STOP) {
			return false;
		} else {
			return true;
		}
	}

}
