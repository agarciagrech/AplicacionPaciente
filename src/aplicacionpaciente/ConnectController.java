/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacionpaciente;

import static aplicacionpaciente.RegisterPatientController.infoMessage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ConnectController {

    @FXML
    private Button connect;

    @FXML
    private TextField iptext;

    @FXML
    void connect(ActionEvent event) {
        //Window owner = connect.getScene().getWindow();
        /*boolean connected = false;
        do {
            String ip = iptext.getText();
            try {

                Menu.initiliazeStreams(ip);

                // habilito el boton 
                // o utilizo un boolean que deje ir
            } catch (IOException e) {
                //System.out.println("AQUI IRIA EL CODIGO");
                infoMessage("Please enter a correct IP Address", null, "Failed");

            }
            connected = true;
        } while (!connected);*/

        String ip = iptext.getText();
        try {
            Menu.initiliazeStreams(ip);

            URL url = new File("src/aplicacionpaciente/logIn.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);
            stage.show();
        } catch (java.net.ConnectException ce) {
            infoMessage("Please enter a correct IP Address", null, "Failed");
        } catch (IOException ex) {
            System.out.println("IOEXCEPTION");
        }

    }

    public static void infoMessage(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
