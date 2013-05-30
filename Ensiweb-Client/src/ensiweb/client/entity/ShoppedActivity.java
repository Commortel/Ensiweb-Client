/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ensiweb.client.entity;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author thibaut
 */
public class ShoppedActivity {

    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleDoubleProperty price = new SimpleDoubleProperty();
    private SimpleStringProperty date = new SimpleStringProperty();
    private SimpleObjectProperty<Article> article = new ReadOnlyObjectWrapper<>();
    private SimpleObjectProperty<Student> student = new ReadOnlyObjectWrapper<>();
    private SimpleObjectProperty<Student> student_sealer = new ReadOnlyObjectWrapper<>();

    public ShoppedActivity() {
    }
    
    public ShoppedActivity(int id, double price, String date, Article article, Student student, Student student_sealer) {
        this.id.set(id);
        this.price.set(price);
        this.date.set(date);
        this.article.set(article);
        this.student.set(student);
        this.student_sealer.set(student_sealer);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public Article getArticle() {
        return article.get();
    }

    public void setArticle(Article article) {
        this.article.set(article);
    }

    public Student getStudent() {
        return student.get();
    }

    public void setStudent(Student student) {
        this.student.set(student);
    }

    public Student getStudent_sealer() {
        return student_sealer.get();
    }

    public void setStudent_sealer(Student student_sealer) {
        this.student_sealer.set(student_sealer);
    }

    @Override
    public String toString() {
        return "ShoppedActivity{"
                + "id=" + id.get()
                + ", price=" + price.get()
                + ", date=" + date.get()
                + ", article=" + article.get()
                + ", student=" + student.get()
                + ", student_sealer=" + student_sealer.get()
                + '}';
    }
}
