package com.dkupinic.minesweeper.Controller;

import com.dkupinic.minesweeper.Exceptions.InvalidDifficultyException;
import com.dkupinic.minesweeper.Model.Board.BoardManager;
import com.dkupinic.minesweeper.Model.Board.BoardSize;
import com.dkupinic.minesweeper.Model.Difficulty.Difficulty;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MinesweeperController {
    @FXML
    public Label timerLabel;
    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private ChoiceBox<String> difficultyChoiceBox;
    @FXML
    private ImageView resetButton;

    public static Pane pane;

    public Label getTimerLabel() {
        return timerLabel;
    }

    public void setTimerLabel(String time) {
        timerLabel.setText(time);
    }

    @FXML
    public void initialize() {
        initBoardPane();
        addDifficultyOptions();
    }

    @FXML
    private void onDifficultySelection() throws InvalidDifficultyException {
        clearPane();
        Difficulty diffc = Difficulty.valueOf(difficultyChoiceBox.getValue());
        BoardManager builder = new BoardManager(diffc);
        builder.drawBoard();
    }

    @FXML
    private void onResetButtonClick() throws InvalidDifficultyException {
        onDifficultySelection();
    }

    private void clearPane() {
        pane.getChildren().clear();
    }

    private void initBoardPane() {
        pane = new Pane();
        pane.setPrefSize(BoardSize.getLength(), BoardSize.getWidth());
        mainAnchor.getChildren().add(pane);
    }

    private void addDifficultyOptions() {
        difficultyChoiceBox.getItems().addAll(
            String.valueOf(Difficulty.BEGINNER),
            String.valueOf(Difficulty.ADVANCED),
            String.valueOf(Difficulty.ENTHUSIAST)
        );

        difficultyChoiceBox.setValue(String.valueOf(Difficulty.BEGINNER));
    }




}