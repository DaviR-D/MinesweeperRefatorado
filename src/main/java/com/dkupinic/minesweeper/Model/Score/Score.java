/*-----------------------------------------------------------------------------
 *              Hoehere Technische Bundeslehranstalt STEYR
 *----------------------------------------------------------------------------*/
/**
 * Kurzbeschreibung
 *
 * @author  : Dino Kupinic
 * @date    : 23.1.2023
 *
 * @details
 * Class used to handle the score
 */

package com.dkupinic.minesweeper.Model.Score;

import com.dkupinic.minesweeper.Exceptions.InvalidDifficultyException;
import com.dkupinic.minesweeper.Model.Difficulty.Difficulty;

public class Score {
    private static int score = 0;
    private static int scoreDifficultyMultiplier = 1;

    public static int getScore() {
        return score;
    }

    public static void increaseScore() {
        int baseScoreWin = 1;
        Score.score += baseScoreWin * scoreDifficultyMultiplier;
    }

    public static void decreaseScore() {
        int baseScoreLoss = 1;
        Score.score -= baseScoreLoss * scoreDifficultyMultiplier;
    }

    /**
     * sets the score multiplier based on the difficulty
     * @param difficulty the current difficulty
     * @throws InvalidDifficultyException in an exceptional case where the choicebox returns a different difficulty than expected
     */
    public static void setScoreDifficultyMultiplier(Difficulty difficulty) throws InvalidDifficultyException {
        switch (difficulty) {
            case BEGINNER -> scoreDifficultyMultiplier = 1;
            case ADVANCED -> scoreDifficultyMultiplier = 2;
            case ENTHUSIAST -> scoreDifficultyMultiplier = 3;
        }
    }
}
