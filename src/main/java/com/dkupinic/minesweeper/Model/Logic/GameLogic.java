package com.dkupinic.minesweeper.Model.Logic;

import com.dkupinic.minesweeper.Model.Board.Board;
import com.dkupinic.minesweeper.Model.Board.BoardManager;
import com.dkupinic.minesweeper.Model.Field.Field;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameLogic {

    public static void revealEmptyFields(boolean isEmpty, Rectangle fieldNode) {
        if (isEmpty) {
            fieldNode.setFill(Color.BLACK);
        }


        //revealEmptyFields();
    }

    public static void revealAllFields() {
        Board tempBoard = new Board(BoardManager.getBoardDifficulty());
        Field[][] grid = BoardManager.getGrid();
        for (int i = 0; i < tempBoard.getSize(); i++) {
            for (int j = 0; j < tempBoard.getSize(); j++) {
                Rectangle tempFieldNode = grid[i][j].getFieldNode();
                tempFieldNode.setFill(Color.BLACK);
            }
        }
    }





}
