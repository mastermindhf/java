/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entity.Livre;
import entity.ReservationLivre;
import entity.User;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.LivreService;
import service.MyServices;
import service.ReservationService;

/**
 * FXML Controller class
 *
 * @author Smirani
 */
public class ReservationEleveController implements Initializable {

    @FXML
    private TableView<Livre> tabLivre;
        
    @FXML
    private TableColumn<Livre, Integer> column_idL;

    @FXML
    private TableColumn<Livre, String> column_nomL;

    @FXML
    private TableColumn<Livre, String> column_descriptionL;

    @FXML
    private TableColumn<Livre, String> column_auteurL;

    @FXML
    private TableColumn<Livre, Number> column_quantiteL;

    @FXML
    private TableColumn<Livre, String> column_imageL;
    
    @FXML
    private TableColumn<Livre, String> column_type;
    
    @FXML
    private TableColumn<Livre, Number> column_nb;
    
    private static MyServices myServices = new MyServices();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //init();
        Reserver_Eleve();
        column_imageL.setVisible(false);
    }    
    private void init() {
        
    }
    public void Reserver_Eleve() {
        System.out.println(pi.Pi.Id_user_connecte);
        User loggedUser = LoginController.getInstance().getLoggedUser();        
        User e=myServices.chercherUtilisateurByid(pi.Pi.Id_user_connecte);          
        System.out.println(e+"useeeeeeeeeeeeeeeeeeer");
        //Eleve e = new Eleve("eleve", 4, "$2y$13$VvqAJNDdUXaxoCnL5Qoscefg9iARL1G0vSHa8kqVlyHTYntulwG/2", "eleve", "eleve", "test@t.hr");
        //LivreService liv= new LivreService();
        ReservationService liv = new ReservationService();
   
        ObservableList<Livre> livres = FXCollections.observableArrayList(liv.DisplayLivreReserver(e));
      
        tabLivre.setItems(livres);
        
        
        column_idL.setCellValueFactory(new PropertyValueFactory<>("idLivre"));
        column_nomL.setCellValueFactory(new PropertyValueFactory<>("nom"));
        column_descriptionL.setCellValueFactory(new PropertyValueFactory<>("description"));
        column_auteurL.setCellValueFactory(new PropertyValueFactory<>("auteur"));
        column_quantiteL.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        column_type.setCellValueFactory((TableColumn.CellDataFeatures<Livre, String> param) -> new SimpleStringProperty(param.getValue().getId_type().getLibelle()));
        column_imageL.setPrefWidth(80);
        column_imageL.setCellValueFactory(new PropertyValueFactory<>("image"));
        
   
    }
        
}
