package application.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.DAO.WordDAO;
import application.api.TextToSpeech;
import application.model.Dictionary;
import application.model.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class SearchWordController implements Initializable {

    @FXML
    private AnchorPane apAdjustDialog;

    @FXML
    private Label lbMeaningWord;

    @FXML
    private TextField tfTargerWord;

    @FXML
    private WebView taDescribeWord;

    @FXML
    private TextField tfAdjustMeaningWord;

    @FXML
    private TextArea tfAdjustDescribeWord;

    @FXML
    private ListView<Word> lvWord = new ListView<>();

    // use to observe the changes in the list
    private final ObservableList<Word> observableList = FXCollections.observableArrayList();

    // hien thi danh sach cac tu
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // get a list of words sorted anphabetically
        List<Word> words = Dictionary.sortWordAsAnphabetically();

        // add data for observable list
        observableList.addAll(words);

        // add data for listview
        lvWord.setItems(observableList);

        // specific data ro render to the screen
        lvWord.setCellFactory(param -> new ListCell<Word>() {
            @Override
            protected void updateItem(Word item, boolean empty) {
                super.updateItem(item, empty);
                // neu word duoc truyen vao la rong , set cell la rong
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getWordTarget());
                }
            }
        });
        lvWord.setVisible(true);

        // hide dialog adjust
        apAdjustDialog.setVisible(false);
    }

    // hien thi dialog chinh sua tu khi an vao icon chinh sua
    public void ShowAdjustDialog() {
        apAdjustDialog.setVisible(true);
    }

    // Display list of words according to client request
    // when client input 'abc' or click button search 'abc' , app gonna display list of words start with 'abc'
    public void SearchWord() {
        // remove all item in listview
        lvWord.getItems().clear();

        // get List word start with '...'
        List<Word> words = Dictionary.SearchingWord(tfTargerWord.getText().toLowerCase().trim());

        // add data for observalelist
        observableList.addAll(words);

        // add data for listview
        lvWord.setItems(observableList);

        // specific data ro render to the screen
        lvWord.setCellFactory(param -> new ListCell<Word>() {
            @Override
            protected void updateItem(Word item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getWordTarget());
                }
            }
        });
    }


    // udapte new word after adjust
    public void ConfirmAdjustWord() {

        String targetWord = tfTargerWord.getText().toLowerCase().trim();
        String adjustMW = tfAdjustMeaningWord.getText().toLowerCase().trim();
        String adjustDW = tfAdjustDescribeWord.getText().toLowerCase().trim();

        if (!(adjustDW.equals("") || adjustMW.equals(""))) {
            lbMeaningWord.setText(adjustMW);

            WebEngine webEngine = taDescribeWord.getEngine();
            webEngine.loadContent(adjustDW);


            for (Word word : Dictionary.dictionary) {
                if (word.getWordTarget().equals(targetWord)) {
                    word.setWordMeaning(adjustMW);
                    word.setDescribeWord(adjustDW);

                    // update data to database
                    WordDAO.getInstance().updateWord(new Word(targetWord, adjustMW, adjustDW));

                    break;
                }
            }
        }
        apAdjustDialog.setVisible(false);
    }


    // delete word in dictionary
    public void DeleteWord() {
        // get the selected element
        Word word = lvWord.getSelectionModel().getSelectedItem();

        if (word != null) {
            String wordTar = word.getWordTarget();

            // delete word in dictionary
            Dictionary.removeWord(word);

            // get index of selected element
            int index = lvWord.getSelectionModel().getSelectedIndex();

            // remove selected element in list view
            lvWord.getItems().remove(index);

            tfTargerWord.setText("");
            lbMeaningWord.setText("");


            WebEngine webEngine = taDescribeWord.getEngine();
            webEngine.loadContent("");
        }

    }

    // xu ly khi click vao 1 item trong list view
    public void MouseClickedLV() {
        Word selectedWord = lvWord.getSelectionModel().getSelectedItem();
        if (selectedWord != null) {
            tfTargerWord.setText(selectedWord.getWordTarget());
            lbMeaningWord.setText(selectedWord.getWordMeaning());

            WebEngine webEngine = taDescribeWord.getEngine();
            webEngine.loadContent(selectedWord.getDescribeWord());
        }
    }

    // speech word
    public void SpeechWord(){
        String content = tfTargerWord.getText();
        if (content.equals("")){
            return;
        }
        TextToSpeech.Speech(content);
    }
}
