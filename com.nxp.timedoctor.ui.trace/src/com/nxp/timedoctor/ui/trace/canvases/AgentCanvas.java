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
package com.nxp.timedoctor.ui.trace.canvases;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;

import com.nxp.timedoctor.core.model.SampleLine;
import com.nxp.timedoctor.core.model.TraceModel;
import com.nxp.timedoctor.core.model.ZoomModel;
import com.nxp.timedoctor.ui.trace.Colors;

/**
 * Canvas to display SampleLines of type agent. Handles creating the correct
 * paint listener with the correct color.
 */
public class AgentCanvas extends TraceCanvas {

	/**
	 * Creates a new canvas for displaying lines of type <code>AGENT</code>.
	 * The canvas handles its own drawing through a
	 * <code>TaskPaintListener</code>.
	 * 
	 * @param parent
	 *            the parent composite in which to create this canvas.
	 * @param sampleLine
	 *            a line of type <code>AGENT</code> containing the data to be
	 *            displayed.
	 * @param data
	 *            the model component containing zoom and scroll data
	 * @param model
	 *            the model containing data on the full set of traces
	 */
	public AgentCanvas(final Composite parent, final SampleLine sampleLine,
			final ZoomModel data, final TraceModel model) {
		super(parent, data);

		addPaintListener(new TaskPaintListener(new Color(getDisplay(),
				Colors.SEA_GREEN), sampleLine, data, model));
	}

}
