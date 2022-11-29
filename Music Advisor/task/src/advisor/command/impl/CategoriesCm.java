package advisor.command.impl;

import advisor.command.Command;
import advisor.command.Parser;
import advisor.server.Request;
import advisor.server.exception.ClientServerException;
import advisor.view.Output;
import advisor.view.impl.FormatCate;

import static advisor.Config.JSON_ITEMS_FIELD_NAME;

public class CategoriesCm implements Command {

    private static final String CATEGORIES_COMMAND = "categories";

    private static final String PATH = "/v1/browse/categories";

    private static final String JSON_FIELD_NAME = "categories";

    @Override
    public String name() {
        return CATEGORIES_COMMAND;
    }

    @Override
    public void execute(final String command) {
        try {
            Output.write(Parser.parseToJsonArray(Request.loadData(PATH), JSON_FIELD_NAME, JSON_ITEMS_FIELD_NAME), new FormatCate());
        } catch (ClientServerException e) {
            System.out.println("Error request");
        }
    }
}