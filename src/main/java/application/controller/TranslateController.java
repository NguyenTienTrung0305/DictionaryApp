package application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class TranslateController {
    private String from = "en";
    private String to = "vi";
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


    // Khi nhấn vào nút nói
    public void SpeechToText() {
        taEnglish.setText(application.api.SpeechRecognition.recognizeFromMicrophone());
    }

    // Khi nhấn vào nút nghe
    public void TextToSpeech(){} {
        application.api.SpeechSynthesis.textToSpeech(taEnglish.getText());
    }

    // Khi nhấn vào nút hình ảnh
    public void ImageToText() {
        taEnglish.setText(application.api.ImageAnalysis.getTextFromImage(taLinkImage.getText()));
    }

    // khi nhấn nút bắt đầu dịch
    public void StartTranslate() {
        taVietnamese.setText(application.api.TranslareAPI.translate(taEnglish.getText(), from, to));
    }

    // khi nhấn nút đổi chiều dịch
    public void RotateTranslate() {
        swap(from, to);
    }

}