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
package com.nxp.timedoctor.ui.trace;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;

import com.nxp.timedoctor.core.model.ZoomModel;

/**
 * Draw a marker line and time label 
 * as composites above all other
 * widgets. 
 */
public class TimeMarker extends TimeLine {
	private static final int OFFSET = 12;

	public TimeMarker(final Composite rulerPane, 
			final Composite tracePane, 
			final ZoomModel zoom) {
		super(rulerPane, tracePane, zoom, SWT.COLOR_GRAY, OFFSET);
		
		addSelectionListener();
		addMouseListener();
	}

	/**
	 * Dispose method, needed to get rid of markers upon double click
	 */
	public void dispose() {
		super.dispose();
		
		cursorSash.dispose();
		cursorLabel.dispose();
		cursorLine.dispose();
	}

	private void addSelectionListener() {
		SelectionListener selectionListener = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				setCursor(e.x);
			}
		};
		cursorSash.addSelectionListener(selectionListener);
	}
	
	private void addMouseListener() {
		MouseListener mouseListener = new MouseAdapter() {
			public void mouseDoubleClick(MouseEvent e) {
				dispose();
			}
		};
		cursorSash.addMouseListener(mouseListener);
	}
}
