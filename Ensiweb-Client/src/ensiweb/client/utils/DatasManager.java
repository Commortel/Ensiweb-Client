package ensiweb.client.utils;

import ensiweb.client.entity.Article;
import ensiweb.client.entity.Category;
import ensiweb.client.entity.Student;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
            JSONObject item = iterator.next();
            /*JSONArray group = (JSONArray) item.get("class_and_groups");
             Iterator<JSONObject> groupiterator = group.iterator();
             while (groupiterator.hasNext()) {
             JSONObject groupitem = groupiterator.next();

             System.out.println(groupitem.get("group"));
             }*/
            System.out.println(item.get("id") + " " + item.get("last_name") + " " + item.get("first_name") + item.get("account"));
            data.add(new Student(
                    Integer.parseInt(item.get("id").toString()),
                    item.get("last_name") + " " + item.get("first_name"),
                    Double.parseDouble(item.get("account").toString())));
        }

        listOfUser.set(data);
    }

    static public void updateListOfCategoriesAction() throws Exception {

        ObservableList<Category> data = FXCollections.observableArrayList();

        JSONObject jsonObject = KfetAPI.getAllCategories();
        JSONArray categories = (JSONArray) jsonObject.get("categories");
        Iterator<JSONObject> iterator = categories.iterator();

        while (iterator.hasNext()) {
            JSONObject item = iterator.next();
            Category c = new Category(Integer.parseInt(item.get("id").toString()), item.get("title").toString());

            JSONArray article = (JSONArray) item.get("article");
            if (article != null && !article.isEmpty()) {
                Iterator<JSONObject> iteratorArticle = article.iterator();
                ArrayList<Article> listArticle = new ArrayList<>();

                while (iteratorArticle.hasNext()) {
                    JSONObject itemArticle = iteratorArticle.next();

                    Article newArticle = new Article();
                    newArticle.setId(Integer.parseInt(itemArticle.get("id").toString()));
                    newArticle.setPrice(Double.parseDouble(itemArticle.get("price").toString()));
                    newArticle.setTitle((String) itemArticle.get("title"));

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
        if(listOfShoppedArticle.contains(sa))
        {
            //data.get(index)
            //sa.getQuantity();
        }
        else
        {
            data.add(sa);
        }
        listOfShoppedArticle.set(data);
    }

    static public void removeShoppedArticle(ShoppedArticle sa) {
        ObservableList<ShoppedArticle> data = FXCollections.observableArrayList();

        data.addAll(listOfShoppedArticle);
        data.remove(sa);
        listOfShoppedArticle.set(data);
    }

    public static void sendShoppedArticle() throws Exception {
        ArrayList<ShoppedArticle> data = new ArrayList<>();
        data.addAll(listOfShoppedArticle);
        KfetAPI.putShoppedArticle(data, selectedUser.get());
    }

    public static class ShoppedArticle {

        private SimpleIntegerProperty id = new SimpleIntegerProperty();
        private SimpleDoubleProperty price = new SimpleDoubleProperty();
        private SimpleStringProperty title = new SimpleStringProperty();
        private SimpleIntegerProperty quantity = new SimpleIntegerProperty();

        public ShoppedArticle(int id, double price, String title) {
            this.id.set(id);
            this.price.set(price);
            this.title.set(title);
            this.quantity.set(1);
        }

        public int getId() {
            return this.id.get();
        }

        public Double getPrice() {
            return price.get();
        }

        public String getTitle() {
            return title.get();
        }

        public int getQuantity() {
            return quantity.get();
        }
        
        public void setQuantity(int quantity){
            this.quantity.set(quantity);
        }
    }
}
