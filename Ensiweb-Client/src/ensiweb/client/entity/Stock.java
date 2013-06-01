/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ensiweb.client.entity;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author thibaut
 */
public class Stock {

    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleStringProperty date = new SimpleStringProperty();
    private SimpleDoubleProperty price = new SimpleDoubleProperty();
    private SimpleIntegerProperty stock = new SimpleIntegerProperty();

    public Stock() {
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public int getStock() {
        return this.stock.get();
    }

    public void setStock(int stock) {
        this.stock.set(stock);
    }
    
    @Override
    public String toString() {
        return "Stock{" + "id=" + id + ", date=" + date + ", price=" + price + ", stock=" + stock + '}';
    }
}
