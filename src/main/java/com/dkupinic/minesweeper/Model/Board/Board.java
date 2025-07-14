package com.dkupinic.minesweeper.Model.Board;

import com.dkupinic.minesweeper.Model.Difficulty.Difficulty;

public class Board {
    private Difficulty currentDifficulty;
    private static final int INITIAL_FLAGS = 999;
    private static int flagCount = INITIAL_FLAGS;
    private static int bombCount = 0;

    private final int size;
    private final int columns;
    private final int rows;

    public Board(Difficulty difficulty) {
        if (difficulty == null) {
            throw new IllegalArgumentException("Difficulty cannot be null");
        }
        this.currentDifficulty = difficulty;
        this.size = mapDifficultyToSize(difficulty);
        this.columns = this.size;
        this.rows = this.size;
    }

    private static int mapDifficultyToSize(Difficulty difficulty) {
        switch (difficulty) {
            case BEGINNER -> {
                return 8;
            }
            case ADVANCED -> {
                return 16;
            }
            case ENTHUSIAST -> {
                return 20;
            }
        }
        throw new IllegalArgumentException("Unknown difficulty: " + difficulty);
    }

    public static int getFlagCount() {
        return flagCount;
    }

    public static void setFlagCount(int count) {
        flagCount = count;
    }

    public static int getBombCount() {
        return bombCount;
    }

    public static void setBombCount(int count) {
        bombCount = count;
    }

    public Difficulty getCurrentDifficulty() {
        return currentDifficulty;
    }

    public int getSize() {
        return size;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }
}
