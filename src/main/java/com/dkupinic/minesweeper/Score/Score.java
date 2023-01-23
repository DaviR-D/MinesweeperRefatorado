package com.dkupinic.minesweeper.Score;

import com.dkupinic.minesweeper.Exceptions.InvalidDifficultyException;
import com.dkupinic.minesweeper.Model.Difficulty.Difficulty;

public class Score {
    public static int score;
    public static int scoreDifficultyMultiplier;

    public Score() {
        score = 0;
        scoreDifficultyMultiplier = 1;
    }

    public static int getScore() {
        return score;
    }

    public static void increaseScore(int score) {
        Score.score += score * scoreDifficultyMultiplier;
    }

    public static void decreaseScore(int score) {
        Score.score -= score * scoreDifficultyMultiplier;
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
        throw new InvalidDifficultyException();
    }
}
