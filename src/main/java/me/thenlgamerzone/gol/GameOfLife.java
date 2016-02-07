package me.thenlgamerzone.gol;

import me.thenlgamerzone.gol.cell.CellManager;
import me.thenlgamerzone.gol.game.GamePhase;
import me.thenlgamerzone.gol.game.Timer;

/*
 * Copyright (c) 2016 Tim
 * See LICENSE for license
 */
public class GameOfLife {
    private static CellManager cellManager;
    private static GOLFrame golFrame;
    private Thread cellTimer;

    public static void main(String[] args) {
        // Temporary settings
        Settings.WIDTH.setSetting(80);
        Settings.HEIGTH.setSetting(80);
        Settings.SPEED.setSetting(1000);
        Settings.GAME_PHASE.setSetting(GamePhase.STARTING);

        // Initialize cellManager
        cellManager = new CellManager();

        // Initialize frame on EDT
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                golFrame = new GOLFrame();
            }
        });

        // Initialize timer in non-static method
        new GameOfLife();
    }

    /**
     * Static -> Non-Static converter
     */
    public GameOfLife() {
        // Initialize timer
        cellTimer = new Thread(new Timer(Settings.SPEED, this));

        // Start the timer
        cellTimer.run();
    }

    /**
     * Returns the cellmanager
     * @return CellManager
     */
    public static CellManager getCellManager() {
        return cellManager;
    }

    /**
     * Returns the timer
     * @return Timer
     */
    public Thread getCellTimer() {
        return cellTimer;
    }

    public static GOLFrame getGOLFFrame() {
        return golFrame;
    }
}