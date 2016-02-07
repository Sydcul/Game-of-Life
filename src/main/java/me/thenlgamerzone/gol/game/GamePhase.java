package me.thenlgamerzone.gol.game;

/*
 * Copyright (c) 2016 Tim
 * See LICENSE for license
 */
public enum GamePhase {
    SELECTING(false), STARTING(true), PLAYING(false), STABLE(false);

    private boolean canSelect;

    private GamePhase(boolean canSelect) {
        this.canSelect = canSelect;
    }

    /**
     * Returns a boolean which is true when the player can make cells living
     * @return The boolean
     */
    public boolean canSelect() {
        return canSelect;
    }
}
