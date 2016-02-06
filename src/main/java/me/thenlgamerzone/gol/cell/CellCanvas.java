package me.thenlgamerzone.gol.cell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/*
 * Copyright (c) 2016 Tim & Lukas
 * See LICENSE for license
 */
public class CellCanvas extends Canvas {
    // Used double-buffer so we don't draw directly on JPanel thus preventing flickering
    private Image drawImage;
    private Graphics drawGraphics;

    private final short width;
    private final short height;

    public CellCanvas(short width, short height, JPanel jPanel) {
        this.width = width;
        this.height = height;
    }

    /**
     * Redraws the canvas
     */
    private void redraw() {
        // Create the background of the canvas
        drawGraphics.setColor(Color.DARK_GRAY);
        drawGraphics.fillRect(0 ,0,getWidth(), getHeight());
    }

    /**
     * Listener for mouse drags
     * @param event The event
     */
    private void mouseDrag(MouseEvent event) {
        // Calculate coordinate
        int x = width * event.getX() / getWidth();
        int y = height * event.getY() / getHeight();

        // Check whether the user is actually clicking with left
        if(SwingUtilities.isLeftMouseButton(event)){
            // TODO: activate cell
            redraw();
        }
    }
}
