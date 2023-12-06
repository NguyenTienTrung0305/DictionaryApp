package application.controller;

import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeAppController implements Initializable {
    @FXML
    private AnchorPane container;

    @FXML
    private ToggleButton tgSearch;
    @FXML
    private ToggleButton tgAdd;
    @FXML
    private ToggleButton tgVoca;
    @FXML
    private ToggleButton tgTranslate;
    @FXML
    private ToggleButton tgGame;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Tạo một PseudoClass với tên "selected".
        // PseudoClass là một cơ chế để xác định trạng thái mà bạn có thể định nghĩa cho phần tử của mình.
        tgSearch.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), false);
        tgAdd.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), false);
        tgVoca.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), false);
        tgTranslate.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), false);
        tgGame.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), false);

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
        tgSearch.setSelected(true);
        tgAdd.setSelected(false);
        tgVoca.setSelected(false);
        tgTranslate.setSelected(false);
        tgGame.setSelected(false);
        showOnSecreen("/application/gui/SearchWord.fxml");
    }

    public void AddBtn() {
        tgAdd.setSelected(true);
        tgSearch.setSelected(false);
        tgVoca.setSelected(false);
        tgTranslate.setSelected(false);
        tgGame.setSelected(false);
        showOnSecreen("/application/gui/AddNewWord.fxml");
    }

    public void VocabularyBtn() {
        tgVoca.setSelected(true);
        tgAdd.setSelected(false);
        tgSearch.setSelected(false);
        tgTranslate.setSelected(false);
        tgGame.setSelected(false);
        showOnSecreen("/application/gui/Vocabulary.fxml");
    }

    public void TranslateBtn() {
        tgTranslate.setSelected(true);
        tgAdd.setSelected(false);
        tgVoca.setSelected(false);
        tgSearch.setSelected(false);
        tgGame.setSelected(false);
        showOnSecreen("/application/gui/Translate.fxml");
    }

    public void GameBtn() {
        tgGame.setSelected(true);
        tgAdd.setSelected(false);
        tgVoca.setSelected(false);
        tgTranslate.setSelected(false);
        tgSearch.setSelected(false);
        showOnSecreen("/application/gui/Game.fxml");
    }
}
