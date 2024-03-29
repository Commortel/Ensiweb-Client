package ensiweb.client.utils;

import ensiweb.client.entity.Article;
import ensiweb.client.entity.Category;
import ensiweb.client.entity.Product;
import ensiweb.client.entity.ShoppedActivity;
import ensiweb.client.entity.ShoppedArticle;
import ensiweb.client.entity.Stock;
import ensiweb.client.entity.Student;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DatasManager {

    static public ReadOnlyListWrapper<Student> listOfUser = new ReadOnlyListWrapper<>();
    static public ReadOnlyListWrapper<Category> listOfCategories = new ReadOnlyListWrapper<>();
    static public ReadOnlyListWrapper<ShoppedArticle> listOfShoppedArticle = new ReadOnlyListWrapper<>();
    static public ReadOnlyObjectWrapper<Student> selectedUser = new ReadOnlyObjectWrapper<>();
    static public DoubleProperty sumOfShoppedArticle = new SimpleDoubleProperty();
    static public ReadOnlyListWrapper<ShoppedActivity> listofShoppedActivity = new ReadOnlyListWrapper<>();
    static public ReadOnlyListWrapper<Product> listOfProduct = new ReadOnlyListWrapper<>();
    static public ReadOnlyListWrapper<Series<Number, Number>> listOfChartStock = new ReadOnlyListWrapper<>();
    static public ReadOnlyListWrapper<Stock> listOfStock = new ReadOnlyListWrapper<>();
    static public ReadOnlyDoubleWrapper accountOfStudent = new ReadOnlyDoubleWrapper();
    static public ReadOnlyStringWrapper displayBy = new ReadOnlyStringWrapper();
    static public ReadOnlyListWrapper<Student> listOfRepoKfet = new ReadOnlyListWrapper<>();

    static public void updateListOfUsersAction(String query) throws Exception {

        ObservableList<Student> data = FXCollections.observableArrayList();

        JSONObject jsonObject = KfetAPI.getAllUser(query);
        JSONArray users = (JSONArray) jsonObject.get("users");
        Iterator<JSONObject> iterator = users.iterator();

        while (iterator.hasNext()) {
            KfetJSONObject item = KfetJSONObject.iteratorNext(iterator);
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
            if(c.getId() != 3)
            {    
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
                data.add(c);
            }
        }
        
        // Traitement des menus à part
        JSONObject obj = KfetAPI.getCategoriesById(3);
        JSONArray menu = (JSONArray) obj.get("category");
        Iterator<JSONObject> it = menu.iterator();
        Category c = new Category();
        c.setId(3);
        c.setTitle("Menu");
            
        while (it.hasNext()) {
            KfetJSONObject item = KfetJSONObject.iteratorNext(it);
            KfetJSONObject itemArticle = item.getJSONObject("data");
            
            Article newArticle = new Article();
            newArticle.setId(itemArticle.getInt("id"));
            newArticle.setPrice(itemArticle.getDouble("price"));
            newArticle.setTitle(itemArticle.getString("title"));
            newArticle.setIsMenu(true);
            
            ArrayList<Long> cat = (ArrayList) item.get("cat");
            Iterator<Long> itcat = cat.iterator();
            ArrayList<Category> listcat = new ArrayList<>();
            while (itcat.hasNext()) {
                Category newCategory = new Category();
                newCategory.setId(itcat.next().intValue());
                listcat.add(newCategory);
            }
            c.getListArticle().add(newArticle);
        }
        data.add(c);

        listOfCategories.set(data);
    }

    static public void updateListOfShoppedArticle(ShoppedArticle sa) {
        ObservableList<ShoppedArticle> data = FXCollections.observableArrayList();
        data.addAll(listOfShoppedArticle);
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
        if (sa.getQuantity() > 1) {
            ObservableList<ShoppedArticle> data = FXCollections.observableArrayList();
            data.addAll(listOfShoppedArticle);
            int test = -1;
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getId() == sa.getId()) {
                    test = i;
                }
            }
            if (test != -1) {
                ShoppedArticle tmp = listOfShoppedArticle.getReadOnlyProperty().get().get(test);
                tmp.setQuantity(tmp.getQuantity() - 1);
                listOfShoppedArticle.remove(sa);
                listOfShoppedArticle.add(tmp);
            } else {
                ShoppedArticle tmp = listOfShoppedArticle.getReadOnlyProperty().get().get(listOfShoppedArticle.getSize() - 1);
                tmp.setQuantity(tmp.getQuantity() - 1);
                listOfShoppedArticle.remove(sa);
                listOfShoppedArticle.add(tmp);
            }
        } else {
            listOfShoppedArticle.remove(sa);
        }
    }

    static public void sendShoppedArticle() throws Exception {
        ArrayList<ShoppedArticle> data = new ArrayList<>();
        data.addAll(listOfShoppedArticle);
        KfetAPI.putShoppedArticle(data, selectedUser.get());
    }

    static public void updateListofShoppedActivity() throws Exception {
        ObservableList<ShoppedActivity> data = FXCollections.observableArrayList();

        JSONObject jsonObject = KfetAPI.getAllShoppedArticle();
        JSONArray sp = (JSONArray) jsonObject.get("sp");
        Iterator<JSONObject> iterator = sp.iterator();

        while (iterator.hasNext()) {
            KfetJSONObject item = KfetJSONObject.iteratorNext(iterator);
            ShoppedActivity s = new ShoppedActivity();
            s.setId(item.getInt("id"));
            s.setPrice(item.getDouble("price"));
            s.setDate(item.getString("date"));

            if (item.has("article")) {
                KfetJSONObject article = item.getJSONObject("article");
                Article newArticle = new Article();
                newArticle.setId(article.getInt("id"));
                newArticle.setPrice(article.getDouble("price"));
                newArticle.setTitle(article.getString("title"));

                s.setArticle(newArticle);
            }
            if (item.has("student")) {
                KfetJSONObject student = item.getJSONObject("student");
                Student newStudent = new Student();
                newStudent.setId(student.getInt("id"));
                newStudent.setName(student.getString("last_name") + " " + student.getString("first_name"));
                newStudent.setAccount(student.getDouble("account"));
                s.setStudent(newStudent);
            }
            if (item.has("student_sealer")) {
                KfetJSONObject sealer = item.getJSONObject("student_sealer");
                Student newStudent = new Student();
                newStudent.setId(sealer.getInt("id"));
                newStudent.setName(sealer.getString("last_name") + " " + sealer.getString("first_name"));
                newStudent.setAccount(sealer.getDouble("account"));
                s.setStudent_sealer(newStudent);
            }
            data.add(s);
        }

        listofShoppedActivity.set(data);
    }

    static public void updateListOfProduct() throws Exception {
        ObservableList<Product> data = FXCollections.observableArrayList();

        JSONObject jsonObject = KfetAPI.getAllProduct();
        JSONArray product = (JSONArray) jsonObject.get("product");
        Iterator<JSONObject> iterator = product.iterator();

        while (iterator.hasNext()) {
            KfetJSONObject item = KfetJSONObject.iteratorNext(iterator);
            Product p = new Product();
            p.setId(item.getInt("id"));
            p.setTitle(item.getString("title"));
            p.setStockSum(item.getInt("stock_sum"));

            if (item.has("stock")) {
                JSONArray stock = item.getArray("stock");
                Iterator<JSONObject> iteratorStock = stock.iterator();
                ObservableList<Stock> listStock = FXCollections.observableArrayList();

                while (iteratorStock.hasNext()) {
                    KfetJSONObject itemStock = KfetJSONObject.iteratorNext(iteratorStock);

                    Stock newStock = new Stock();
                    newStock.setId(itemStock.getInt("id"));
                    newStock.setDate(itemStock.getString("date"));
                    newStock.setStock(itemStock.getInt("stock"));
                    newStock.setPrice(itemStock.getDouble("price"));

                    listStock.add(newStock);
                }
                p.getStock().set(listStock);
            }
            data.add(p);
        }

        listOfProduct.set(data);
    }

    static public void updateListOfStock(int p) throws Exception {
        ObservableList<Stock> data = FXCollections.observableArrayList();

        JSONObject jsonObject = KfetAPI.getArticleById(p);
        JSONArray products = (JSONArray) jsonObject.get("products");
        Iterator<JSONObject> iterator = products.iterator();

        if (iterator.hasNext()) {
            KfetJSONObject item = KfetJSONObject.iteratorNext(iterator);
            JSONArray itemStock = (JSONArray) item.get("stock");
            Iterator<JSONObject> iteratorStock = itemStock.iterator();

            while (iteratorStock.hasNext()) {
                KfetJSONObject i = KfetJSONObject.iteratorNext(iteratorStock);
                Stock newStock = new Stock();
                newStock.setId(i.getInt("id"));
                newStock.setDate(i.getString("date"));
                newStock.setStock(i.getInt("stock"));
                newStock.setPrice(i.getDouble("price"));
                data.add(newStock);
            }
        }
        listOfStock.set(data);
    }

    static public void updateStudentAccount() throws Exception {
        KfetAPI.putStudentAccount(selectedUser.get(), accountOfStudent.get());
    }

    static public void updateChartOfStock() throws Exception {
        ObservableList<Series<Number, Number>> data = FXCollections.observableArrayList();
        String[] month = {"Jan", "Fév", "Mar", "Avr", "Mai", "Jun", "Jui", "Aou", "Sep", "Oct", "Nov", "Déc"};
        XYChart.Series series = new XYChart.Series();

        for (Entry<Integer, Integer> en : UI.refactorDate(displayBy.get(), listOfStock).entrySet()) {
            series.getData().add(new XYChart.Data(
                    displayBy.get().equals("Month") ? month[en.getKey()] : en.getKey().toString(),
                    en.getValue()));
        }
        data.add(series);
        listOfChartStock.set(data);
    }

    static public boolean checkCredential(int login, String password) {
        try {
            JSONObject item = KfetAPI.checkCredential(login, password);
            JSONObject respo = (JSONObject) item.get("student");
            return (boolean) respo.get("credentialPassword");
        } catch (Exception ex) {
            Logger.getLogger(DatasManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    static public void updatelistOfRespoKfet() throws Exception {
        ObservableList<Student> data = FXCollections.observableArrayList();
        JSONObject item = KfetAPI.getRespo();
        JSONArray resp = (JSONArray) item.get("resp");
        Iterator<JSONObject> iterator = resp.iterator();

        while (iterator.hasNext()) {
            KfetJSONObject respo = KfetJSONObject.iteratorNext(iterator);
            Student respoStudent = new Student();
            respoStudent.setId(respo.getInt("id"));
            respoStudent.setName(respo.getString("name"));
            data.add(respoStudent);
        }
        listOfRepoKfet.set(data);
    }
}
