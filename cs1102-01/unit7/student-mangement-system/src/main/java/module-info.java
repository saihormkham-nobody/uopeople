module cs1102.unit7 {
    requires javafx.controls;
    requires javafx.fxml;

    opens cs1102.unit7 to javafx.fxml;
    exports cs1102.unit7;
}
