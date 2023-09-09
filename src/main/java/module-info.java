module com.example.codesubmitter {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.codesubmitter to javafx.fxml;
    exports com.example.codesubmitter;
}
