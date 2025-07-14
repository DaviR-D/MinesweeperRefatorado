// ImageProvider.java
package com.dkupinic.minesweeper.Model.Field;

import javafx.scene.image.Image;
import java.util.Map;
import java.util.HashMap;

public class ImageProvider {
    private static ImageProvider instance;
    private final Map<String, Image> cache = new HashMap<>();

    private ImageProvider() {}

    public static ImageProvider getInstance() {
        if (instance == null) {
            instance = new ImageProvider();
        }
        return instance;
    }

    private Image loadImage(String path) {
        return new Image(path);
    }

    private Image getCachedImage(String key, String path) {
        return cache.computeIfAbsent(key, k -> loadImage(path));
    }

    public Image getFlagImage20px() {
        return getCachedImage("flag20", "file:src/main/resources/com/dkupinic/minesweeper/img/flag/flag20px.png");
    }

    public Image getFlagImage25px() {
        return getCachedImage("flag25", "file:src/main/resources/com/dkupinic/minesweeper/img/flag/flag25px.png");
    }

    public Image getFlagImage50px() {
        return getCachedImage("flag50", "file:src/main/resources/com/dkupinic/minesweeper/img/flag/flag50px.png");
    }

    public Image getBombImage20px() {
        return getCachedImage("bomb20", "file:src/main/resources/com/dkupinic/minesweeper/img/bomb/bomb20px.png");
    }

    public Image getBombImage25px() {
        return getCachedImage("bomb25", "file:src/main/resources/com/dkupinic/minesweeper/img/bomb/bomb25px.png");
    }

    public Image getBombImage50px() {
        return getCachedImage("bomb50", "file:src/main/resources/com/dkupinic/minesweeper/img/bomb/bomb50px.png");
    }
}

