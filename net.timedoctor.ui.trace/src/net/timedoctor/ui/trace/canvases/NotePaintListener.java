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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import net.timedoctor.core.model.SampleLine;
import net.timedoctor.core.model.ZoomModel;

/**
 * Contains the code to paint a note.
 */
public class NotePaintListener extends TracePaintListener implements PaintListener {

	/**
	 * Vertical padding value on the bottom of trace lines.
	 */
	private static final int VERTICAL_PADDING = 2;

	/**
     * The color to use in painting the line.
     */
    private Color color;

    /**
     * <code>Observable</code> containing zoom and scroll data.
     */
    private ZoomModel data;

    /**
     * The line containing data to visualize.
     */
    private SampleLine line;

    /**
     * The starting time of the part of the line currently displayed, based on
     * scroll data.
     */
    private double timeOffset;
    
    private SampleFlag sampleFlag;

    /**
     * Constructs a new <code>NotePaintListener</code> with the given
     * color,sample line, and source of zoom/scroll data.
     * 
     * @param col
     *            the color with which to paint the line
     * @param sampleLine
     *            contains the data to be displayed
     * @param zoomData
     *            contains data on the zoom/scroll state of the system
     */

    public NotePaintListener(final Color col, final SampleLine sampleLine,
            final ZoomModel zoomData) {
        this.color = col;
        this.line = sampleLine;
        this.data = zoomData;
        this.sampleFlag = new SampleFlag();
    }

    /**
     * Sent when a paint event occurs for the control. Repaints the affected
     * section of the line.
     * 
     * @param e
     *            an event containing information about the paint
     * 
     * @see PaintListener#paintControl(PaintEvent)
     */
    public final void paintControl(final PaintEvent e) {
        if (data.getStartTime() != data.getEndTime()) {

            timeOffset = data.getStartTime();
            Canvas canvas = ((Canvas) e.widget);
            Composite section = canvas.getParent();
            Composite rightPane = section.getParent();
            Composite scroll = rightPane.getParent();

            int fullWidth = scroll.getBounds().width;
			int traceHeight = canvas.getBounds().height - VERTICAL_PADDING;

            double zoom = fullWidth / (data.getEndTime() - data.getStartTime());
            final double drawStartTime = timeOffset + (e.x / zoom);
            final double drawEndTime = drawStartTime + (e.width / zoom);
            int index = line.binarySearch(drawStartTime);

            e.gc.setBackground(e.display.getSystemColor(SWT.COLOR_WHITE));
            e.gc.fillRectangle(e.x, e.y, e.width, e.height);

            for (int xOld = -1; index < line.getCount() - 1; index++) {

                if (line.getSample(index).time > drawEndTime) {
                    break;
                }

                final int xStart = boundedInt((line.getSample(index).time - timeOffset)
                        * zoom);
                if ( xStart <= xOld ) {
                    continue;
                }
                xOld = xStart;

                sampleFlag.draw(e, color, color, xStart, VERTICAL_PADDING, traceHeight);
            }
        }
    }
}
