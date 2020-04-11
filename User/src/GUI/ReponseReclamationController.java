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
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;

import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import sun.java2d.pipe.hw.AccelDeviceEventNotifier;

/**
 * FXML Controller class
 *
 * @author farou
 */
public class ReponseReclamationController implements Initializable {

    @FXML
    private AnchorPane no;
    @FXML
    private TableView<reclamation> tabRep;
    @FXML
    private TableColumn<reclamation, String> nomRc;
    @FXML
    private TableColumn<reclamation, String> prenomRc;
    @FXML
    private TableColumn<reclamation, String> TelRc;
    private TableColumn<reclamation, String> emailRc;
    @FXML
    private TableColumn<reclamation, String> typeRc;
    @FXML
    private TableColumn<reclamation, String> objetRc;
    @FXML
    private TableColumn<reclamation, String> screenshotRc;
    @FXML
    private TableColumn<reclamation, String> RecRc;
    @FXML
    private TableColumn<reclamation, String> EtatRc;
    @FXML
    private TableColumn<reclamation, String> DateD;
    @FXML
    private Button repondre;
    private TextArea repT;
    @FXML
    private TextField nomrep;
    @FXML
    private Text nomText;
    @FXML
    private TextField PrenomRep;
    @FXML
    private Text prenomT;
    @FXML
    private TextField TelRep;
    @FXML
    private Text TelT;
    @FXML
    private TextField EmailREp;
    @FXML
    private Text EmailT;
    @FXML
    private TextField TypeRep;
    @FXML
    private TextField ObejtRep;
    @FXML
    private TextField DateRep;
    @FXML
    private Text TypeT;
    @FXML
    private Text ObjetT;
    @FXML
    private Text DateT;
    @FXML
    private TextArea ReclamationT;
    @FXML
    private Text RecT;
    @FXML
    private TextArea repondreReclamation;
    @FXML
    private TableColumn<reclamation, String> EmailTest;
    @FXML
    private ImageView nomimage;
    @FXML
    private ImageView prenomImage;
    @FXML
    private ImageView teleimage;
    @FXML
    private ImageView emailimage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        afficher();
       
         
        
        
         nomrep.setVisible(false);
         nomText.setVisible(false);
         PrenomRep.setVisible(false);
         prenomT.setVisible(false);
         TelRep.setVisible(false);
         TelT.setVisible(false);
         EmailREp.setVisible(false);
         EmailT.setVisible(false);
         TypeRep.setVisible(false);
         ObejtRep.setVisible(false);
         DateRep.setVisible(false);
         TypeT.setVisible(false);
         ObjetT.setVisible(false);
         DateT.setVisible(false);
         ReclamationT.setVisible(false);
         RecT.setVisible(false);
         repondre.setVisible(false);
         repondreReclamation.setVisible(false);
      
          getRecalamtion();
          
                  reclamationService cs = new reclamationService();
        
           ObservableList<reclamation> Cours = FXCollections.observableArrayList(cs.displayAll());
//    

//    
        
