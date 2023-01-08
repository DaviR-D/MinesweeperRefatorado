package com.dkupinic.minesweeper.Model.Board;

import com.dkupinic.minesweeper.Model.Difficulty.Difficulty;

public class Board {
    private int columns;
    private int rows;
    private int size;
    private Difficulty currentDifficulty;

    public Board(Difficulty difficulty) {
        if (checkValidDifficulty(difficulty)) setCurrentDifficulty(difficulty);

        setFieldSizes();
    }

    private boolean checkValidDifficulty(Difficulty difficulty) {
        switch (difficulty) {
            case BEGINNER, ADVANCED, ENTHUSIAST -> {
                return true;
            }
        }
        return false;
    }

    private void setFieldSizes() {
        switch (getCurrentDifficulty()) {
            case BEGINNER -> setSize(8);
            case ADVANCED -> setSize(16);
            case ENTHUSIAST -> setSize(20);
        }
        setColumns(size);
        setRows(size);
    }

    //region getter setter

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

    //endregion
}
