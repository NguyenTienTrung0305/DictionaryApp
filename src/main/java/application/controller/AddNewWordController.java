package application.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import application.model.Dictionary;
import application.model.Word;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

public class AddNewWordController implements Initializable {
    @FXML
    private TextField tfNewWord;

    @FXML
    private TextField tfTranslateWord;

    @FXML
    private TextArea taDescribeWord;

    @FXML
    private Label lbArlertExistsWord;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        lbArlertExistsWord.setVisible(false);
    }

    // save new word
    public void SaveNewWord() {
        String targetWord = tfNewWord.getText().toLowerCase().trim();
        String wordMeaning = tfTranslateWord.getText().toLowerCase().trim();
        String describeWord = taDescribeWord.getText().toLowerCase().trim();


        if (!Dictionary.dictionary.stream().filter(d -> d.getWordTarget().equals(targetWord)).collect(Collectors.toList()).isEmpty()) {
            lbArlertExistsWord.setText("Existed Word!!");
            for (Word word : Dictionary.dictionary) {
                System.out.println(word.getWordTarget());
            }
        } else {
            if (targetWord.equals("") || wordMeaning.equals("")) {
                lbArlertExistsWord.setText("Invalid Word!!");
            } else {
                Dictionary.dictionary.add(new Word(targetWord, wordMeaning, describeWord));
                lbArlertExistsWord.setText("Succesfully!!");
            }
        }
        tfNewWord.clear();
        tfTranslateWord.clear();
        taDescribeWord.clear();
        lbArlertExistsWord.setTextFill(Paint.valueOf("#2ecc71"));
        lbArlertExistsWord.setVisible(true);
    }
}
