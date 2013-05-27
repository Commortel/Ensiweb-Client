package ensiweb.client;

import ensiweb.client.entity.Article;
import ensiweb.client.entity.Category;
import ensiweb.client.entity.Student;
import ensiweb.client.utils.DatasManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
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
    private ListView StockCategoryList;
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
    private Label ShoppedListSubmit;

    @FXML
    void handleExitAction(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void FilterTextChanged(ActionEvent event) throws Exception {
        if (event != null && !((TextField) event.getSource()).getText().equals("")) {
            DatasManager.updateListOfUsersAction(((TextField) event.getSource()).getText());
            ((TextField) event.getSource()).setText(null);
        }
    }

    @FXML
    void ShoppedListButtonMouseClicked(MouseEvent event) throws Exception {
        DatasManager.sendShoppedArticle();
        DatasManager.listOfShoppedArticle.clear();
        this.ShoppedListSubmit.setText("0,00€");
    }

    @FXML
    void initialize() throws Exception {
        this.initializeHome();
        this.initializeUser();
        this.initializeCategory();
        this.initializeStock();
    }

    void initializeHome() throws Exception {
    }

    void initializeUser() throws Exception {
        DatasManager.updateListOfUsersAction("");
        ListUser.itemsProperty().bind(DatasManager.listOfUser.getReadOnlyProperty());
        ((ListView) this.ListUser).getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue obsV, Object oldV, Object newV) {
                NameOfSelectedStudent.setText(((Student) newV).getName());
                AccountStudentSelected.setText(Double.toString(((Student)newV).getAccount()) + "€");
                DatasManager.selectedUser.set((Student) newV);
            }
        });
    }

    private void initializeCategory() throws Exception {

        this.ShoppedArticleList.itemsProperty().bind(DatasManager.listOfShoppedArticle.getReadOnlyProperty());
        this.ShoppedArticlePriceColumn.setCellValueFactory(new PropertyValueFactory<DatasManager.ShoppedArticle, String>("price"));
        this.ShoppedArticleTitleColumn.setCellValueFactory(new PropertyValueFactory<DatasManager.ShoppedArticle, String>("title"));

        /*DatasManager.listOfCategories.addListener(new ChangeListener() {
         @Override
         public void changed(ObservableValue ov, Object oldValue, Object newValue) {*/
        DatasManager.updateListOfCategoriesAction();
        for (Category c : DatasManager.listOfCategories.getValue()) {
            for (final Article a : c.getListArticle()) {
                Button tmp = new Button(a.getTitle());
                tmp.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        DatasManager.updateListOfShoppedArticle(a.getId(), a.getPrice(), ((Button) t.getSource()).getText());
                        //SampleController.this.ShoppedListSubmit.setText();
                    }
                });
                SampleController.this.CategoryPane.getChildren().add(tmp);
            }
        }
        //}
        //});

        this.ShoppedArticleList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue obsV, Object oldV, Object newV) {
                DatasManager.removeShoppedArticle((DatasManager.ShoppedArticle) newV);
            }
        });
    }

    void initializeStock() throws Exception {
        StockCategoryList.itemsProperty().bind(DatasManager.listOfCategories.getReadOnlyProperty());
    }
}
