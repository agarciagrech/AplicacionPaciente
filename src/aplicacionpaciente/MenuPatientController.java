/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package aplicacionpaciente;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
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
import javafx.stage.Window;
import patientUtilities.Menu;

/**
 * FXML Controller class
 *
 * @author jaime
 */
public class MenuPatientController {
    
    @FXML
    private Button RecordButton;

    @FXML
    private Button ShowSignalButton;

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
        URL url = new File("src/aplicacionpaciente/logIn.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void Record(ActionEvent event) throws MalformedURLException, IOException {
        try{
            URL url = new File("src/aplicacionpaciente/recordSignal.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);    
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void ShowSignal(ActionEvent event) throws MalformedURLException, IOException {
        URL url = new File("src/aplicacionpaciente/showSignal.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);    
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void Update(ActionEvent event) {
        try{
            URL url = new File("src/aplicacionpaciente/updatePatient.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);    
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
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