package ensiweb.client;

import ensiweb.client.entity.Article;
import ensiweb.client.entity.Category;
import ensiweb.client.entity.Student;
import ensiweb.client.utils.DatasManager;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
    void handleExitAction(ActionEvent event) {
        Platform.exit();
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
        this.initializeHome();
        this.initializeStock();
        this.initializeUser();
    }

    void initializeHome() throws Exception {
    }

    void initializeUser() throws Exception {
        this.ShoppedArticleList.itemsProperty().bind(DatasManager.listOfShoppedArticle.getReadOnlyProperty());
        this.ShoppedArticlePriceColumn.setCellValueFactory(new PropertyValueFactory<DatasManager.Test, String>("id"));
        this.ShoppedArticleTitleColumn.setCellValueFactory(new PropertyValueFactory<DatasManager.Test, String>("element"));

        DatasManager.listOfCategories.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change change) {
                Accordion accordion = new Accordion();
                for (Category c : DatasManager.listOfCategories.getValue()) {
                    System.out.println("Category:" + c);
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

                SampleController.this.CategoryPane.getChildren().add(accordion);
            }
        });

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

    void initializeStock() throws Exception {
        // Add categories
        this.StockCategoryList.itemsProperty().bind(DatasManager.listOfCategories.getReadOnlyProperty());

        DatasManager.listOfCategories.addListener(new ListChangeListener<Category>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Category> change) {
                ArrayList<Article> articles = new ArrayList<>();
                
                for (Category c : DatasManager.listOfCategories.getValue()) {
                    articles.addAll(c.getListArticle());
                }
                //SampleController.this.StockCategoryList.
                //SampleController.this.StockCategoryList.getChildrenUnmodifiable().add(articles);
            }
        });

        // Select category
        this.StockCategoryList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Category>() {
            @Override
            public void changed(ObservableValue<? extends Category> ov, Category oldValue, Category newValue) {
                Category category = (Category) newValue;
                System.out.println("Selected item : " + category);
            }
        });
    }
}
