import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

    public class ApiClient {
        private static final String API_KEY = "1204c7d79a237b64c9baa707";
        private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

        public String getExchangeRates(String baseCurrency) throws IOException, InterruptedException {
            String url = BASE_URL + API_KEY + "/latest/" + baseCurrency;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return response.body();
            } else {
                throw new IOException("Error en la API: Código " + response.statusCode());
            }
        }
    }


