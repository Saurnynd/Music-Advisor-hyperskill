package advisor.view.impl;

import advisor.view.Format;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class FormatCate implements Format {

    @Override
    public void out(final JsonArray arr, final int Counter) {

        System.out.println(((JsonObject) arr.get(Counter)).get("name").getAsString());

    }
}
