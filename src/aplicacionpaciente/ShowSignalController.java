/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package aplicacionpaciente;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import patientUtilities.Menu;

/**
 * FXML Controller class
 *
 * @author jaime
 */
public class ShowSignalController {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button DisplayButton;

    @FXML
    private LineChart<?, ?> Graph;
    
     @FXML
    private TextField txtSignalFilename;
    
    @FXML
    private Button consultFilenames;

    @FXML
    private Button exitButton;
    
    @FXML
    private Button backButton;
    
    @FXML
    private TextField SectionLabel;
    
    @FXML
    private Button SelectSignalButton;
        
    private String section;
    private List<Integer> data;
    
    
    @FXML
    void back(ActionEvent event) throws IOException {
        Menu.backToMenu();
        URL url = new File("src/aplicacionpaciente/menuPatient.fxml").toURI().toURL();
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
    
     @FXML
    void showFilenames(ActionEvent event) {
        
        Window owner = consultFilenames.getScene().getWindow();
        List<String> list = Menu.showSignalList();
        showAlert(Alert.AlertType.INFORMATION,owner,"Signals of patient",list.toString());
    }
    
    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message ) {
       Alert alert = new Alert(alertType);
       alert.setTitle(title);
       alert.setHeaderText(null);
       alert.setContentText(message);
       alert.initOwner(owner);
       alert.show();
       }
    
       @FXML
    void SelectSignal(ActionEvent event) throws IOException {
       String filename;
       filename = txtSignalFilename.getText();
       data = Menu.showSignal(filename);
    }
    
    @FXML
    void Display(ActionEvent event) throws FileNotFoundException, IOException {
        Graph.getData().clear();
        XYChart.Series series = new XYChart.Series();
       
       section = SectionLabel.getText();
       
       for (int i =0; i<600/*values.size()*/;i++){
            series.getData().add(new XYChart.Data(i,data.get(i + 600*(Integer.parseInt(section)))));
        }
      
        Graph.getData().addAll(series);
        
        
    }
    
}
