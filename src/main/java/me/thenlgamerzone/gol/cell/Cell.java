package me.thenlgamerzone.gol.cell;

/*
 * Copyright (c) 2016 Tim & Lukas
 * See LICENSE for license
 */
public class Cell {
    public enum CELL_STATE {DEAD, ALIVE}

    private final short x;
    private final short y;
    private CELL_STATE cellState;

    public Cell(short x, short y, CELL_STATE cellState) {
        this.x = x;
        this.y = y;

        this.cellState = cellState;
    }

    public boolean isAlive() {
        return cellState == CELL_STATE.ALIVE;
    }
}
