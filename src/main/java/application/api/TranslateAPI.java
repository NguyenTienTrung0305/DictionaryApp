package application.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TranslateAPI implements APIKey {
    public static String translate(String input, String from, String to) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(translateURI + "to=" + to + "&api-version=3.0&"
                        + "from=" + from + "&profanityAction=NoAction&textType=plain"))
                .header("content-type", "application/json")
                .header("X-RapidAPI-Key", translateKey)
                .header("X-RapidAPI-Host", translateHost)
                .method("POST", HttpRequest.BodyPublishers.ofString("[\r {\r\"Text\": \"" + input + "\"\r}\r]"))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        String cur = response.body();
        String result = "";
        int index = 0;
        for (int i = 0; i < cur.length(); i++) {
            if (index == 5) {
                if (cur.charAt(i) != '"') {
                    result = result + cur.charAt(i);
                } else {
                    break;
                }
            }
            if (cur.charAt(i) == '"') {
                index++;
            }
        }
        return result;
    }
}