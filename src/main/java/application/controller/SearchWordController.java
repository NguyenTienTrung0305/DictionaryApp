package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.model.Dictionary;
import application.model.Word;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class SearchWordController implements Initializable{

    @FXML
    private AnchorPane apAdjustDialog;

    @FXML
    private Label lbMeaningWord;

    @FXML
    private TextField tfTargerWord;

    @FXML
    private TextArea taDescribeWord;

    @FXML
    private TextField tfAdjustMeaningWord;

    @FXML
    private TextField tfAdjustDescribeWord;
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        apAdjustDialog.setVisible(false);
    }

    public void ShowAdjustDialog() {
        apAdjustDialog.setVisible(true);
    }

    public void ConfirmAdjustWord() {
        String targetWord = tfTargerWord.getText().toLowerCase().trim();
        String adjustMW = tfAdjustMeaningWord.getText().toLowerCase().trim();
        String adjustDW = tfAdjustDescribeWord.getText().toLowerCase().trim();
        if (!(adjustDW.equals("") || adjustMW.equals(""))) {
            lbMeaningWord.setText(adjustMW);
            taDescribeWord.setText(adjustDW);

            for (Word word : Dictionary.dictionary) {
                if (word.getWordTarget().equals(targetWord)) {
                    word.setWordMeaning(adjustMW);
                    word.setDescribeWord(adjustDW);
                    break;
                }
            }
        }
        apAdjustDialog.setVisible(false);
    }
}
