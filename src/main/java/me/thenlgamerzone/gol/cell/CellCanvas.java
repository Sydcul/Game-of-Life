package me.thenlgamerzone.gol.cell;

import me.thenlgamerzone.gol.GameOfLife;
import me.thenlgamerzone.gol.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/*
 * Copyright (c) 2016 Tim
 * See LICENSE for license
 */
public class CellCanvas extends JPanel {
    // Used double-buffer so we don't draw directly on JPanel thus preventing flickering
    private BufferedImage drawImage;
    private Graphics drawGraphics;

    private final int width;
    private final int height;

    public CellCanvas(int width, int height) {
        // Set width and height
        this.width = width;
        this.height = height;
    }

    /**
     * Paint the canvas using double buffer
     * @param graphics 'Real' canvas, used for double buffer
     */
    public void paint(Graphics graphics) {
        // Init drawImage and drawGraphics
        if(drawImage == null)
            drawImage = (BufferedImage) createImage(getWidth(), getHeight());
        drawGraphics = drawImage.getGraphics();

        paintComponent(drawGraphics);

        // Update the main canvas
        graphics.drawImage(drawImage, 0, 0, this);
    }

    /**
     * Paint the given graphics
     * @param graphics The graphics to be painted
     */
    public void paintComponent(Graphics graphics) {
        // Create the background of the canvas
        graphics.clearRect(0, 0, getHeight(), getWidth());
        graphics.setColor(Color.DARK_GRAY);
        graphics.fillRect(0, 0, getWidth(), getHeight());

        // Loop through height and width to create lines which are equally separated
        // Horizontal lines
        graphics.setColor(Color.BLACK);
        for(int horLines = 0; horLines < height; horLines++) {
            int yCoord = horLines * getHeight() / height;

            // Draw the line
            graphics.drawLine(0, yCoord, getWidth(), yCoord);
        }

        // Vertical lines
        for (int vertLines = 0; vertLines < width; vertLines++) {
            int xCoord = vertLines * getWidth() / width;

            // Draw the line
            graphics.drawLine(xCoord, 0, xCoord, getHeight());
        }

        // Loop through all the cells that will be alive next round
        for (Cell cell : GameOfLife.getCellManager().getNextAliveCells()) {
            // Get cell's location on JPanel
            int x = cell.getX() * getWidth() / width;
            int y = cell.getY() * getHeight() / height;

            // Set cell's color
            graphics.setColor(Color.RED);

            // Paint the cell
            graphics.fillRect(x, y, getWidth() / width, getHeight() / height);
        }
    }

    /**
     * Listener for mouse drags
     *
     * @param event The event
     */
    public void mouseDrag(MouseEvent event) {
        // Check if the current game phase allows the user to make cells living
        if (!Settings.GAME_PHASE.getGamePhase().canSelect())
            return;

        // Calculate coordinate
        int x = width * event.getX() / getWidth();
        int y = height * event.getY() / getHeight();

        // Check whether the user is actually clicking with left
        if (SwingUtilities.isLeftMouseButton(event)) {
            // Toggle state
            GameOfLife.getCellManager().getCellAt(x, y).setNextCellState(GameOfLife.getCellManager().getCellAt(x, y).getNextCellState() == Cell.CELL_STATE.DEAD ? Cell.CELL_STATE.ALIVE : Cell.CELL_STATE.DEAD);
            GameOfLife.getCellManager().getCellAt(x, y).setState(!GameOfLife.getCellManager().getCellAt(x, y).isAlive() ? Cell.CELL_STATE.ALIVE : Cell.CELL_STATE.DEAD);
            // Update JPanel
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
