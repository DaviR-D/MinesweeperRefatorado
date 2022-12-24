package com.dkupinic.minesweeper.Model.Board;

import com.dkupinic.minesweeper.Model.Difficulty.Difficulty;

public class BoardSize {
    private int columns;
    private int rows;
    private Difficulty currentDifficulty;

    public BoardSize(Difficulty difficulty) {
        if (checkValidDifficulty(difficulty)) {
            setCurrentDifficulty(difficulty);
        }
        setFieldSizes();
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
            case BEGINNER -> {
                int beginnerSize = 8;
                setColumns(beginnerSize);
                setRows(beginnerSize);
            }
            case ADVANCED -> {
                int advancedSize = 16;
                setColumns(advancedSize);
                setRows(advancedSize);
            }
            case ENTHUSIAST -> {
                int enthusiastSize = 24;
                setColumns(enthusiastSize);
                setRows(enthusiastSize);
            }
        }
    }
}
