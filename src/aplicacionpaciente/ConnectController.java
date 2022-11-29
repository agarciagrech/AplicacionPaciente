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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
    void connect(ActionEvent event) throws IOException {
        String ip = iptext.getText();
        try {
            Menu.initiliazeStreams(ip);
            // habilito el boton 
            // o utilizo un boolean que deje ir
        } catch (Exception e) {
            System.out.println("AQUI IRIA EL CODIGO");
        }
        URL url = new File("src/aplicacionpaciente/logIn.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();

    }
}
