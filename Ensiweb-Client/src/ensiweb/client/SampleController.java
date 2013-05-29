package ensiweb.client;

import ensiweb.client.entity.Article;
import ensiweb.client.entity.Category;
import ensiweb.client.entity.Student;
import ensiweb.client.utils.DatasManager;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
    private TableColumn ShoppedArticleQuantityColumn;
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
        if (DatasManager.selectedUser.get() != null && !DatasManager.listOfShoppedArticle.isEmpty()) {
            DatasManager.sendShoppedArticle();
            DatasManager.listOfShoppedArticle.clear();
            DatasManager.sumOfShoppedArticle.set(0);
        } else {
            System.out.println("Fail Valid");
        }
    }

    @FXML
    void initialize() throws Exception {
        this.initializeHome();
        this.initializeUser();
        this.initializeCategory();
        this.initializeShoppedArticle();
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
                BigDecimal d = (new BigDecimal(((Student) newV).getAccount())).setScale(2, RoundingMode.HALF_EVEN);
                AccountStudentSelected.setText(d.doubleValue() + "€");
                DatasManager.selectedUser.set((Student) newV);
            }
        });
    }

    private void initializeCategory() throws Exception {
        this.ShoppedArticleList.itemsProperty().bind(DatasManager.listOfShoppedArticle.getReadOnlyProperty());
        this.ShoppedArticlePriceColumn.setCellValueFactory(new PropertyValueFactory<DatasManager.ShoppedArticle, String>("price"));
        this.ShoppedArticleTitleColumn.setCellValueFactory(new PropertyValueFactory<DatasManager.ShoppedArticle, String>("title"));
        this.ShoppedArticleQuantityColumn.setCellValueFactory(new PropertyValueFactory<DatasManager.ShoppedArticle, String>("quantity"));

        final Map<Integer, FlowPane> listcat = new HashMap<>();

        DatasManager.listOfCategories.addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object oldValue, Object newValue) {
                FlowPane flowCat = new FlowPane();
                flowCat.setVgap(8);
                flowCat.setHgap(8);
                flowCat.setPrefWrapLength(600);
                for (final Category c : DatasManager.listOfCategories.getValue()) {
                    FlowPane flow = new FlowPane();
                    flow.setVgap(8);
                    flow.setHgap(8);
                    flow.setPrefWrapLength(600);

                    Button cattmp = new Button(c.getTitle());
                    cattmp.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent t) {
                            SampleController.this.CategoryPane.getChildren().clear();
                            SampleController.this.CategoryPane.getChildren().add(listcat.get(c.getId()));
                        }
                    });
                    cattmp.setMinSize(100, 100);

                    Button back = new Button("Retour");
                    back.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent t) {
                            SampleController.this.CategoryPane.getChildren().clear();
                            SampleController.this.CategoryPane.getChildren().add(listcat.get(0));
                        }
                    });
                    back.setMinSize(100, 100);
                    flow.getChildren().add(back);

                    for (final Article a : c.getListArticle()) {
                        Button tmp = new Button(a.getTitle());
                        tmp.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent t) {
                                DatasManager.updateListOfShoppedArticle(
                                        new DatasManager.ShoppedArticle(
                                        a.getId(),
                                        a.getPrice(),
                                        ((Button) t.getSource()).getText()));
                                DatasManager.sumOfShoppedArticle.set(DatasManager.sumOfShoppedArticle.get() + a.getPrice());
                            }
                        });
                        tmp.setMinSize(100, 100);
                        flow.getChildren().add(tmp);
                    }

                    listcat.put(c.getId(), flow);
                    flowCat.getChildren().add(cattmp);
                }
                listcat.put(0, flowCat);
                SampleController.this.CategoryPane.getChildren().add(flowCat);
            }
        });
        DatasManager.updateListOfCategoriesAction();

        this.ShoppedArticleList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<DatasManager.ShoppedArticle>() {
            @Override
            public void changed(ObservableValue<? extends DatasManager.ShoppedArticle> ov, DatasManager.ShoppedArticle t, DatasManager.ShoppedArticle t1) {
                if (t1 != null) {
                    DatasManager.sumOfShoppedArticle.set(DatasManager.sumOfShoppedArticle.get() - t1.getPrice()*t1.getQuantity());
                    DatasManager.removeShoppedArticle((DatasManager.ShoppedArticle) t1);
                }
            }
        });
    }

    void initializeStock() throws Exception {
        StockCategoryList.itemsProperty().bind(DatasManager.listOfCategories.getReadOnlyProperty());
    }

    private void initializeShoppedArticle() {
        DatasManager.sumOfShoppedArticle.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                BigDecimal d = (new BigDecimal(t1.doubleValue())).setScale(2, RoundingMode.HALF_EVEN);
                /*NumberFormat frCostFormat = NumberFormat.getCurrencyInstance(Locale.ROOT);
                 frCostFormat.setMinimumFractionDigits( 1 );
                 frCostFormat.setMaximumFractionDigits( 2 );
                 SampleController.this.ShoppedListSubmit.setText(frCostFormat.format(d.doubleValue()));*/
                SampleController.this.ShoppedListSubmit.setText(d.doubleValue() + "€");
            }
        });
    }
}
