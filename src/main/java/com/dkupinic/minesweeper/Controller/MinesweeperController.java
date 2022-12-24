package com.dkupinic.minesweeper.Controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class MinesweeperController {
    @FXML
    private AnchorPane mainAnchor;

    public static Pane pane;

    @FXML
    public void initialize() {
        pane = new Pane();
        pane.setPrefSize(400,400);
        pane.setStyle("-fx-background-color: grey");
        mainAnchor.getChildren().add(pane);

    }


}