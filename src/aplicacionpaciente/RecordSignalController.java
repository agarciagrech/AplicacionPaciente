/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package aplicacionpaciente;

import static aplicacionpaciente.RegisterPatientController.infoMessage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import patientUtilities.Menu;
import static patientUtilities.Menu.br;
import static patientUtilities.Menu.pw;
import pojos.Patient;

/**
 * FXML Controller class
 *
 * @author jaime
 */
public class RecordSignalController {

    @FXML
    private Button recordButton;
    @FXML
    private Button backButton;

    @FXML
    private Button exitButton;

    @FXML
    void record(ActionEvent event) {
        Menu.recordSignal();
        infoMessage("End of recording signal", null, "End");
    }
    
    
    @FXML
    void back(ActionEvent event) throws IOException{
        Menu.backToMenu();
        URL url = new File("src/aplicacionpaciente/menuPatient.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void exit(ActionEvent event) {
        Menu.exit();
    }
    
    public static void infoMessage(String infoMessage, String headerText, String title) {
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setContentText(infoMessage);
           alert.setTitle(title);
           alert.setHeaderText(headerText);
           alert.showAndWait();
       }
    
    
}
