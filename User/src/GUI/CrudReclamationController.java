/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Ens;
import Entities.reclamation;
import Services.EnsService;
import Services.ReponseReclamationService;
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
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import static java.lang.ProcessBuilder.Redirect.to;
import java.net.MalformedURLException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import static javafx.collections.FXCollections.observableList;
import static javafx.collections.FXCollections.observableList;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;
import org.controlsfx.control.Notifications;

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
    private DatePicker dateR;
    
    List<String> typeP;
    @FXML

    private Button reclamer;
    @FXML
    private TableView<reclamation> table_reclamation;
    private TableColumn<reclamation, String> identifiant;
    @FXML
    private TableColumn<reclamation, String> telephone;
    @FXML
    private TableColumn<reclamation, String> email;
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
    private Button image;
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
    private TableColumn<reclamation, Date> DateD;
    public File selectedFile;
    String path_img;
    @FXML
    private Label path;
    static reclamation rec ; 
    private String pathe;
    @FXML
    private Button importer;
    
String erreur;
    @FXML
    private ImageView emailCheckMark;
    @FXML
    private ImageView telCheckMark;
    @FXML
    private ImageView nomCheckMark;
    @FXML
    private ImageView prenomCheckMark;
    @FXML
    private ImageView NomImage;
    @FXML
    private ImageView TeleImage;
    @FXML
    private ImageView PrenomImage;
    @FXML
    private ImageView EmailImage;
    @FXML
    private TableView<Entities.Ens> tabEnseignant;
    @FXML
    private TableColumn<Entities.Ens, StringBuffer> NomEns;
    @FXML
    private TableColumn<Entities.Ens, String> PrenomEns;
    @FXML
    private TableColumn<Entities.Ens, String> EmailEns;
     

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        afficher();  // TODO
        choiceBox();
        afficherEnstype();
        getRecalamtion();
        
      tabEnseignant.setVisible(false);
        typeP = new ArrayList();
        typeP.add("*.jpg");
        typeP.add("*.png");
        NomImage.setImage(new Image("Images/nom.png"));
         PrenomImage.setImage(new Image("Images/prenom.png"));
          TeleImage.setImage(new Image("Images/tel.png"));
           EmailImage.setImage(new Image("Images/email.png"));
           
        //  afficherEnstype();
  
 
       
        imageview.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasFiles()) {
                    event.acceptTransferModes(TransferMode.COPY);
                } else {
                    event.consume();
                }
            }
        });

       
       imageview.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    success = true;
                    pathe = null;
                    for (File file : db.getFiles()) {
                        pathe = file.getName();
                        selectedFile = new File(file.getAbsolutePath());
                        System.out.println("Drag and drop file done and path=" + file.getAbsolutePath());//file.getAbsolutePath()="C:\Users\X\Desktop\ScreenShot.6.png"
                        imageview.setImage(new Image("file:" + file.getAbsolutePath()));
                        System.out.println("test");
//                        screenshotView.setFitHeight(150);
//                        screenshotView.setFitWidth(250);
                        image.setText(pathe);
                        
                    }
                }
                event.setDropCompleted(success);
                event.consume();
                System.out.println("test2");
            }
        });

        imageview.setImage(new Image("file:C:\\3a16\\Image\\drag-drop.gif"));
       
        
        
  imageview.setOnMouseClicked((MouseEvent event2)
                -> {
            if (event2.getClickCount() >= 1) {
                //  if (AfficherReclamationController.selectionedReclamation.getScreenshot() != null) 
                {
                    Stage window = new Stage();
                    window.setMinWidth(250);
                    ImageView imagevPOPUP = new ImageView(new Image(table_reclamation.getSelectionModel().getSelectedItem().getScreenshot()));
                    imagevPOPUP.setFitHeight(576);
                    imagevPOPUP.setFitWidth(1024);

                    VBox layout = new VBox(10);
                    layout.getChildren().addAll(imagevPOPUP);
                    layout.setAlignment(Pos.CENTER);

                    Scene scene = new Scene(layout);
                    window.setScene(scene);
                    window.show();

                }
            }

        });
  /*********************************************************************************************Modifier Iamge */////////////////////////

  
  
  
  
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
  Etat.setCellFactory(cellFactoryStatus);
  
  
  
  
  
  
  
  
  
  
  
  
  
  /*************************************************************************************************************************************************/
  
  Callback<TableColumn<reclamation, String>, TableCell<reclamation, String>> cellFactoryImage;
        cellFactoryImage = //
                new Callback<TableColumn<reclamation, String>, TableCell<reclamation, String>>() {
                    String path;
                    
                    @Override
                    public TableCell<reclamation, String> call(TableColumn<reclamation, String> param) {
                        
                        
                        final TableCell<reclamation, String> cell;
                        cell = new TableCell<reclamation, String>() {
                            
                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    path = item;
                                    
                                    
                                    ImageView ime =new ImageView(new Image(item));
                                    ime.setFitHeight(100);
                                    ime.setFitWidth(100);
                                    setGraphic(ime);
                                    setText(null);
//                            System.out.println(item);
                                }
                            }
                        };
                        
                        cell.setOnMouseClicked((MouseEvent event2)
                                -> {
                            if (event2.getClickCount() == 1) {
                                if (table_reclamation.getSelectionModel().getSelectedItem() != null && !table_reclamation.getSelectionModel().getSelectedItem().getScreenshot().contains("null")) {
                                    Stage window = new Stage();
//
window.setMinWidth(250);
ImageView imagevPOPUP = new ImageView(new Image(table_reclamation.getSelectionModel().getSelectedItem().getScreenshot()));
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

        screenshot.setCellFactory(cellFactoryImage);
  
  }
  
     
  

   
    @FXML
    
    private void ajouterReclamation(ActionEvent event) {
        String nom = nomR.getText();

        String prenom = prenomR.getText();

        String tel = telR.getText();

        String email = emailR.getText();
        String objet = objetR.getText();
        String reclamation = reclamationR.getText();
        String type = typeR.getValue();
        LocalDate dd = java.time.LocalDate.now();
        Date datd = java.sql.Date.valueOf(dd);
        String image = path.getText();
             try {
                      
                     File source = new File(selectedFile.toString());
            File dest = new File("C:\\3a16\\Image");

            FileUtils.copyFileToDirectory(source, dest);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
             
             
             
            Ens X = tabEnseignant.getSelectionModel().getSelectedItem();
          
            
              
             if(    testSaisie()&& typeR.getSelectionModel().getSelectedItem().equals("Ens"))
             {
                
       
        reclamationService rec = new reclamationService();
        reclamation re = new reclamation(nom, prenom, tel, email, type,image, objet, reclamation, datd, "En Attente");

        
                 
                 
                    Ens l = tabEnseignant.getSelectionModel().getSelectedItem();
                 
       tabEnseignant.setOnMouseClicked((e) -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                if (e.getClickCount() >= 2) {
                    System.out.println("double clique");
                    //modifierBTN.setVisible(true);
                 
                    System.out.println(  "teeeeeeeest"+      l.getEmail()) ;
                    l.getNom();
                    l.getPrenom();
             
                    tabEnseignant.setVisible(false);
                    
                    
                }
            }
        });
                 
                 
        rec.insert(re);
           SendMail sm = new SendMail();
                  sm.send(l.getEmail(), "farouk", "test", "farouk.elhamdi@esprit.tn", "193JMT2513");
                 System.out.println("insere");
        vider();
                 System.out.println("vider");
        afficher();
       
       Notifications n = Notifications.create()
                    .title("Ajout ")
                     .text("Ajout avec succées")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
            n.showWarning();
            
        
             
        
        
        
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
                        if (table_reclamation.getSelectionModel().getSelectedItem() != null && !table_reclamation.getSelectionModel().getSelectedItem().getScreenshot().contains("null")) {
                            Stage window = new Stage();
//
                            window.setMinWidth(250);
                            ImageView imagevPOPUP = new ImageView(new Image(table_reclamation.getSelectionModel().getSelectedItem().getScreenshot()));
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

        screenshot.setCellFactory(cellFactoryImage);
             }
             else
             {
                 System.out.println("autre");
                 
        reclamationService rec = new reclamationService();
        reclamation re = new reclamation(nom, prenom, tel, email, type,image, objet, reclamation, datd, "En Attente");
        
      
        rec.insert(re);
                 System.out.println("insere");
        vider();
                 System.out.println("vider");
        afficher();
       
       Notifications n = Notifications.create()
                    .title("Ajout ")
                     .text("Ajout avec succées")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
            n.showWarning();
            
        
             
        
        
        
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
                        if (table_reclamation.getSelectionModel().getSelectedItem() != null && !table_reclamation.getSelectionModel().getSelectedItem().getScreenshot().contains("null")) {
                            Stage window = new Stage();
//
                            window.setMinWidth(250);
                            ImageView imagevPOPUP = new ImageView(new Image(table_reclamation.getSelectionModel().getSelectedItem().getScreenshot()));
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

        screenshot.setCellFactory(cellFactoryImage);
             }
           
    }

  /**********************************************Controle Saissie*******************************************************************************************/
    
    private Boolean testSaisie() {
        erreur = "";
        if (!testMail()) {
            erreur = erreur + ("Veuillez verifier que votre adresse email est de la forme : ***@***.** \n");
        }
        if (!testTel()) {
            erreur = erreur + ("Telephone doit avoir 8 chiffres et ne doit pas contenir des caracteres \n");
        }
        
        
        if (!testNom()) {
            erreur = erreur + ("Veuillez verifier votre Nom: seulement des caractères et de nombre >= 3 \n");
        }
        if (!testPrenom()) {
            erreur = erreur + ("Veuillez verifier votre Prenom: seulement des caractères et de nombre >= 3");
        }
        if(!testEns())
        {
            erreur = erreur + ("Veuillez choisir un Enseignant");
        }

        if (!testMail() || !testTel() || !testNom() || !testPrenom()||!testEns()) {
            Notifications n = Notifications.create()
                    .title("Erreur de format")
                    .text(erreur)
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(6));
            n.showInformation();
        }

        return testMail() && testTel()   && testNom() && testPrenom();
    }

    public  boolean  testEns()
    {
        if ( tabEnseignant.getSelectionModel().getSelectedItem()==null)
            return false ;
        else 
            return 
                    false;
    }
    
    private Boolean testMail() {

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (emailR.getText() == null) {
            return false;
        }

        if (pat.matcher(emailR.getText()).matches() == false) {
            emailCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
//                erreur = erreur + ("Veuillez verifier votre adresse email\n");
            return false;
//            

        } else {
            emailCheckMark.setImage(new Image("Images/checkMark.png"));
        }
        return true;

    }

//   
    private Boolean testTel() {
        if (telR.getText().trim().length() == 8) {
            int nbChar = 0;
            for (int i = 1; i < telR.getText().trim().length(); i++) {
                char ch = telR.getText().charAt(i);
                if (Character.isLetter(ch)) {
                    nbChar++;
                }
            }

            if (nbChar == 0) {
                telCheckMark.setImage(new Image("Images/checkMark.png"));
                return true;
            } else {
                telCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
                return false;

            }
        } else if (telR.getText().trim().length() != 8) {
//            erreur = erreur + ("Il faut saisir 8 chiffres dans le numéro de telephone\n");
            telCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
            return false;
        }

        return true;

    }

    
    private Boolean testNom() {
        int nbNonChar = 0;
        for (int i = 1; i < nomR.getText().trim().length(); i++) {
            char ch = nomR.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && nomR.getText().trim().length() >= 3) {
            nomCheckMark.setImage(new Image("Images/checkMark.png"));
            return true;
        } else {
            nomCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }

    private Boolean testPrenom() {
        int nbNonChar = 0;
        for (int i = 1; i < prenomR.getText().trim().length(); i++) {
            char ch = prenomR.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && prenomR.getText().trim().length() >= 3) {
            prenomCheckMark.setImage(new Image("Images/checkMark.png"));
            return true;
        } else {
            prenomCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /********************************************************************Fin controlle Saiise *////////////////////////////////////////////////////////////////////////

    private void afficher() {
        reclamationService sp = new reclamationService();
        List parascolaire = sp.displayAll();
        ObservableList et = FXCollections.observableArrayList(parascolaire);
        table_reclamation.setItems(et);
  
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
   /*     Callback<TableColumn<reclamation, String>, TableCell<reclamation, String>> cellFactoryImage
                =                 //
         new Callback<TableColumn<reclamation, String>, TableCell<reclamation, String>>() {
            @Override
            public TableCell<reclamation, String> call(TableColumn<reclamation, String> param) {
               
                 
            final TableCell<reclamation, String> cell = new TableCell<reclamation, String>() {
                
                 @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText("Aucune image n'existe dans cette liste");
                        } else {
                            System.out.println(item);
                            ImageView imagev = new ImageView(new Image(item));
                            imagev.setFitHeight(200);
                            imagev.setFitWidth(240);
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
        screenshot.setCellFactory(cellFactoryImage);
        
        /****************************************************/
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
  Etat.setCellFactory(cellFactoryStatus);
  
  
  
  
                
    }

    @FXML
    private void importer(ActionEvent event) throws MalformedURLException {
   
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
           imageview.setImage(imagea) ;
        } else {
            System.out.println("File is not valid!");
        }
    }



 
    @FXML
    private void modifierReclamation(ActionEvent event) {

      

         java.util.Date current_date = new java.util.Date();
        
        java.util.Date creation_date = table_reclamation.getSelectionModel().getSelectedItem().getDate();
        long diffInMillies = Math.abs(current_date.getTime() - creation_date.getTime());
        long diff = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        reclamationService liv = new reclamationService();
        try {
            if(table_reclamation.getSelectionModel().getSelectedItem().getEtat().equals("En Attente")&&(diff<24))
            {
            getRecalamtion().setNom(nomR.getText());
            getRecalamtion().setPrenom(prenomR.getText());
            getRecalamtion().setTel(telR.getText());
            getRecalamtion().setEmail(emailR.getText());
            getRecalamtion().setType(typeR.getSelectionModel().getSelectedItem());
            getRecalamtion().setScreenshot(path.getText());
            getRecalamtion().setObjet(objetR.getText());
            getRecalamtion().setReclamation(reclamationR.getText());
            getRecalamtion().setDate(Date.valueOf(java.time.LocalDate.now()));
            liv.update(getRecalamtion());
            ReponseReclamationService rs=new ReponseReclamationService();
          reclamation x = table_reclamation.getSelectionModel().getSelectedItem();
          rs.ModifierEtatEnAttente(x);
            table_reclamation.getItems().clear();
            afficher();
            }
               else 
           
          
                if (table_reclamation.getSelectionModel().getSelectedItem().getEtat().equals("En cours"))
                        {
                            Notifications n = Notifications.create()
                    .title("Erreur")
                    .text("Votre réclamation en cours de traitement")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
            n.showWarning();
                
                }
                   else if (table_reclamation.getSelectionModel().getSelectedItem().getEtat().equals("Traite"))
                    {
                           getRecalamtion().setNom(nomR.getText());
            getRecalamtion().setPrenom(prenomR.getText());
            getRecalamtion().setTel(telR.getText());
            getRecalamtion().setEmail(emailR.getText());
            getRecalamtion().setType(typeR.getSelectionModel().getSelectedItem());
            getRecalamtion().setScreenshot(path.getText());
            getRecalamtion().setObjet(objetR.getText());
            getRecalamtion().setReclamation(reclamationR.getText());
            getRecalamtion().setDate(Date.valueOf(java.time.LocalDate.now()));
            liv.update(getRecalamtion());
            ReponseReclamationService rs=new ReponseReclamationService();
          reclamation x = table_reclamation.getSelectionModel().getSelectedItem();
          rs.ModifierEtatEnAttente(x);
            table_reclamation.getItems().clear();
            afficher();
                    }
                   else 
                   {
                             Notifications n = Notifications.create()
                    .title("Erreur")
                    .text("Modification impossible: Reclamation a depasée 24h depuis sa creation")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
            n.showWarning();
                   }
         
        } catch (Exception e) {

            System.out.println("Oops Something Went Wrong on Modifier Cours ...");
        }
     
    }
    
    
    
    
    
    
    
     public reclamation getRecalamtion() {
        reclamationService cs = new reclamationService();
        ReponseReclamationService re=new ReponseReclamationService();
        table_reclamation.setOnMouseClicked((e) -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                if (e.getClickCount() == 2 ){
                   
                    reclamation x = table_reclamation.getSelectionModel().getSelectedItem();
                   
                    nomR.setText(x.getNom());
                    
                    prenomR.setText(x.getPrenom());
                          
                    telR.setText(x.getTel());
                
                    
                    emailR.setText(x.getEmail());
               
                    
                    typeR.setValue(x.getType());
               
                    
                    path.setText(x.getScreenshot());
                    objetR.setText(x.getObjet());
                  
                    
                  reclamationR.setText(x.getReclamation());
 
                }
                else 
                {
                      Alert alert = new Alert(Alert.AlertType.NONE, "Récalamtion Tst ", ButtonType.OK, ButtonType.NO, ButtonType.CANCEL);
                }
            }
        });
        reclamation x = table_reclamation.getSelectionModel().getSelectedItem();
        return x;
     }
  
            
    @FXML
    private void supprimerReclamationn(ActionEvent event) {

        reclamationService cs = new reclamationService();
        reclamation cc = (reclamation) table_reclamation.getSelectionModel().getSelectedItem();
        System.out.println(cc);
        if (cc == null) {
           Notifications n = Notifications.create()
                    .title("Erreur")
                    .text("Choisir reclamation SVP !")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
            n.showWarning();
        } else {
            if(!table_reclamation.getSelectionModel().getSelectedItem().getEtat().equals("En cours"))
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous voulez vraiment supprimer cette categorie");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                
            cs.delete(cc.getId());
            afficher();
        

            cc = null;
            }
          
    }
          else
            {
                 Notifications n = Notifications.create()
                    .title("Erreur")
                    .text("Reclamation en cours de traitement ! ")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
            n.showWarning();
            }
        }}

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

                if (String.valueOf(myObject.getNom()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;

                }
                return false;
            });
        });
        SortedList<reclamation> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_reclamation.comparatorProperty());
        table_reclamation.setItems(sortedData);
    }

    public boolean isInt(String s) {
        try {
            int i = Integer.parseInt(s);
            return true;
        } catch (NumberFormatException er) {
            return false;
        }
    }

    @FXML
    private void choiceBox() {
      
        typeR.getItems().add("Ens");
        typeR.getItems().add("Autre");
        
       

    }
    
    public void vider()
    {
                  nomR.setText(null);
                    
                    prenomR.setText(null);
                          
                    telR.setText(null);
                
                    
                    emailR.setText(null);
               
                    
                    typeR.setValue(null);
        boolean name = selectedFile == null;
                
                    path.setText(null);
                    objetR.setText(null);
                   
                   
                  reclamationR.setText(null);
                
    }

    private void image(ActionEvent event) throws MalformedURLException {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.home") + "\\Desktop"));
        fc.setTitle("Veuillez choisir l'image");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {

            pathe = 
                    selectedFile.getName();
            System.out.println(pathe);
//                path = selectedFile.toURI().toURL().toExternalForm();
            imageview.setImage(new Image(selectedFile.toURI().toURL().toString()));
            imageview.setFitHeight(150);
            imageview.setFitWidth(250);
            image.setText(pathe);

        }

    }

     
 private void afficherEns() {


        EnsService sp = new EnsService();
        List p = sp.displayAll();
        ObservableList et = FXCollections.observableArrayList(p);
        tabEnseignant.setItems(et);
       
        NomEns.setCellValueFactory(new PropertyValueFactory<>("nom"));
   
      
        EmailEns.setCellValueFactory(new PropertyValueFactory<>("email"));
         PrenomEns.setCellValueFactory(new PropertyValueFactory<>("prenom"));
     
 
}


private void afficherEnstype(){
    typeR.setOnAction(t->{
        if(typeR.getValue().equals("Ens"))
        {
            tabEnseignant.setVisible(true);
            
           
    afficherEns();
   
        } 
        
        else 
            tabEnseignant.setVisible(false);
       
     
    });
    
}
/*public void ens()
{
       tabEnseignant.setOnMouseClicked((e) -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                if (e.getClickCount() >= 2) {
                    System.out.println("double clique");
                    //modifierBTN.setVisible(true);
                    Ens l = tabEnseignant.getSelectionModel().getSelectedItem();
                    System.out.println(        l.getEmail()) ;
                    l.getNom();
                    l.getPrenom();
            
                    tabEnseignant.setVisible(false);
                    
                    
                }
            }
        });
}*/
}

