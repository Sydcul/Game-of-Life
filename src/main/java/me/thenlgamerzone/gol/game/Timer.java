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
        while (Settings.GAME_PHASE.getGamePhase() == GamePhase.PLAYING) {

            try {
                timerThread.sleep(speedSetting.getSetting());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
