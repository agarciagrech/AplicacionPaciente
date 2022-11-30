/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacionpaciente;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import patientUtilities.Menu;

/**
 *
 * @author agarc
 */
public class logInController {
    @FXML
    private Button logInButton;

    @FXML
    private TextField Username;

    @FXML
    private TextField Password;

    @FXML
    private Button exitButton;
    
    @FXML
    private Button register;
    

    @FXML
    void exit(ActionEvent event) {

    }
    
     @FXML
    void register(ActionEvent event) throws IOException {
         Menu.goToregister();
         URL url = new File("src/aplicacionpaciente/registerPatient.fxml").toURI().toURL();
         Parent root = FXMLLoader.load(url);    
         Scene scene = new Scene(root);
         Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
         stage.setScene(scene);
         stage.show();
    }

    @FXML
    void logIn(ActionEvent event) throws  IOException, Exception {
        
        Window owner = logInButton.getScene().getWindow();
        if(Username.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR, owner, "Error!", "Please enter your username");
            return;
        }
        if(Password.getText().isEmpty()){
             showAlert(Alert.AlertType.ERROR, owner, "Error!", "Please enter your password");
             return;
        }
        
        
         
        String username = Username.getText();
        String password = Password.getText();
      
        Boolean logInCheck = Menu.login(username, password);
        
        if(!logInCheck){
            infoMessage("Please enter correct username or password", null, "Failed");
        }else{
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
    public static void infoMessage(String infoMessage, String headerText, String title) {
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setContentText(infoMessage);
           alert.setTitle(title);
           alert.setHeaderText(headerText);
           alert.showAndWait();
       }

       public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message ) {
       Alert alert = new Alert(alertType);
       alert.setTitle(title);
       alert.setHeaderText(null);
       alert.setContentText(message);
       alert.initOwner(owner);
       alert.show();
       }
    
}
