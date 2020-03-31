/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi_1;

import static com.sun.javafx.fxml.expression.Expression.and;
import entities.Plat;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import static javafx.beans.binding.Bindings.and;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import services.ServicePlat;
import utils.ConnectionClass;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class NouveauController implements Initializable {

    @FXML
    private TextField libelle_plat;
    @FXML
    private TextField calories_plat;
    @FXML
    private TextArea description_plat;
    @FXML
    private TableColumn<Plat, String> libelle_plat_column;
    @FXML
    private TableColumn<Plat, String> desc_plat_column;
    @FXML
    private TableColumn<Plat, Integer> calories_plat_column;
    private TableColumn<Plat, Integer> id_plat;
    // initialize observable list to hold out database data 

    private ConnectionClass conn;
    @FXML
    private TableView<Plat> table_afficher_plat;
    @FXML
    private Button load;
    @FXML
    private TextField recherche_plat_field;
    @FXML
    private Button recherche_plat_button;
    @FXML
    private Button ajouter;
    @FXML
    private Button supprimer_plat_row_button;
    @FXML
    private TableColumn<Plat, Integer> id_plat_column;

   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        conn = new ConnectionClass();

    }

    @FXML
    private void amal(ActionEvent event) throws SQLException {



 

if((!libelle_plat.getText().equals("")) && (!description_plat.getText().equals("")) && (!calories_plat.getText().equals("")) ){
  if (!calories_plat.getText().matches("[0-9]") ){
          Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("erreur");
      alert.setHeaderText(null);
      alert.setContentText("calories doit etre un entier");
      alert.showAndWait();
 }

  
  else {
        String libelle = libelle_plat.getText();
        String description = description_plat.getText();
        String calories = calories_plat.getText();
        int calories_int = Integer.parseInt(calories);
        Plat p = new Plat();
    
        p.setLibelle(libelle);
        p.setDescription(description);
        p.setCalories(calories_int);
     
       

    ServicePlat sp = new ServicePlat();
    sp.ajouter(p);
       
          List<Plat> list = sp.read();

         ObservableList<Plat> obs = FXCollections.observableArrayList(list);

// set cell value factory to tableView 
        table_afficher_plat.setItems(obs);
        id_plat_column.setCellValueFactory(new PropertyValueFactory<>("id"));
        libelle_plat_column.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        desc_plat_column.setCellValueFactory(new PropertyValueFactory<>("description"));
        calories_plat_column.setCellValueFactory(new PropertyValueFactory<>("calories"));
      
  }
}
else {
    
            Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("erreur");
      alert.setHeaderText(null);
      alert.setContentText("veuillez remplir tous les champs");
      alert.showAndWait();
}
    }

    @FXML
    private void load(ActionEvent event) throws SQLException {

        ServicePlat sp = new ServicePlat();

        List<Plat> list = sp.read();

        ObservableList<Plat> obs = FXCollections.observableArrayList(list);

// set cell value factory to tableView 
        table_afficher_plat.setItems(obs);
      id_plat_column.setCellValueFactory(new PropertyValueFactory<>("id"));
        libelle_plat_column.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        desc_plat_column.setCellValueFactory(new PropertyValueFactory<>("description"));
        calories_plat_column.setCellValueFactory(new PropertyValueFactory<>("calories"));

    }

    @FXML
    private void rechercherPlat(ActionEvent event) throws SQLException {
        ServicePlat se = new ServicePlat();

        String libelle = recherche_plat_field.getText();
        List<Plat> list = se.rechercher(libelle);

        ObservableList<Plat> obs = FXCollections.observableArrayList(list);

        table_afficher_plat.setItems(obs);
         id_plat_column.setCellValueFactory(new PropertyValueFactory<>("id"));
        libelle_plat_column.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        desc_plat_column.setCellValueFactory(new PropertyValueFactory<>("description"));
        calories_plat_column.setCellValueFactory(new PropertyValueFactory<>("calories"));

    }

    @FXML
    private void supprimer_row(ActionEvent event) throws SQLException {
        
       Plat x = table_afficher_plat.getSelectionModel().getSelectedItem();
            
        ServicePlat sp = new ServicePlat();
        
      sp.supprimer(x);
        List<Plat> list = sp.read();

        ObservableList<Plat> obs = FXCollections.observableArrayList(list);

// set cell value factory to tableView 
        table_afficher_plat.setItems(obs);
           id_plat_column.setCellValueFactory(new PropertyValueFactory<>("id"));
        libelle_plat_column.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        desc_plat_column.setCellValueFactory(new PropertyValueFactory<>("description"));
        calories_plat_column.setCellValueFactory(new PropertyValueFactory<>("calories"));

            
        
    }
}
