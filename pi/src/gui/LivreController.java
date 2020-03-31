/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import DB.DataSource;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import entity.Livre;
import entity.Type;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.converter.NumberStringConverter;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import service.LivreService;
import static service.LivreService.TxtFiNotEmpty;
import service.TypeService;

/**
 * FXML Controller class
 *
 * @author Smirani
 */
public class LivreController implements Initializable {
    
    Connection cn = DataSource.getInstance().getCnx();
    /**
     * Initializes the controller class.
     */
    public static Livre selectedLivre;
    @FXML
    private AnchorPane anchorpane_center;

        @FXML
    private JFXTextField txt_search;
        
    @FXML
    private Pane pane_top;

    @FXML
    private AnchorPane anchorpane_right;
        @FXML
    private AnchorPane anchorpane_rightL;

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
    private AnchorPane anchorpane_left;

    @FXML
    private JFXTextField txt_nom;

    @FXML
    private JFXTextField txt_description;

    @FXML
    private JFXTextField txt_auteur;

    @FXML
    private JFXTextField txt_quantite;
    
    @FXML
    private JFXComboBox<Type> txt_type;

    @FXML
    private JFXButton btn_image;
    
    @FXML
    private JFXButton btn_type;
    
    @FXML
    private JFXTextField path;

    @FXML
    private JFXButton btn_ajouterLivre;

    @FXML
    private Label err_image;

    @FXML
    private Label err_nom;

    @FXML
    private Label err_des;

    @FXML
    private Label err_auteur;
    
    
    @FXML
    private JFXTextField filterInput;

    @FXML
    private Label err_qte;
        @FXML
    private Label err_typ;

    @FXML
    private JFXButton modifierBTN;

    @FXML
    private JFXButton supprimerBTN;

     ObservableList<Livre> LivreList = FXCollections.observableArrayList();

    
    static Livre selectionedLivre;
    

    
    
    ObservableList<String> TypeList = FXCollections.observableArrayList();
    
    
    private FXMLLoader loader;
    private String query;
    LivreService types;
    public File selectedFile;
    //private boolean edit=false,add=false;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficherLivre();
        tabLivre.setEditable(true);
        column_idL.setVisible(false);
        
        TypeService ty =new TypeService();        
        ObservableList<Type> typess = FXCollections.observableArrayList(ty.AfficherType());
        
        txt_type.setItems(typess);
        
