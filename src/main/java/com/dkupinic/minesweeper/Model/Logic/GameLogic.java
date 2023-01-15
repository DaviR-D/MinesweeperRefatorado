package com.dkupinic.minesweeper.Model.Logic;

import com.dkupinic.minesweeper.Model.Board.Board;
import com.dkupinic.minesweeper.Model.Board.BoardManager;
import com.dkupinic.minesweeper.Model.Field.Field;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class GameLogic {
    private static Field[][] grid = BoardManager.getGrid();

    public static void revealEmptyField(boolean isEmpty, Rectangle fieldNode) {
        if (isEmpty) {
            fieldNode.setFill(Color.BLACK);
        }
    }

    public static void revealAllFields() {
        Board tempBoard = new Board(BoardManager.getBoardDifficulty());

        for (int i = 0; i < tempBoard.getSize(); i++) {
            for (int j = 0; j < tempBoard.getSize(); j++) {
                Rectangle tempFieldNode = grid[i][j].getFieldNode();
                tempFieldNode.setFill(Color.BLACK);
                grid[i][j].revealClickedField();
                grid[i][j].revealBomb();
            }
        }
    }

    /**
     * flood fill algorithm to reveal surrounding fields
     * <a href="https://en.wikipedia.org/wiki/Flood_fill">Wikipedia</a>
     */
    public static void revealSurroundingFields(int i, int j) {
        boolean invalidxCoordinates = i < 0 || i >= grid.length;
        boolean invalidyCoordinates = j < 0 || j >= grid[i].length;

        if (invalidxCoordinates || invalidyCoordinates) {
            return;
        }

        if (grid[i][j].getIsEmpty()) {
            return;
        }
        Field tempF = grid[i][j];
        revealEmptyField(tempF.getIsEmpty(), tempF.getFieldNode());

        //if (Objects.equals(grid[i][j].getBombCountAsString(), "0")) {
            revealSurroundingFields(i - 1, j);
            revealSurroundingFields(i + 1, j);
            revealSurroundingFields(i , j - 1);
            revealSurroundingFields(i , j + 1);
        //}

    }







}
