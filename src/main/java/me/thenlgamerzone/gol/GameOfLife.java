package me.thenlgamerzone.gol;

import me.thenlgamerzone.gol.cell.CellCanvas;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
 * Copyright (c) 2016 Tim & Lukas
 * See LICENSE for license
 */
public class GameOfLife {
    private JFrame mainFrame;
    private CellCanvas cellCanvas;

    public static void main(String[] args) {
        // Temporary settings
        Settings.WIDTH.setSetting(50);
        Settings.HEIGTH.setSetting(50);

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameOfLife();
            }
        });
    }

    /**
     * Initializes our frame and shows it
     */
    public GameOfLife() {
        // Create JFrame and JPanel
        mainFrame = new JFrame();
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
        mainFrame.getContentPane().add(cellCanvas);
        mainFrame.setSize(500, 500);
        mainFrame.setTitle("Game of Life");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);

        // Show the JFrame
        mainFrame.setVisible(true);
    }
}