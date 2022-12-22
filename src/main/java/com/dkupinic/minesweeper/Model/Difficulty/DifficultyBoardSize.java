package com.dkupinic.minesweeper.Model.Difficulty;

public class DifficultyBoardSize {
    private int totalFieldsX;
    private int totalFieldsY;
    private PossibleDifficulty currentDifficulty;

    public DifficultyBoardSize(PossibleDifficulty difficulty) {
        if (checkValidDifficulty(difficulty)) setCurrentDifficulty(difficulty);
    }

    public PossibleDifficulty getCurrentDifficulty() {
        return currentDifficulty;
    }

    public void setCurrentDifficulty(PossibleDifficulty difficulty) {
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

    private boolean checkValidDifficulty(PossibleDifficulty difficulty) {
        switch (difficulty) {
            case BEGINNER, ADVANCED, ENTHUSIAST -> {
                return true;
            }
        }
        return false;
    }

    private void setFieldSizes() {
        final int beginnerSize = 8;
        final int advancedSize = 16;
        final int enthusiastSize = 24;
        switch (getCurrentDifficulty()) {
            case BEGINNER -> {
                setTotalFieldsX(beginnerSize);
                setTotalFieldsY(beginnerSize);
            }
            case ADVANCED -> {
                setTotalFieldsX(advancedSize);
                setTotalFieldsY(advancedSize);
            }
            case ENTHUSIAST -> {
                setTotalFieldsX(enthusiastSize);
                setTotalFieldsY(enthusiastSize);
            }
        }
    }
}
