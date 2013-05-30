package ensiweb.client.utils;

import ensiweb.client.entity.Article;
import ensiweb.client.entity.Category;
import ensiweb.client.entity.ShoppedArticle;
import ensiweb.client.entity.Student;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DatasManager {

    static public ReadOnlyListWrapper<Student> listOfUser = new ReadOnlyListWrapper<>();
    static public ReadOnlyListWrapper<Category> listOfCategories = new ReadOnlyListWrapper<>();
    static public ReadOnlyListWrapper<ShoppedArticle> listOfShoppedArticle = new ReadOnlyListWrapper<>();
    static public ReadOnlyObjectWrapper<Student> selectedUser = new ReadOnlyObjectWrapper<>();
    static public DoubleProperty sumOfShoppedArticle = new SimpleDoubleProperty();

    static public void updateListOfUsersAction(String query) throws Exception {

        ObservableList<Student> data = FXCollections.observableArrayList();

        JSONObject jsonObject = KfetAPI.getAllUser(query);
        JSONArray users = (JSONArray) jsonObject.get("users");
        Iterator<JSONObject> iterator = users.iterator();

        while (iterator.hasNext()) {
            KfetJSONObject item = KfetJSONObject.iteratorNext(iterator);
            /*JSONArray group = (JSONArray) item.get("class_and_groups");
             Iterator<JSONObject> groupiterator = group.iterator();
             while (groupiterator.hasNext()) {
             JSONObject groupitem = groupiterator.next();

             System.out.println(groupitem.get("group"));
             }*/
            System.out.println(item.get("id") + " " + item.get("last_name") + " " + item.get("first_name") + item.get("account"));
            Student newStudent = new Student();
            newStudent.setId(item.getInt("id"));
            newStudent.setName(item.getString("last_name") + " " + item.getString("first_name"));
            newStudent.setAccount(item.getDouble("account"));

            data.add(newStudent);
        }

        listOfUser.set(data);
    }

    static public void updateListOfCategoriesAction() throws Exception {

        ObservableList<Category> data = FXCollections.observableArrayList();

        JSONObject jsonObject = KfetAPI.getAllCategories();
        JSONArray categories = (JSONArray) jsonObject.get("categories");
        Iterator<JSONObject> iterator = categories.iterator();

        while (iterator.hasNext()) {
            KfetJSONObject item = KfetJSONObject.iteratorNext(iterator);
            Category c = new Category();
            c.setId(item.getInt("id"));
            c.setTitle(item.getString("title"));

            if (item.has("article")) {
                JSONArray article = item.getArray("article");
                Iterator<JSONObject> iteratorArticle = article.iterator();
                ArrayList<Article> listArticle = new ArrayList<>();

                while (iteratorArticle.hasNext()) {
                    KfetJSONObject itemArticle = KfetJSONObject.iteratorNext(iteratorArticle);

                    Article newArticle = new Article();
                    newArticle.setId(itemArticle.getInt("id"));
                    newArticle.setPrice(itemArticle.getDouble("price"));
                    newArticle.setTitle(itemArticle.getString("title"));

                    listArticle.add(newArticle);
                }
                c.getListArticle().addAll(listArticle);
            }
            System.out.println(c);
            data.add(c);
        }

        listOfCategories.set(data);
    }

    static public void updateListOfShoppedArticle(ShoppedArticle sa) {
        ObservableList<ShoppedArticle> data = FXCollections.observableArrayList();
        data.addAll(listOfShoppedArticle);
        System.out.println(data);
        System.out.println(sa);
        int test = -1;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId() == sa.getId()) {
                test = i;
            }
        }
        if (test != -1) {
            // No change detected, need new object
            //data.get(test).setQuantity(data.get(test).getQuantity() + 1);
            ShoppedArticle tmp = sa;
            tmp.setQuantity(data.get(test).getQuantity() + 1);
            data.remove(data.get(test));
            data.add(test, tmp);
        } else {
            data.add(sa);
        }
        listOfShoppedArticle.set(data);
    }

    static public void removeShoppedArticle(ShoppedArticle sa) {
        ObservableList<ShoppedArticle> data = FXCollections.observableArrayList();
        data.addAll(listOfShoppedArticle);
        System.out.println(data);
        System.out.println(sa);
        int test = -1;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId() == sa.getId()) {
                test = i;
            }
        }
        if (test != -1) {
            // No change detected, need new object
            //data.get(test).setQuantity(data.get(test).getQuantity() + 1);
            ShoppedArticle tmp = sa;
            tmp.setQuantity(data.get(test).getQuantity() - 1);
            data.remove(data.get(test));
            data.add(test, tmp);
        } else {
            data.add(sa);
        }
        listOfShoppedArticle.set(data);
        /*if (sa.getQuantity() > 1) {
         ObservableList<ShoppedArticle> data = FXCollections.observableArrayList();
         data.addAll(listOfShoppedArticle);
         System.out.println(data);
         System.out.println(sa);
         int test = -1;
         for (int i = 0; i < data.size(); i++) {
         if (data.get(i).getId() == sa.getId()) {
         test = i;
         }
         }

         ShoppedArticle tmp = sa;
         tmp.setQuantity(data.get(test).getQuantity() - 1);
         data.remove(data.get(test));
         listOfShoppedArticle.set(data);
         data.add(test, tmp);
         System.out.println(data);
         //listOfShoppedArticle.removeAll();
         listOfShoppedArticle.set(data);
         } else {
         listOfShoppedArticle.remove(sa);
         }*/
    }

    public static void sendShoppedArticle() throws Exception {
        ArrayList<ShoppedArticle> data = new ArrayList<>();
        data.addAll(listOfShoppedArticle);
        KfetAPI.putShoppedArticle(data, selectedUser.get());
    }
}
