package ensiweb.client;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TitledPane;


public class SampleController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TitledPane x2;


    @FXML
    void handleExitAction(ActionEvent event) {
    }

    @FXML
    void initialize() {
        assert x2 != null : "fx:id=\"x2\" was not injected: check your FXML file 'MainFrame.fxml'.";


    }

}
