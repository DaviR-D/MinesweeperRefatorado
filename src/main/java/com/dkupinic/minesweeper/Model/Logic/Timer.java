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
 * Class used to handle the timer
 */

package com.dkupinic.minesweeper.Model.Logic;

import com.dkupinic.minesweeper.Controller.MinesweeperController;
import com.dkupinic.minesweeper.Model.Field.Field;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;

import java.util.Date;

public class Timer {
    public static boolean activeTimer;
    private static float timePlayed = 0;
    private long lastFrame = -1;
    public static AnimationTimer animationTimer;

    /**
     * starts the timer and updates the timer label
     * @param controller the MinesweeperController
     */
    public void startTimer(MinesweeperController controller) {
        activeTimer = true;
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (lastFrame != -1) {
                    timePlayed += (new Date().getTime() - lastFrame) / 1000.0;
                }
                lastFrame = new Date().getTime();
                Platform.runLater(() -> controller.setTimerLabel(String.format("%.2fs", timePlayed)));
            }
        };
        animationTimer.start();
    }

    /**
     * stops the animation timer
     */
    public void stopTimer() {
        if (animationTimer != null) {
            animationTimer.stop();
        }

        timePlayed = 0;
        lastFrame = -1;


    }

    /**
     * sets the time played to a passed value
     * @param value
     */
    public static void setTimePlayed(int value) {
        timePlayed = value;
    }
}
