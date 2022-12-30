package com.dkupinic.minesweeper.Model.Field;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Field extends StackPane {
    int x, y;
    boolean containsBomb;
    private String text;

    public Text bombCount = new Text();
    private Rectangle fieldNode = null;
    public static final int FIELD_SIZE = 50;
    private Button myButton;

    public Field(int x, int y, boolean containsBomb) {
        this.x = x;
        this.y = y;
        this.containsBomb = containsBomb;

        fieldNode = new Rectangle(FIELD_SIZE, FIELD_SIZE);
        fieldNode.setFill(Color.BLACK);
        fieldNode.setStroke(Color.web("#1f246a"));
        fieldNode.setStrokeWidth(2);
        fieldNode.setVisible(true);

        bombCount.setText(this.containsBomb ? "X" : "");
        bombCount.setStroke(Color.DARKRED);
        bombCount.setVisible(true); // field default not opened

        getChildren().addAll(fieldNode, bombCount);
        setTranslateX(x*FIELD_SIZE);
        setTranslateY(y*FIELD_SIZE);

        if (this.containsBomb) {
            text="x";
        }
    }


}
