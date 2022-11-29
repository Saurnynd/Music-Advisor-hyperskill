package advisor.command.impl;

import advisor.command.Command;
import advisor.command.Parser;
import advisor.server.Request;
import advisor.server.exception.ClientServerException;
import advisor.view.Output;
import advisor.view.impl.FormatNew;

import static advisor.Config.JSON_ITEMS_FIELD_NAME;

public class NewCm implements Command {

    private static final String NEW_COMMAND = "new";

    private static final String PATH = "/v1/browse/new-releases";

    private static final String JSON_FIELD_NAME = "albums";

    @Override
    public String name() {
        return NEW_COMMAND;
    }

    @Override
    public void execute(final String command) {

        try {
            Output.write(Parser.parseToJsonArray(Request.loadData(PATH), JSON_FIELD_NAME, JSON_ITEMS_FIELD_NAME), new FormatNew());
        } catch (ClientServerException e) {
            System.out.println("Error request");
        }
    }
}