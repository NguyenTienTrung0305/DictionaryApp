module application {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens application to javafx.fxml;
    opens application.controller to javafx.fxml;
    exports application;
    exports application.controller;
}