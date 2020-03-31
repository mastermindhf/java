/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entity.Type;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import service.TypeService;

/**
 * FXML Controller class
 *
 * @author Smirani
 */
public class TypeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TableView<?> tabl;
        
    @FXML
    private AnchorPane anchorpane_parent;

    @FXML
    private Pane pane_top;

    @FXML
    private AnchorPane anchorpane_center;

    @FXML
    private AnchorPane anchorpane_left;

    @FXML
    private JFXTextField txt_libelle;

    @FXML
    private JFXButton btn_ajouter;

    @FXML
    private AnchorPane anchorpane_right;

    @FXML
    private TableView<Type> tabview;

    @FXML
    private TableColumn<Type,Integer> column_id;

    @FXML
    private TableColumn<Type, String> column_libelle;




    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficherType();
        SupprimerType();
        tabview.setEditable(true);
        column_id.setVisible(true);
        column_libelle.setCellFactory(TextFieldTableCell.forTableColumn());
    }    
        @FXML
    void AjouterType(ActionEvent event) {
        TypeService ty = new TypeService();
   
        //ObservableList<Type> types = FXCollections.observableArrayList(ty.AfficherType());
        String Lib = txt_libelle.getText();
        Type t = new Type(Lib);

        if(ty.AjouterType(t)){
            Refresh();
        }
        else{
           Alert alert = new Alert(AlertType.ERROR, "Oops Something went wrong ..", ButtonType.OK, ButtonType.NO, ButtonType.CANCEL);
                alert.showAndWait(); 
        }
    }
    
    public void AfficherType() {
        TypeService ty = new TypeService();
   
        ObservableList<Type> types = FXCollections.observableArrayList(ty.AfficherType());
        tabview.setItems(types);

        column_id.setCellValueFactory(new PropertyValueFactory<>("idL"));
        column_libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
    }
    
    public void SupprimerType() {
         TypeService ty = new TypeService();
   
      
        tabview.setOnMouseClicked((event) -> {
            if (event.getButton() == MouseButton.PRIMARY) {

            } else if (event.getButton() == MouseButton.SECONDARY) {
                Type x = tabview.getSelectionModel().getSelectedItem();

                Alert alert = new Alert(AlertType.NONE, "Voulez vous supprimer " + x.getLibelle() +  " ?", ButtonType.OK, ButtonType.NO, ButtonType.CANCEL);
                alert.showAndWait();

                if (alert.getResult() == ButtonType.OK) {
                    //do stuff
                    ty.SupprimerType(x);
                    Refresh();
                }
            }
        });

    }
    
     @FXML
    void ModifierType(TableColumn.CellEditEvent edittedCell) {
        TypeService ty = new TypeService();     
        Type x = tabview.getSelectionModel().getSelectedItem();

        x.setLibelle(edittedCell.getNewValue().toString());
        ty.ModifierType(x);
     Refresh();
    }
    

    private void Refresh() {
        TypeService ty = new TypeService();     
       ObservableList<Type> types = FXCollections.observableArrayList(ty.AfficherType());
       types.clear();
      AfficherType();
    }

    
}
