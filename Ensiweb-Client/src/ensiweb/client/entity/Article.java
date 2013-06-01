/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ensiweb.client.entity;

import java.util.ArrayList;

/**
 *
 * @author thibaut
 */
public class Article {

    private int id;
    private double price;
    private String title;
    private ArrayList<Product> products;
    private boolean isMenu;
    private ArrayList<Category> categories;

    public Article() {
    }

    public Article(int id, double price, String title, boolean menu) {
        this.id = id;
        this.price = price;
        this.title = title;
        this.products = new ArrayList<>();
        this.isMenu = menu;
        this.categories = new ArrayList<>();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
