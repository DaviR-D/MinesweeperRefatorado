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
 * Class used to handle logic like recursively revealing the board
 */

package com.dkupinic.minesweeper.Model.Field;

import com.dkupinic.minesweeper.Controller.MinesweeperController;
import com.dkupinic.minesweeper.Model.Board.Board;
import com.dkupinic.minesweeper.Model.Board.BoardManager;
import com.dkupinic.minesweeper.Model.Board.BoardSize;
import com.dkupinic.minesweeper.Model.Logic.GameLogic;
import com.dkupinic.minesweeper.Model.Logic.Timer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Field extends StackPane {
    public static int FLAG_COUNT = 999;
    private static Timer time;
    private final int xCoord;
    private final int yCoord;
    private boolean isEmpty;
    private boolean containsBomb;
    private boolean flagRevealed;
    private int fieldSize;

    public ImageView flag;
    private ImageView bomb;
    private Image flagImage20px;
    private Image flagImage25px;
    private Image flagImage50px;
    private Image bombImage20px;
    private Image bombImage25px;
    private Image bombImage50px;
    private Rectangle fieldNode;
    private Text bombCount;

    public Field(int x, int y, boolean containsBomb, Board board) {
        time = new Timer();
        this.xCoord = x;
        this.yCoord = y;
        setFieldFlags(containsBomb);
        initBombImages();
        calcFieldSize(board);
        prepareFields(board);
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



    private void setFieldFlags(boolean containsBomb) {
        if (containsBomb) {
            this.containsBomb = true;
            this.isEmpty = false;
            Board.BOMB_COUNT++;
        } else {
            this.isEmpty = true;
        }
    }

    private void calcFieldSize(Board bd) {
        fieldSize = BoardSize.getLength() / bd.getRows();
    }

    private void prepareFields(Board board) {
        styleFields();
        styleBombCount();
        styleFlags(board);
        prepareBombs(board);
        getChildren().addAll(fieldNode, bomb, bombCount, flag);
        offsetFields();
        handleOnClick();
    }

    private void styleFlags(Board board) {
        initFlagImages();
        setCorrectFlagImage(board);
        flag.setVisible(false);
    }

    private void offsetFields() {
        setTranslateX(xCoord * fieldSize);
        setTranslateY(yCoord * fieldSize);
    }

    private void initBombImages() {
        bomb = new ImageView();
        bombImage20px = new Image("file:src/main/resources/com/dkupinic/minesweeper/img/bomb/bomb20px.png");
        bombImage25px = new Image("file:src/main/resources/com/dkupinic/minesweeper/img/bomb/bomb25px.png");
        bombImage50px = new Image("file:src/main/resources/com/dkupinic/minesweeper/img/bomb/bomb50px.png");
    }

    private void initFlagImages() {
        flag = new ImageView();
        flagImage20px = new Image("file:src/main/resources/com/dkupinic/minesweeper/img/flag/flag20px.png");
        flagImage25px = new Image("file:src/main/resources/com/dkupinic/minesweeper/img/flag/flag25px.png");
        flagImage50px = new Image("file:src/main/resources/com/dkupinic/minesweeper/img/flag/flag50px.png");
    }

    private void prepareBombs(Board board) {
        setCorrectBombImage(board);
        bomb.setVisible(this.containsBomb);
        bomb.setOpacity(0);
    }

    private void styleBombCount() {
        bombCount = new Text();
        bombCount.setOpacity(0);
        setBombCountFontSize();
    }

    private void setBombCountFontSize() {
        switch (BoardManager.getBoardDifficulty()) {
            case BEGINNER -> bombCount.setStyle("-fx-font-size: 32px; -fx-font-family: 'Consolas'");
            case ADVANCED -> bombCount.setStyle("-fx-font-size: 22px; -fx-font-family: 'Consolas'");
            case ENTHUSIAST -> bombCount.setStyle("-fx-font-size: 18px; -fx-font-family: 'Consolas'");
        }
    }

    private void styleFields() {
        fieldNode = new Rectangle(fieldSize, fieldSize);
        fieldNode.setFill(Color.web("#0a0d36"));
        fieldNode.setStroke(Color.web("#1f246a"));
        fieldNode.setStrokeWidth(2);
        fieldNode.setVisible(true);
    }

    private void setCorrectBombImage(Board board) {
        switch (board.getCurrentDifficulty()) {
            case BEGINNER -> bomb.setImage(bombImage50px);
            case ADVANCED -> bomb.setImage(bombImage25px);
            case ENTHUSIAST -> bomb.setImage(bombImage20px);
        }
    }

    private void setCorrectFlagImage(Board board) {
        switch (board.getCurrentDifficulty()) {
            case BEGINNER -> flag.setImage(flagImage50px);
            case ADVANCED -> flag.setImage(flagImage25px);
            case ENTHUSIAST -> flag.setImage(flagImage20px);
        }
    }

    private void handleOnClick() {
        onBombFieldClick();
        onEmptyFieldClick();
    }

    private void onBombFieldClick() {
        bomb.setOnMouseClicked(this::handleBombField);
    }

    private void onEmptyFieldClick() {
        bombCount.setOnMouseClicked(this::handleEvent);
        fieldNode.setOnMouseClicked(this::handleEvent);
        flag.setOnMouseClicked(event -> handleFlagField());
    }

    private void handleEvent(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            handleNonBombField();
            initTimer();
        }
        if (event.getButton() == MouseButton.SECONDARY) {
            handleFlagField();
        }
    }

    private static void initTimer() {
        if (!Timer.activeTimer) {
            time.startTimer(MinesweeperController.getInstance());
        }
    }

    private void handleFlagField() {
        if (!flagRevealed) {
            flag.setVisible(true);
            flagRevealed = true;
            FLAG_COUNT--;
        } else {
            flag.setVisible(false);
            flagRevealed = false;
            FLAG_COUNT++;
        }
        updateFlagLabel(MinesweeperController.getInstance());
    }

    public static void updateFlagLabel(MinesweeperController controller) {
        controller.flagLabel.setText(String.valueOf(FLAG_COUNT));
    }

    private void handleNonBombField() {
        revealClickedField();
        int x = getxCoord();
        int y = getyCoord();
        GameLogic.revealSurroundingFields(x, y, GameLogic.revealedFields);
    }

    public void revealClickedField() {
        revealBombCount();
        System.out.println("empty " + xCoord + "," + yCoord);
    }

    public void revealBombCount() {
        bombCount.setOpacity(1);
    }

    private void handleBombField(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            revealBomb();
            GameLogic.revealAllFields();
            GameLogic.removeFlags();
            GameLogic.resetFlagCount();
            time.stopTimer();
        }
        if (event.getButton() == MouseButton.SECONDARY) {
            handleFlagField();
        }
    }



    public void revealBomb() {
        bomb.setOpacity(1);
        fieldNode.setFill(Color.BLACK);
        System.out.println("bomb " + xCoord + "," + yCoord);
    }
}