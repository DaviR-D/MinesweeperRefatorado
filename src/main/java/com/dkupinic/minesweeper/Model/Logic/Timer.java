// Timer.java
package com.dkupinic.minesweeper.Model.Logic;

import com.dkupinic.minesweeper.Controller.MinesweeperController;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import java.time.Duration;

public class Timer {
    private static AnimationTimer animationTimer;
    private static boolean activeTimer;
    private static Duration elapsedTime = Duration.ZERO;

    public static boolean getActiveTimer() {
        return activeTimer;
    }

    public static void setActiveTimer(boolean active) {
        activeTimer = active;
    }

    public static void setTimePlayed(int seconds) {
        elapsedTime = Duration.ofSeconds(seconds);
    }

    public void startTimer(MinesweeperController controller) {
        activeTimer = true;
        animationTimer = new AnimationTimer() {
            private long lastFrame = -1;

            @Override
            public void handle(long now) {
                if (lastFrame != -1) {
                    Duration delta = Duration.ofNanos(now - lastFrame);
                    elapsedTime = elapsedTime.plus(delta);
                }
                lastFrame = now;
                double seconds = elapsedTime.toMillis() / 1000.0;
                String formattedTime = String.format("%.2fs", seconds);
                Platform.runLater(() -> controller.setTimerLabel(formattedTime));
            }
        };
        animationTimer.start();
    }

    public void stopTimer() {
        if (animationTimer != null) {
            animationTimer.stop();
        }
        elapsedTime = Duration.ZERO;
        activeTimer = false;
    }
}
