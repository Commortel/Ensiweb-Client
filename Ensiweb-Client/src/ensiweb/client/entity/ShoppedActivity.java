/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ensiweb.client.entity;

/**
 *
 * @author thibaut
 */
public class ShoppedActivity {
    private int id;
    private double price;
    private String date;
    private Article article;
    private Student student;
    private Student student_sealer;

    public ShoppedActivity(int id, double price, String date, Article article, Student student, Student student_sealer) {
        this.id = id;
        this.price = price;
        this.date = date;
        this.article = article;
        this.student = student;
        this.student_sealer = student_sealer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Student getStudent_sealer() {
        return student_sealer;
    }

    public void setStudent_sealer(Student student_sealer) {
        this.student_sealer = student_sealer;
    }

    @Override
    public String toString() {
        return "ShoppedActivity{" + "id=" + id + ", price=" + price + ", date=" + date + ", article=" + article + ", student=" + student + ", student_sealer=" + student_sealer + '}';
    }
}
