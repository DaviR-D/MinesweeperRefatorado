// Score.java
package com.dkupinic.minesweeper.Model.Score;

import com.dkupinic.minesweeper.Exceptions.InvalidDifficultyException;
import com.dkupinic.minesweeper.Model.Difficulty.Difficulty;

public class Score {
    private static int score = 0;
    private static int scoreDifficultyMultiplier = 1;
    private static final int BASE_SCORE = 1;

    public static int getScore() {
        return score;
    }

    public static void increaseScore() {
        adjustScore(1);
    }

    public static void decreaseScore() {
        adjustScore(-1);
    }

    private static void adjustScore(int sign) {
        score += sign * BASE_SCORE * scoreDifficultyMultiplier;
    }

    public static void setScoreDifficultyMultiplier(Difficulty difficulty) throws InvalidDifficultyException {
        if (difficulty == null) {
            throw new InvalidDifficultyException("Difficulty cannot be null");
        }
        scoreDifficultyMultiplier = difficulty.getMultiplier();
    }
}