/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cours.GUI;

import com.sun.javaws.Globals;
import cours.Entities.Cours;
import cours.Entities.Matiere;
import cours.Entities.Question;
import cours.Entities.date;
import cours.Services.CoursServices;
import java.awt.Color;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.effect.GaussianBlur;
import static javafx.scene.input.KeyCode.S;
import static javafx.scene.input.KeyCode.T;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
    private Button btn;
    @FXML
    private TableColumn<Cours, String> IDC;
    @FXML
    private TableColumn<Cours, String> LIBC;
    @FXML
    private TableColumn<Cours, String> NIVC;
    @FXML
    private TextField lib;
    @FXML
    private TextField niv;
    private ComboBox<Cours> id_C;
    private TextField question;
    private TableView<Question> tableQuestion;
    private TableColumn<Question, String> IDQ;
    private TableColumn<Question, String> COURSQ;
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
    private Button BModifierCours;
    private DatePicker date;
    private TableColumn<Cours, Date> DATE;
    @FXML
    private TableColumn<Cours, String> PDF;
    @FXML
    private AnchorPane crudCours;
    @FXML
    private AnchorPane anchorCours;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        AfficherCours();
        AfficherQuestions();
        anchorCours.setVisible(false);
//        ModifierQuestion();
        ModifierCours();
        //getQuestions();
        getCours();

        IDC.setVisible(false);
//        BModifierQuestion.setVisible(false);

        CoursServices cs = new CoursServices();
        ObservableList<Cours> Cours = FXCollections.observableArrayList(cs.AfficherCours());
