/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.reclamation;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import Services.reclamationService;
import java.io.File;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;
/**
 * FXML Controller class
 *
 * @author farou
 */
public class CrudReclamationController implements Initializable {

    @FXML
    private Label nom;
   
    @FXML
    private TextField nomR;
    @FXML
    private TextField prenomR;
    @FXML
    private TextField telR;
    @FXML
    private TextField emailR;
    @FXML
    private TextField objetR;
    @FXML
    private TextField reclamationR;
    @FXML
    private DatePicker dateR;
     String img="";
      List<String> typeP;
    @FXML

   
    private Button reclamer;
    @FXML
    private TableView<reclamation> table_reclamation;
    @FXML
    private TableColumn<reclamation, String> identifiant;
    @FXML
    private TableColumn<reclamation, String> telephone;
    @FXML
    private TableColumn<reclamation, String> email;
    @FXML
    private TableColumn<reclamation, String> date;
    @FXML
    private TableColumn<reclamation, String> type;
    @FXML
    private TableColumn<reclamation, String> objet;
    @FXML
    private TableColumn<reclamation, String> screenshot;
    @FXML
    private TableColumn<reclamation, String> reclamationTab;
    @FXML
    private TableColumn<reclamation, String> noR;
    @FXML
    private AnchorPane objet2;
    @FXML

    private TableColumn<reclamation, String> f;
    @FXML
    private Label prenom;
    @FXML
    private Button importer;
    @FXML
    private ChoiceBox<String> typeR;
    private TextField imaaaage;
    private Object FileChooser;
    @FXML
    private ImageView imageview;
    @FXML
    private Button modiferR;
    @FXML
    private Button supprimer;
    @FXML
    private TextField recherche;
    @FXML
    private Label error_type;
    @FXML
    private Label reche;
    @FXML
    private TableColumn<reclamation, String> Etat;
    @FXML
    private TableColumn<?, ?> DateD;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      afficher();  // TODO
      choiceBox();
       typeP =new ArrayList();
        typeP.add("*.jpg");
         typeP.add("*.png");
          
         
  
