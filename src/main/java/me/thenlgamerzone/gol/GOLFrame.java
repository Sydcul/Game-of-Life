package me.thenlgamerzone.gol;

/*
 * Copyright (c) 2016 Tim
 * See LICENSE for license
 */

import me.thenlgamerzone.gol.cell.CellCanvas;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GOLFrame {
    private CellCanvas cellCanvas;

    public GOLFrame() {
        // Create JFrame and JPanel
        JFrame jFrame = new JFrame();
        cellCanvas = new CellCanvas(Settings.WIDTH.getSetting(), Settings.HEIGTH.getSetting());

        // Adding listeners for JPanel
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
        jFrame.getContentPane().add(cellCanvas);
        jFrame.setSize(500, 500);
        jFrame.setTitle("Game of Life");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);

        // Show the JFrame
        jFrame.setVisible(true);
    }
}
