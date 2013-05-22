package ensiweb.client.utils;

import java.util.HashMap;
import java.util.Iterator;
import javafx.beans.property.ReadOnlyMapWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DatasManager {

    static public ReadOnlyMapWrapper<String, String> listOfUser = new ReadOnlyMapWrapper<>();
    static public ReadOnlyMapWrapper<String, String> listOfCategories = new ReadOnlyMapWrapper<>();

    static public void uptadeListOfUsersAction(String query) throws Exception {

        ObservableMap<String, String> data = FXCollections.observableMap(new HashMap<String, String>());

        JSONObject jsonObject = KfetAPI.getAllUser(query);
        JSONArray users = (JSONArray) jsonObject.get("users");
        Iterator<JSONObject> iterator = users.iterator();

        while (iterator.hasNext()) {
            JSONObject item = iterator.next();
            System.out.println(item.get("id") + " " + item.get("last_name") + " " + item.get("first_name"));
            data.put(item.get("id").toString(), item.get("last_name") + " " + item.get("first_name"));
        }

        listOfUser.set(data);
    }

    static public void uptadeListOfCategoriesAction(String query) throws Exception {

        ObservableMap<String, String> data = FXCollections.observableMap(new HashMap<String, String>());

        JSONObject jsonObject = KfetAPI.getAllUser(query);
        JSONArray users = (JSONArray) jsonObject.get("users");
        Iterator<JSONObject> iterator = users.iterator();

        while (iterator.hasNext()) {
            JSONObject item = iterator.next();
            System.out.println(item.get("id") + " " + item.get("title"));
            data.put(item.get("id").toString(), item.get("title").toString());
        }

        listOfCategories.set(data);
    }
}
