package application;

import application.model.fileio.WordFileIO;
import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/gui/HomeApp.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root,830,708);
            scene.getStylesheets().add(getClass().getResource("/application/style/style.css").toExternalForm());

            primaryStage.setTitle("Dictionary App");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() throws Exception {
        WordFileIO.loadRecordedWordsFromFile();
    }

    @Override
    public void stop() throws Exception {
        WordFileIO.saveRecordedWordsToFile();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
