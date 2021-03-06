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
package net.timedoctor.ui.trace.canvases;

import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.widgets.Composite;

import net.timedoctor.core.model.SampleLine;
import net.timedoctor.core.model.TraceModel;
import net.timedoctor.core.model.ZoomModel;
import net.timedoctor.ui.trace.Colors;
import net.timedoctor.ui.trace.descriptions.TaskSampleInfo;

/**
 * Canvas for drawing ISRs.
 */
public class IsrCanvas extends TraceCanvas {

	/**
	 * Creates a new ISR canvas with the proper paint listener.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param line
	 *            <code>SampleLine</code> to be drawn
	 * @param zoom
	 *            <code>ZoomModel</code> containing all zoom and scroll
	 *            information
	 * @param model
	 *            model containing information on the full trace
	 */
	public IsrCanvas(final Composite parent, 
			final SampleLine line,
			final ZoomModel zoom,
			final TraceModel model) {
		super(parent, zoom, new TaskSampleInfo(line, zoom));

		addPaintListener(new TaskPaintListener(Colors.getColorRegistry().get(Colors.DARK_GREEN), line, zoom, model));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent event) {
		//Do nothing
	}

}
