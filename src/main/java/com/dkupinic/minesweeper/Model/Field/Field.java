package com.dkupinic.minesweeper.Model.Field;

import com.dkupinic.minesweeper.Model.Board.Board;
import com.dkupinic.minesweeper.Model.Board.BoardSize;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Field extends StackPane {
    int xCoord;
    int yCoord;
    boolean containsBomb;

    private Rectangle fieldNode;
    public static int FIELD_SIZE;

    private Image bombImage20px;
    private Image bombImage25px;
    private Image bombImage50px;
    private ImageView bomb = new ImageView();

    public Field(int x, int y, boolean containsBomb, Board board) {
        this.xCoord = x;
        this.yCoord = y;
        this.containsBomb = containsBomb;
        initImages();
        calcFieldSize(board);
        prepareFields(board);
    }

    private void calcFieldSize(Board bd) {
        FIELD_SIZE = BoardSize.getLength() / bd.getRows();
    }

    private void initImages() {
        bombImage20px = new Image("file:src/main/resources/com/dkupinic/minesweeper/img/bomb/bomb20px.png");
        bombImage25px = new Image("file:src/main/resources/com/dkupinic/minesweeper/img/bomb/bomb25px.png");
        bombImage50px = new Image("file:src/main/resources/com/dkupinic/minesweeper/img/bomb/bomb50px.png");
    }

    private void prepareFields(Board board) {
        styleFields();
        styleBombs();
        setCorrectImage(board);
        getChildren().addAll(fieldNode, bomb);
        setTranslateX(xCoord * FIELD_SIZE);
        setTranslateY(yCoord * FIELD_SIZE);
    }

    private void styleBombs() {
        bomb.setVisible(this.containsBomb);
    }

    private void styleFields() {
        fieldNode = new Rectangle(FIELD_SIZE, FIELD_SIZE);
        fieldNode.setFill(Color.BLACK);
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

}
