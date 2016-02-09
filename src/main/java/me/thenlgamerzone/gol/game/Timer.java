package me.thenlgamerzone.gol.game;

import me.thenlgamerzone.gol.GameOfLife;
import me.thenlgamerzone.gol.Settings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Copyright (c) 2016 Tim
 * See LICENSE for license
 */
public class Timer implements ActionListener {

    /**
     * Runs every X (defined in Settings > Speed) milliseconds and triggers all the necessary methods for the game to progress
     * @param event ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        // Check if the current game phase is PLAYING, return otherwise
        if (Settings.GAME_PHASE.getGamePhase() != GamePhase.PLAYING)
            return;

        // Update all cells
        GameOfLife.getCellManager().updateCells();

        // Paint new canvas
        GameOfLife.getGOLFFrame().getCellCanvas().paint(GameOfLife.getGOLFFrame().getCellCanvas().getGraphics());

        // Update all cells
        GameOfLife.getCellManager().nextRound();
    }
}
