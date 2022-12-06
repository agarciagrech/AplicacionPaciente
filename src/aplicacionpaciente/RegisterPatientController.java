/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacionpaciente;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import patientUtilities.Menu;


public class RegisterPatientController {
    @FXML
    private TextField txtname;

    @FXML
    private RadioButton RBMale;

    @FXML
    private RadioButton RBFemale;

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
    void registerPatient(ActionEvent event) throws Exception {
        Window owner = registerPatientButton.getScene().getWindow();
        
        if(this.txtname.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR, owner, "Error!", "Please enter the name");
            return;
        }
        if(this.txtsurname.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR, owner, "Error!", "Please enter the surname");
            return;
        }
        if(this.txtaddress.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR, owner, "Error!", "Please enter the address");
            return;
        }
        if(this.txtdiagnosis.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR, owner, "Error!", "Please enter the diagnosis");
            return;
        }
        if(this.txtdob.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR, owner, "Error!", "Please enter the date of birth");
            return;
        }
        if(this.txtmacAddress.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR, owner, "Error!", "Please enter the macAddress of your Bitalino");
            return;
        }
        String name = txtname.getText();
        String surname = txtsurname.getText();
        String email = txtemail.getText();
        Pattern pattern = Pattern.compile("([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]+))+");
        Matcher matcher = pattern.matcher(email);
        if (matcher.find() == false) {
            showAlert(Alert.AlertType.ERROR, owner, "Error!", "Please enter a valid email");
            return;
        }
        String address = txtaddress.getText();
        String diagnosis = txtdiagnosis.getText();
        String gender = "";
        if(RBMale.isSelected()){
           gender = "Male";
        }

        if(RBFemale.isSelected()){
           gender = "Female";
        }
        Date dob = new Date(txtdob.getText());
        String medCardNum = txtmedCardNumber.getText();
        String allergies = txtallergies.getText();
        String macAddress = txtmacAddress.getText();
        boolean correctData = ComprobarData(name,surname,diagnosis,allergies,medCardNum);
        if(!correctData){
            infoMessage("Please enter the data correctly", null, "Failed");
        }else{
        int medCard = Integer.parseInt(medCardNum);
        String usernamePass = Menu.registerPatient( medCard,name, surname, dob, address, email, diagnosis, allergies, gender, macAddress);
        
       
        if(usernamePass==null){
             infoMessage("Please enter the data correctly", null, "Failed");
        }else{
             showAlert2(Alert.AlertType.INFORMATION, owner,"Your Username and Password are",usernamePass);
              try{
                
                URL url = new File("src/aplicacionpaciente/selectDoctor.fxml").toURI().toURL();
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
    }

    @FXML
    void backtoMenu(ActionEvent event) throws MalformedURLException, IOException {
        Menu.backTologin();
        URL url = new File("src/aplicacionpaciente/logIn.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);    
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void exitApp(ActionEvent event) {
        Menu.exit();
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
     
    
     
    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message ) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
      
    public boolean ComprobarData(String name, String surname, String diagnosis, String allergies, String medcard){
        char[] chars = name.toCharArray();
        boolean validData = true;

        for (char c : chars) {
            if (Character.isDigit(c)) {
                validData = false;
                this.txtname.clear();
                break;
            }
        }
        
        char[] chars2 = surname.toCharArray();
        for(char c1: chars){
            if(Character.isDigit(c1)){
                validData = false;
                this.txtsurname.clear();
                break;
            }
        }
        
        char[] chars3 = allergies.toCharArray();
        for(char c2: chars){
            if(Character.isDigit(c2)){
                validData = false;
                this.txtsurname.clear();
                break;
            }
        }
        
        char[] chars4 = diagnosis.toCharArray();
        for(char c2: chars){
            if(Character.isDigit(c2)){
                validData = false;
                this.txtsurname.clear();
                break;
            }
        }
        
        char[] chars5 = medcard.toCharArray();
        for(char c3: chars){
            if(!Character.isDigit(c3)){
                validData = false;
                this.txtsurname.clear();
                break;
            }
        }

        if (this.txtname.getText().equals("")) {
            validData = false;
        }
        if (this.txtsurname.getText().equals("")) {
            validData = false;
        }
        if (this.txtdiagnosis.getText().equals("")){
            validData = false;
        }
        if (this.txtmedCardNumber.getText().equals("")){
            validData = false;
        }
        return validData;
    }
}
