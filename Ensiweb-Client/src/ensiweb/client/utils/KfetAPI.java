package ensiweb.client.utils;

import ensiweb.client.Config;
import ensiweb.client.entity.ShoppedArticle;
import ensiweb.client.entity.Student;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
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
        return readUrl(Config.SERVER + Config.SERVER_URL_GET_ALL_USERS + "?search=" + URLEncoder.encode(query, "UTF-8"));
    }

    public static JSONObject getAllCategories() throws Exception {
        return readUrl(Config.SERVER + Config.SERVER_URL_GET_ALL_CATEGORIES);
    }

    public static JSONObject putShoppedArticle(ArrayList<ShoppedArticle> al, Student user) throws Exception {
        String tmp = "";

        for (ShoppedArticle s : al) {
            int quantity = s.getQuantity();
            tmp += "&articles[" + s.getId() + "]=" + (quantity >= 0 ? quantity : 1);
        }
        return readUrl(Config.SERVER + Config.SERVER_URL_PUT_SHOPPEDARTICLE + "?student=" + user.getId() + "&sealer=2" + tmp);
    }

    public static JSONObject getAllShoppedArticle() throws Exception {
        return readUrl(Config.SERVER + Config.SERVER_URL_GET_ALL_SHOPPEDARTICLE);
    }
    
    public static JSONObject getAllProduct() throws Exception {
        return readUrl(Config.SERVER + Config.SERVER_URL_GET_ALL_PRODUCT);
    }
    
    public static JSONObject getArticleById(int p) throws Exception {
        return readUrl(Config.SERVER + Config.SERVER_URL_GET_ARTICLE_BY_ID + "?id=" + p);
    }
    
    public static JSONObject putStudentAccount(Student user, double amount) throws Exception {
        return readUrl(Config.SERVER + Config.SERVER_URL_PUT_STUDENT_ACCOUNT + "?student=" + user.getId() + "&amount=" + amount);
    }
}
