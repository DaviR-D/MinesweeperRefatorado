package com.dkupinic.minesweeper.Model.Board;

import com.dkupinic.minesweeper.Controller.MinesweeperController;
import com.dkupinic.minesweeper.Exceptions.InvalidDifficultyException;
import com.dkupinic.minesweeper.Model.Difficulty.Difficulty;
import com.dkupinic.minesweeper.Model.Field.Field;

import java.util.ArrayList;
import java.util.List;

public class BoardBuilder {
    private static Field[][] grid;

    public void drawBoard(Difficulty difficulty) throws InvalidDifficultyException {
        Board boardSize = new Board(difficulty);
        int columns = boardSize.getColumns();
        int rows = boardSize.getRows();

        // Create the fields and add them to the pane
        grid = new Field[columns][rows];
        double minePercentage = getMinePercentage(difficulty);


        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {

                boolean hasMine = Math.random() < minePercentage;
                Field field = new Field(i, j, hasMine, boardSize);

                grid[i][j] = field;

                MinesweeperController.pane.getChildren().add(field);

            }
        }

        for (int a = 0; a < columns; a++) {
            for (int b = 0; b < rows; b++) {

                Field fd = grid[a][b];

                if (!fd.containsBomb) {
                    ArrayList<Field> fields = (ArrayList<Field>) getNeighbours(fd,boardSize);

                    int bombcount = 0;
                    for (Field f : fields) {
                        if (f.containsBomb) {
                            bombcount++;
                        }
                    }
                    //field.bombCount.setText(Integer.toString(bombcount));
                    if (bombcount > 0) {
                        fd.bombCount.setText(Integer.toString(bombcount));
                    }
                }

            }
        }
    }

    static List<Field> getNeighbours(Field field, Board b) {

        List<Field> neighbours = new ArrayList<>();

        int[] points = new int[]{
                -1, -1,
                -1,  0,
                -1,  1,
                 0, -1,
                 0,  1,
                 1, -1,
                 1,  0,
                 1,  1
        };

        for (int i = 0; i < points.length; i++) {
            int dx = points[i];
            int dy = points[i+1];

            int newX = field.getxCoord() + dx;
            int newY = field.getyCoord() + dy;
            if (newX >= 0 && newX < b.getSize() && newY >= 0 && newY < b.getSize()) {
                neighbours.add(grid[newX][newY]);
            }
            i++;
        }

        return neighbours;
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
