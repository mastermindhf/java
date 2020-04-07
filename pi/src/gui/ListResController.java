/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.ReservationLivre;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ReservationService;

/**
 * FXML Controller class
 *
 * @author Smirani
 */
public class ListResController implements Initializable {

   @FXML
    private TableView<ReservationLivre> tabRes;

    @FXML
    private TableColumn<ReservationLivre, Integer> id;

    @FXML
    private TableColumn<ReservationLivre, Number> livre;

    @FXML
    private TableColumn<ReservationLivre, Number> eleve;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ListRes();
    }    
    
    public void ListRes(){
        ReservationService res = new ReservationService();
   
        ObservableList<ReservationLivre> list = FXCollections.observableArrayList(res.afficher_Reservation());
        tabRes.setItems(list);
        
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        eleve.setCellValueFactory(new PropertyValueFactory<>("idLivre"));
        livre.setCellValueFactory(new PropertyValueFactory<>("idEleve"));
        System.out.println(list);
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i).getId()+" "+list.get(i).getId_eleve()+" "+list.get(i).getId_livre());
        }
    }
    
}
