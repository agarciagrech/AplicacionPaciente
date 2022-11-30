/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package aplicacionpaciente;

import static aplicacionpaciente.RegisterPatientController.showAlert;
import java.io.File;
import java.io.IOException;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import patientUtilities.Menu;

/**
 * FXML Controller class
 *
 * @author albic
 */
public class SelectDoctorController {

    @FXML
    private Button DoctorList;

    @FXML
    private TextField doctorIdtxt;

    @FXML
    private Button selectdoctor;

 @FXML
    void DoctorList(ActionEvent event) throws IOException {
        Window owner = DoctorList.getScene().getWindow();
       List <String> Doctors = Menu.showDoctors();
       showAlert(Alert.AlertType.INFORMATION,owner,"Doctors",Doctors.toString());
        
    }
      @FXML
    void selectDoctor(ActionEvent event) {
        Window owner = selectdoctor.getScene().getWindow();
        if(doctorIdtxt.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR, owner, "Error!", "Please enter the doctor's name");
            return;
        }else{
         int doctorId = Integer.parseInt(doctorIdtxt.getText());
        
        Menu.sendDoctorId(doctorId);
        try{
                Menu.backTologin();
                URL url = new File("src/aplicacionpaciente/logIn.fxml").toURI().toURL();
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
    
      public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message ) {
       Alert alert = new Alert(alertType);
       alert.setTitle(title);
       alert.setHeaderText(null);
       alert.setContentText(message);
       alert.initOwner(owner);
       alert.show();
       }
   
}
