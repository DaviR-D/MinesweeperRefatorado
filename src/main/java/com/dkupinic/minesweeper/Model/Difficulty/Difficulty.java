// Difficulty.java
package com.dkupinic.minesweeper.Model.Difficulty;

public enum Difficulty {
    BEGINNER(1),
    ADVANCED(2),
    ENTHUSIAST(3);

    private final int multiplier;

    Difficulty(int multiplier) {
        this.multiplier = multiplier;
    }

    public int getMultiplier() {
        return multiplier;
    }
}
