/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacionpaciente;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import patientUtilities.Menu;

/**
 *
 * @author agarc
 */
public class RegisterPatientController {
     @FXML
    private TextField txtname;

    @FXML
    private ChoiceBox<String> genderChoiceBox;

    @FXML
    private Button registerPatientButton;

    @FXML
    private TextField txtdob;

    @FXML
    private Button backMenuButton;

    @FXML
    private Label nameError;

    @FXML
    private Label ageError;

    @FXML
    private TextField txtsurname;

    @FXML
    private TextField txtaddress;

    @FXML
    private TextField txtemail;
    
    @FXML
    private TextField txtmacAddress;

    @FXML
    private TextField txtdiagnosis;

    @FXML
    private TextField txtmedCardNumber;

    @FXML
    private TextField txtallergies;

    @FXML
    private Button exitButton;
    
    @FXML
    private Button DoctorList;
    
     @FXML
    private TextField doctorIdtxt;


    @FXML
    void registerPatient(ActionEvent event) throws Exception {
        Window owner = registerPatientButton.getScene().getWindow();
        ObservableList<String> genderList = FXCollections.observableArrayList("Male", "Female");
        genderChoiceBox.setItems(genderList);
        genderChoiceBox.setValue("Male");
        
        String name = txtname.getText();
        String surname = txtsurname.getText();
        String email = txtemail.getText();
        String address = txtaddress.getText();
        String diagnosis = txtdiagnosis.getText();
        String gender = genderChoiceBox.getValue();
        Date dob = new Date(txtdob.getText());
        Integer medCard = Integer.parseInt(txtmedCardNumber.getText());
        String allergies = txtallergies.getText();
        String macAddress = txtmacAddress.getText();
        //Patient p = new Patient(name, surname, medical_card_number, dob, address, email, diagnosis, allergies, gender, userId, macAddress);
        String usernamePass = Menu.registerPatient( medCard,name, surname, dob, address, email, diagnosis, allergies, gender, macAddress);
        
        int doctorId = Integer.parseInt(doctorIdtxt.getText());
        Menu.sendDoctorId(doctorId);
        if(usernamePass==null){
             infoMessage("Please enter the data correctly", null, "Failed");
        }else{
             try{
               
                showAlert2(Alert.AlertType.INFORMATION, owner,"Your Username and Password are",usernamePass);
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

    @FXML
    void backtoMenu(ActionEvent event) {

    }
    
    @FXML
    void exitApp(ActionEvent event) {

    }
     public static void showAlert2(Alert.AlertType alertType, Window owner, String title, String message ) {
       Alert alert = new Alert(alertType);
       alert.setTitle(title);
       alert.setHeaderText(null);
       alert.setContentText(message);
       alert.initOwner(owner);
       alert.show();
    }
     
     public static void infoMessage(String infoMessage, String headerText, String title) {
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setContentText(infoMessage);
           alert.setTitle(title);
           alert.setHeaderText(headerText);
           alert.showAndWait();
       }
     
     @FXML
    void DoctorList(ActionEvent event) throws IOException {
         Window owner = DoctorList.getScene().getWindow();
       List <String> Doctors = Menu.showDoctors();
       showAlert(Alert.AlertType.INFORMATION,owner,"Doctors",Doctors.toString());
        
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
