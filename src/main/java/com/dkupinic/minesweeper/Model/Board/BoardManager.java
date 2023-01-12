package com.dkupinic.minesweeper.Model.Board;

import com.dkupinic.minesweeper.Controller.MinesweeperController;
import com.dkupinic.minesweeper.Exceptions.InvalidDifficultyException;
import com.dkupinic.minesweeper.Model.Difficulty.Difficulty;
import com.dkupinic.minesweeper.Model.Field.Field;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Objects;

public class BoardManager {
    private final Board boardSize;
    private static Difficulty boardDifficulty;
    private final int columns;
    private final int rows;
    private static Field[][] grid;

    public BoardManager(Difficulty difficulty) {
        boardDifficulty = difficulty;
        this.boardSize = new Board(boardDifficulty);
        this.columns = boardSize.getColumns();
        this.rows = boardSize.getRows();
    }

    /**
     * creates the board including the grid
     * @throws InvalidDifficultyException thrown when mine percentage can not be determined
     */
    public void drawBoard() throws InvalidDifficultyException {
        grid = new Field[columns][rows];
        generateFields();
        drawNeighbours();
    }

    /**
     * generates Fields with all properties
     * @throws InvalidDifficultyException thrown when mine percentage can not be determined
     */
    private void generateFields() throws InvalidDifficultyException {
        for (int columnIterator = 0; columnIterator < columns; columnIterator++) {
            for (int rowIterator = 0; rowIterator < rows; rowIterator++) {

                Field field = new Field(columnIterator, rowIterator, generateBombProbalitity(getMinePercentage()), boardSize);
                grid[columnIterator][rowIterator] = field;
                addToPane(field);
            }
        }
    }

    /**
     * adds the generated fields to the board
     * @param field field which contains nothing, a number or a bomb
     */
    private void addToPane(Field field) {
        MinesweeperController.pane.getChildren().add(field);
    }

    /**
     * generates a random number for bomb generation
     * @param bombPercentage number of bombs in each board, in %
     * @return boolean whether field contains bomb or not
     */
    private boolean generateBombProbalitity(double bombPercentage) {
        return Math.random() < bombPercentage;
    }

    /**
     * get mine percentage based on difficulty
     * @return Exception if an unusual case of no match
     * @throws InvalidDifficultyException thrown if no match
     */
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

    /**
     * draw a number (0-8) on the screen,
     * based on how many mines are around the current field that is being iterated
     */
    private void drawNeighbours() {
        for (int columnIterator = 0; columnIterator < columns; columnIterator++) {
            for (int rowIterator = 0; rowIterator < rows; rowIterator++) {
                Field field = grid[columnIterator][rowIterator];
                checkIfBombField(field);
            }
        }
    }

    /**
     * updates the bomb count if the field doesn't contain a bomb
     * @param field field from drawNeighbours()
     */
    private void checkIfBombField(Field field) {
        if (!field.containsBomb) {
            ArrayList<Field> fields = getNeighbours(field);

            int bombcount = 0;
            updateBombCountText(increaseBombCount(fields, bombcount), field);
        }
    }

    /**
     * draw the bomb count on screen with the updated value
     * @param bombCount integer which will be drawn
     * @param field field which will contain the number
     */
    private void updateBombCountText(int bombCount, Field field) {
        if (bombCount > 0) {
            field.setBombCount(Integer.toString(bombCount));
            setBombCountColor(field);
        }
    }

    private void setBombCountColor(Field field) {
        if (Objects.equals(field.getBombCountString(), "")) return;
        Text bombCount = field.getBombCount();
        int bombNumber = Integer.parseInt(bombCount.getText());
        switch (bombNumber) {
            case 1 -> bombCount.setFill(Color.WHITE);
            case 2 -> bombCount.setFill(Color.YELLOW);
            case 3 -> bombCount.setFill(Color.ORANGE);
            case 4 -> bombCount.setFill(Color.RED);
            case 5 -> bombCount.setFill(Color.GREEN);
            case 6 -> bombCount.setFill(Color.ROYALBLUE);
            case 7 -> bombCount.setFill(Color.DARKMAGENTA);
            case 8 -> bombCount.setFill(Color.BEIGE);
        }
    }

    /**
     * iterate through the field Array and decide if the bomb count needs to be incremented or not
     * @param fields the field array from checkIfBombField()
     * @param bombCount the number of bombs in the surrounding area
     * @return the bomb count
     */
    private int increaseBombCount(ArrayList<Field> fields, int bombCount) {
        for (Field f : fields) {
            if (f.containsBomb) {
                bombCount++;
            }
        }
        return bombCount;
    }

    /**
     * generates an array with the coordinates of the surrounding fields
     * @return the integer array
     */
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

    /**
     * creates an empty list of neighbours and filled array of points
     *
     * @param field field used for iterateNeighbours()
     * @return filled list of neighbours
     */
    private ArrayList<Field> getNeighbours(Field field) {
        ArrayList<Field> neighbours = new ArrayList<>();
        int[] points = generateNeightbourPoints();

        return iterateNeighbours(neighbours, points, field);
    }

    /**
     * iterates over pairs of points and adds valid neighbours to the neighbour list
     *
     * @param neighbours the neighbour list
     * @param points     the pair array
     * @param field      the field to get the coordinates from
     * @return the updated neighbour list
     */
    private ArrayList<Field> iterateNeighbours(ArrayList<Field> neighbours, int[] points, Field field) {
        for (int i = 0; i < points.length; i += 2) {
            int dx = points[i];
            int dy = points[i + 1];

            int newX = field.getxCoord() + dx;
            int newY = field.getyCoord() + dy;

            if (validNeighbour(newX, newY)) {
                neighbours.add(grid[newX][newY]);
            }
        }
        return neighbours;
    }

    /**
     * checks for valid neighbour
     * @param newX new x coordinate from iterateNeighbours()
     * @param newY new y coordinate from iterateNeighbours()
     * @return true for a valid neighbour and false for an invalid neighbour
     */
    private boolean validNeighbour(int newX, int newY) {
        return newX >= 0 && newX < boardSize.getSize() &&
               newY >= 0 && newY < boardSize.getSize();
    }

    public static Difficulty getBoardDifficulty() {
        return boardDifficulty;
    }

    public static Field[][] getGrid() {
        return grid;
    }
}
