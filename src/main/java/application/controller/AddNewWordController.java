package application.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import application.DAO.WordDAO;
import application.alerts.Alerts;
import application.model.Dictionary;
import application.model.Word;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class AddNewWordController implements Initializable {
    @FXML
    private TextField tfNewWord;

    @FXML
    private TextField tfTranslateWord;

    @FXML
    private TextArea taDescribeWord;

    @FXML
    private Label lbArlertExistsWord;

    private Alerts alerts = new Alerts();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        lbArlertExistsWord.setVisible(false);
    }

    // save new word
    public void SaveNewWord() {
        Alert alertConfirmation = alerts.alertConfirmation("Add word", "Bạn chắc chắn muốn thêm từ này?");
        Optional<ButtonType> option = alertConfirmation.showAndWait();


        String targetWord = tfNewWord.getText().toLowerCase().trim();
        String wordMeaning = tfTranslateWord.getText().toLowerCase().trim();
        String describeWord = taDescribeWord.getText().toLowerCase().trim();

        if (option.get() == ButtonType.OK) {
            // If the word already exists in the database, do not save it to the database
            if (!Dictionary.dictionary.stream().filter(d -> d.getWordTarget().equals(targetWord)).collect(Collectors.toList()).isEmpty()) {
                alerts.showAlertInfo("Information", "Failed!!\nTừ đã tồn tại");
            } else {
                if (targetWord.equals("") || wordMeaning.equals("")) {
                    alerts.showAlertInfo("Information", "Failed!!\nBạn cần điền đầy đủ nghĩa của từ");
                } else {
                    // save word to list
                    Dictionary.dictionary.add(new Word(targetWord, wordMeaning, describeWord));
                    // save word to database
                    WordDAO.getInstance().insertWord(new Word(targetWord, wordMeaning, describeWord));
//                    showSuccessAlert();
                    alerts.showAlertInfo("Information", "Successfully!!\nTừ đã được thêm");
                }
            }

            // refresh screen
            tfNewWord.clear();
            tfTranslateWord.clear();
            taDescribeWord.clear();
//        lbArlertExistsWord.setTextFill(Paint.valueOf("#2ecc71"));
//            lbArlertExistsWord.setVisible(true);
        }
    }
}
