/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ensiweb.client;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author thibaut
 */
public class LoginController implements Initializable {

    @FXML
    void handleExitAction(ActionEvent event) {
        
        Platform.exit();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
