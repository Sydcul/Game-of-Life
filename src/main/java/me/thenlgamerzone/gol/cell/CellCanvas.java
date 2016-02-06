package me.thenlgamerzone.gol.cell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/*
 * Copyright (c) 2016 Tim & Lukas
 * See LICENSE for license
 */
public class CellCanvas extends JPanel {
    // Used double-buffer so we don't draw directly on JPanel thus preventing flickering
    private BufferedImage drawImage;
    private Graphics drawGraphics;

    private final int width;
    private final int height;

    public CellCanvas(int width, int height) {
        this.width = width;
        this.height = height;
    }
    public void paint(Graphics g) {
        super.paintComponent(g);

        // Init drawImage and drawGraphics
        if(drawImage == null)
            drawImage = (BufferedImage) createImage(getWidth(), getHeight());
        drawGraphics = drawImage.getGraphics();

        // Create the background of the canvas
        drawGraphics.setColor(Color.DARK_GRAY);
        drawGraphics.fillRect(0, 0, getWidth(), getHeight());

        // Loop through height and width to create lines which are equally separated
        // Horizontal lines
        drawGraphics.setColor(Color.BLACK);
        for(int horLines = 0; horLines < height; horLines++) {
            int yCoord = horLines * getHeight() / height;

            // Draw the line
            drawGraphics.drawLine(0, yCoord, getWidth(), yCoord);
        }

        // Vertical lines
        for (int vertLines = 0; vertLines < width; vertLines++) {
            int xCoord = vertLines * getWidth() / width;

            // Draw the line
            drawGraphics.drawLine(xCoord, 0, xCoord, getHeight());
        }

        // TODO: Color alive cells
        // Update the main canvas
        g.drawImage(drawImage, 0, 0, this);
    }

    /**
     * Listener for mouse drags
     *
     * @param event The event
     */
    public void mouseDrag(MouseEvent event) {
        // Calculate coordinate
        int x = width * event.getX() / getWidth();
        int y = height * event.getY() / getHeight();

        // Check whether the user is actually clicking with left
        if (SwingUtilities.isLeftMouseButton(event)) {
            // TODO: activate cell
            paint(getGraphics());
        }
    }

    /**
     * Listener for resize events, due to the double buffering a new image and graphics are needed
     * @param evt The event
     */
    public void resizeEvent(ComponentEvent evt) {
        drawImage = (BufferedImage) createImage(getWidth(), getHeight());
        drawGraphics = drawImage.getGraphics();

        // Redraw the canvas
        paint(getGraphics());
    }
}
