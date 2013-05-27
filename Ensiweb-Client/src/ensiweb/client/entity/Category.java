/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ensiweb.client.entity;

import java.util.ArrayList;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.ReadOnlyListWrapper;

/**
 *
 * @author thibaut
 */
public class Category {

    private int id;
    private String title;
    private ReadOnlyListWrapper<Article> listArticle;

    public Category(int id, String title) {
        this.id = id;
        this.listArticle = new ReadOnlyListWrapper<>();
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
    
    public boolean addArticle(Article article) {
        return this.listArticle.add(article);
    }

    public ReadOnlyListProperty<Article> getListArticle() {
        return listArticle.getReadOnlyProperty();
    }
}
