package application.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeAppController implements Initializable {
    @FXML
    private AnchorPane container;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showOnSecreen("/application/gui/SearchWord.fxml");
    }

    // set style for anchorpane
    public void showOnSecreen(String path) {
        try {
            System.out.println(getClass().getResource(path));
            AnchorPane component = FXMLLoader.load(getClass().getResource(path));
            container.getChildren().clear();
            container.getChildren().add(component);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SearchBtn() {
        showOnSecreen("/application/gui/SearchWord.fxml");
    }

    public void AddBtn() {
        showOnSecreen("/application/gui/AddNewWord.fxml");
    }

    public void VocabularyBtn() {
        showOnSecreen("/application/Vocabulary.fxml");
    }

    public void TranslateBtn() {
        showOnSecreen("/application/Translate.fxml");
    }

    public void GameBtn() {
        showOnSecreen("/application/gui/Game.fxml");
    }
}
