/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package aplicacionpaciente;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

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
    void Display(ActionEvent event) throws FileNotFoundException, IOException {
        XYChart.Series series = new XYChart.Series();
        /*series.getData().add(new XYChart.Data(1,10));
        series.getData().add(new XYChart.Data(2,12));
        series.getData().add(new XYChart.Data(3,4));
        series.getData().add(new XYChart.Data(4,5));
        series.getData().add(new XYChart.Data(5,8));
        series.getData().add(new XYChart.Data(6,7));
        series.getData().add(new XYChart.Data(7,1));
        series.getData().add(new XYChart.Data(8,10));
        series.getData().add(new XYChart.Data(9,0));
        Graph.getData().add(series);*/
        
        String ruta1;
        String cadena1;
        List<Integer> values = new ArrayList();
        FileReader f = null;
        BufferedReader b = null;
        
        ruta1 = "C:/Users/jaime/OneDrive/Escritorio/4º Ingeniería Biomédica/Primer cuatri/Telemedicina/TSApp/PatientTS/bECG29102022_115334300.txt";
            f = new FileReader(ruta1);
            b = new BufferedReader(f);
            while((cadena1 = b.readLine())!=null) {
                String[] separatedCadena = cadena1.replaceAll("\\[", "").replaceAll("]", "").replace(" ", "").split(",");
                for (int i=0; i < separatedCadena.length;i++){
                    values.add(i, Integer.parseInt(separatedCadena[i]));
                }

             }
        for (int i =0; i<values.size();i++){
            String x = ""+i;
            series.getData().add(new XYChart.Data(x,values.get(i)));
        }
        Graph.getData().addAll(series);
        
        
    }
    
}
