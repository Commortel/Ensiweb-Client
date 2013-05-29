package ensiweb.client.entity;

public class Student {

    private int id;
    private String name;
    private double account;
    private String promo;
    private String years;

    public Student(int id, String name, double account) {
        this.id = id;
        this.name = name;
        this.account = account;
    }

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAccount() {
        return account;
    }

    public void setAccount(double account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
