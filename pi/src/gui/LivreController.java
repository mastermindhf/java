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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;
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
    String path_img;
    
    @FXML
    private ImageView imageaff;
    
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
    
        @FXML
    private ImageView exit;
    
    
    
    ObservableList<String> TypeList = FXCollections.observableArrayList();
    
    
    private FXMLLoader loader;
    private String query;
    LivreService types;
    public File selectedFile;
    //private boolean edit=false,add=false;
    @FXML
    private AnchorPane anchorpane_parentL;
    @FXML
    private AnchorPane anchorpane_leftL;
    @FXML
    private Label label_titleL;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficherLivre();
        tabLivre.setEditable(true);
        
        TypeService ty =new TypeService();        
        ObservableList<Type> typess = FXCollections.observableArrayList(ty.AfficherType());        
        txt_type.setItems(typess);
        
        btn_type.setOnAction(e->{
            AfficheType();
        });
        
        column_nb.setVisible(true);
        
        Callback<TableColumn<Livre, String>, TableCell<Livre, String>> cellFactoryImage
                = //
                new Callback<TableColumn<Livre, String>, TableCell<Livre, String>>() {
            String path;
           
            @Override
            public TableCell<Livre, String> call(TableColumn<Livre, String> param) {
                
                final TableCell<Livre, String> cell = new TableCell<Livre, String>() {
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
                        if (tabLivre.getSelectionModel().getSelectedItem() != null && !tabLivre.getSelectionModel().getSelectedItem().getImage().contains("null")) {
                            Stage window = new Stage();
//
                            window.setMinWidth(250);
                            ImageView imagevPOPUP = new ImageView(new Image(tabLivre.getSelectionModel().getSelectedItem().getImage()));
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
        column_imageL.setCellFactory(cellFactoryImage);
    
    }    
    
    
    
    @FXML
    void uploadButton(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Veuillez choisir l'image");
        fc.getExtensionFilters().addAll(
                //new FileChooser.ExtensionFilter("Image", ".jpg", ".png"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {

            path_img = selectedFile.getName();
//    
            path.setText(path_img);
            Image imagea = new Image(selectedFile.toURI().toString());
           imageaff.setImage(imagea) ;
        } else {
            System.out.println("File is not valid!");
        }
    }

   @FXML
    void AjouterLivre(ActionEvent event) throws SQLException, IOException{
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
            //String image = selectedFile.getAbsolutePath();
            //File f = new File(image);
            Type type = txt_type.getSelectionModel().getSelectedItem();
             String image = path.getText();
             try {
                      
                    File source = new File(selectedFile.toString());
                    File dest = new File("C:\\3A13\\ressources");

            FileUtils.copyFileToDirectory(source, dest);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            Livre livre = new Livre(type, nom, description, auteur, quantite , image);




            if(liv.AjouterLivre(livre)){
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
        column_nb.setCellValueFactory(new PropertyValueFactory<>("nbPersonnes"));
        column_type.setCellValueFactory((CellDataFeatures<Livre, String> param) -> new SimpleStringProperty(param.getValue().getId_type().getLibelle()));
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
        //ImageView img = new ImageView("image");
        
        
    }
    
  
    public void AfficheType(){
        try{
            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Type.fxml"));
            TypeController controller = new TypeController();
            loader.load();
            Scene scene = new Scene(loader.getRoot());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
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
       Refresh();
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
                    path.setText("file:C:\\3A13\\ressources"+l.getImage());
                    
                }
            }
        });
        Livre l = tabLivre.getSelectionModel().getSelectedItem();
        return l;
    }


    @FXML
    void modifierLivre() {
      
        LivreService liv = new LivreService();
        try {
            recuperLivre().setId_type(txt_type.getSelectionModel().getSelectedItem());
            recuperLivre().setNom(txt_nom.getText());
            recuperLivre().setDescription(txt_description.getText());
            recuperLivre().setAuteur(txt_auteur.getText());
            recuperLivre().setQuantite(Integer.parseInt(txt_quantite.getText()));
            recuperLivre().setImage(path.getText());
            try {
                      
            File source = new File(selectedFile.toString());
            File dest = new File("C:\\3A13\\ressources");

            FileUtils.copyFileToDirectory(source, dest);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            
            liv.update(recuperLivre());
            tabLivre.getItems().clear();
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
    }*/

    
/*    
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
                }tabLivre.setItems(filteredList);
            }
            //tabLivre.setItems(filteredList);
        }tabLivre.setItems(filteredList);
    }
*/

     @FXML
    private void search(ActionEvent event) {
        
        LivreService cs = new LivreService();
        ArrayList AL = (ArrayList) cs.AfficherLivre();
        ObservableList OReservation = FXCollections.observableArrayList(AL);
        FilteredList<Livre> filteredData = new FilteredList<>(OReservation, p -> true);
        filterInput.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(myObject -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(myObject.getNom()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Livre> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tabLivre.comparatorProperty());
        tabLivre.setItems(sortedData);
    
    }

    @FXML
    private void AfficheRes(MouseEvent event) {
        try{
            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AfficheRes.fxml"));
            TypeController controller = new TypeController();
            loader.load();
            Scene scene = new Scene(loader.getRoot());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            
        }catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void Stat(MouseEvent event) {
        try{
            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Stat.fxml"));
            TypeController controller = new TypeController();
            loader.load();
            Scene scene = new Scene(loader.getRoot());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            
        }catch(IOException ex) {
            System.out.println(ex.getMessage());
        }        
    }
    
    @FXML
    void exitApplication(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    private void clear(MouseEvent event) {
        txt_auteur.clear();
            txt_nom.clear();
            txt_description.clear();
            txt_quantite.clear();
            path.clear();
            txt_type.setValue(null);
    }
}



