package application.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class TranslateController implements Initializable {

    public String lanOne = "en";
    public String textOne = "English";
    public String lanTwo = "vi";
    public String textTwo = "Vietnamese";

    // nút nói
    @FXML
    private Button btnSpeechToText;

    // nút nghe
    @FXML
    private Button btnTextToSpeech;

    // nút hình ảnh
    @FXML
    private Button btnImageToText;

    // nút đổi chiều dịch
    @FXML
    private Button btnRotate;

    // nút bắt đầu dịch
    @FXML
    private Button btnStartTranslate;

    // Vùng chứa english text
    @FXML
    private TextArea taEnglish;

    // Vùng chứa vietnamese text
    @FXML
    private TextArea taVietnamese;

    // vùng chứa link image
    @FXML
    private TextArea taLinkImage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    // Khi nhấn vào nút nói
    public void SpeechToText() {
        try {
            taEnglish.setText(application.api.SpeechRecognition.recognizeFromMicrophone());

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    // Khi nhấn vào nút nghe
    public void textToSpeech() {
        try {
            application.api.SpeechSynthesis.textToSpeech(taEnglish.getText());
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        } catch (ExecutionException e) {
//            throw new RuntimeException(e);
        }
    }

//     Khi nhấn vào nút hình ảnh
    public void ImageToText() {
        try {
            taEnglish.setText(application.api.ImageAnalysis.getTextFromImage(taLinkImage.getText()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // khi nhấn nút bắt đầu dịch
    public void StartTranslate() {
        try {
            taVietnamese.setText(application.api.TranslateAPI.translate(taEnglish.getText(), lanOne, lanTwo));
        } catch (IOException e) {
//            throw new RuntimeException(e);
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        }
    }

    // khi nhấn nút đổi chiều dịch
    public void RotateTranslate(){
        String tmp = lanOne;
        String tmp2 = textOne;
        lanOne = lanTwo;
        textOne = textTwo;
        lanTwo = tmp;
        textTwo = tmp2;

        taEnglish.setPromptText(textOne);
        taVietnamese.setPromptText(textTwo);
    };



}