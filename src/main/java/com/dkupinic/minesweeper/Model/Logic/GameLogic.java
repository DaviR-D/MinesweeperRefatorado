package com.dkupinic.minesweeper.Model.Logic;

import com.dkupinic.minesweeper.Model.Board.Board;
import com.dkupinic.minesweeper.Model.Board.BoardManager;
import com.dkupinic.minesweeper.Model.Field.Field;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class GameLogic {
    private static final Field[][] grid = BoardManager.getGrid();
    public static boolean[][] revealedFields = new boolean[grid.length][grid[0].length];

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
     */
    public static void revealSurroundingFields(int x, int y, boolean[][] revealed) {
        if (checkValidInputs(x, y, revealed)) return;
        if (gridCoordinatesArentEmpty(x, y)) return;

        Field temporaryField = getTemporaryField(x, y);
        if (checkFloodFillBorder(temporaryField)) return;

        if (Objects.equals(temporaryField.getBombCountAsString(), "")) {
            revealSurroundingFields(x - 1, y, revealed);
            revealSurroundingFields(x + 1, y, revealed);
            revealSurroundingFields(x, y - 1, revealed);
            revealSurroundingFields(x, y + 1, revealed);
        }
    }

    private static Field getTemporaryField(int x, int y) {
        Field temporaryField = grid[x][y];
        revealEmptyField(temporaryField.getIsEmpty(), temporaryField.getFieldNode());
        temporaryField.revealBombCount();
        return temporaryField;
    }

    private static boolean checkValidInputs(int x, int y, boolean[][] revealed) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[x].length || revealed[x][y]) {
            return true;
        }
        revealed[x][y] = true;
        return false;
    }

    private static boolean checkFloodFillBorder(Field tempF) {
        return tempF.getBombCountAsString().matches("\\d");
    }

    private static boolean gridCoordinatesArentEmpty(int x, int y) {
        return !grid[x][y].getIsEmpty();
    }


}
