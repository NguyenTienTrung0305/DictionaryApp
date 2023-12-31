package application.api;

import javax.speech.Engine;
import javax.speech.Central;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Synthesizer;
import java.util.Locale;

public class TextToSpeech {
    public static void Speech(String content) {
        try {
            // Set property as Kevin Dictionary
            System.setProperty(
                    "freetts.voices",
                    "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

            // Register Engine
            Central.registerEngineCentral(
                    "com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");

            // Create a Synthesizer
            Synthesizer synthesizer = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));

            // Allocate synthesizer
            synthesizer.allocate();

            // Resume Synthesizer
            synthesizer.resume();

            // Speaks the given text
            // until the queue is empty.
            synthesizer.speakPlainText(content, null);
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);

            // Deallocate the Synthesizer.
//            synthesizer.deallocate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
