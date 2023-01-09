package com.dkupinic.minesweeper.Model.Logic;

import com.dkupinic.minesweeper.Model.Field.Field;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class GameLogic {

    public void revealEmptyFields(boolean isEmpty, Rectangle fieldNode) {
        if (isEmpty) {
            fieldNode.setFill(Color.BLACK);
        }

        //revealEmptyFields();
    }


}
