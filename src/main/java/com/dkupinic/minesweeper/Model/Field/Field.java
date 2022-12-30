package com.dkupinic.minesweeper.Model.Field;

import com.dkupinic.minesweeper.Model.Board.Board;
import com.dkupinic.minesweeper.Model.Board.BoardSize;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Field extends StackPane {
    int xCoord;
    int yCoord;
    boolean containsBomb;
    private String text;

    public Text bombCount = new Text();
    private Rectangle fieldNode;

    public static int FIELD_SIZE;
    private Button myButton;

    private void calcFieldSize(Board bd) {
        FIELD_SIZE = BoardSize.getLength() / bd.getRows();
    }

    public Field(int x, int y, boolean containsBomb, Board board) {
        this.xCoord = x;
        this.yCoord = y;
        this.containsBomb = containsBomb;

        calcFieldSize(board);
        prepareFields();
    }

    private void prepareFields() {
        styleFields();
        styleBombs();

        getChildren().addAll(fieldNode, bombCount);
        setTranslateX(xCoord * FIELD_SIZE);
        setTranslateY(yCoord * FIELD_SIZE);

        if (this.containsBomb) {
            text="x";
        }
    }

    private void styleBombs() {
        bombCount.setText(this.containsBomb ? "X" : "");
        bombCount.setStroke(Color.DARKRED);
        bombCount.setVisible(true); // field default not opened
    }

    private void styleFields() {
        fieldNode = new Rectangle(FIELD_SIZE, FIELD_SIZE);
        fieldNode.setFill(Color.BLACK);
        fieldNode.setStroke(Color.web("#1f246a"));
        fieldNode.setStrokeWidth(2);
        fieldNode.setVisible(true);
    }


}
