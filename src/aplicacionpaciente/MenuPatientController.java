/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package aplicacionpaciente;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jaime
 */
public class MenuPatientController {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button BackButton;

    @FXML
    private Button RecordButton;

    @FXML
    private Button ShowSignalButton;

    @FXML
    private Button UpdateButton;

    @FXML
    void Back(ActionEvent event) {

    }

    @FXML
    void Record(ActionEvent event) throws MalformedURLException, IOException {
        System.out.println("Hola");
        try{
            URL url = new File("src/aplicacionpaciente/recordSignal.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);    
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            System.out.println("Se creo record");
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
                e.printStackTrace();
        }
         
    }

    @FXML
    void ShowSignal(ActionEvent event) {

    }

    @FXML
    void Update(ActionEvent event) {

    }

    
}
