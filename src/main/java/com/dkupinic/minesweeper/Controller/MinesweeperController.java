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
 * Class used to handle GUI input by the user
 * This class is also a singleton
 */

package com.dkupinic.minesweeper.Controller;

import com.dkupinic.minesweeper.Exceptions.InvalidDifficultyException;
import com.dkupinic.minesweeper.Model.Board.Board;
import com.dkupinic.minesweeper.Model.Board.BoardManager;
import com.dkupinic.minesweeper.Model.Board.BoardSize;
import com.dkupinic.minesweeper.Model.Difficulty.Difficulty;
import com.dkupinic.minesweeper.Model.Field.Field;
import com.dkupinic.minesweeper.Model.Logic.GameLogic;
import com.dkupinic.minesweeper.Model.Logic.Timer;
import com.dkupinic.minesweeper.Model.Score.Score;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.Objects;

import static com.dkupinic.minesweeper.Model.Field.Field.time;

public class MinesweeperController {
    // FXML Variables
    public Label timerLabel;
    public Label flagLabel;
    public Label bombLabel;
    public Label scoreLabel;
    public AnchorPane mainAnchor;
    public ChoiceBox<String> difficultyChoiceBox;
    public ImageView winImage;
    public ImageView loseImage;
    public ImageView resetButton;
    public ImageView checkWinButton;

    public static Pane pane;
    public static MinesweeperController instance;
    public boolean usedCheckWin;

    public void initialize() {
        initBoardPane();
        addDifficultyOptions();
    }

    public void setTimerLabel(String time) {
        timerLabel.setText(time);
    }

    public MinesweeperController() {
        instance = this;
    }

    /**
     * Reset the game and generate a new board when the player switches difficulty
     * @throws InvalidDifficultyException in an exceptional case where the choicebox returns a different difficulty than expected
     */
    @FXML
    private void onDifficultySelection() throws InvalidDifficultyException {
        checkWinButton.setDisable(false);
        usedCheckWin = false;
        winImage.setVisible(false);
        loseImage.setVisible(false);
        clearPane();
        resetTimePlayed();
        resetBombCount();
        generateNewBoard();
        GameLogic.grid = BoardManager.getGrid();
        GameLogic.revealedFields = new boolean[BoardManager.getGrid().length][BoardManager.getGrid()[0].length];
        time.stopTimer();
        Timer.activeTimer = false;
    }

    /**
     * check if the player has won or not
     */
    @FXML
    private void onCheckWinButtonClicked() {
        if (usedCheckWin) {
            return;
        }
        if (GameLogic.checkWin()) {
            Score.increaseScore();
            winImage.setVisible(true);
            updateScore();
        } else {
            Score.decreaseScore();
            loseImage.setVisible(true);
            updateScore();
        }
        usedCheckWin = true;
        checkWinButton.setDisable(true);
        time.stopTimer();
    }

    /**
     * updates the score
     */
    @FXML
    public void updateScore() {
        scoreLabel.setText(String.valueOf(Score.score));
    }

    /**
     * Do the same as onDifficultySelection()
     * @throws InvalidDifficultyException in an exceptional case where the choicebox returns a different difficulty than expected
     */
    @FXML
    private void onResetButtonClick() throws InvalidDifficultyException {
        onDifficultySelection();
    }

    /**
     * Generate a new board and set the score multiplier
     * @throws InvalidDifficultyException in an exceptional case where the choicebox returns a different difficulty than expected
     */
    private void generateNewBoard() throws InvalidDifficultyException {
        Difficulty diffc = Difficulty.valueOf(difficultyChoiceBox.getValue());
        Score.setScoreDifficultyMultiplier(diffc);
        BoardManager builder = new BoardManager(diffc);
        builder.drawBoard();
    }

    /**
     * reset time played to 0
     */
    private static void resetTimePlayed() {
        Timer.setTimePlayed(0);
    }

    /**
     * reset bomb count to 0
     */
    private static void resetBombCount() {
        Board.bombCount = 0;
    }

    /**
     * clear the pane that is attached to the FXML anchor pane
     */
    private void clearPane() {
        pane.getChildren().clear();
    }

    /**
     * initialize the pane with 400 width and length and add it to the anchor pane
     */
    private void initBoardPane() {
        pane = new Pane();
        pane.setPrefSize(BoardSize.getLength(), BoardSize.getWidth());
        mainAnchor.getChildren().add(pane);
    }

    /**
     * adds 3 difficulty choice boxes to the choice box
     */
    private void addDifficultyOptions() {
        difficultyChoiceBox.getItems().addAll(
            String.valueOf(Difficulty.BEGINNER),
            String.valueOf(Difficulty.ADVANCED),
            String.valueOf(Difficulty.ENTHUSIAST)
        );

        difficultyChoiceBox.setValue(String.valueOf(Difficulty.BEGINNER));
    }

    /**
     * return a new MinesweeperController instance if it is null, otherwise just return it
     * @return instance of MinesweeperController()
     */
    public static MinesweeperController getInstance() {
        if (instance == null) {
            instance = new MinesweeperController();
        }

        return instance;
    }
}