package me.thenlgamerzone.gol.game;

import me.thenlgamerzone.gol.GameOfLife;
import me.thenlgamerzone.gol.Settings;

/*
 * Copyright (c) 2016 Tim
 * See LICENSE for license
 */
public class Timer implements Runnable {
    private final Settings speedSetting;
    private final GameOfLife gameOfLife;
    private boolean run = true;

    private Thread timerThread;

    public Timer(Settings speedSetting, GameOfLife gameOfLife) {
        // Set the speed setting
        this.speedSetting = speedSetting;

        // Set GameOfLife instance
        this.gameOfLife = gameOfLife;

        // Set instance of timer used
        this.timerThread = gameOfLife.getCellTimer();
    }

    @Override
    public void run() {
        while (run) {
            // Sleep the thread/timer
            try {
                timerThread.sleep(speedSetting.getSetting());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Check if the timer has to do anything
            if (Settings.GAME_PHASE.getGamePhase() != GamePhase.PLAYING)
                continue;

            // Update all cells
            GameOfLife.getCellManager().updateCells();

            // Paint new canvas
            GameOfLife.getGOLFFrame().getCellCanvas().paint(GameOfLife.getGOLFFrame().getCellCanvas().getGraphics());

            // Update all cells
            GameOfLife.getCellManager().nextRound();
        }
    }


}
