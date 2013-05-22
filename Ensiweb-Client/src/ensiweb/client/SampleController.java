package ensiweb.client;

import ensiweb.client.utils.KfetAPI;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;


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
    void handleExitAction(ActionEvent event){
    }
    
    void ProductClickedAction(ActionEvent event){
                
    }

    @FXML
    void initialize() {
        assert x2 != null : "fx:id=\"x2\" was not injected: check your FXML file 'MainFrame.fxml'.";


    }

}