        btn_type.setOnAction(e->{
            AfficheType();
        });
        
        
        //searchLivre();
        //recuperData();
        filterInput.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                filterStudentList((String) oldValue, (String) newValue);

            }
        });
              
        
    }    
    
    
    
    @FXML
    void uploadButton(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("*.png", "*.jpg", "*.jpeg"));
        selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {

            path.setText((selectedFile.getAbsolutePath()));
        } else {
            System.out.println("File is not valid!");
        }
    }

   @FXML
    void AjouterLivre(ActionEvent event) throws SQLException{
        System.out.println("debut add");
        boolean nomEmp = TxtFiNotEmpty(txt_nom, err_nom, "Saisie Nom Obligatoire ");
        boolean desEmp = TxtFiNotEmpty(txt_description, err_des, "Saisie Description Obligatoire");
        boolean autEmp = TxtFiNotEmpty(txt_auteur, err_auteur, "Saisie Auteur Obligatoire");
        boolean qteEmp = TxtFiNotEmpty(txt_quantite, err_qte, "Saisie Quantite Obligatoire");
        boolean ImgEmp = TxtFiNotEmpty(path, err_image, "Insertion Image Obligatoire");
        boolean qteNum = service.LivreService.TxtFiNumber(txt_quantite, err_qte, "Qunatite Invalide");
        if(nomEmp && desEmp && autEmp&& qteEmp && ImgEmp && qteNum){
            LivreService liv =new LivreService();

            String nom =txt_nom.getText();
            String description = txt_description.getText();
            String auteur = txt_auteur.getText();
            int quantite = Integer.valueOf(txt_quantite.getText());
            String image = selectedFile.getAbsolutePath();
            File f = new File(image);



            Livre livre = new Livre(txt_type.getSelectionModel().getSelectedItem(), nom, description, auteur, quantite , image);




            if(liv.AjouterLivre(livre,f)){
                tabLivre.getItems().clear();
                AfficherLivre();
                }
            else{
               Alert alert = new Alert(Alert.AlertType.ERROR, "Oops Something went wrong ..", ButtonType.OK, ButtonType.NO, ButtonType.CANCEL);
                    alert.showAndWait(); 
            }
        }
    }
    
    
    public void AfficherLivre() {
        LivreService liv = new LivreService();
   
        ObservableList<Livre> livres = FXCollections.observableArrayList(liv.AfficherLivre());
        tabLivre.setItems(livres);
        
        column_idL.setCellValueFactory(new PropertyValueFactory<>("idLivre"));
        column_nomL.setCellValueFactory(new PropertyValueFactory<>("nom"));
        column_descriptionL.setCellValueFactory(new PropertyValueFactory<>("description"));
        column_auteurL.setCellValueFactory(new PropertyValueFactory<>("auteur"));
        column_quantiteL.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        column_type.setCellValueFactory((CellDataFeatures<Livre, String> param) -> new SimpleStringProperty(param.getValue().getId_type().getLibelle()));
        column_imageL.setPrefWidth(80);
        column_imageL.setCellValueFactory(new PropertyValueFactory<>("image"));
        
   
    }
    
  /*  
    private void recuperData (){
        tabLivre.setOnMouseClicked(e -> {
            Livre l = tabLivre.getItems().get(tabLivre.getSelectionModel().getSelectedIndex());
            txt_nom.setText(l.getNom());
            txt_description.setText(l.getDescription());
            txt_auteur.setText(l.getAuteur());
            //txt_type.setSelectionModel(l.getId_type());
            txt_quantite.setText(Integer.toString(l.getQuantite()));
            path.setText(l.getImage());
        });
    }
             */ 
    public void AfficheType(){
        try{
            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Type.fxml"));
            TypeController controller = new TypeController();
            loader.load();
            Scene scene = new Scene(loader.getRoot());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            
        }catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    private void Refresh() {
        LivreService liv = new LivreService();
       ObservableList<Livre> livres = FXCollections.observableArrayList(liv.AfficherLivre());
       livres.clear();
      AfficherLivre();
    }
      
    
    public Livre recuperLivre() {
        LivreService liv = new LivreService();
        tabLivre.setOnMouseClicked((e) -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                if (e.getClickCount() >= 2) {
                    System.out.println("double clique");
                    //modifierBTN.setVisible(true);
                    Livre l = tabLivre.getSelectionModel().getSelectedItem();
                    txt_type.setValue(l.getId_type());
                    txt_nom.setText(l.getNom());
                    txt_description.setText(l.getDescription());
                    txt_auteur.setText(l.getAuteur());
                    txt_quantite.setText(Integer.toString(l.getQuantite()));
                    path.setText(l.getImage());
                }
            }
        });
        Livre l = tabLivre.getSelectionModel().getSelectedItem();
        return l;
    }


    @FXML
    void modifierLivre() {
        /*
              LivreService cs = new LivreService();
        Livre cc = (Livre)tabLivre.getSelectionModel().getSelectedItem();
        System.out.println(cc);
        System.out.println("modiffff");
        if(cc== null){
            JOptionPane.showMessageDialog(null, "choisir un Livre");
        }
      
        else{
         //int qte = Integer.parseInt(txt_quantite.getText());
         //Type t = txt_type.getSelectionModel().getSelectedItem();
         //int idt = t.getIdL();
         cs.update(cc.getId(), txt_nom.getText(), txt_description.getText(), txt_auteur.getText(),idt,qte,path.getText());
       AfficherLivre();
       
           
        System.out.println("modiffff2");
        JOptionPane.showMessageDialog(null, "Livre modifier");
             
        cc=null;
        }
             */
        LivreService liv = new LivreService();

        try {

            recuperLivre().setId_type(txt_type.getSelectionModel().getSelectedItem());
            recuperLivre().setNom(txt_nom.getText());
            recuperLivre().setDescription(txt_description.getText());
            recuperLivre().setAuteur(txt_auteur.getText());
            recuperLivre().setQuantite(Integer.parseInt(txt_quantite.getText()));
            recuperLivre().setImage(path.getText());
            
            liv.update(recuperLivre());
            //modifierBTN.setVisible(false);
            //btn.setVisible(true);
            AfficherLivre();

        } catch (Exception e) {

            System.out.println("Oops Something Went Wrong on Modifier Cours ...");
        }
    }
    
    @FXML
    void supprimerLivre(ActionEvent event) {
          
       LivreService cs = new LivreService();
         Livre cc = (Livre)tabLivre.getSelectionModel().getSelectedItem();
        System.out.println(cc);
        if(cc== null){
            JOptionPane.showMessageDialog(null, "choisir Livre");      
        }else{
            cs.delete(cc.getId());
           AfficherLivre();
           JOptionPane.showMessageDialog(null, "Livre supprimer");
           
        cc=null;
    }
    }
