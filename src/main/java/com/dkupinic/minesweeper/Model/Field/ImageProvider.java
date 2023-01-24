package com.dkupinic.minesweeper.Model.Field;

import javafx.scene.image.Image;

public class ImageProvider {
    private Image flagImage20px;
    private Image flagImage25px;
    private Image flagImage50px;
    private Image bombImage20px;
    private Image bombImage25px;
    private Image bombImage50px;

    private static ImageProvider instance;
    private ImageProvider() {}

    public Image getFlagImage20px() {
        if (flagImage20px == null) {
            flagImage20px = new Image("file:src/main/resources/com/dkupinic/minesweeper/img/flag/flag20px.png");
        }
        return flagImage20px;
    }

    public Image getFlagImage25px() {
        if (flagImage25px == null) {
            flagImage25px = new Image("file:src/main/resources/com/dkupinic/minesweeper/img/flag/flag25px.png");
        }
        return flagImage25px;
    }

    public Image getFlagImage50px() {
        if (flagImage50px == null) {
            flagImage50px = new Image("file:src/main/resources/com/dkupinic/minesweeper/img/flag/flag50px.png");
        }
        return flagImage50px;
    }

    public Image getBombImage20px() {
        if (bombImage20px == null) {
            bombImage20px = new Image("file:src/main/resources/com/dkupinic/minesweeper/img/bomb/bomb20px.png");
        }
        return bombImage20px;
    }

    public Image getBombImage25px() {
        if (bombImage25px == null) {
            bombImage25px = new Image("file:src/main/resources/com/dkupinic/minesweeper/img/bomb/bomb25px.png");
        }
        return bombImage25px;
    }

    public Image getBombImage50px() {
        if (bombImage50px == null) {
            bombImage50px = new Image("file:src/main/resources/com/dkupinic/minesweeper/img/bomb/bomb50px.png");
        }
        return bombImage50px;
    }
    
    public static ImageProvider getInstance() {
        if (instance == null) {
            instance = new ImageProvider();
        }

        return instance;
    }
}
