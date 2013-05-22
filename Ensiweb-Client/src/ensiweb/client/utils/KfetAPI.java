/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ensiweb.client.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author thibaut
 */
public class KfetAPI 
{
    private static JSONObject readUrl(String url) throws MalformedURLException, IOException, ParseException
    {
        System.out.println("JsonTest");
        BufferedReader reader = new BufferedReader(new InputStreamReader((new URL(url)).openStream()));
        JSONParser parser = new JSONParser();
        
        JSONObject jsonObject =  (JSONObject)parser.parse(reader);
        
        JSONArray users = (JSONArray) jsonObject.get("users");
        Iterator<JSONObject> iterator = users.iterator();
        
        while (iterator.hasNext()) {
            JSONObject item = iterator.next();
            System.out.println(item.get("id") + " " + item.get("last_name") + " " + item.get("first_name"));
        }
        
        return null;
    }
    
    public static JSONObject getAllUser() throws Exception
    { 
        readUrl(Config.SERVER + Config.SERVER_URL_GET_ALL_USERS);
        return null;
    }
}
