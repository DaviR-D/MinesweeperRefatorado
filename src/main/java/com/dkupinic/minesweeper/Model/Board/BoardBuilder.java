package com.dkupinic.minesweeper.Model.Board;

import com.dkupinic.minesweeper.Controller.MinesweeperController;
import com.dkupinic.minesweeper.Exceptions.InvalidDifficultyException;
import com.dkupinic.minesweeper.Model.Difficulty.Difficulty;
import com.dkupinic.minesweeper.Model.Field.Field;

public class BoardBuilder {

    public void drawBoard(Difficulty difficulty) throws InvalidDifficultyException {
        Board boardSize = new Board(difficulty);
        int columns = boardSize.getColumns();
        int rows = boardSize.getRows();

        // Create the fields and add them to the pane
        Field[][] grid = new Field[columns][rows];
        double minePercentage = getMinePercentage(difficulty);

        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {

                boolean hasMine = Math.random() < minePercentage;
                Field field = new Field(i, j, hasMine, boardSize);
                grid[i][j] = field;
                MinesweeperController.pane.getChildren().add(field);
            }
        }
    }

    private double getMinePercentage(Difficulty dc) throws InvalidDifficultyException {
        switch (dc) {
            case BEGINNER, ADVANCED -> {
                return 0.16;
            }
            case ENTHUSIAST -> {
                return 0.21;
            }
        }
        throw new InvalidDifficultyException();
    }

}
