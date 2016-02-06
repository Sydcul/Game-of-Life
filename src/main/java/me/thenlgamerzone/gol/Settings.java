package me.thenlgamerzone.gol;

/*
 * Copyright (c) 2016 Tim & Lukas
 * See LICENSE for license
 */
public enum Settings {
    WIDTH(0), HEIGTH(0), SPEED(0);

    private int setting;

    private Settings(int setting) {
        this.setting = setting;
    }

    /**
     * Returns the setting's value
     * @return Value of setting
     */
    public int getSetting() {
        return setting;
    }

    /**
     * Edit a setting's value
     * @param newSetting The new value
     */
    public void setSetting(int newSetting) {
        setting = newSetting;
    }
}
