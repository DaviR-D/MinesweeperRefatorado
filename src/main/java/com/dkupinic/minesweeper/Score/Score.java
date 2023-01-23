package com.dkupinic.minesweeper.Score;

import com.dkupinic.minesweeper.Exceptions.InvalidDifficultyException;
import com.dkupinic.minesweeper.Model.Difficulty.Difficulty;

public class Score {
    public static int score;
    private static int baseScoreWin;
    private static int baseScoreLoss;
    public static int scoreDifficultyMultiplier;

    public Score() {
        baseScoreWin = 1;
        baseScoreLoss = 1;
        score = 0;
        scoreDifficultyMultiplier = 1;
    }

    public static int getScore() {
        return score;
    }

    public static void increaseScore() {
        Score.score += baseScoreWin * scoreDifficultyMultiplier;
    }

    public static void decreaseScore() {
        Score.score -= baseScoreLoss * scoreDifficultyMultiplier;
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
