package application.controller;

import application.model.Dictionary;
import application.model.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class VocabularyController implements Initializable {
    @FXML
    private ListView<String> listViewFavoriteWords = new ListView<>();
    @FXML
    private ListView<String> listViewRecentWords = new ListView<>();
    @FXML
    WebView favoriteWordView;
    @FXML
    WebView recentWordView;

    private final ObservableList<String> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void selectFavoriteWordsTab() {
        observableList.clear();
        observableList.addAll(Dictionary.favoriteWords);
        listViewFavoriteWords.setItems(observableList);
    }

    public void selectRecentWordsTab() {
        observableList.clear();
        observableList.addAll(Dictionary.recentWords);
        listViewRecentWords.setItems(observableList);
    }

    public void clickRecentWordsListView() {
        String wordTarget = listViewRecentWords.getSelectionModel().getSelectedItem();
        String styleContent =
                "<link rel=\"stylesheet\" " +
                "type=\"text/css\" " +
                "href=\"" + getClass().getResource(( "/application/style/webView.css")) + "\"/>";
        if (wordTarget != null) {
            for (Word w : Dictionary.dictionary) {
                if (w.getWordTarget().equals(wordTarget)) {
                    recentWordView.getEngine().loadContent(styleContent + w.getDescribeWord());
                    break;
                }
            }
        }
    }

    public void clickFavoriteWordsListView() {
        String wordTarget = listViewFavoriteWords.getSelectionModel().getSelectedItem();
        String styleContent =
                "<link rel=\"stylesheet\" " +
                "type=\"text/css\" " +
                "href=\"" + getClass().getResource(("/application/style/webView.css")) + "\"/>";
        if (wordTarget != null) {
            for (Word w : Dictionary.dictionary) {
                if (w.getWordTarget().equals(wordTarget)) {
                    favoriteWordView.getEngine().loadContent(styleContent + w.getDescribeWord());
                    break;
                }
            }


        }
    }

    public void clickBinButtonFavoriteWordsTab() {
        String wordTarget = listViewFavoriteWords.getSelectionModel().getSelectedItem();
        if (wordTarget != null) {
            Dictionary.favoriteWords.remove(wordTarget);
            int index = listViewFavoriteWords.getSelectionModel().getSelectedIndex();
            listViewFavoriteWords.getItems().remove(index);
            favoriteWordView.getEngine().loadContent("");
        }
    }

    public void clickBinButtonRecentWordsTab() {
        Dictionary.recentWords.clear();
        listViewRecentWords.getItems().clear();
        recentWordView.getEngine().loadContent("");
    }
}

