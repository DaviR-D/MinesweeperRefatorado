package com.dkupinic.minesweeper.Controller;

import com.dkupinic.minesweeper.MinesweeperApplication;
import com.dkupinic.minesweeper.Model.Board.BoardSize;
import com.dkupinic.minesweeper.Model.Difficulty.Difficulty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MinesweeperController {
    @FXML
    private AnchorPane mainAnchor;

    @FXML
    public void initialize() throws IOException {
        Pane pane = new Pane();
        pane.setPrefSize(400,400);
        pane.setStyle("-fx-background-color: red");

        BoardSize boardSize = new BoardSize(Difficulty.BEGINNER);
    }

}