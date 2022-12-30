package com.dkupinic.minesweeper.Controller;

import com.dkupinic.minesweeper.Model.Board.BoardBuilder;
import com.dkupinic.minesweeper.Model.Difficulty.Difficulty;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class MinesweeperController {
    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private ChoiceBox<String> difficultyChoiceBox;

    public static Pane pane;

    @FXML
    public void initialize() {
        initBoardPane();
        addDifficultyOptions();
    }

    @FXML
    private void onDifficultySelection() {
        BoardBuilder builder = new BoardBuilder();
        Difficulty d = Difficulty.valueOf(difficultyChoiceBox.getValue());
        builder.drawBoard(d);
    }

    private void initBoardPane() {
        pane = new Pane();
        pane.setPrefSize(400,400);
        mainAnchor.getChildren().add(pane);
    }

    private void addDifficultyOptions() {
        difficultyChoiceBox.getItems().addAll(
                String.valueOf(Difficulty.BEGINNER),
                String.valueOf(Difficulty.ADVANCED),
                String.valueOf(Difficulty.ENTHUSIAST));

        difficultyChoiceBox.setValue(String.valueOf(Difficulty.BEGINNER));
    }


}