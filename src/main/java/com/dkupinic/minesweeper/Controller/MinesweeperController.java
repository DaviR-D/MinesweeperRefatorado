package com.dkupinic.minesweeper.Controller;

import com.dkupinic.minesweeper.Exceptions.InvalidDifficultyException;
import com.dkupinic.minesweeper.Model.Board.BoardManager;
import com.dkupinic.minesweeper.Model.Board.BoardSize;
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
    private void onDifficultySelection() throws InvalidDifficultyException {
        clearPane();
        Difficulty diffc = Difficulty.valueOf(difficultyChoiceBox.getValue());
        BoardManager builder = new BoardManager(diffc);
        builder.drawBoard();
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