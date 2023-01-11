package com.dkupinic.minesweeper.Model.Board;

import com.dkupinic.minesweeper.Controller.MinesweeperController;
import com.dkupinic.minesweeper.Exceptions.InvalidDifficultyException;
import com.dkupinic.minesweeper.Model.Difficulty.Difficulty;
import com.dkupinic.minesweeper.Model.Field.Field;

import java.util.ArrayList;
import java.util.List;

public class BoardManager {
    private final Board boardSize;
    private final Difficulty boardDifficulty;
    private final int columns;
    private final int rows;
    private Field[][] grid;

    public BoardManager(Difficulty difficulty) {
        this.boardDifficulty = difficulty;
        this.boardSize = new Board(boardDifficulty);
        this.columns = boardSize.getColumns();
        this.rows = boardSize.getRows();
    }

    public void drawBoard() throws InvalidDifficultyException {
        grid = new Field[columns][rows];
        double minePercentage = getMinePercentage();

        generateFields(minePercentage);
        drawNeighbours();
    }

    private void generateFields(double minePercentage) {
        for (int columnIterator = 0; columnIterator < columns; columnIterator++) {
            for (int rowIterator = 0; rowIterator < rows; rowIterator++) {

                Field field = new Field(columnIterator, rowIterator, containsMine(minePercentage), boardSize);
                grid[columnIterator][rowIterator] = field;
                addToPane(field);
            }
        }
    }

    private boolean containsMine(double minePercentage) {
        return Math.random() < minePercentage;
    }

    private void addToPane(Field field) {
        MinesweeperController.pane.getChildren().add(field);
    }

    private void drawNeighbours() {
        for (int columnIterator = 0; columnIterator < columns; columnIterator++) {
            for (int rowIterator = 0; rowIterator < rows; rowIterator++) {

                Field field = grid[columnIterator][rowIterator];
                checkIfBombField(field);
            }
        }
    }

    private void checkIfBombField(Field field) {
        if (!field.containsBomb) {
            ArrayList<Field> fields = (ArrayList<Field>) getNeighbours(field, boardSize);

            int bombcount = 0;
            updateBombCountText(increaseBombCount(fields, bombcount), field);
        }
    }

    private void updateBombCountText(int bombCount, Field field) {
        if (bombCount > 0) {
            field.bombCount.setText(Integer.toString(bombCount));
        }
    }

    private int increaseBombCount(ArrayList<Field> fields, int bombcount) {
        for (Field f : fields) {
            if (f.containsBomb) {
                bombcount++;
            }
        }
        return bombcount;
    }

    private int[] generateNeightbourPoints() {
        return new int[]{
                -1, -1,
                -1,  0,
                -1,  1,
                 0, -1,
                 0,  1,
                 1, -1,
                 1,  0,
                 1,  1
        };
    }

    private List<Field> iterateNeighbours(List<Field> neighbours,
                                          int[] points,
                                          Field field,
                                          Board board
    ) {
        for (int iterator = 0; iterator < points.length; iterator++) {
            int dx = points[iterator];
            int dy = points[iterator + 1];

            int newX = field.getxCoord() + dx;
            int newY = field.getyCoord() + dy;

            if (validNeighbour(board, newX, newY)) {
                neighbours.add(grid[newX][newY]);
            }
            iterator++;
        }
        return neighbours;
    }

    private boolean validNeighbour(Board b, int newX, int newY) {
        return newX >= 0 && newX < b.getSize() && newY >= 0 && newY < b.getSize();
    }

    private List<Field> getNeighbours(Field field, Board board) {
        List<Field> neighbours = new ArrayList<>();
        int[] points = generateNeightbourPoints();

        return iterateNeighbours(neighbours, points, field, board);
    }

    private double getMinePercentage() throws InvalidDifficultyException {
        switch (boardDifficulty) {
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
