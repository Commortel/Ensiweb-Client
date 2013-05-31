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

    public Category() {
        this.listArticle = new ArrayList<>();
    }
    
    public Category(int id, String title) {
        super();
        this.id = id;
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

    @Override
    public String toString() {
        return this.title;
    }
}
