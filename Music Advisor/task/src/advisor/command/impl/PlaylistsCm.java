package advisor.command.impl;

import advisor.command.Command;
import advisor.command.Parser;
import advisor.server.Request;
import advisor.server.exception.ClientServerException;
import advisor.view.Output;
import advisor.view.impl.FormatPlaylist;
import com.google.gson.JsonArray;

import java.util.stream.StreamSupport;

import static advisor.Config.JSON_ITEMS_FIELD_NAME;


public class PlaylistsCm implements Command {

    private static final String PLAYLISTS_COMMAND = "playlists";

    private static final String CATEGORY_PATH = "/v1/browse/categories";

    @Override
    public String name() {
        return PLAYLISTS_COMMAND;
    }

    @Override
    public void execute(final String command) {
        final var categoryName = command.split(" ", 2)[1];
        final var categoryId = getCategoryIdByName(categoryName, CATEGORY_PATH);

        if ("".equals(categoryId)) {
            System.out.println("Unknown category name.");
            return;
        }

        final var PATH = CATEGORY_PATH + "/" + categoryId + "/playlists";

        try {
            Output.write(Parser.parseToJsonArray(Request.loadData(PATH), "playlists", JSON_ITEMS_FIELD_NAME), new FormatPlaylist());
        } catch (ClientServerException e) {
            System.out.println("Error request");
        }
    }

    public String getCategoryIdByName(final String categoryName, final String categoryPath) {

        try {
            JsonArray arr = Parser.parseToJsonArray(Request.loadData(categoryPath), "categories", JSON_ITEMS_FIELD_NAME);
            return StreamSupport.stream(arr.spliterator(), false)
                    .filter(x -> categoryName.equals(x.getAsJsonObject().get("name").getAsString()))
                    .map(x -> x.getAsJsonObject().get("id").getAsString())
                    .findAny()
                    .orElse("");
        } catch (ClientServerException e) {
            System.out.println("Error request");
        }
        return "";
    }

}