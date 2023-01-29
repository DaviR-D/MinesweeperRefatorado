/*-----------------------------------------------------------------------------
 *              Hoehere Technische Bundeslehranstalt STEYR
 *----------------------------------------------------------------------------*/
/**
 * Kurzbeschreibung
 *
 * @author  : Dino Kupinic
 * @date    : 29.1.2023
 *
 * @details
 * Class used to handle logic like recursively revealing the board
 */

package com.dkupinic.minesweeper.Model.Logic;

import com.dkupinic.minesweeper.Controller.MinesweeperController;
import com.dkupinic.minesweeper.Model.Board.Board;
import com.dkupinic.minesweeper.Model.Board.BoardManager;
import com.dkupinic.minesweeper.Model.Field.Field;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class GameLogic {
    private static Field[][] grid = BoardManager.getGrid();
    private static boolean[][] revealedFields = new boolean[grid.length][grid[0].length];

    public static void setGrid(Field[][] grid) {
        GameLogic.grid = grid;
    }

    public static void setRevealedFields(boolean[][] revealedFields) {
        GameLogic.revealedFields = revealedFields;
    }

    public static boolean[][] getRevealedFields() {
        return revealedFields;
    }

    /**
     * reveal a field that has not been revealed yet
     * @param isEmpty whether it contains a bomb or not
     * @param fieldNode the javafx rectangle object so it can be painted black
     */
    public static void revealEmptyField(boolean isEmpty, Rectangle fieldNode) {
        if (isEmpty) {
            fieldNode.setFill(Color.BLACK);
            fieldNode.setDisable(true);
        }
    }

    /**
     * reveal all fields on a loss for example
     */
    public static void revealAllFields() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
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
        if (gridCoordinatesAreNotEmpty(x, y)) return;

        Field temporaryField = getTemporaryField(x, y);
        if (checkFloodFillBorder(temporaryField)) return;

        if (Objects.equals(temporaryField.getBombCountAsString(), "")) {
            revealNeighbours(x, y, revealed);
            revealCorners(x, y, revealed);
        }
    }

    /**
     * recursively reveals the neighbours of a field
     * @param x x coordinate
     * @param y y coordinate
     * @param revealed array which contains all revealed fields
     */
    private static void revealNeighbours(int x, int y, boolean[][] revealed) {
        revealSurroundingFields(x - 1, y, revealed);
        revealSurroundingFields(x + 1, y, revealed);
        revealSurroundingFields(x, y - 1, revealed);
        revealSurroundingFields(x, y + 1, revealed);
    }

    /**
     * recursevely reveals the corners of a field
     * @param x x coordinate
     * @param y y coordinate
     * @param revealed array which contains all revealed fields
     */
    private static void revealCorners(int x, int y, boolean[][] revealed) {
        revealSurroundingFields(x - 1, y + 1, revealed);
        revealSurroundingFields(x + 1, y - 1, revealed);
        revealSurroundingFields(x + 1, y + 1, revealed);
        revealSurroundingFields(x - 1, y - 1, revealed);
    }

    /**
     * generate a temporary field and also reveal it's bombCount
     * @param x x coordinate
     * @param y y coordinate
     * @return the temporary field
     */
    private static Field getTemporaryField(int x, int y) {
        Field temporaryField = grid[x][y];
        revealEmptyField(temporaryField.getIsEmpty(), temporaryField.getFieldNode());
        temporaryField.revealBombCount();
        return temporaryField;
    }

    /**
     * checks if the algorithm goes out of bounds for example
     * @param x x coordinate
     * @param y y coordinate
     * @param revealed array which contains all revealed fields
     * @return true or false
     */
    private static boolean checkValidInputs(int x, int y, boolean[][] revealed) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[x].length || revealed[x][y]) {
            return true;
        }
        revealed[x][y] = true;
        return false;
    }

    /**
     * checks bomb count for a valid digit
     * @param tempF temporary field
     * @return true or false
     */
    private static boolean checkFloodFillBorder(Field tempF) {
        return tempF.getBombCountAsString().matches("\\d");
    }

    /**
     * returns true if the field isn't empty, otherwise false
     * @param x x coordinate
     * @param y y coordinate
     * @return true or false
     */
    private static boolean gridCoordinatesAreNotEmpty(int x, int y) {
        return !grid[x][y].getIsEmpty();
    }

    /**
     * removes all flags from the board
     */
    public static void removeFlags() {
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid.length; y++) {
                grid[x][y].getFlag().setVisible(false);
            }
        }
    }

    /**
     * resets and updates the flag count
     */
    public static void resetFlagCount() {
        Board.setFlagCount(999);
        Field.updateFlagLabel(MinesweeperController.getInstance());
    }

    /**
     * checks if the player has won, this is done by checking if the amount of revealed
     * fields equals the total amount minus the amount of bombs
     * @return true or false
     */
    public static boolean checkWin() {
        int revealedTiles = 0;
        int totalTiles = grid.length * grid[0].length;

        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid.length; y++) {
                if (grid[x][y].getFieldNode().isDisabled()) {
                    revealedTiles++;
                }
            }
        }
        return revealedTiles == totalTiles - Board.getBombCount();
    }
}
