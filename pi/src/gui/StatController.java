/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Livre;
import entity.ReservationLivre;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.color;
import static javafx.scene.paint.Color.color;
import static javafx.scene.paint.Color.color;
import service.LivreService;

/**
 * FXML Controller class
 *
 * @author Smirani
 */
public class StatController implements Initializable {

    @FXML
    private PieChart pieChart;
        
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Stat();
    }    
    public void Stat(){
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        LivreService liv = new LivreService();
        ArrayList<Livre> list =  new ArrayList<>();
        list = liv.AfficherLivre();
        
        for(int i=0 ; i<list.size();i++){
            pieChartData.add(new PieChart.Data(list.get(i).getNom(),list.get(i).getNbPersonnes()));
            
        }
        //pieChart.setTextFill(color(0, 0, 0));
        pieChart.setLegendSide(Side.BOTTOM);
        pieChart.setBackground(Background.EMPTY);
        pieChart.setData(pieChartData);        
                
        
        
        /*
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList( 
        new PieChart.Data("Iphone 5S", 13), 
        new PieChart.Data("Samsung Grand", 25), 
        new PieChart.Data("MOTO G", 10), 
        new PieChart.Data("Nokia Lumia", 22)); 
        //final PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Imported Fruits");
        pieChart.setData(pieChartData);*/
        
        
    }
    
}
