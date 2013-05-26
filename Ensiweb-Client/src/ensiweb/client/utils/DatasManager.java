package ensiweb.client.utils;

import ensiweb.client.entity.Article;
import ensiweb.client.entity.Category;
import ensiweb.client.entity.Student;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DatasManager {

    static public ReadOnlyListWrapper<Student> listOfUser = new ReadOnlyListWrapper<>();
    static public ReadOnlyListWrapper<Category> listOfCategories = new ReadOnlyListWrapper<>();
    static public ReadOnlyListWrapper<ShoppedArticle> listOfShoppedArticle = new ReadOnlyListWrapper<>();

    static public void uptadeListOfUsersAction(String query) throws Exception {

        ObservableList<Student> data = FXCollections.observableArrayList();

        JSONObject jsonObject = KfetAPI.getAllUser(query);
        JSONArray users = (JSONArray) jsonObject.get("users");
        Iterator<JSONObject> iterator = users.iterator();

        while (iterator.hasNext()) {
            JSONObject item = iterator.next();
            System.out.println(item.get("id") + " " + item.get("last_name") + " " + item.get("first_name"));
            data.add(new Student(Integer.parseInt(item.get("id").toString()), item.get("last_name") + " " + item.get("first_name")));
        }

        listOfUser.set(data);
    }

    static public void uptadeListOfCategoriesAction() throws Exception {

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
                    newArticle.setPrice((double) itemArticle.get("price"));
                    newArticle.setTitle((String) itemArticle.get("title"));

                    listArticle.add(newArticle);
                }
                c.getListArticle().addAll(listArticle);
            }
            data.add(c);
        }

        listOfCategories.set(data);
    }

    static public void updateListOfShoppedArticle(double price, String title) {
        ObservableList<ShoppedArticle> data = FXCollections.observableArrayList();

        data.addAll(listOfShoppedArticle);
        data.add(new ShoppedArticle(price, title));

        listOfShoppedArticle.set(data);
    }

    public static class ShoppedArticle {

        private SimpleDoubleProperty price = new SimpleDoubleProperty();
        private SimpleStringProperty title = new SimpleStringProperty();

        public ShoppedArticle(double price, String title) {
            this.price.set(price);
            this.title.set(title);
        }

        public Double getPrice() {
            return price.get();
        }

        public String getTitle() {
            return title.get();
        }
    }
}
