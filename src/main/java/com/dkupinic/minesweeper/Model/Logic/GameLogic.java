package com.dkupinic.minesweeper.Model.Logic;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameLogic {

    public void revealEmptyFields(boolean isEmpty, Rectangle fieldNode) {
        if (isEmpty) {
            fieldNode.setFill(Color.BLACK);
        }

        //revealEmptyFields();
    }


}
