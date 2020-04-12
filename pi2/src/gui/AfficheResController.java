/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Livre;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import service.ReservationService;

/**
 * FXML Controller class
 *
 * @author Smirani
 */
public class AfficheResController implements Initializable {

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
    private TableColumn<Livre, Integer> column_nb;
    
    private FXMLLoader loader;
    
    public static ObservableList<Livre> tableData;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficheRes();
        column_idL.setVisible(false);
    }   
    
    public void AfficheRes(){
        ReservationService liv = new ReservationService();
   
        ObservableList<Livre> livres = FXCollections.observableArrayList(liv.DisplayAllReserver());
      
        tabLivre.setItems(livres);
        System.out.println(livres);
        
        column_idL.setCellValueFactory(new PropertyValueFactory<>("idLivre"));
        column_nomL.setCellValueFactory(new PropertyValueFactory<>("nom"));
        column_descriptionL.setCellValueFactory(new PropertyValueFactory<>("description"));
        column_auteurL.setCellValueFactory(new PropertyValueFactory<>("auteur"));
        column_quantiteL.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        column_nb.setCellValueFactory(new PropertyValueFactory<>("nbPersonnes"));
        column_type.setCellValueFactory((TableColumn.CellDataFeatures<Livre, String> param) -> new SimpleStringProperty(param.getValue().getId_type().getLibelle()));
        column_imageL.setPrefWidth(80);
        column_imageL.setCellValueFactory(new PropertyValueFactory<>("image"));
        
       Callback<TableColumn<Livre, String>, TableCell<Livre, String>> cellFactoryImage
                =                 //
         new Callback<TableColumn<Livre, String>, TableCell<Livre, String>>() {
            @Override
            public TableCell call(final TableColumn<Livre, String> param) {
            final TableCell<Livre, String> cell = new TableCell<Livre, String>() {
                
                 @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText("Aucune image n'existe dans cette liste");
                        } else {
                            System.out.println(item);
                            ImageView imagev = new ImageView(new Image(item));
                            imagev.setFitHeight(100);
                            imagev.setFitWidth(100);
                            setGraphic(imagev);
                            //setGraphic(imagev);
                            setText(item);
                            //System.out.println(item);
                        }
                    }
            };
            return cell;
        }
        };
        column_imageL.setCellFactory(cellFactoryImage);
        
    }

    
    @FXML
    private void retour(MouseEvent event) {
       
            final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}