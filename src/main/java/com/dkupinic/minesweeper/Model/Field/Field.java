package com.dkupinic.minesweeper.Model.Field;

import com.dkupinic.minesweeper.Model.Board.Board;
import com.dkupinic.minesweeper.Model.Board.BoardSize;
import com.dkupinic.minesweeper.Model.Logic.GameLogic;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Field extends StackPane {

    public static int FIELD_SIZE;
    private final int xCoord;
    private final int yCoord;
    public final boolean isEmpty;
    public boolean containsBomb;

    public Text bombCount;

    private Rectangle fieldNode;
    private Image bombImage20px;
    private Image bombImage25px;
    private Image bombImage50px;
    private ImageView bomb;

    public Field(int x, int y, boolean containsBomb, Board board) {
        this.xCoord = x;
        this.yCoord = y;
        if (containsBomb) {
            this.containsBomb = true;
            this.isEmpty = false;
        } else {
            this.isEmpty = true;
        }
        initImages();
        calcFieldSize(board);
        prepareFields(board);
    }

    private void calcFieldSize(Board bd) {
        FIELD_SIZE = BoardSize.getLength() / bd.getRows();
    }

    private void prepareFields(Board board) {
        styleFields();
        prepareBombs(board);

        bomb.setOpacity(0);
        bombCount = new Text();
        bombCount.setFill(Color.RED);
        bombCount.setStyle("-fx-font-size: 20px");


        getChildren().addAll(fieldNode, bomb, bombCount);
        setTranslateX(xCoord * FIELD_SIZE);
        setTranslateY(yCoord * FIELD_SIZE);
        handleOnClick();
    }

    private void initImages() {
        bomb = new ImageView();
        bombImage20px = new Image("file:src/main/resources/com/dkupinic/minesweeper/img/bomb/bomb20px.png");
        bombImage25px = new Image("file:src/main/resources/com/dkupinic/minesweeper/img/bomb/bomb25px.png");
        bombImage50px = new Image("file:src/main/resources/com/dkupinic/minesweeper/img/bomb/bomb50px.png");
    }

    private void prepareBombs(Board board) {
        setCorrectImage(board);
        bomb.setVisible(this.containsBomb);
    }

    private void styleFields() {
        fieldNode = new Rectangle(FIELD_SIZE, FIELD_SIZE);
        fieldNode.setFill(Color.web("#0a0d36"));
        fieldNode.setStroke(Color.web("#1f246a"));
        fieldNode.setStrokeWidth(2);
        fieldNode.setVisible(true);
    }

    private void setCorrectImage(Board board) {
        switch (board.getCurrentDifficulty()) {
            case BEGINNER -> bomb.setImage(bombImage50px);
            case ADVANCED -> bomb.setImage(bombImage25px);
            case ENTHUSIAST -> bomb.setImage(bombImage20px);
        }
    }

    private void handleOnClick() {
        handleBombs();
        handleEmpty();
    }

    private void handleBombs() {
        bomb.setOnMouseClicked(event -> {
            System.out.println("bomb " + xCoord + "," + yCoord);
            bomb.setOpacity(1);
            fieldNode.setFill(Color.BLACK);
            //reveal all
            //lose
        });
    }

    private void handleEmpty() {
        fieldNode.setOnMouseClicked(event -> {

            System.out.println("empty " + xCoord + "," + yCoord);
            GameLogic gl = new GameLogic();
            gl.revealEmptyFields(isEmpty, fieldNode);
            // reveal non number

        });
    }

    public int getxCoord() {
        return xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }
}
