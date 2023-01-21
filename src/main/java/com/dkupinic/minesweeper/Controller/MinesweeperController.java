package com.dkupinic.minesweeper.Controller;

import com.dkupinic.minesweeper.Exceptions.InvalidDifficultyException;
import com.dkupinic.minesweeper.Model.Board.BoardManager;
import com.dkupinic.minesweeper.Model.Board.BoardSize;
import com.dkupinic.minesweeper.Model.Difficulty.Difficulty;
import com.dkupinic.minesweeper.Model.Logic.Timer;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class MinesweeperController {
    public Label timerLabel;
    public Label flagLabel;
    public Label bombLabel;
    public AnchorPane mainAnchor;
    public ChoiceBox<String> difficultyChoiceBox;
    public ImageView resetButton;

    public static Pane pane;

    public void setTimerLabel(String time) {
        timerLabel.setText(time);
    }

    public static MinesweeperController instance;
    public MinesweeperController() {
        instance = this;
    }

    public void initialize() {
        initBoardPane();
        addDifficultyOptions();
    }

    @FXML
    private void onDifficultySelection() throws InvalidDifficultyException {
        clearPane();
        Timer.setTimePlayed(0);
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

    public static MinesweeperController getInstance() {
        if (instance == null) {
            instance = new MinesweeperController();
        }

        return instance;
    }









}