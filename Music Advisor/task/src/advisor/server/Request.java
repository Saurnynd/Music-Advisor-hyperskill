package advisor.server;

import advisor.server.exception.ClientServerException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static advisor.Config.ACCESS_TOKEN;
import static advisor.Config.API_SERVER;

public class Request {

    private static final HttpClient client = HttpClient.newHttpClient();

    public static JsonObject loadData(final String path) throws ClientServerException {

        HttpRequest request = createRequest(path);

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return JsonParser.parseString(response.body()).getAsJsonObject();
            } else {
                String message = JsonParser.parseString(response.body())
                        .getAsJsonObject()
                        .get("error")
                        .getAsJsonObject()
                        .get("message").toString();
                throw new ClientServerException(message);
            }
        } catch (IOException | InterruptedException e) {
            throw new ClientServerException(e.getMessage());
        }
    }

    private static HttpRequest createRequest(String path) {
        return HttpRequest.newBuilder()
                .header("Authorization", " Bearer " + ACCESS_TOKEN)
                .uri(URI.create(API_SERVER + path))
                .GET()
                .build();
    }

}
