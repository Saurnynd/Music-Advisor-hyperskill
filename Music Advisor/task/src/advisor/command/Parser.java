package advisor.command;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Parser {

    public static JsonArray parseToJsonArray(final JsonObject data, final String memberFirst, final String memberSecond) {
        return data.getAsJsonObject(memberFirst)
                .getAsJsonArray(memberSecond);
    }
}
