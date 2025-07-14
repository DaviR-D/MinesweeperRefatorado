module com.dkupinic.minesweeper {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.dkupinic.minesweeper to javafx.fxml;
    exports com.dkupinic.minesweeper;
    exports com.dkupinic.minesweeper.Controller;
    opens com.dkupinic.minesweeper.Controller to javafx.fxml;
}