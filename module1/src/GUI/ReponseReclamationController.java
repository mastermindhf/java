/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.ReponseReclamation;
import Entities.reclamation;
import Services.ReponseReclamationService;
import Services.reclamationService;
import java.net.URL;
import java.sql.Blob;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author farou
 */
public class ReponseReclamationController implements Initializable {

    @FXML
    private AnchorPane no;
    @FXML
    private TableView<ReponseReclamation> tabRep;
    @FXML
    private TableColumn<ReponseReclamation, String> nomRc;
    @FXML
    private TableColumn<ReponseReclamation, String> prenomRc;
    @FXML
    private TableColumn<ReponseReclamation, String> TelRc;
    @FXML
    private TableColumn<ReponseReclamation, String> emailRc;
    @FXML
    private TableColumn<ReponseReclamation, String> typeRc;
    @FXML
    private TableColumn<ReponseReclamation, String> objetRc;
    @FXML
    private TableColumn<ReponseReclamation, String> screenshotRc;
    @FXML
    private TableColumn<ReponseReclamation, String> RecRc;
    @FXML
    private TableColumn<ReponseReclamation, String> EtatRc;
    @FXML
    private TableColumn<ReponseReclamation,String> idreponseRc;
    @FXML
    private TableColumn<ReponseReclamation, String> DateD;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        afficher();
     /*         ReponseReclamationService rs=new ReponseReclamationService();
        reclamationService rq=new reclamationService();
        ReponseReclamation rp=new ReponseReclamation();
        Map<reclamation,ReponseReclamation> map = null ;
    System.err.println("aaaaa");
    for(Map.Entry<reclamation,ReponseReclamation> entry : map.entrySet())
	{
		System.out.println(entry.getKey());
		System.out.println(entry.getValue());
	
    }*/
        
    }    
      private void afficher()
   {
       ReponseReclamationService  sp = new ReponseReclamationService();
       List reponse=sp.AfficherReponseReclamation();
       ObservableList et=FXCollections.observableArrayList(reponse);
       tabRep.setItems(et);
       idreponseRc.setCellValueFactory(new PropertyValueFactory<>("id_rep"));
              
       
           nomRc.setCellValueFactory((CellDataFeatures<ReponseReclamation, String> param) -> new SimpleStringProperty(param.getValue().getReprec().getNom()));
           
           prenomRc.setCellValueFactory((CellDataFeatures<ReponseReclamation, String> param) -> new SimpleStringProperty(param.getValue().getReprec().getPrenom()));
           
           TelRc.setCellValueFactory((CellDataFeatures<ReponseReclamation, String> param) -> new SimpleStringProperty(param.getValue().getReprec().getTel()));
           
           emailRc.setCellValueFactory((CellDataFeatures<ReponseReclamation, String> param) -> new SimpleStringProperty(param.getValue().getReprec().getEmail()));
           
           typeRc.setCellValueFactory((CellDataFeatures<ReponseReclamation, String> param) -> new SimpleStringProperty(param.getValue().getReprec().getType()));
           
           objetRc.setCellValueFactory((CellDataFeatures<ReponseReclamation, String> param) -> new SimpleStringProperty(param.getValue().getReprec().getObjet()));
           
           RecRc.setCellValueFactory((CellDataFeatures<ReponseReclamation, String> param) -> new SimpleStringProperty(param.getValue().getReprec().getReclamation()));
           
        
       screenshotRc.setCellValueFactory((CellDataFeatures<ReponseReclamation, String> param) -> new SimpleStringProperty(param.getValue().getReprec().getScreenshot()));
           
         // DateD.setCellValueFactory((CellDataFeatures<ReponseReclamation, String> param) -> new SimpleStringProperty(param.getValue().getReprec().getDate()));
           DateD.setCellValueFactory(new PropertyValueFactory<>("date"));
         
       
           EtatRc.setCellValueFactory((CellDataFeatures<ReponseReclamation, String> param) -> new SimpleStringProperty(param.getValue().getReprec().getEtat()));
         
         
         
           
     
       
      
                  
  }
    
}
