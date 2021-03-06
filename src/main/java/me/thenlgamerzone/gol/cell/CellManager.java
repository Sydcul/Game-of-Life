package me.thenlgamerzone.gol.cell;

import me.thenlgamerzone.gol.GameOfLife;
import me.thenlgamerzone.gol.Settings;

import java.util.HashSet;
import java.util.Set;

/*
 * Copyright (c) 2016 Tim
 * See LICENSE for license
 */
public class CellManager {
    private Cell[][] currentCells;
    private Set<Cell> allCells;

    public CellManager() {
        currentCells = new Cell[Settings.WIDTH.getSetting()][Settings.HEIGTH.getSetting()];
        allCells = new HashSet<Cell>();

        // Populate arrays
        for (int column = 0; column < Settings.WIDTH.getSetting(); column++)
            for (int row = 0; row < Settings.HEIGTH.getSetting(); row++) {
                // Initiate new cell
                Cell cell = new Cell(column, row, Cell.CELL_STATE.DEAD);

                // Add cell to arrays
                currentCells[column][row] = cell;
                allCells.add(cell);
            }
    }

    /**
     * Returns the cell at a given location. Can be null if the location is invalid
     * @param x X coordinate
     * @param y Y coordinate
     * @return The cell
     */
    public Cell getCellAt(int x, int y) {
        return x >= Settings.WIDTH.getSetting() ||
                y >= Settings.HEIGTH.getSetting() ||
                x < 0 ||
                y < 0
                ? null : currentCells[x][y];
    }

    /**
     * Get the amount of neighbours of a cell at a given location
     * @param x X coordinate
     * @param y Y coordinate
     * @return Number of neighbours
     */
    public int getNeighbours(int x, int y) {
        return getNeighbours(getCellAt(x, y));
    }

    /**
     * Get the amount of neighbours of a cell
     * @param cell Cell to be checked
     * @return Number of neighbours
     */
    public int getNeighbours(Cell cell) {
        int aliveNeighbours = 0;

        // Loop though all the surrounding cells
        for (int x = cell.getX() - 1; x <= cell.getX() + 1; x++) {
            // Check if the current X is out of bounds
            if (x < 0 || x >= Settings.WIDTH.getSetting())
                continue;

            for (int y = cell.getY() - 1; y <= cell.getY() + 1; y++) {
                // Check if the current Y is out of bounds or if the current cell is the given cell
                if (y < 0 || y >= Settings.HEIGTH.getSetting() || (x == cell.getX() && y == cell.getY()))
                    continue;

                // Increase aliveNeighbours by one if the neighbour is alive
                if (getCellAt(x, y).isAlive())
                    aliveNeighbours++;
            }
        }

        return aliveNeighbours;
    }

    /**
     * Calculates the state for all cells
     */
    public void updateCells() {
        // Loop through all the cells and give them new states for next round based on their neighbours
        for (Cell cell : allCells) {
            // Get the number of neighbours for the current cell
            int neighbours = getNeighbours(cell);

            // Check if the cell is alive
            if(cell.isAlive()) {
                // Check for overpopulation
                if(neighbours >= 4) {
                    cell.setNextCellState(Cell.CELL_STATE.DEAD);
                    continue;
                }

                // Check for starvation
                else if (neighbours < 2) {
                    cell.setNextCellState(Cell.CELL_STATE.DEAD);
                    continue;
                }

                // Cell will survive this round
                cell.setNextCellState(Cell.CELL_STATE.ALIVE);
            } else {
                // Check for growth
                if(neighbours == 3) {
                    cell.setNextCellState(Cell.CELL_STATE.ALIVE);
                    continue;
                }

                // Cell won't grow
                cell.setNextCellState(Cell.CELL_STATE.DEAD);
            }
        }
    }

    /**
     * Updates the state of all cells
     */
    public void nextRound() {
        // Loop through all the cells and trigger the necessary methods to progress to the next round
        for (Cell cell : allCells) {
            // Change states
            cell.setState(cell.getNextCellState());
            cell.setNextCellState(null);
        }
    }

    /**
     * Returns a set with all the cells, dead or alive
     * @return Set containing all the cells
     */
    public Set<Cell> getAllCells() {
        return allCells;
    }

    /**
     * Returns a set with all the cells that will be alive next round
     * @return Set containing all the cells that will be alive next round
     */
    public Set<Cell> getNextAliveCells() {
        Set<Cell> nextCells = new HashSet<Cell>();

        // Loop through all cells and check which will be alive next round
        for (Cell cell : allCells)
            if (cell.getNextCellState() == Cell.CELL_STATE.ALIVE)
                nextCells.add(cell);

        return nextCells;
    }

    /**
     * Kills every living cell
     */
    public void killAllCells() {
        // Loop through all cells and kill them
        for (Cell cell : getAllCells()) {
            // Change the cell's states
            cell.setState(Cell.CELL_STATE.DEAD);
            cell.setNextCellState(Cell.CELL_STATE.DEAD);
        }

        // Manually repaint the CellCanvas
        GameOfLife.getGOLFFrame().getCellCanvas().paint(GameOfLife.getGOLFFrame().getCellCanvas().getGraphics());
    }
}
