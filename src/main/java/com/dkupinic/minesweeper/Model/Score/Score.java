package com.dkupinic.minesweeper.Model.Score;

import com.dkupinic.minesweeper.Exceptions.InvalidDifficultyException;
import com.dkupinic.minesweeper.Model.Difficulty.Difficulty;

public class Score {
    public static int score = 0;
    public static int scoreDifficultyMultiplier = 1;

    public static void increaseScore() {
        int baseScoreWin = 1;
        Score.score += baseScoreWin * scoreDifficultyMultiplier;
    }

    public static void decreaseScore() {
        int baseScoreLoss = 1;
        Score.score -= baseScoreLoss * scoreDifficultyMultiplier;
    }

    public static int getScore() {
        return score;
    }

    public static double getScoreDifficultyMultiplier() {
        return scoreDifficultyMultiplier;
    }

    public static void setScoreDifficultyMultiplier(Difficulty difficulty) throws InvalidDifficultyException {
        switch (difficulty) {
            case BEGINNER -> scoreDifficultyMultiplier = 1;
            case ADVANCED -> scoreDifficultyMultiplier = 2;
            case ENTHUSIAST -> scoreDifficultyMultiplier = 3;
        }
    }
}
