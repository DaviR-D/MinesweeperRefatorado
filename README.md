# Minesweeper JavaFX

### Made by Dino Kupinic

---

### Features:
- 3 Difficulties
- 8-bit pixel art
- Highscore
- Flags

How to run:
---
Navigate to \out\artifacts\Minesweeper_jar and double-click Minesweeper.jar.
You can also open it in an IDE.

### Class Diagram

---

```mermaid
classDiagram
direction BT
class Board {
  - int rows
  - int size
  - int INITIAL_BOMBS
  - Difficulty currentDifficulty
  - int INITIAL_FLAGS
  - int flagCount
  - int bombCount
  - int columns
  + getBombCount() int
  + setRows(int) void
  - checkValidDifficulty(Difficulty) boolean
  + setBombCount(int) void
  - setFieldSizes() void
  + getFlagCount() int
  + getColumns() int
  + setCurrentDifficulty(Difficulty) void
  + setFlagCount(int) void
  + setColumns(int) void
  + getSize() int
  + getCurrentDifficulty() Difficulty
  + setSize(int) void
  + getRows() int
}
class BoardManager {
  - int rows
  - Board boardSize
  - Field[][] grid
  - Difficulty boardDifficulty
  - int columns
  - iterateNeighbours(ArrayList~Field~, int[], Field) ArrayList~Field~
  - generateNeightbourPoints() int[]
  - addToPane(Field) void
  - checkIfBombField(Field) void
  + setGrid(Field[][]) void
  - displayBombCount() void
  + drawBoard() void
  - generateFields() void
  - getNeighbours(Field) ArrayList~Field~
  + getBoardDifficulty() Difficulty
  - getMinePercentage() double
  + getGrid() Field[][]
  - drawNeighbours() void
  - increaseBombCount(ArrayList~Field~, int) int
  - generateBombProbalitity(double) boolean
  - validNeighbour(int, int) boolean
  - updateBombCountText(int, Field) void
  - setBombCountColor(Field) void
}
class BoardSize {
  - int length
  - int width
  + getLength() int
  + getWidth() int
}
class Difficulty {
<<enumeration>>
  +  ENTHUSIAST
  +  BEGINNER
  +  ADVANCED
  + valueOf(String) Difficulty
  + values() Difficulty[]
}
class Field {
  + Timer time
  - boolean containsBomb
  - ImageView flag
  - int yCoord
  - ImageView bomb
  - boolean isEmpty
  - int xCoord
  - Text bombCount
  - boolean flagRevealed
  - int fieldSize
  - Rectangle fieldNode
  + getBombCount() Text
  + getyCoord() int
  - prepareBombs(Board) void
  - prepareFields(Board) void
  - handleOnClick() void
  - cleanUpForNextRound() void
  - onEmptyFieldClick() void
  + setBombCount(String) void
  - offsetFields() void
  - styleBombCount() void
  - addToPane() void
  + getFlag() ImageView
  + getIsEmpty() boolean
  - handleBombField(MouseEvent) void
  + revealBomb() void
  + getFieldNode() Rectangle
  - initBombImages() void
  - setBombCountFontSize() void
  + updateFlagLabel(MinesweeperController) void
  - initFlagImages() void
  - handleFlagField() void
  - setBombOpacity(int) void
  - setCorrectBombImage(Board) void
  - setCorrectValueOfFields(boolean) void
  - calcFieldSize(Board) void
  + getBombCountAsString() String
  - revealFlag(ImageView, boolean) void
  - styleFlags(Board) void
  - onBombFieldClick() void
  - handleNonBombField() void
  + getContainsBomb() boolean
  + revealBombCount() void
  + getxCoord() int
  - styleFields() void
  - initTimer() void
  - setCorrectFlagImage(Board) void
  - handleEvent(MouseEvent) void
}
class GameLogic {
  - boolean[][] revealedFields
  - Field[][] grid
  + getRevealedFields() boolean[][]
  + setRevealedFields(boolean[][]) void
  + revealEmptyField(boolean, Rectangle) void
  + setGrid(Field[][]) void
  + resetFlagCount() void
  + removeFlags() void
  + checkWin() boolean
  - getTemporaryField(int, int) Field
  - gridCoordinatesAreNotEmpty(int, int) boolean
  - checkValidInputs(int, int, boolean[][]) boolean
  - revealCorners(int, int, boolean[][]) void
  + revealSurroundingFields(int, int, boolean[][]) void
  - revealNeighbours(int, int, boolean[][]) void
  + revealAllFields() void
  - checkFloodFillBorder(Field) boolean
}
class ImageProvider {
  - Image flagImage25px
  - Image bombImage25px
  - ImageProvider instance
  - Image flagImage50px
  - Image bombImage20px
  - Image flagImage20px
  - Image bombImage50px
  + getFlagImage20px() Image
  + getInstance() ImageProvider
  + getBombImage20px() Image
  + getBombImage25px() Image
  + getBombImage50px() Image
  + getFlagImage50px() Image
  + getFlagImage25px() Image
}
class InvalidDifficultyException
class MinesweeperApplication {
  + main(String[]) void
  + start(Stage) void
}
class MinesweeperController {
  - boolean usedCheckWin
  + ImageView checkWinButton
  - MinesweeperController instance
  + ImageView winImage
  + Label scoreLabel
  + AnchorPane mainAnchor
  - boolean firstMatch
  + ImageView loseImage
  - Pane pane
  + Label bombLabel
  + ImageView resetButton
  + Label timerLabel
  + ChoiceBox~String~ difficultyChoiceBox
  + Label flagLabel
  + getPane() Pane
  - disableLabels() void
  + getInstance() MinesweeperController
  + initialize() void
  - generateNewBoard() void
  - stopTimer() void
  - resetTimePlayed() void
  - onDifficultySelection() void
  - initBoardPane() void
  - clearPane() void
  + updateScore() void
  - addDifficultyOptions() void
  - renewGrid() void
  - onCheckWinButtonClicked() void
  - handleGameResultLogic() void
  - handleCheckWin() void
  - onResetButtonClick() void
  + setTimerLabel(String) void
  - resetBombCount() void
  - cleanUpForNewRound() void
}
class Score {
  - int score
  - int scoreDifficultyMultiplier
  + getScore() int
  + increaseScore() void
  + decreaseScore() void
  + setScoreDifficultyMultiplier(Difficulty) void
}
class Timer {
  - long lastFrame
  - boolean activeTimer
  - AnimationTimer animationTimer
  - float timePlayed
  + stopTimer() void
  + setTimePlayed(int) void
  + getActiveTimer() boolean
  + setActiveTimer(boolean) void
  + startTimer(MinesweeperController) void
}

Board "1" *--> "currentDifficulty 1" Difficulty 
BoardManager  ..>  Board : «create»
BoardManager "1" *--> "boardSize 1" Board 
BoardManager "1" *--> "boardDifficulty 1" Difficulty 
BoardManager "1" *--> "grid *" Field 
BoardManager  ..>  Field : «create»
BoardManager  ..>  InvalidDifficultyException : «create»
Field "1" *--> "time 1" Timer 
Field  ..>  Timer : «create»
GameLogic "1" *--> "grid *" Field 
MinesweeperController  ..>  BoardManager : «create»

```

### Preview images

![image](preview_images/image1.png)
![image](preview_images/image2.png)
![image](preview_images/image3.png)