/*
    private void searchLivre(){
        LivreService liv = new LivreService();
         ObservableList<Livre> livres = FXCollections.observableArrayList();
        txt_search.setOnKeyReleased(e->{
            if (txt_search.getText().equals("")){
                AfficherLivre();
            }else{        
                livres.clear();
                String sql = "Select * from livres where nom like '%"+txt_search.getText()+"%'";
                try{
                    PreparedStatement pst = cn.prepareStatement(sql);
                    pst.setString(1, txt_search.getText());
                    pst.executeUpdate();
                    ResultSet rs = pst.executeQuery();
                    while(rs.next()){
                        Livre l = new Livre();
                    l.setId(rs.getInt("idLivre"));
                    l.setNom(rs.getString("nom"));
                    l.setDescription(rs.getString("description"));
                    l.setAuteur(rs.getString("auteur"));
                    l.setId_type(rs.getInt("id_type"));
                    l.setQuantite(rs.getInt("quantite"));
                    l.setImage(rs.getString("image"));
                    livres.add(l);
                    }
                tabLivre.setItems((ObservableList<Livre>) livres);
                }catch(SQLException ex){

                }
            }
        });
    }

    */
    
    
    
public void filterStudentList(String oldValue, String newValue) {
        ObservableList<Livre> filteredList = FXCollections.observableArrayList();
        if(filterInput == null || (newValue.length() < oldValue.length()) || newValue == null) {
            tabLivre.setItems(LivreList);
        }
        else {
            newValue = newValue.toUpperCase();
            for(Livre students : tabLivre.getItems()) {
                String filterFirstName = students.getNom();
                String filterLastName = students.getDescription();
                if(filterFirstName.toUpperCase().contains(newValue) || filterLastName.toUpperCase().contains(newValue)) {
                    filteredList.add(students);
                }
            }
            tabLivre.setItems(filteredList);
        }
    }

    public void handleClearButtonClick(ActionEvent event) {
            txt_auteur.clear();
            txt_nom.clear();
            txt_description.clear();
            txt_quantite.clear();
            path.clear();
            txt_type.setValue(null);
        }
    
    @FXML
    public void btnminimize(MouseEvent event)
    {
      minimizeStageOfNode((Node) event.getSource());
    }

    private void minimizeStageOfNode(Node node) 
    {
        ((Stage) (node).getScene().getWindow()).setIconified(true);
    }
    
    
}



