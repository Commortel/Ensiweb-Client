/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ensiweb.client.entity;

/**
 *
 * @author thibaut
 */
public class Article {

    private int id;
    private double price;
    private String title;

    public Article(int id, double price, String title) {
        this.id = id;
        this.price = price;
        this.title = title;
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
}
