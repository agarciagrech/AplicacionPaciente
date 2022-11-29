/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package aplicacionpaciente;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import static patientUtilities.Menu.br;
import static patientUtilities.Menu.pw;
import pojos.Patient;

/**
 * FXML Controller class
 *
 * @author jaime
 */
public class RecordSignalController {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button recordButton;

    @FXML
    void record(ActionEvent event) {
        System.out.println("aca dentro del record");
        Patient patient = patientUtilities.CommunicationWithServer.receivePatient(br);
        patientUtilities.CommunicationWithServer.recordSignal(patient, pw);
        System.out.println("La señal se ha grabado");
    }
    
    
}
