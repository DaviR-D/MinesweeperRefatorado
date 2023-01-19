package com.dkupinic.minesweeper.Model;

import com.dkupinic.minesweeper.Controller.MinesweeperController;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.control.Label;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Timer {
    public void startTimer(MinesweeperController controller) {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                LocalTime time = LocalTime.now();
                Platform.runLater(() -> controller.timerLabel.setText(time.format(DateTimeFormatter.ofPattern("HH::mm::ss"))));
            }
        };
        animationTimer.start();
    }
}
