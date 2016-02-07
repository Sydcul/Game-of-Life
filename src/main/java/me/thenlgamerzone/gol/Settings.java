package me.thenlgamerzone.gol;

import me.thenlgamerzone.gol.game.GamePhase;

/*
 * Copyright (c) 2016 Tim
 * See LICENSE for license
 */
public enum Settings {
    WIDTH(0, null), HEIGTH(0, null), SPEED(0, null), GAME_PHASE(0, GamePhase.SELECTING);

    private int setting;
    private GamePhase gamePhase;

    private Settings(int setting, GamePhase gamePhase) {
        this.setting = setting;
        this.gamePhase = gamePhase;
    }

    /**
     * Returns the setting's value
     * @return Value of setting
     */
    public int getSetting() {
        return setting;
    }

    /**
     * Returns the current game phase
     * @return Current game phase
     */
    public GamePhase getGamePhase() {
        return gamePhase;
    }

    /**
     * Edit a setting's value
     * @param newSetting The new value
     */
    public void setSetting(int newSetting) {
        setting = newSetting;
    }

    /**
     * Edit the current game phase
     * @param gamePhase The new game phase
     */
    public void setSetting(GamePhase gamePhase) {
        this.gamePhase = gamePhase;
    }
}