  Callback<TableColumn<reclamation, String>, TableCell<reclamation, String>> cellFactoryStatus =
           new Callback<TableColumn<reclamation, String>, TableCell<reclamation, String>>() {
            @Override
            public TableCell<reclamation, String> call(TableColumn<reclamation, String> param) {
               final TableCell<reclamation, String> cell = new TableCell<reclamation, String>() {

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {

                            ImageView imagev;
                            if (item.equals("En Attente")) {
                                imagev = new ImageView(new Image("file:C:\\3a16\\Image\\enAttente.png"));
                                imagev.setFitHeight(30);
                                imagev.setFitWidth(100);
                                setGraphic(imagev);

                            }
                            if (item.equals("En cours")) {
                                imagev = new ImageView(new Image("file:C:\\3a16\\Image\\enTraitement.png"));
                                imagev.setFitHeight(30);
                                imagev.setFitWidth(100);
                                setGraphic(imagev);
                            } else if (item.equals("Traite")) {
                                imagev = new ImageView(new Image("file:C:\\3a16\\Image\\traite.png"));
                                imagev.setFitHeight(30);
                                imagev.setFitWidth(100);
                                setGraphic(imagev);
                            }

                        }
                    }
                };
                return cell;
            }
        };
  EtatRc.setCellFactory(cellFactoryStatus);
  
  
  
  
   Callback<TableColumn<reclamation, String>, TableCell<reclamation, String>> cellFactoryImage
                = //
                new Callback<TableColumn<reclamation, String>, TableCell<reclamation, String>>() {
            String path;

            @Override
            public TableCell<reclamation, String> call(TableColumn<reclamation, String> param) {
                 
           
                final TableCell<reclamation, String> cell = new TableCell<reclamation, String>() {

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            path = item;

                            ImageView imagev = new ImageView(new Image(item));
                            imagev.setFitHeight(100);
                            imagev.setFitWidth(100);
                            setGraphic(imagev);
                            setText(null);
//                            System.out.println(item);
                        }
                    }
                };

                cell.setOnMouseClicked((MouseEvent event2)
                        -> {
                    if (event2.getClickCount() == 1) {
                        if (tabRep.getSelectionModel().getSelectedItem() != null && !tabRep.getSelectionModel().getSelectedItem().getScreenshot().contains("null")) {
                            Stage window = new Stage();
//
                            window.setMinWidth(250);
                            ImageView imagevPOPUP = new ImageView(new Image(tabRep.getSelectionModel().getSelectedItem().getScreenshot()));
                            imagevPOPUP.setFitHeight(576);
                            imagevPOPUP.setFitWidth(1024);

                            VBox layout = new VBox(10);
                            layout.getChildren().addAll(imagevPOPUP);
                            layout.setAlignment(Pos.CENTER);

                            //Display window and wait for it to be closed before returning
                            Scene scene = new Scene(layout);
                            window.setScene(scene);
                            window.show();

                        }
                    }

//              
                });

                return cell;
            
            }

           
        };

        screenshotRc.setCellFactory(cellFactoryImage);
  
  
  
  


        
    
    }    
      private void afficher()
   {
      
         
           reclamationService sp = new reclamationService();
        List parascolaire = sp.displayAll();
        ObservableList et = FXCollections.observableArrayList(parascolaire);
        tabRep.setItems(et);
     
        nomRc.setCellValueFactory(new PropertyValueFactory<>("Nom"));
      
         prenomRc.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
           TelRc.setCellValueFactory(new PropertyValueFactory<>("Tel"));
      
       EmailTest.setCellValueFactory(new PropertyValueFactory<>("Email"));
       typeRc.setCellValueFactory(new PropertyValueFactory<>("Type"));
       
      objetRc.setCellValueFactory(new PropertyValueFactory<>("Objet"));
      screenshotRc.setCellValueFactory(new PropertyValueFactory<>("Screenshot"));
      RecRc.setCellValueFactory(new PropertyValueFactory<>("Reclamation"));
      EtatRc.setCellValueFactory(new PropertyValueFactory<>("Etat"));
      DateD.setCellValueFactory(new PropertyValueFactory<>("Date"));
      
      
                  
  }

    @FXML
    private void repondre(ActionEvent event) {

      
          ReponseReclamationService cs = new ReponseReclamationService();
    
        String repondre = repondreReclamation.getText();
        reclamation x=tabRep.getSelectionModel().getSelectedItem();

      
      
       if (repondre.isEmpty()) {
             Alert alert = new Alert(Alert.AlertType.NONE, "Réponse Vide ", ButtonType.OK, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();
       }
  
       else {
          
            ReponseReclamationService re=new ReponseReclamationService();
            
            ReponseReclamation reprec =new ReponseReclamation(x, repondre);
       
        cs.insert(reprec);
     
       cs.ModifierEtatTraite(x);
       afficher();
        repondreReclamation.setText("");
 
       }
       
        
    }
    
     public reclamation getRecalamtion() {
        reclamationService cs = new reclamationService();
        ReponseReclamationService re=new ReponseReclamationService();
        tabRep.setOnMouseClicked((e) -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                if (e.getClickCount() == 2 && !tabRep.getSelectionModel().getSelectedItem().getEtat().equals("Traite")){
                   
                    reclamation x = tabRep.getSelectionModel().getSelectedItem();
                   
                    nomrep.setText(x.getNom());
                    nomrep.setDisable(true);
                    nomText.setVisible(true);
                    nomrep.setVisible(true);
                    PrenomRep.setText(x.getPrenom());
                    PrenomRep.setDisable(true);
                    prenomT.setVisible(true);
                    PrenomRep.setVisible(true);
                                                          
                    TelRep.setText(x.getTel());
                    TelRep.setDisable(true);
                    TelT.setVisible(true);
                    TelRep.setVisible(true);
                    
                    EmailREp.setText(x.getEmail());
                    EmailREp.setDisable(true);
                    EmailT.setVisible(true);
                    EmailREp.setVisible(true);
                    
                    TypeRep.setText(x.getType());
                    TypeRep.setDisable(true);
                    TypeT.setVisible(true);
                    TypeRep.setVisible(true);
                    
                    
                    ObejtRep.setText(x.getObjet());
                    ObejtRep.setDisable(true);
                    ObjetT.setVisible(true);
                    ObejtRep.setVisible(true);
                    
                  ReclamationT.setText(x.getReclamation());
                    ReclamationT.setDisable(true);
                    RecT.setVisible(true);
                    ReclamationT.setVisible(true);
                    
                           nomimage.setImage(new Image("Images/nom.png"));
         prenomImage.setImage(new Image("Images/prenom.png"));
          teleimage.setImage(new Image("Images/tel.png"));
           emailimage.setImage(new Image("Images/email.png"));
                      
                  
                    repondreReclamation.setVisible(true);
                    repondre.setVisible(true);
                    
                      re.ModifierEtatEncours(x);
            
           
          

                    
                }
                else 
                {
                      Alert alert = new Alert(Alert.AlertType.NONE, "Récalamtion Traite ", ButtonType.OK, ButtonType.NO, ButtonType.CANCEL);
                }
            }
        });
        reclamation x = tabRep.getSelectionModel().getSelectedItem();
        return x;
     }

      private void Refresh() {
        tabRep.getItems().clear();
        afficher();
      

        afficher();
    }
    

    }
     
