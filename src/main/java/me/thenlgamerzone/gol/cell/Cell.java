package me.thenlgamerzone.gol.cell;

/*
 * Copyright (c) 2016 Tim & Lukas
 * See LICENSE for license
 */
public class Cell {
    public enum CELL_STATE {DEAD, ALIVE}

    private final int x;
    private final int y;
    private CELL_STATE cellState;
    private CELL_STATE nextCellState;

    public Cell(int x, int y, CELL_STATE cellState) {
        this.x = x;
        this.y = y;

        this.cellState = cellState;
        nextCellState = CELL_STATE.DEAD;
    }

    /**
     * Returns true if the current cell is alive
     * @return Boolean representing cell state
     */
    public boolean isAlive() {
        return cellState == CELL_STATE.ALIVE;
    }

    /**
     * Returns the next state of this
     * @return Next state
     */
    public CELL_STATE getNextCellState() {
        return nextCellState;
    }

    /**
     * Set the state of the cell
     * @param cellState New state
     */
    public void setState(CELL_STATE cellState) {
        this.cellState = cellState;
    }

    /**
     * Change the next state of the cell
     * @param cellState Next cell state
     */
    public void setNextCellState(CELL_STATE cellState) {
        nextCellState = cellState;
    }

    /**
     * Get the cell's X coordinate
     * @return X coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Get the cell's Y coordinate
     * @return Y coordinate
     */
    public int getY() {
        return y;
    }
}