         nomR.textProperty().addListener((observable, oldValue, newValue) ->
    {
        if (!newValue.matches("\\sa-zA-Z*")) {
            nomR.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
         nomR.textProperty().addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                   if(newValue.isEmpty())
                       error_type.setText("Ce champs est obligatoire");
                   else if(newValue.length()>25)
                       error_type.setText("Max 255");
                   else
                error_type.setText("");
                }});
    });  
       nomR.setOnMouseClicked(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event) 
                    {
                    if(nomR.getText().length()==0)
                     error_type.setText("Ce champs est obligatoire");    
                    }
            });
       nomR.textProperty().addListener((observable, oldValue, newValue) ->
        {
        if (!newValue.matches("\\sa-zA-Z*")) {
            nomR.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
        }
        });    
       

    }
    @FXML
    private void ajouterReclamation(ActionEvent event) {
        
                String nom = nomR.getText();
                
                String prenom =prenomR.getText();
             
             String tel=telR.getText();
               
                 String email = emailR.getText();
                String objet=objetR.getText();
                String reclamation=reclamationR.getText();
               String type=typeR.getValue();
           LocalDate dd=dateR.getValue();
        Date datd = java.sql.Date.valueOf(dd);
   
                reclamationService rec = new reclamationService();
               reclamation re =new reclamation(nom, prenom, tel,email,type, img, objet, reclamation, datd,"En Attente");
      
       if(emailisValid(email)==false)
       {
            JOptionPane.showMessageDialog(null, " Format Email invalide");
       }
       else if (TelsValid(tel)==false)
               {
                    JOptionPane.showMessageDialog(null, "Format Tel invalide");
               }
       else if (nom.isEmpty() || prenom.isEmpty() ||tel.isEmpty()||email.isEmpty()||objet.isEmpty()||reclamation.isEmpty()||type.isEmpty()||date==null)
       {
             JOptionPane.showMessageDialog(null, "Champs obligatoires ");
       }
       else if (dateR.getValue().equals(java.time.LocalDate.now())||(dateR.getValue().isBefore(java.time.LocalDate.now())))
               {
                   JOptionPane.showMessageDialog(null, "la date doit etre sup a la date d'aujourd'hui");
               }
         else{
       rec.insert(re);
         JOptionPane.showMessageDialog(null, "Ajout avec succes");
         afficher();
         }
                
            
                
         
            
    }
        
    public boolean emailisValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";
                             
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
        public boolean TelsValid(String tel)
    {
        int taille = tel.length();
        if( taille==8 && isInt(tel))
        {
            return true;
        }
        else return false;
        
    }
 

   private void afficher()
   {
    reclamationService  sp = new reclamationService();
       List parascolaire=sp.displayAll();
       ObservableList et=FXCollections.observableArrayList(parascolaire);
       table_reclamation.setItems(et);
       identifiant.setCellValueFactory(new PropertyValueFactory<>("id"));
       noR.setCellValueFactory(new PropertyValueFactory<>("Nom"));
       f.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
       telephone.setCellValueFactory(new PropertyValueFactory<>("Tel"));
       email.setCellValueFactory(new PropertyValueFactory<>("Email"));
       type.setCellValueFactory(new PropertyValueFactory<>("Type"));
       objet.setCellValueFactory(new PropertyValueFactory<>("Objet"));
       screenshot.setCellValueFactory(new PropertyValueFactory<>("Screenshot"));
       reclamationTab.setCellValueFactory(new PropertyValueFactory<>("Reclamation"));
   
       Etat.setCellValueFactory(new PropertyValueFactory<>("Etat"));
        
       DateD.setCellValueFactory(new PropertyValueFactory<>("Date"));          
   
   }

    @FXML
    private void importer(ActionEvent event) {
          FileChooser f=new FileChooser();
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("jpeg,png",typeP));
        File fc=f.showOpenDialog(null);
        if(f!= null)
        {
            System.out.println(fc.getName());
            img=fc.getAbsoluteFile().toURI().toString();
            Image i = new Image(img);
            imageview.setImage(i);
        }
    }

    @FXML
    private void modifierReclamation(ActionEvent event) {
        
              reclamationService cs = new reclamationService();
        reclamation cc = (reclamation)table_reclamation.getSelectionModel().getSelectedItem();
        System.out.println(cc);

        if(cc== null){
            JOptionPane.showMessageDialog(null, "choisir une réclamation");
        }
      
        else{
         String date = dateR.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
  cs.update(cc.getId(), nomR.getText(), prenomR.getText(), telR.getText(),emailR.getText(), typeR.getValue(),img,objetR.getText(), reclamationTab.getText(),date);
       afficher();
       
           
        
        JOptionPane.showMessageDialog(null, "reclamation modifier");
        nomR.clear();
         prenomR.clear();  
         telR.clear();
              emailR.clear();
                    typeR.setValue(null);
       imageview.setImage(null);
       objetR.clear();
       reclamationR.clear();

       dateR.setValue(null);
     
        cc=null;
        }
        
        
        
    
    
    }

    @FXML
    private void supprimerReclamationn(ActionEvent event) {
          
       reclamationService cs = new reclamationService();
         reclamation cc = (reclamation)table_reclamation.getSelectionModel().getSelectedItem();
        System.out.println(cc);
        if(cc== null){
            JOptionPane.showMessageDialog(null, "choisir reclamation");      
        }else{
            cs.delete(cc.getId());
           afficher();
           JOptionPane.showMessageDialog(null, "reclamation supprimer");
           
        cc=null;
    }
    }

    @FXML
    private void rechercher(ActionEvent event) {
         reclamationService cs = new reclamationService();
        ArrayList AL = (ArrayList) cs.displayAll();
        ObservableList OReservation = FXCollections.observableArrayList(AL);
        FilteredList<reclamation> filteredData = new FilteredList<>(OReservation, p -> true);
        recherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(myObject -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(myObject.getType()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;

                }
                return false;
            });
        });
        SortedList<reclamation> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_reclamation.comparatorProperty());
        table_reclamation.setItems(sortedData);
    }

    public boolean isInt(String s)
    {
        try
        { int i = Integer.parseInt(s); return true; }

        catch(NumberFormatException er)
        { return false; } 
    }

    @FXML
    private void choiceBox() {
        typeR.getItems().add("Type1");
        typeR.getItems().add("Type2");
          typeR.getItems().add("Type3");
    }

   
    }
    
    
  

