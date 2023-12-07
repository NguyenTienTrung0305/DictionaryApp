module application {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires freetts;
    requires javafx.web;
    requires java.net.http;
    requires client.sdk;
    requires com.azure.ai.vision.common;
    requires com.azure.ai.vision.imageanalysis;

    opens application to javafx.fxml;
    opens application.controller to javafx.fxml;
    exports application;
    exports application.controller;
}