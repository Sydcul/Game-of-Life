package me.thenlgamerzone.gol;

/*
 * Copyright (c) 2016 Tim
 * See LICENSE for license
 */

import me.thenlgamerzone.gol.cell.CellCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GOLFrame {
    private final CellCanvas cellCanvas;
    private ControlPanel controlPanel;

    public GOLFrame() {
        // Create JFrame, JPanels and layout
        JFrame jFrame = new JFrame();
        BorderLayout borderLayout = new BorderLayout();
        cellCanvas = new CellCanvas(Settings.WIDTH.getSetting(), Settings.HEIGTH.getSetting());
        controlPanel = new ControlPanel();

        // Adding listeners for cellCanvas
        cellCanvas.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                cellCanvas.mouseDrag(event);
            }
        });

        cellCanvas.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent event) {
                cellCanvas.resizeEvent(event);
            }
        });

        // Configuring JFrame
        jFrame.setLayout(borderLayout);
        jFrame.add(cellCanvas);
        jFrame.add(controlPanel, BorderLayout.PAGE_END);
        jFrame.setSize(750, 750);
        jFrame.setTitle("Game of Life");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);

        // Show the JFrame
        jFrame.setVisible(true);
    }

    /**
     * Returns the cell canvas all the cells are drawn on
     * @return The cell canvas
     */
    public CellCanvas getCellCanvas() {
        return cellCanvas;
    }

    /**
     * Returns the control panel
     * @return The control panel
     */
    public ControlPanel getControlPanel() {
        return controlPanel;
    }
}
