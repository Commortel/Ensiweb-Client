package ensiweb.client.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class KfetAPI {

    private static JSONObject readUrl(String url) throws MalformedURLException, IOException, ParseException {
        BufferedReader reader = new BufferedReader(new InputStreamReader((new URL(url)).openStream()));
        JSONParser parser = new JSONParser();

        return (JSONObject) parser.parse(reader);
    }

    public static JSONObject getAllUser(String query) throws Exception {
        return readUrl(Config.SERVER + Config.SERVER_URL_GET_ALL_USERS + "?search=\"" + query + "\"");
    }

    public static JSONObject getAllCategories() throws Exception {
        return readUrl(Config.SERVER + Config.SERVER_URL_GET_ALL_CATEGORIES);
    }
}
