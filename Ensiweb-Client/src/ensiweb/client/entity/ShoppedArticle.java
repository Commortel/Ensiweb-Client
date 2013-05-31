package ensiweb.client.entity;

import java.util.Objects;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ShoppedArticle {

    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleDoubleProperty price = new SimpleDoubleProperty();
    private SimpleStringProperty title = new SimpleStringProperty();
    private ReadOnlyIntegerWrapper quantity = new ReadOnlyIntegerWrapper();

    public ShoppedArticle(int id, double price, String title) {
        this.id.set(id);
        this.price.set(price);
        this.title.set(title);
        this.quantity.set(1);
    }

    public int getId() {
        return this.id.get();
    }

    public Double getPrice() {
        return price.get();
    }

    public String getTitle() {
        return title.get();
    }

    public int getQuantity() {
        return quantity.get();
    }
    public ReadOnlyIntegerProperty getQuantityProperty() {
        return quantity.getReadOnlyProperty();
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    @Override
    public String toString() {
        return "ShoppedArticle{" + "id=" + id.get()
                + ", price=" + price.get()
                + ", title=" + title.get()
                + ", quantity=" + quantity.get()
                + ", hash=" + this.hashCode() + '}';
    }

    @Override
    public int hashCode() {
        return this.id.get();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ShoppedArticle other = (ShoppedArticle) obj;
        if (!Objects.equals(this.id.get(), other.id.get())) {
            return false;
        }
        return true;
    }
}
