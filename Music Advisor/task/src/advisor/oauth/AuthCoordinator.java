package advisor.oauth;

import advisor.oauth.exception.AuthException;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static advisor.Config.*;
import static java.lang.Thread.sleep;

public class AuthCoordinator {

    private final String authLink;
    private RedirectServer server;
    private String accessToken;

    private AuthCoordinator() {
        authLink = getAuthLink();
    }


    public static AuthCoordinator create() {
        return new AuthCoordinator();
    }

    public String getAccessRequestLink() {
        return authLink;
    }

    public String getAccessToken() throws AuthException {
        try {
            return tryToExchangeCodeForToken();
        } catch (IOException | InterruptedException e) {
            throw new AuthException(e.getMessage());
        }
    }

    private String tryToExchangeCodeForToken() throws IOException, InterruptedException {
        waitForAuthorizationCodeGrant();
        exchangeAuthorizationCodeForAccessToken();

        return accessToken;
    }

    private void waitForAuthorizationCodeGrant() throws IOException {
        server = RedirectServer.create();
        server.startServer();
        waitingForCode();
        server.stopServer(1);
    }

    private void exchangeAuthorizationCodeForAccessToken() throws IOException, InterruptedException {
        HttpRequest requestForAccessToken = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(
                        getAccessTokenRequestBody()
                ))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .uri(URI.create(getAuthUriString()))
                .build();

        HttpResponse<String> responseWithAccessToken = HttpClient
                .newBuilder()
                .build()
                .send(requestForAccessToken, HttpResponse.BodyHandlers.ofString());
        accessToken = parseAccessToken(responseWithAccessToken.body());
        ACCESS_TOKEN = accessToken;
    }


    private static String parseAccessToken(final String bearerToken) {

        return JsonParser.parseString(bearerToken)
                .getAsJsonObject()
                .get("access_token")
                .getAsString();
    }

    @SuppressWarnings("BusyWait")
    private void waitingForCode() {
        while (server.getCode() == null) {
            try {
                sleep(300);
            } catch (InterruptedException e) {
                assert false : "This will never happen";
            }
        }
    }



    private static String getAuthUriString() {
        return AUTH_SERVER + "/api/token";
    }

    private String getAccessTokenRequestBody() {

        return "client_id=" + CLIENT_ID +
                "&client_secret=" + CLIENT_SECRET +
                "&grant_type=authorization_code" +
                "&code=" + server.getCode() +
                "&redirect_uri=" + REDIRECT_URI;
    }

    private static String getAuthLink() {

        return AUTH_SERVER +
                "/authorize?client_id=" + CLIENT_ID +
                "&redirect_uri=" + REDIRECT_URI +
                "&response_type=code";
    }
}
