package chap07.autodebit;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

// 외부 서비스에서 제공하는 HTTP URL을 이용해 카드번호가 유효한지 검사하고 결과를 리턴하는 클래스
public class CardNumberValidator {
    public CardValidity validate(String cardNumber) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://some-external-pg.com/card"))
                .header("Content-Type", "text/plain")
                .POST(HttpRequest.BodyPublishers.ofString(cardNumber))
                .build();

        try {
            HttpResponse<String> response =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            switch (response.body()) {
                case "ok": return CardValidity.VALID;
                case "bad": return CardValidity.INVALID;
                case "expired": return CardValidity.EXPIRED;
                case "theft": return CardValidity.THEFT;
                default: return CardValidity.UNKNOWN;
            }
        } catch (IOException | InterruptedException e) {
            return CardValidity.ERROR;
        }
    }
}
