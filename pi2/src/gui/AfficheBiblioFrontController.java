/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Livre;
import entity.ReservationLivre;
import entity.User;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import pi.Pi;
import service.LivreService;
import service.MyServices;
import service.ReservationService;

/**
 * FXML Controller class
 *
 * @author Smirani
 */
public class AfficheBiblioFrontController implements Initializable {

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
        
    @FXML
    private Button empreinter;

    @FXML
    private Button rendre;
    
    
    private FXMLLoader loader;
    
    public static ObservableList<Livre> tableData;
    @FXML
    private Button mesEmpreinte;
    @FXML
    private ImageView exit;
    public static int userid;
    private static MyServices myServices = new MyServices();
    
    //public static int Id_user_connecte;
     User UserConneter;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficherLivre();
        column_idL.setVisible(false);
        /*
        //MyServices myServices = new MyServices();
        User loggedUser = gui.LoginController.getInstance().getLoggedUser();
        User UserConneter = myServices.chercherUtilisateurByid(loggedUser.getId());
        userid = loggedUser.getId();
        System.out.println("deeerfr");
        
        pi.Pi.Id_user_connecte=loggedUser.getId();
        */
        
        System.out.println(Pi.Id_user_connecte+" Test Cnx ");
        
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
        //ImageView img = new ImageView("image");
        
        
    }


    @FXML
    void empreinter(ActionEvent event) {
        int exist=1;
       
        System.out.println(pi.Pi.Id_user_connecte);
        User loggedUser = LoginController.getInstance().getLoggedUser();        
        User e=myServices.chercherUtilisateurByid(pi.Pi.Id_user_connecte);          
        System.out.println(e+"useeeeeeeeeeeeeeeeeeer");
        //User e = new User("abdou", 2, "$2y$13$JAzQ8n5HzEdMaZZT0CXyyeCTl2Vz9iuglgPknSv8ctRJ0/A2Ispdq", "abdou", "abdou", "abdessalem.smirani@esprit.tn");
        //Eleve e = new Eleve("eleve", 4, "$2y$13$VvqAJNDdUXaxoCnL5Qoscefg9iARL1G0vSHa8kqVlyHTYntulwG/2", "eleve", "eleve", "test@t.hr");
        Livre l = (Livre) tabLivre.getSelectionModel().getSelectedItem();
        ReservationService r = new ReservationService();
        ArrayList<ReservationLivre> list= new ArrayList<>();
        list = r.afficher_Reservation();
        //System.out.println(list);
        
        for(int i=0;i<list.size();i++){
            if(e.getId()==list.get(i).getId_livre() && l.getId()==list.get(i).getId_eleve()){
                exist=0;
                //System.out.println(e.getId()+"="+list.get(i).getId_livre());
                //System.out.println(l.getId()+"="+list.get(i).getId_eleve());
                System.out.println("Livre Existe");
                JOptionPane.showMessageDialog(null, "Livre deja Emprinter");
                break;
            }        

        }
        System.out.println("exist="+exist);
        
            if(exist==1){    
            r.reserver_livre(l, e);
            JOptionPane.showMessageDialog(null, "Votre empreinte du Livre "+l.getNom()+ " a été effectuée avec succés ! \n");
            }
        LoadDataLivre();
          //this.empreinter.setDisable(true);
          //this.rendre.setDisable(false);

    }

    @FXML
    void rendre(ActionEvent event) {
        int exist=0;
        System.out.println(pi.Pi.Id_user_connecte);
        User loggedUser = LoginController.getInstance().getLoggedUser();        
        User e=myServices.chercherUtilisateurByid(pi.Pi.Id_user_connecte);          
        System.out.println(e+"useeeeeeeeeeeeeeeeeeer");
        //User e = new User("abdou", 2, "$2y$13$JAzQ8n5HzEdMaZZT0CXyyeCTl2Vz9iuglgPknSv8ctRJ0/A2Ispdq", "abdou", "abdou", "abdessalem.smirani@esprit.tn");
        //Eleve e = new Eleve("eleve", 4, "$2y$13$VvqAJNDdUXaxoCnL5Qoscefg9iARL1G0vSHa8kqVlyHTYntulwG/2", "eleve", "eleve", "test@t.hr");
        Livre l = (Livre) tabLivre.getSelectionModel().getSelectedItem();
        ReservationService r = new ReservationService();
        ArrayList<ReservationLivre> list= new ArrayList<>();
        list = r.afficher_Reservation();
        
        for(int i=0;i<list.size();i++){
            if((e.getId()==list.get(i).getId_livre()) && (l.getId()==list.get(i).getId_eleve())){
                exist=1;
               // System.out.println(e.getId()+"="+list.get(i).getId_livre());
                //System.out.println(l.getId()+"="+list.get(i).getId_eleve());
                //System.out.println("Livre n Existe pas ");
                JOptionPane.showMessageDialog(null, "Vous n'avez pas Emreinter se livre");
                break;
            }
        }
        
        if(exist==1){    
            r.rendre_livre(l,e);
            JOptionPane.showMessageDialog(null,"Livre "+l.getNom()+ " Rendu");            
        }
        else{
            System.out.println("Livre n Existe pas ");
                JOptionPane.showMessageDialog(null, "Vous n'avez pas Emreinter se livre");
        }
       
        LoadDataLivre();
        //this.rendre.setDisable(true);
        //this.empreinter.setDisable(false);

    }
    
    private void LoadDataLivre() {
        LivreService se = new LivreService();
        ArrayList<Livre> livres = new ArrayList<>();
        tableData = FXCollections.observableArrayList(livres);
        livres = se.AfficherLivre();

        for (int i = 0; i < livres.size(); i++) {
            tableData.add(livres.get(i));

        }
        tabLivre.setItems(tableData);
    }
    
    @FXML
    public void mesEmpreinte(){
        try{
            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ReservationEleve.fxml"));
            TypeController controller = new TypeController();
            loader.load();
            Scene scene = new Scene(loader.getRoot());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
             //Stage.initStyle(StageStyle.TRANSPARENT);
            scene.setFill(Color.TRANSPARENT);
            stage.show();
            
        }catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void actualiserPushed() {
        LoadDataLivre();
    }

    @FXML
    private void retour(MouseEvent event) {
       
            final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void exitApplication(MouseEvent event) {
        Platform.exit();
    }
    
    
    
}
