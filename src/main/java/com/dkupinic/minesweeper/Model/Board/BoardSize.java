package com.dkupinic.minesweeper.Model.Board;

import com.dkupinic.minesweeper.Model.Difficulty.Difficulty;

public class BoardSize {
    private final int beginnerSize = 8;
    private int totalFieldsX;
    private int totalFieldsY;
    private Difficulty currentDifficulty;

    public BoardSize(Difficulty difficulty) {
        if (checkValidDifficulty(difficulty)) {
            setCurrentDifficulty(difficulty);
        }
        setFieldSizes();
    }



    public int getBeginnerSize() {
        return beginnerSize;
    }







    public Difficulty getCurrentDifficulty() {
        return currentDifficulty;
    }

    public void setCurrentDifficulty(Difficulty difficulty) {
        this.currentDifficulty = difficulty;
    }

    public int getTotalFieldsX() {
        return totalFieldsX;
    }

    public void setTotalFieldsX(int totalFieldsX) {
        this.totalFieldsX = totalFieldsX;
    }

    public int getTotalFieldsY() {
        return totalFieldsY;
    }

    public void setTotalFieldsY(int totalFieldsY) {
        this.totalFieldsY = totalFieldsY;
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
                setTotalFieldsX(beginnerSize);
                setTotalFieldsY(beginnerSize);
            }
            case ADVANCED -> {
                int advancedSize = 16;
                setTotalFieldsX(advancedSize);
                setTotalFieldsY(advancedSize);
            }
            case ENTHUSIAST -> {
                int enthusiastSize = 24;
                setTotalFieldsX(enthusiastSize);
                setTotalFieldsY(enthusiastSize);
            }
        }
    }
}
