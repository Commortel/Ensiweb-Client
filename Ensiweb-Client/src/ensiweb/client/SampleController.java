package ensiweb.client;

import ensiweb.client.entity.Category;
import ensiweb.client.entity.Student;
import ensiweb.client.utils.DatasManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;

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
    private FlowPane CategoryPane;
    @FXML
    private TableView ShoppedArticleList;
    @FXML
    private TableColumn ShoppedArticlePriceColumn;
    @FXML
    private TableColumn ShoppedArticleTitleColumn;

    @FXML
    void handleExitAction(ActionEvent event) {
    }

    @FXML
    void FilterTextChanged(ActionEvent event) throws Exception {
        if (event != null && !((TextField) event.getSource()).getText().equals("")) {
            DatasManager.uptadeListOfUsersAction(((TextField) event.getSource()).getText());
            ((TextField) event.getSource()).setText(null);
        }
    }

    @FXML
    void ListUserMouseClicked(MouseEvent event) {
        //System.out.println(((Student)((ListView) this.ListUser).getSelectionModel().getSelectedItem()).getName());
        //this.NameOfSelectedStudent.setText(((Student)((ListView) this.ListUser).getSelectionModel().getSelectedItem()).getName());
    }

    @FXML
    void initialize() throws Exception {
        DatasManager.uptadeListOfCategoriesAction();
        DatasManager.uptadeListOfUsersAction(null);

        Accordion accordion = new Accordion();
        this.ShoppedArticleList.itemsProperty().bind(DatasManager.listOfShoppedArticle.getReadOnlyProperty());
        this.ShoppedArticlePriceColumn.setCellValueFactory(new PropertyValueFactory<DatasManager.Test, String>("id"));
        this.ShoppedArticleTitleColumn.setCellValueFactory(new PropertyValueFactory<DatasManager.Test, String>("element"));

        for (Category c : DatasManager.listOfCategories.getValue()) {
            Button tmp = new Button(c.getTitle());
            //this.ShoppedArticleTitleColumn..bind(tmp.textProperty());
            tmp.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    DatasManager.updateListOfShoppedArticle("0", ((Button) t.getSource()).getText());
                }
            });

            tmp.setMaxWidth(Double.MAX_VALUE);
            tmp.setMaxHeight(Double.MAX_VALUE);
            TitledPane t = new TitledPane(c.getTitle(), tmp);

            accordion.getPanes().add(t);
        }
        accordion.setMaxHeight(Double.MAX_VALUE);
        accordion.setMaxWidth(Double.MAX_VALUE);
        this.CategoryPane.getChildren().add(accordion);

        //ListUser.visibleProperty().bind(FilterText.textProperty().isEqualTo("").not());
        ListUser.itemsProperty().bind(DatasManager.listOfUser.getReadOnlyProperty());
        //NameOfSelectedStudent.textProperty().bind(((ListView)this.ListUser).getSelectionModel().selectedItemProperty());
        ((ListView) this.ListUser).getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue obsV, Object oldV, Object newV) {
                NameOfSelectedStudent.setText(((Student) newV).getName());
            }
        });
    }
}
