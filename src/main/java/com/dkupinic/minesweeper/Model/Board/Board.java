/*-----------------------------------------------------------------------------
 *              Hoehere Technische Bundeslehranstalt STEYR
 *----------------------------------------------------------------------------*/
/**
 * Kurzbeschreibung
 *
 * @author  : Dino Kupinic
 * @date    : 23.1.2023
 *
 * @details
 * Class containing information about the minesweeper board
 */

package com.dkupinic.minesweeper.Model.Board;

import com.dkupinic.minesweeper.Model.Difficulty.Difficulty;

public class Board {
    private Difficulty currentDifficulty;
    public static final int INITIAL_FLAGS = 999;
    public static final int INITIAL_BOMBS = 0;
    public static int flagCount = INITIAL_FLAGS;
    public static int bombCount = INITIAL_BOMBS;

    private int columns;
    private int rows;
    private int size;

    public Board(Difficulty difficulty) {
        if (checkValidDifficulty(difficulty)) setCurrentDifficulty(difficulty);

        setFieldSizes();
    }

    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }

    public Difficulty getCurrentDifficulty() {
        return currentDifficulty;
    }

    public void setCurrentDifficulty(Difficulty difficulty) {
        this.currentDifficulty = difficulty;
    }

    public int getColumns() {
        return columns;
    }
    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }
    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * checks if the difficulty is valid
     * @param difficulty one of the 3 difficulties
     * @return true or false
     */
    private boolean checkValidDifficulty(Difficulty difficulty) {
        switch (difficulty) {
            case BEGINNER, ADVANCED, ENTHUSIAST -> {
                return true;
            }
        }
        return false;
    }

    /**
     * sets the size of the board based on the currently selected difficulty
     */
    private void setFieldSizes() {
        switch (getCurrentDifficulty()) {
            case BEGINNER -> setSize(8);
            case ADVANCED -> setSize(16);
            case ENTHUSIAST -> setSize(20);
        }
        setColumns(size);
        setRows(size);
    }
}
