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
public class Category {

    private int id;
    private String title;
    private ArrayList<Article> listArticle;

    public Category(int id, String title) {
        this.id = id;
        this.listArticle = new ArrayList<>();
        this.title = title;
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

    public ArrayList<Article> getListArticle() {
        return listArticle;
    }
}