//        id_C.setItems(Cours);
        mat.setItems(FXCollections.observableArrayList(cs.AfficherMatiere()));
        BModifierCours.setVisible(false);
       
           
    }
   

    @FXML
    public void AjouterCours(ActionEvent event) {
        CoursServices cs = new CoursServices();
        // ObservableList<Cours> cours = FXCollections.observableArrayList(cs.AfficherCours());
        String Lib = lib.getText();
        String Niv = niv.getText();
 
        
        Cours c = new Cours(mat.getSelectionModel().getSelectedItem(), Lib, Niv, "C:\\Users\\ASUS\\Downloads\\Documents\\Chapitres-v-a-continues-lois usuelles.pdf");
        Pattern p = Pattern.compile("[^A-Za-z0-9 ]");
        Matcher m = p.matcher(Lib);
        boolean b = m.find();
        if ((!Lib.isEmpty()) && (!b)) {
            if (cs.AjouterCours(c)) {
                table.getItems().clear();
                AfficherCours();

            }
        } else if (Lib.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR, "Libelle vide", ButtonType.OK, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();
        } else if (b) {
            Alert alert = new Alert(AlertType.ERROR, "Libelle contient des caracteres speciaux", ButtonType.OK, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();
        }
    }

    public void AfficherCours() {
        CoursServices cs = new CoursServices();
        ObservableList<Cours> cours = FXCollections.observableArrayList(cs.AfficherCours());
        table.setItems(cours);
        IDC.setCellValueFactory(new PropertyValueFactory<>("id"));
        LIBC.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        NIVC.setCellValueFactory(new PropertyValueFactory<>("niveau"));
        PDF.setCellValueFactory(new PropertyValueFactory<>("contract"));
        MATC.setCellValueFactory((CellDataFeatures<Cours, String> param) -> new SimpleStringProperty(param.getValue().getM().getLibelle()));
    }

    @FXML
    public void SupprimerCours(MouseEvent event) {
        CoursServices cs = new CoursServices();

        if (event.isSecondaryButtonDown()) {
            Cours x = table.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(AlertType.NONE, "Voulez vous supprimer " + x.getLibelle() + " " + x.getNiveau() + " ?", ButtonType.OK, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                cs.SupprimerCours(x);
                Refresh();
            }

        }
    }

    public Cours getCours() {
        CoursServices cs = new CoursServices();
        table.setOnMouseClicked((e) -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                if (e.getClickCount() == 2) {
                    BModifierCours.setVisible(true);
                    Cours x = table.getSelectionModel().getSelectedItem();
                    mat.setValue(x.getM());
                    lib.setText(x.getLibelle());
                    niv.setText(x.getNiveau());
                }
            }
        });
        Cours x = table.getSelectionModel().getSelectedItem();
        return x;
    }

    @FXML
    public void ModifierCours() {
        CoursServices cs = new CoursServices();

        try {

            getCours().setM(mat.getSelectionModel().getSelectedItem());
            getCours().setLibelle(lib.getText());
            getCours().setNiveau(niv.getText());
            getCours().setContract(null);
            cs.ModifierCours(getCours());
            BModifierCours.setVisible(false);
            btn.setVisible(true);

        } catch (Exception e) {

            System.out.println("Oops Something Went Wrong on Modifier Cours ...");
        }
    }

    private void Refresh() {
        table.getItems().clear();
        AfficherCours();
        tableQuestion.getItems().clear();

        AfficherQuestions();
    }

    private void AjouterQuestion(ActionEvent event) {
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
                Refresh();

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
        try {
            CoursServices cs = new CoursServices();
            ObservableList<Question> questions = FXCollections.observableArrayList(cs.AfficherQuestion());
            tableQuestion.setItems(questions);
            IDQ.setCellValueFactory(new PropertyValueFactory<>("id"));
            // COURSQ.setCellValueFactory(new PropertyValueFactory<>("cour"));
            COURSQ.setCellValueFactory((CellDataFeatures<Question, String> param) -> new SimpleStringProperty(param.getValue().getCour().getLibelle()));
            DATE.setCellValueFactory(new PropertyValueFactory<>("date"));
            QUESTIONQ.setCellValueFactory(new PropertyValueFactory<>("Question"));

        } catch (NullPointerException e) {
            System.out.println("oops");
        }
    }

    public void SupprimerQuestion(MouseEvent event) {
        CoursServices cs = new CoursServices();
        if (event.isSecondaryButtonDown()) {
            Question x = tableQuestion.getSelectionModel().getSelectedItem();
            System.out.println(x.getId());
            Alert alert = new Alert(AlertType.WARNING, "Voulez vous supprimer " + x.getQuestion() + " ?", ButtonType.OK, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                cs.SupprimerQuestion(x);
                Refresh();
            }
        }

    }
/*
    public Question getQuestions() {
        CoursServices cs = new CoursServices();
        tableQuestion.setOnMouseClicked((e) -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                if (e.getClickCount() == 2) {
                    BModifierQuestion.setVisible(true);
                    Question x = tableQuestion.getSelectionModel().getSelectedItem();
                    id_C.setValue(x.getCour());
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
/*
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
        } catch (Exception e) {

            System.out.println("Oops Something Went Wrong on Modifier Question ...");
        }
    }

    @FXML
    private String pdf() {
        FileChooser f = new FileChooser();
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("Pdf", "*.pdf"));
       File fc=f.showOpenDialog(null);
       if(f!=null){
           pdf.setText("OK!"+fc.getAbsolutePath());
       }
        System.out.println(fc.getAbsoluteFile());
       return fc.getAbsolutePath();
       
    }
*/

    @FXML
    private void AfficherTableCours(MouseEvent event) {
        anchorCours.setVisible(true);
         GaussianBlur blur = new GaussianBlur(30); 
         GaussianBlur Noblur=new GaussianBlur(0);
        
        TranslateTransition translate= new TranslateTransition(Duration.seconds(0.2), anchorCours);
        translate.setToX(anchorCours.getLayoutX()-(anchorCours.getPrefWidth()));
        translate.play();
        anchorCours.setEffect(blur);
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
             anchorCours.setEffect(Noblur);
                System.out.println("hi");
            }
        }, 200);
        
    }
    
}
