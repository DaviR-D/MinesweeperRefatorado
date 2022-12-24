package com.dkupinic.minesweeper.Model.Board;

import com.dkupinic.minesweeper.Controller.MinesweeperController;
import com.dkupinic.minesweeper.Model.Difficulty.Difficulty;
import com.dkupinic.minesweeper.Model.Field.Field;

public class BoardBuilder {

    public void drawBoard() {
        BoardSize boardSize = new BoardSize(Difficulty.BEGINNER);
        Board board = new Board();

        int x = board.getLength() / boardSize.getColumns();
        int y = board.getWidth() / boardSize.getRows();

        Field grid[][] = new Field[x][y];

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; i++) {
                Field field = new Field(i, j, Math.random() < 0.2);
                grid[i][j] = field;
                MinesweeperController.pane.getChildren().add(field);
            }
        }

    }
}
