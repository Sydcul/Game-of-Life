package me.thenlgamerzone.gol;

import me.thenlgamerzone.gol.cell.CellManager;
import me.thenlgamerzone.gol.frame.SettingsFrame;
import me.thenlgamerzone.gol.game.GamePhase;
import me.thenlgamerzone.gol.game.Timer;

/*
 * Copyright (c) 2016 Tim
 * See LICENSE for license
 */
public class GameOfLife {
    private static CellManager cellManager;
    private static GOLFrame golFrame;
    private static SettingsFrame settingsFrame;

    private javax.swing.Timer cellTimer;
    private me.thenlgamerzone.gol.game.Timer actionListener;

    public static void main(String[] args) {
        // Temporary settings
        Settings.GAME_PHASE.setSetting(GamePhase.SELECTING);

        // Initialize frame on EDT
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                settingsFrame = new SettingsFrame();
            }
        });
    }

    /**
     * Static -> Non-Static converter
     */
    public GameOfLife() {
        // Initialize actionListener and timer
        actionListener = new me.thenlgamerzone.gol.game.Timer();
        cellTimer = new javax.swing.Timer(Settings.SPEED.getSetting(), actionListener);

        // Start the timer
        cellTimer.start();
    }

    /**
     * Initialize GOLFrame
     */
    public static void initGOLFFrame() {
        // Initialize frame on EDT
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                golFrame = new GOLFrame();
            }
        });
    }

    /**
     * Initialize cell manager
     */
    public static void initCellManager() {
        // Initialize cellManager
        cellManager = new CellManager();
    }

    /**
     * Returns the cellmanager
     * @return CellManager
     */
    public static CellManager getCellManager() {
        return cellManager;
    }

    /**
     * Returns the actionListener
     * @return The ActionListener
     */
    public Timer getActionListener() {
        return actionListener;
    }

    /**
     * Returns the cell timer
     * @return The Timer
     */
    public javax.swing.Timer getCellTimer() {
        return cellTimer;
    }

    /**
     * Returns used instance of main game frame
     * @return Main game frame or GOLFrame
     */
    public static GOLFrame getGOLFFrame() {
        return golFrame;
    }

    /**
     * Returns used instance of settings frame
     * @return Setting frame
     */
    public static SettingsFrame getSettingsFrame() {
        return settingsFrame;
    }
}