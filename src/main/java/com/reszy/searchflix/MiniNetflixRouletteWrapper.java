package com.reszy.searchflix;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by Rafał on 25.04.2017.
 */
public class MiniNetflixRouletteWrapper {

    private static final String API_URL = "http://netflixroulette.net/api/api.php?";

    public String getMovie(String title) throws JSONException, IOException {
        return useAPI(title,"title");
    }

    public String getDirectorData(String name) throws JSONException, IOException {
        return useAPI(name,"director");
    }

    public String getActorData(String name) throws JSONException, IOException {
        return useAPI(name,"actor");
    }

    private String useAPI(String value, String param)throws JSONException, IOException {
        value = value.replace(" ", "%20");
        String message = "place";
        JSONArray json = new JSONArray(readJsonFromUrl(API_URL+ param + "=" + value));
        if(json.getJSONObject(0).has("error")) {
            message = "Unable to locate data";
        } else {
            message = json.toString();
        }

        return message;
    }


    static String readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = (new URL(url)).openStream();
        String jsonText = new String();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            StringBuilder sb = new StringBuilder();

            int cp;
            while((cp = rd.read()) != -1) {
                sb.append((char)cp);
            }

            jsonText = sb.toString();
        } finally {
            is.close();
        }

        return jsonText;
    }
}
