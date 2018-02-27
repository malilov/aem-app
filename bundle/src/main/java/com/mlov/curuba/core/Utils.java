package com.mlov.curuba.core;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Utils {
    private static final String ALBUM_LINE = "%s (Link: %s). ";
    private static final String NAME = "name";
    private static final String ITEMS = "items";
    private static final String LINK = "href";

    public static String getAlbumnsFromJson(String json) {
        StringBuilder albums = new StringBuilder();
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        JsonArray jsonAlbums = jsonObject.get("items").getAsJsonArray();

        for (JsonElement element : jsonAlbums) {
            albums.append(String.format(ALBUM_LINE, ((JsonObject) element).get(NAME)
                    , ((JsonObject) element).get(LINK)));
        }


        return albums.toString();
    }
}
