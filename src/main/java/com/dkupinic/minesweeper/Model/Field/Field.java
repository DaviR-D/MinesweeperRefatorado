/*-----------------------------------------------------------------------------
 *              Hoehere Technische Bundeslehranstalt STEYR
 *----------------------------------------------------------------------------*/
/**
 * Kurzbeschreibung
 *
 * @author  : Dino Kupinic
 * @date    : 23.1.2023
 *
 * @details
 * Class used to handle everything related to each field in the minesweeper grid
 */

package com.dkupinic.minesweeper.Model.Field;

import com.dkupinic.minesweeper.Controller.MinesweeperController;
import com.dkupinic.minesweeper.Model.Board.Board;
import com.dkupinic.minesweeper.Model.Board.BoardManager;
import com.dkupinic.minesweeper.Model.Board.BoardSize;
import com.dkupinic.minesweeper.Model.Logic.GameLogic;
import com.dkupinic.minesweeper.Model.Logic.Timer;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Field extends StackPane {
    public static Timer time;
    private final int xCoord;
    private final int yCoord;
    private boolean isEmpty;
    private boolean containsBomb;
    private boolean flagRevealed;
    private int fieldSize;

    private ImageView flag;
    private ImageView bomb;

    private Rectangle fieldNode;
    private Text bombCount;

    public Field(int x, int y, boolean containsBomb, Board board) {
        this.xCoord = x;
        this.yCoord = y;
        time = new Timer();
        setCorrectValueOfFields(containsBomb);
        initBombImages();
        calcFieldSize(board);
        prepareFields(board);
    }

    public ImageView getFlag() {
        return flag;
    }

    public Rectangle getFieldNode() {
        return fieldNode;
    }

    public int getxCoord() {
        return xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setBombCount(String count) {
        bombCount.setText(count);
    }

    public Text getBombCount() {
        return bombCount;
    }

    public String getBombCountAsString() {
        return bombCount.getText();
    }

    public boolean getContainsBomb() {
        return containsBomb;
    }

    public boolean getIsEmpty() {
        return isEmpty;
    }

    /**
     * Sets the field as empty if there is no bomb and to containsBomb if there is a bomb
     * @param containsBomb boolean whether there is a bomb or not
     */
    private void setCorrectValueOfFields(boolean containsBomb) {
        if (containsBomb) {
            this.containsBomb = true;
            this.isEmpty = false;
            Board.setBombCount(Board.getBombCount() + 1);
        } else {
            this.isEmpty = true;
        }
    }

    /**
     * calculate the size of the fields
     * @param bd board object containing information about the amount of rows and columns
     */
    private void calcFieldSize(Board bd) {
        fieldSize = BoardSize.getLength() / bd.getRows();
    }

    /**
     * calls methods to give the fields correct styling and functionality
     * @param board board object containing information about the amount of rows and columns
     */
    private void prepareFields(Board board) {
        styleFields();
        styleBombCount();
        styleFlags(board);
        prepareBombs(board);
        addToPane();
        offsetFields();
        handleOnClick();
    }

    /**
     * Adds the fieldNode, the bombs, the bombCount text and the flags to the pane
     */
    private void addToPane() {
        getChildren().addAll(fieldNode, bomb, bombCount, flag);
    }

    /**
     * styles the flags
     * @param board board object containing information about the amount of rows and columns
     */
    private void styleFlags(Board board) {
        initFlagImages();
        setCorrectFlagImage(board);
        revealFlag(flag, false);
    }

    /**
     * sets the flag either visible or invisible
     * @param flag flag image
     * @param bool true or false
     */
    private void revealFlag(ImageView flag, boolean bool) {
        flag.setVisible(bool);
    }

    /**
     * apply offset to fields, so they don't stack on each other
     */
    private void offsetFields() {
        setTranslateX(xCoord * fieldSize);
        setTranslateY(yCoord * fieldSize);
    }

    /**
     * creats a new bomb image
     */
    private void initBombImages() {
        bomb = new ImageView();
    }

    /**
     * creates a new flag image
     */
    private void initFlagImages() {
        flag = new ImageView();
    }

    /**
     * calls methods to give the bombs correct functionality
     * @param board board object to get the difficulty
     */
    private void prepareBombs(Board board) {
        setCorrectBombImage(board);
        revealFlag(bomb, this.containsBomb);
        setBombOpacity(0);
    }

    /**
     * sets the opacity to value
     * @param value value between 0 and 1
     */
    private void setBombOpacity(int value) {
        bomb.setOpacity(value);
    }

    /**
     * gives the bombs correct styling
     */
    private void styleBombCount() {
        bombCount = new Text();
        bombCount.setOpacity(0);
        setBombCountFontSize();
    }

    /**
     * gives the bombCount a different font size based on difficulty
     */
    private void setBombCountFontSize() {
        switch (BoardManager.getBoardDifficulty()) {
            case BEGINNER -> bombCount.setStyle("-fx-font-size: 32px; -fx-font-family: 'Consolas'");
            case ADVANCED -> bombCount.setStyle("-fx-font-size: 22px; -fx-font-family: 'Consolas'");
            case ENTHUSIAST -> bombCount.setStyle("-fx-font-size: 18px; -fx-font-family: 'Consolas'");
        }
    }

    /**
     * applies correct styling to the fields
     */
    private void styleFields() {
        fieldNode = new Rectangle(fieldSize, fieldSize);
        fieldNode.setFill(Color.web("#0a0d36"));
        fieldNode.setStroke(Color.web("#1f246a"));
        fieldNode.setStrokeWidth(2);
        fieldNode.setVisible(true);
    }

    /**
     * gives the bombs a different image (which has a different size) based on difficulty
     */
    private void setCorrectBombImage(Board board) {
        switch (board.getCurrentDifficulty()) {
            case BEGINNER -> bomb.setImage(ImageProvider.getInstance().getBombImage50px());
            case ADVANCED -> bomb.setImage(ImageProvider.getInstance().getBombImage25px());
            case ENTHUSIAST -> bomb.setImage(ImageProvider.getInstance().getBombImage20px());
        }
    }

    /**
     * gives the flags a different image (which has a different size) based on difficulty
     */
    private void setCorrectFlagImage(Board board) {
        switch (board.getCurrentDifficulty()) {
            case BEGINNER -> flag.setImage(ImageProvider.getInstance().getFlagImage50px());
            case ADVANCED -> flag.setImage(ImageProvider.getInstance().getFlagImage25px());
            case ENTHUSIAST -> flag.setImage(ImageProvider.getInstance().getFlagImage20px());
        }
    }

    /**
     * calls methods when the user clicks on a field
     */
    private void handleOnClick() {
        onBombFieldClick();
        onEmptyFieldClick();
    }

    /**
     * calls the handleBombField() method when the user clicks on a bomb
     */
    private void onBombFieldClick() {
        bomb.setOnMouseClicked(this::handleBombField);
    }

    /**
     * calls methods when the user clicks on an empty field
     */
    private void onEmptyFieldClick() {
        bombCount.setOnMouseClicked(this::handleEvent);
        fieldNode.setOnMouseClicked(this::handleEvent);
        flag.setOnMouseClicked(event -> handleFlagField());
    }

    /**
     * calls a method based on whether it was a primary or secondary mouse click
     * @param event mouse event
     */
    private void handleEvent(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            handleNonBombField();
            initTimer();
        }
        if (event.getButton() == MouseButton.SECONDARY) {
            handleFlagField();
        }
    }

    /**
     * checks if there is an active timer to decide whether a new one should be started
     */
    private static void initTimer() {
        if (!Timer.getActiveTimer()) {
            time.startTimer(MinesweeperController.getInstance());
        }
    }

    /**
     * handles the logic of removing and setting flags on fields
     */
    private void handleFlagField() {
        if (!flagRevealed) {
            revealFlag(flag, true);
            flagRevealed = true;
            Board.setFlagCount(Board.getFlagCount() - 1);
        } else {
            revealFlag(flag, false);
            flagRevealed = false;
            Board.setFlagCount(Board.getFlagCount() + 1);
        }
        updateFlagLabel(MinesweeperController.getInstance());
    }

    /**
     * updates the flag label to the flag count that gets incremented or decremented on mouse clicks
     */
    public static void updateFlagLabel(MinesweeperController controller) {
        controller.flagLabel.setText(String.valueOf(Board.getFlagCount()));
    }

    /**
     * gets the coordinates of the field that was clicked on and passes it to the revealSurroundFields() function
     * which recursively reveals empty fields
     */
    private void handleNonBombField() {
        revealBombCount();
        int x = getxCoord();
        int y = getyCoord();
        GameLogic.revealSurroundingFields(x, y, GameLogic.getRevealedFields());
    }

    /**
     * calls methods based on if the bomb was clicked or a flag was set on it
     * @param event mouse event
     */
    private void handleBombField(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            GameLogic.checkWin();
            cleanUpForNextRound();
        }
        if (event.getButton() == MouseButton.SECONDARY) {
            handleFlagField();
        }
    }

    /**
     * calls methods to clean up state for a new round
     */
    private void cleanUpForNextRound() {
        revealBomb();
        GameLogic.revealAllFields();
        GameLogic.removeFlags();
        GameLogic.resetFlagCount();
        time.stopTimer();
    }

    /**
     * reveals a bomb by setting its opacity to maximum
     */
    public void revealBomb() {
        setBombOpacity(1);
    }

    /**
     * sets the bombCount opacity to maximum
     */
    public void revealBombCount() {
        bombCount.setOpacity(1);
    }
}