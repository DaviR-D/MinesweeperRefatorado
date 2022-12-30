package com.dkupinic.minesweeper.Model.Board;

import com.dkupinic.minesweeper.Controller.MinesweeperController;
import com.dkupinic.minesweeper.Model.Difficulty.Difficulty;
import com.dkupinic.minesweeper.Model.Field.Field;

public class BoardBuilder {

    public void drawBoard() {
        BoardSize boardSize = new BoardSize(Difficulty.BEGINNER);
        Board board = new Board();
        int columns = boardSize.getColumns();
        int rows = boardSize.getRows();

        // Create the fields and add them to the pane
        Field[][] grid = new Field[columns][rows];

        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                boolean hasMine = Math.random() < 0.2;
                Field field = new Field(i, j, hasMine);
                grid[i][j] = field;
                MinesweeperController.pane.getChildren().add(field);
            }
        }
    }

}
