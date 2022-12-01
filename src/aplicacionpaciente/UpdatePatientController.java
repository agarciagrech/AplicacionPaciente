/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package aplicacionpaciente;

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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import patientUtilities.Menu;

/**
 * FXML Controller class
 *
 * @author jaime
 */
public class UpdatePatientController {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField MacAddressLabel;

    @FXML
    private Button UpdateButton;
    @FXML
    private Button exitButton;
    
    @FXML
    private Button BackButton;
    
    @FXML
    void exit (ActionEvent event) {
        Menu.exit();
    }
    @FXML
    void Back(ActionEvent event) throws IOException {
        URL url = new File("src/aplicacionpaciente/menuPatient.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        System.out.println("Se creo record");
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void Update(ActionEvent event) throws Exception {
        String macAddress = MacAddressLabel.getText();
        Menu.updateMacAddress(macAddress);
        try{
                
                URL url = new File("src/aplicacionpaciente/menuPatient.fxml").toURI().toURL();
                Parent root = FXMLLoader.load(url);    
                Scene scene = new Scene(root);
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

                stage.setScene(scene);
                stage.show();
            }catch(Exception e){
                e.printStackTrace();
            }
    }   
    
}
