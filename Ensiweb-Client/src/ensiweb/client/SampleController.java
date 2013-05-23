package ensiweb.client;

import ensiweb.client.entity.Student;
import ensiweb.client.utils.DatasManager;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;

public class SampleController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TitledPane x2;
    @FXML
    private TableView ShoppedProductList;
    @FXML
    private TableView TableViewHistory;
    @FXML
    private ScrollPane ScrollPaneProduct;
    @FXML
    private ListView ListUser;
    @FXML
    private TextField FilterText;
    @FXML
    private Label AccountStudentSelected;
    @FXML
    private Label IsCotisantSelectedStudent;
    @FXML
    private Label NameOfSelectedStudent;

    @FXML
    void handleExitAction(ActionEvent event) {
    }

    @FXML
    void FilterTextChanged(ActionEvent event) throws Exception {
        DatasManager.uptadeListOfUsersAction(((TextField) event.getSource()).getText());
        ((TextField) event.getSource()).setText(null);
    }

    @FXML
    void ListUserMouseClicked(MouseEvent event) {
        //System.out.println(((Student)((ListView) this.ListUser).getSelectionModel().getSelectedItem()).getName());
        //this.NameOfSelectedStudent.setText(((Student)((ListView) this.ListUser).getSelectionModel().getSelectedItem()).getName());
    }

    @FXML
    void initialize() {
        //ListUser.visibleProperty().bind(FilterText.textProperty().isEqualTo("").not());
        //ListUser.itemsProperty().bind(DatasManager.listOfUser.getReadOnlyProperty());
        //Student s = (Student)((ListView) this.ListUser).getSelectionModel().getSelectedItems();
        ((ListView)this.ListUser).getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue obsV, Object oldV, Object newV) {
                NameOfSelectedStudent.setText(((Student)newV).getName());
            }
        });


        DatasManager.listOfUser.addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {

                ObservableList<Student> data = FXCollections.observableArrayList();
                
                for (Student s : DatasManager.listOfUser) {
                    data.add(s);
                }

                ListUser.setItems(data);
            }
        });
    }
}
