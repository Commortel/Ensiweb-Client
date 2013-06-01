/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ensiweb.client.entity;

import java.util.ArrayList;
import java.util.Observable;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author thibaut
 */
public class Product {

    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleStringProperty title = new SimpleStringProperty();
    private SimpleStringProperty date = new SimpleStringProperty();
    private SimpleIntegerProperty stock_sum = new SimpleIntegerProperty();
    private SimpleListProperty<Stock> stock = new ReadOnlyListWrapper<>();

    public Product() {
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getTitle() {
        return this.title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public int getStockSum() {
        return this.stock_sum.get();
    }

    public void setStockSum(int stock_sum) {
        this.stock_sum.set(stock_sum);
    }

    public SimpleListProperty<Stock> getStock() {
        return stock;
    }
}
