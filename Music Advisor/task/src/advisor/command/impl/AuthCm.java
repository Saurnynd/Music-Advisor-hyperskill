package advisor.command.impl;

import advisor.command.Command;
import advisor.oauth.AuthCoordinator;
import advisor.oauth.exception.AuthException;

import static advisor.Config.AUTH_SERVER;

public class AuthCm implements Command {
    private static final String AUTH_COMMAND = "auth";

    @Override
    public String name() {
        return AUTH_COMMAND;
    }

    @Override
    public void execute(final String command) {
        AuthCoordinator coordinator = AuthCoordinator.create();
        System.out.println(AUTH_SERVER + "/authorize?client_id=b180b9e7265640b49c33ea7cccca13ed&redirect_uri=http://localhost:8080&response_type=code\n");
        System.out.println(coordinator.getAccessRequestLink());
        System.out.println("waiting for code...");
        try {
            System.out.println(coordinator.getAccessToken());
        } catch (AuthException e) {
            throw new RuntimeException(e);
        }
        System.out.println("---SUCCESS---");
    }
}