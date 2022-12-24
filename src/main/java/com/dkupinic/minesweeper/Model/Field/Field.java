package com.dkupinic.minesweeper.Model.Field;

import javafx.scene.Node;

public class Field extends Node {
    private int x;
    private int y;
    private boolean containsBomb;

    public Field(int x, int y, boolean containsBomb) {
        this.x = x;
        this.y = y;
        this.containsBomb = containsBomb;
    }


    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }
}
