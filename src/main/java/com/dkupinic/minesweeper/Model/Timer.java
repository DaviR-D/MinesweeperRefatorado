package com.dkupinic.minesweeper.Model;

import com.dkupinic.minesweeper.Controller.MinesweeperController;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.control.Label;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Timer {
    private float timePlayed = 0;
    private long lastFrame = -1;

    public void startTimer(MinesweeperController controller) {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (lastFrame != -1) {
                    timePlayed += (new Date().getTime() - lastFrame) / 1000.0;
                }
                lastFrame = new Date().getTime();
                Platform.runLater(() -> controller.timerLabel.setText(String.format("%.2f", timePlayed)));
            }
        };
        animationTimer.start();
    }
}
