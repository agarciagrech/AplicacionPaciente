/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package aplicacionpaciente;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import patientUtilities.Menu;

/**
 * FXML Controller class
 *
 * @author jaime
 */
public class ShowSignalsController {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button ConsultSignalsButton;

    @FXML
    private TextField Signals;

    @FXML
    void ConsultSignals(ActionEvent event) {
        Window owner = ConsultSignalsButton.getScene().getWindow();
        List <String> sList = Menu.showSignalList();
        showSignals(Alert.AlertType.INFORMATION, owner, "Signals of patient",sList.toString());

    }
    
     public static void showSignals(Alert.AlertType alertType, Window owner, String title, String message ) {
       Alert alert = new Alert(alertType);
       alert.setTitle(title);
       alert.setHeaderText(null);
       alert.setContentText(message);
       alert.initOwner(owner);
       alert.show();
    }
    
}
