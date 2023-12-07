package application.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    @FXML
    private AnchorPane containerGame;

    @FXML
    private Button btnExitGame;

    @FXML
    private AnchorPane choicesContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        containerGame.setVisible(false);
        btnExitGame.setVisible(false);
    }

    public void showOnSecreen(String path) {
        containerGame.setVisible(true);
        btnExitGame.setVisible(true);
        try {
            System.out.println(getClass().getResource(path));
            AnchorPane component = FXMLLoader.load(getClass().getResource(path));
            containerGame.getChildren().clear();
            containerGame.getChildren().add(component);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void StartGuessGame(){
        choicesContainer.setVisible(false);
        showOnSecreen("/application/gui/GuessGame.fxml");
    }

    public void startTypoGame() {
        choicesContainer.setVisible(false);
        showOnSecreen("/application/gui/TypoGame.fxml");
    }

    public void ExitGame(){
        containerGame.setVisible(false);
        btnExitGame.setVisible(false);
        choicesContainer.setVisible(true);
    }

}
