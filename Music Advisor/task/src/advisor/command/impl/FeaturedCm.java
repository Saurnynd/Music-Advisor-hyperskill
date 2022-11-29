package advisor.command.impl;

import advisor.command.Command;
import advisor.command.Parser;
import advisor.server.Request;
import advisor.server.exception.ClientServerException;
import advisor.view.Output;
import advisor.view.impl.FormatFeatured;

import static advisor.Config.JSON_ITEMS_FIELD_NAME;

public class FeaturedCm implements Command {

    private static final String FEATURED_COMMAND = "featured";
    private static final String PATH = "/v1/browse/featured-playlists";

    private static final String JSON_FIELD_NAME = "playlists";

    @Override
    public String name() {
        return FEATURED_COMMAND;
    }

    @Override
    public void execute(final String command) {

        try {

            Output.write(Parser.parseToJsonArray(Request.loadData(PATH), JSON_FIELD_NAME, JSON_ITEMS_FIELD_NAME), new FormatFeatured());
        } catch (ClientServerException e) {
            System.out.println("Error request");
        }
    }
}