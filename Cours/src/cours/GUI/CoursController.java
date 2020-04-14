/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cours.GUI;

import com.aspose.pdf.*;
import cours.Entities.Cours;
import cours.Entities.Matiere;
import cours.Entities.Question;
import cours.Entities.Reponse;
import cours.Services.CoursServices;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class CoursController implements Initializable {

    @FXML
    private TableView<Cours> table;
    @FXML
    private ImageView btn;
    private TableColumn<Cours, Integer> IDC;
    @FXML
    private TableColumn<Cours, String> LIBC;
    @FXML
    private TableColumn<Cours, String> NIVC;
    private TextField lib;
    @FXML
    private TextField niv;
    @FXML
    private ComboBox<Cours> id_C;
    @FXML
    private TextField question;
    @FXML
    private TableView<Question> tableQuestion;
    private TableColumn<Question, String> IDQ;
    @FXML
    private TableColumn<Question, String> COURSQ;
    @FXML
    private TableColumn<Question, String> QUESTIONQ;
    private Label etatQ;
    private Label dial;
    @FXML
    private AnchorPane back;
    @FXML
    private TableColumn<Cours, String> MATC;
    @FXML
    private ComboBox<Matiere> mat;
    @FXML
    private ImageView BModifierCours;
    @FXML
    private DatePicker date;
    @FXML
    private TableColumn<Cours, Date> DATE;
    @FXML
    private AnchorPane crudCours;
    @FXML
    private AnchorPane anchorCours;
    @FXML
    private AnchorPane crudQuestion;
    @FXML
    private Button BAjouterQuestion;
    @FXML
    private Button BModifierQuestion;
    @FXML
    private AnchorPane anchorQuestion;
    @FXML
    private Circle questionCircle;
    @FXML
    private ImageView questionIcon;
    @FXML
    private Circle coursCircle;
    @FXML
    private ImageView coursIcon;
    @FXML
    private Group iconQuestion;
    @FXML
    private Group iconCours;
    @FXML
    private Group exit;
    private Label LabelLibelle;
    @FXML
    private Label LabelNiveau;
    private ImageView imglib;
    @FXML
    private ImageView imgniv;
    @FXML
    private Label LabelNiveau1;
    @FXML
    private ImageView Importer;
    @FXML
    private TextField FilePath;
    @FXML
    private AnchorPane crudReponse;
    @FXML
    private ComboBox<Cours> id_CR;
    @FXML
    private ComboBox<Question> id_QR;
    @FXML
    private TextField repR;
    @FXML
    private Group iconReponse;
    @FXML
    private AnchorPane anchorReponse;
    @FXML
    private TableView<Reponse> tableReponse;
    @FXML
    private TableColumn<Reponse, String> REPONSE;
    @FXML
    private TableColumn<Reponse, String> ETAT;
    @FXML
    private TableColumn<Reponse, String> COURS;
    @FXML
    private TableColumn<Reponse, String> QUESTION;
    @FXML
    private ComboBox<String> etatrep;
    @FXML
    private Button BAjouterReponse;
    @FXML
    private Button BModifierReponse;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        exit();
        IconChange();
        AfficherTableCours();
        AfficherTableQuestion();
        AfficherTableReponse();
        controleCours();
        BModifierQuestion.setVisible(false);
        anchorCours.setVisible(false);
        anchorQuestion.setVisible(false);
        anchorReponse.setVisible(false);
        imgniv.setVisible(false);
        BModifierCours.setVisible(false);
        BModifierReponse.setVisible(false);
        FilePath.setDisable(true);
        CoursServices cs = new CoursServices();
        mat.setItems(FXCollections.observableArrayList(cs.AfficherMatiere()));
    }

    @FXML
    public void AjouterCours(MouseEvent event) throws Exception {
        CoursServices cs = new CoursServices();
        ObservableList<Cours> cours = FXCollections.observableArrayList(cs.AfficherCours());
        String Lib = FilePath.getText().replaceAll(".pdf", "");
        String Niv = niv.getText();

        Cours c = new Cours(mat.getSelectionModel().getSelectedItem(), Lib, Niv, "C:/Users/ASUS/Documents/NetBeansProjects/Cours/Cours/src/cours/PDF/" + FilePath.getText());

        cs.AjouterCours(c);
        table.getItems().clear();
        AfficherCours();
        TableCours();
        id_C.getItems().clear();
        id_C.setItems(cours);
        clearValues();

    }

    public void AfficherCours() {
        CoursServices cs = new CoursServices();
        ObservableList<Cours> cours = FXCollections.observableArrayList(cs.AfficherCours());

        LIBC.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        NIVC.setCellValueFactory(new PropertyValueFactory<>("niveau"));

        MATC.setCellValueFactory((CellDataFeatures<Cours, String> param) -> new SimpleStringProperty(param.getValue().getM().getLibelle()));
        table.setItems(cours);
        
    }

    
    public void SupprimerCours() {
        
            
        
        CoursServices cs = new CoursServices();

        table.setOnMouseClicked(t->{
            if(t.getButton()==MouseButton.SECONDARY){
                Cours x = table.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(AlertType.NONE, "Voulez vous supprimer " + x.getLibelle() + " " + x.getNiveau() + " ?", ButtonType.OK, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.OK) {
              
                if(cs.SupprimerCours(x)){
                    Refresh();
                } else{ Alert alert1 = new Alert(AlertType.NONE,  x.getLibelle()  + " possede une question vous devez la supprimer dabord", ButtonType.OK, ButtonType.NO, ButtonType.CANCEL);
            alert1.showAndWait();}
        
                
            } 
            }
             
        });
       

        
    }

    public Cours getCours() {
        CoursServices cs = new CoursServices();


            table.setOnMousePressed((e) -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                if (e.getClickCount() == 2) {

                BModifierCours.setVisible(true);
                Cours x = table.getSelectionModel().getSelectedItem();
                mat.setValue(x.getM());
                
                niv.setText(x.getNiveau());
                FilePath.setText(x.getContract().replace("C:/Users/ASUS/Documents/NetBeansProjects/Cours/Cours/src/cours/PDF/", ""));
                    System.out.println("hi");

            }}
            
        });
        
     
        Cours x = table.getSelectionModel().getSelectedItem();
        return x;
    }
                
                

    @FXML
    public void ModifierCours() {
        CoursServices cs = new CoursServices();

        try {

            getCours().setM(mat.getSelectionModel().getSelectedItem());
            getCours().setLibelle(FilePath.getText().replaceAll(".pdf", ""));
            getCours().setNiveau(niv.getText());
            getCours().setContract("C:/Users/ASUS/Documents/NetBeansProjects/Cours/Cours/src/cours/PDF/" + FilePath.getText());
            System.out.println(FilePath.getText());
            cs.ModifierCours(getCours());
            BModifierCours.setVisible(false);
        clearValues();

            btn.setVisible(true);
            Refresh();
                    


        } catch (Exception e) {
  System.out.println(FilePath.getText());
            System.out.println("Oops Something Went Wrong on Modifier Cours ...");
        }
    }

    public void Refresh() {
        table.getItems().clear();
        AfficherCours();
        tableQuestion.getItems().clear();
        id_C.getItems().clear();

        AfficherQuestions();
    }

    @FXML
    public void AjouterQuestion(ActionEvent event) {
        CoursServices cs = new CoursServices();
        Cours x = id_C.getSelectionModel().getSelectedItem();//bech tekhou l haja li selectionitha
        System.out.println(x);
        String Q = question.getText();//les attributs bech tzidhom fl base
        if (id_C.getSelectionModel().isEmpty()) {
            System.out.println("hi");
        }

        Question q = new Question(x, Q, Date.valueOf(date.getValue()));//star mta3 l ajouter appel cstructeur
        Pattern p = Pattern.compile("[^A-Za-z0-9 ]");
        Matcher m = p.matcher(Q);
        boolean b = m.find();
        if ((!Q.isEmpty()) && (!b)) {
            if (cs.AjouterQuestion(q)) {
                TableQuestions();
                Refresh();
                        clearValues();


            }
        } else if (Q.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR, "Question vide", ButtonType.OK, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();
        } else if (b) {
            Alert alert = new Alert(AlertType.ERROR, "Question contient des caracteres speciaux", ButtonType.OK, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();
        }
    }

    public void AfficherQuestions() {

            CoursServices cs = new CoursServices();
            ObservableList<Question> questions = FXCollections.observableArrayList(cs.AfficherQuestion());

            tableQuestion.setItems(questions);

            // COURSQ.setCellValueFactory(new PropertyValueFactory<>("cour"));
            COURSQ.setCellValueFactory((CellDataFeatures<Question, String> param) -> new SimpleStringProperty(param.getValue().getCour().getLibelle()));
            DATE.setCellValueFactory(new PropertyValueFactory<>("date"));
            QUESTIONQ.setCellValueFactory(new PropertyValueFactory<>("Question"));

 
    }

    @FXML
    public void SupprimerQuestion(MouseEvent event) {
        CoursServices cs = new CoursServices();
        if (event.isSecondaryButtonDown()) {
            Question x = tableQuestion.getSelectionModel().getSelectedItem();
            System.out.println(x.getId());
            Alert alert = new Alert(AlertType.WARNING, "Voulez vous supprimer " + x.getQuestion() + " ?", ButtonType.OK, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                 if(cs.SupprimerQuestion(x)){
                    Refresh();
                } else{ Alert alert1 = new Alert(AlertType.NONE,  x.getQuestion()+ " possede une reponse vous devez la supprimer dabord", ButtonType.OK, ButtonType.NO, ButtonType.CANCEL);
            alert1.showAndWait();}
            }
        }

    }

    public Question getQuestions() {
        CoursServices cs = new CoursServices();
        tableQuestion.setOnMouseClicked((e) -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                if (e.getClickCount() == 2) {
                    BModifierQuestion.setVisible(true);
                    Question x = tableQuestion.getSelectionModel().getSelectedItem();
                    id_C.setValue(x.getCour());
                    id_C.setOnAction(t->{
                    id_C.setItems(FXCollections.observableArrayList(cs.AfficherCours()));
                    });
                    question.setText(x.getQuestion());
                    String str = x.getDate().toString();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate dateTime = LocalDate.parse(str, formatter);
                    System.out.println(dateTime);
                    date.setValue(dateTime);

                }
            }
        });
        Question x = tableQuestion.getSelectionModel().getSelectedItem();
        return x;
    }

    @FXML
    public void ModifierQuestion() {
        CoursServices cs = new CoursServices();
        String x = question.getText();
        try {

            getQuestions().setQuestion(x);
            getQuestions().setCour(id_C.getValue());
            getQuestions().setDate(Date.valueOf(date.getValue()));
            cs.ModifierQuestion(getQuestions());
            BModifierQuestion.setVisible(false);
            BAjouterQuestion.setVisible(true);
            id_C.setValue(null);
            question.clear();
            Refresh();
                    clearValues();

        } catch (Exception e) {

            System.out.println("Oops Something Went Wrong on Modifier Question ...");
        }
    }

    public void TableCours() {
        AfficherCours();
        controleCours();
        getCours();
        SupprimerCours();
        CoursServices cs = new CoursServices();
        ObservableList<Cours> Cours = FXCollections.observableArrayList(cs.AfficherCours());
        id_C.setItems(Cours);
        TelechargerPdf();
        anchorCours.setVisible(true);
        crudCours.setVisible(true);
        iconCours.toFront();
        anchorQuestion.setVisible(false);
        crudQuestion.setVisible(false);
        anchorReponse.setVisible(false);
        crudReponse.setVisible(false);
        iconQuestion.toBack();
        iconReponse.toBack();
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.2), anchorCours);
        translate.setToX(-190);
        translate.play();
    }

    public void AfficherTableCours() {
        iconCours.setOnMousePressed((event) -> {
            TableCours();
        });

    }

    public void TableReponse() {
        CoursServices cs = new CoursServices();
        ObservableList<Cours> Cours = FXCollections.observableArrayList(cs.AfficherCours());
        ObservableList<Question> Questions = FXCollections.observableArrayList(cs.AfficherQuestion());
        ObservableList<String> Etat = FXCollections.observableArrayList("vrai", "faux");
        AfficherReponse();
        getReponse();
        SupprimerReponse();
        id_QR.setItems(null);
        id_CR.setItems(Cours);
        etatrep.setItems(Etat);
        anchorReponse.setVisible(true);
        anchorCours.setVisible(false);
        anchorQuestion.setVisible(false);
        crudReponse.setVisible(true);
        crudQuestion.setVisible(false);
        crudCours.setVisible(false);
        iconReponse.toFront();
        iconCours.toBack();
        iconQuestion.toBack();
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.2), anchorReponse);
        translate.setToX(-190);
        translate.play();
           id_CR.setOnAction(t->{
        id_QR.setItems(FXCollections.observableArrayList(cs.AfficherQuestionParCours(id_CR.getValue())));
        });
    }

    public void AfficherTableReponse() {
        iconReponse.setOnMousePressed((event) -> {
            TableReponse();
        });

    }

    public void TableQuestions() {
        CoursServices cs = new CoursServices();
        AfficherQuestions();
        getQuestions();
        id_C.setItems(FXCollections.observableArrayList(cs.AfficherCours()));
        BModifierCours.setVisible(false);
        anchorQuestion.setVisible(true);
        anchorCours.setVisible(false);
        crudQuestion.setVisible(true);
        crudCours.setVisible(false);
        iconQuestion.toFront();
        iconCours.toBack();
        iconReponse.toBack();
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.2), anchorQuestion);
        translate.setToX(-190);
        translate.play();
    }

    public void AfficherTableQuestion() {
        iconQuestion.setOnMousePressed((event) -> {
            TableQuestions();
        });

    }

    public void IconChange() {
        iconCours.setOnMouseEntered((event) -> {
            iconCours.setScaleX(1.1);
            iconCours.setScaleY(1.1);
        });
        iconCours.setOnMouseExited((event) -> {
            iconCours.setScaleX(1);
            iconCours.setScaleY(1);
        });
        iconQuestion.setOnMouseEntered((event) -> {
            iconQuestion.setScaleX(1.1);
            iconQuestion.setScaleY(1.1);
        });
        iconQuestion.setOnMouseExited((event) -> {
            iconQuestion.setScaleX(1);
            iconQuestion.setScaleY(1);
        });
        iconReponse.setOnMouseEntered((event) -> {
            iconReponse.setScaleX(1.1);
            iconReponse.setScaleY(1.1);
        });
        iconReponse.setOnMouseExited((event) -> {
            iconReponse.setScaleX(1);
            iconReponse.setScaleY(1);
        });
        exit.setOnMouseEntered((event) -> {
            exit.setScaleX(1.1);
            exit.setScaleY(1.1);
        });
        exit.setOnMouseExited((event) -> {
            exit.setScaleX(1);
            exit.setScaleY(1);
        });
    }

    public void exit() {
        exit.setOnMouseClicked((event) -> {
            System.exit(0);
        });
    }

    public void controleCours() {

        niv.setOnKeyReleased((event) -> {
            Pattern p = Pattern.compile("[^1-6 ]");

            if ((p.matcher(niv.getText()).find())) {
                btn.setDisable(true);
                BModifierCours.setDisable(true);
                LabelNiveau.setText("Entre 1 et 6");
                imgniv.setVisible(true);
            } else {
                btn.setDisable(false);
                BModifierCours.setDisable(false);
                imgniv.setVisible(false);
                LabelNiveau.setText("Niveau");
            }

        });

    }

    @FXML
    public String File() {

        FileChooser fc = new FileChooser();

        fc.setInitialDirectory(new File("C:\\Users\\ASUS\\Documents\\NetBeansProjects\\Cours\\Cours\\src\\cours\\PDF\\"));

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Pdf (*.pdf)", "*.pdf");
        fc.getExtensionFilters().add(extFilter);

        fc.setTitle("Choisir Un cours");
        File f = fc.showOpenDialog(null);

        String fp = f.getAbsolutePath();
        System.out.println(fp);
        int c = 0;
        for (int i = 0; i < fp.length(); i++) {
            if (fp.charAt(i) == '\\') {
                c++;

            }

        }
        String File = fp.replace("C:\\Users\\ASUS\\Documents\\NetBeansProjects\\Cours\\Cours\\src\\cours\\PDF\\", "");
        if ((fp.equals("C:\\Users\\ASUS\\Documents\\NetBeansProjects\\Cours\\Cours\\src\\cours\\PDF\\" + File) && (c == 10))) {

            FilePath.setText(File);
            LabelNiveau1.setText("Support : ");

            return File;
        } else {
            LabelNiveau1.setText("Dossier Pdf !");
        }
        return null;

    }

    public void TelechargerPdf() {
        table.setOnMouseClicked((r) -> {
            if (r.getButton() == MouseButton.MIDDLE) {

                String x = table.getSelectionModel().getSelectedItem().getContract().replace("/", "////");

                Document doc = new Document(x);

                Page pdf = (Page) doc.getPages().add();
                String f = x.replaceAll("C:////Users////ASUS////Documents////NetBeansProjects////Cours////Cours////src////cours////PDF////", "");
                doc.save("C:/Users/ASUS/Desktop/" + f);

            }
        });
    }

    public void test() {
        table.setOnMouseClicked((er) -> {

            if (er.getButton() == MouseButton.PRIMARY) {
                if (er.getClickCount() == 2) {

                    System.out.println("non");
                }
            }
        });
    }

    @FXML
    public void AjouterReponse() {
        CoursServices cs = new CoursServices();
     
        Reponse r = new Reponse(repR.getText(), etatrep.getSelectionModel().getSelectedItem(), id_QR.getSelectionModel().getSelectedItem(), id_CR.getSelectionModel().getSelectedItem());
        cs.AjouterReponse(r);
        tableQuestion.getItems().clear();
        AfficherReponse();
                clearValues();


    }

    public void AfficherReponse() {
        CoursServices cs = new CoursServices();
        ObservableList<Reponse> rep = FXCollections.observableArrayList(cs.AfficherReponse());

        REPONSE.setCellValueFactory(new PropertyValueFactory<>("Reponse"));
        ETAT.setCellValueFactory(new PropertyValueFactory<>("etat"));
        COURS.setCellValueFactory((CellDataFeatures<Reponse, String> param) -> new SimpleStringProperty(param.getValue().getC().getLibelle()));
        QUESTION.setCellValueFactory((CellDataFeatures<Reponse, String> param) -> new SimpleStringProperty(param.getValue().getQ().getQuestion()));

        tableReponse.setItems(rep);
    }
    public Reponse getReponse() {
        CoursServices cs = new CoursServices();
        tableReponse.setOnMousePressed((e) -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                if (e.getClickCount() == 2) {
                    BModifierReponse.setVisible(true);
                    Reponse x = tableReponse.getSelectionModel().getSelectedItem();
                    id_CR.setValue(x.getC());
                    id_QR.setValue(x.getQ());
                    etatrep.setValue(x.getEtat());
                    repR.setText(x.getReponse());
                     id_CR.setOnAction(t->{
        id_QR.setItems(FXCollections.observableArrayList(cs.AfficherQuestionParCours(id_CR.getValue())));
        });

                }
            }
        });
        Reponse x = tableReponse.getSelectionModel().getSelectedItem();
        return x;
    }

    @FXML
    public void ModifierReponse() {
        CoursServices cs = new CoursServices();
       
        try {

            getReponse().setC(id_CR.getValue());
            getReponse().setQ(id_QR.getValue());
            getReponse().setReponse(repR.getText());
            getReponse().setEtat(etatrep.getValue());
            cs.ModifierReponse(getReponse());
            BModifierReponse.setVisible(false);
            BAjouterReponse.setVisible(true);
            
            question.clear();
            tableReponse.getItems().clear();
            AfficherReponse();
                    clearValues();

        } catch (Exception e) {

            System.out.println("Oops Something Went Wrong on Modifier Reponse ...");
        }
    }
   
    public void SupprimerReponse() {
        CoursServices cs = new CoursServices();
      tableReponse.setOnMouseClicked(t->{
      if(t.getButton()==MouseButton.SECONDARY){
      Reponse x = tableReponse.getSelectionModel().getSelectedItem();
            
            Alert alert = new Alert(AlertType.WARNING, "Voulez vous supprimer " + x.getReponse()+ " ?", ButtonType.OK, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                cs.SupprimerReponse(x);
                tableReponse.getItems().clear();
                AfficherReponse();
            }
      
      }
      });
            
        }
    public void clearValues(){
        mat.getItems().clear();
        niv.clear();
        FilePath.clear();
        id_CR.getItems().clear();
        id_QR.getItems().clear();
        repR.clear();
        etatrep.getItems().clear();
        id_C.getItems().clear();
        question.clear();
        date.setValue(null);
                
    }

    }


