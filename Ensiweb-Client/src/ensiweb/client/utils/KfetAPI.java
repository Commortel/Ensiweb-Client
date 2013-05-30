package ensiweb.client.utils;

import ensiweb.client.Config;
import ensiweb.client.entity.ShoppedArticle;
import ensiweb.client.entity.Student;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class KfetAPI {

    private static JSONObject readUrl(String url) throws MalformedURLException, IOException, ParseException {
        System.out.println("URL[" + url + "]");
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

    public static JSONObject putShoppedArticle(ArrayList<ShoppedArticle> al, Student user) throws Exception {
        String tmp = "";
        
        for(ShoppedArticle s : al)
        {
            tmp += "&articles["+ s.getId() +"]=1";
        }
        return readUrl(Config.SERVER + Config.SERVER_URL_PUT_SHOPPEDARTICLE + "?student=" + user.getId() + "&sealer=2" + tmp);
    }
}
