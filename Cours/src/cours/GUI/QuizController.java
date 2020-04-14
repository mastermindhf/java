/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cours.GUI;

import com.aspose.pdf.Document;
import com.aspose.pdf.Page;
import cours.Entities.Cours;
import cours.Entities.NoteQuiz;
import cours.Entities.Question;
import cours.Entities.Reponse;
import cours.Entities.User;
import cours.Services.CoursServices;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class QuizController implements Initializable {

    @FXML
    private ComboBox<Cours> Cours;
    @FXML
    private ComboBox<Question> Questions;
    @FXML
    private TableView<Reponse> tableReponses;
    @FXML
    private TableColumn<Reponse, String> Reponses;
    @FXML
    private Label labelCours;
    @FXML
    private Button btn;
    @FXML
    private TableView<Cours> tableCours;
    @FXML
    private TableColumn<Cours, String> colcours;
    @FXML
    private Group iconCours;
    @FXML
    private Circle coursCircle;
    @FXML
    private ImageView coursIcon;
    @FXML
    private Group iconReponse;
    @FXML
    private Group exit;
    @FXML
    private AnchorPane rep;
    @FXML
    private AnchorPane tablerep;
    @FXML
    private AnchorPane tablecou;
    @FXML
    private Label courLabel;
    @FXML
    private Label note;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        GetCours();
        IconChange();
        exit();
        AfficherTableReponse();
        AfficherTableCours();
        tablecou.setVisible(false);
        tablerep.setVisible(false);
        TelechargerPdf();
        
      
    }


    private void GetCours() {
        CoursServices cs = new CoursServices();
        ObservableList<Cours> cours = FXCollections.observableArrayList(cs.AfficherCours());
        Cours.setItems(cours);
        LoginController lg = LoginController.getInstance();
        int u = lg.getUserc().getId();
        Cours.setOnAction(t -> {
            int x = Cours.getSelectionModel().getSelectedItem().getId();
            Questions.setItems(FXCollections.observableArrayList(cs.QuestionSpec(x)));

         courLabel.setText(Cours.getValue().getLibelle());
           float NotedeCours=cs.noteTotal(Cours.getValue().getId(), u);
           String Lanote=Float.toString(NotedeCours);
            this.note.setText(Lanote);
            Questions.setItems(FXCollections.observableArrayList(cs.AfficherQuestionParCours(Cours.getValue())));
            Questions.setOnAction(c -> {

                try {
                    int r = Questions.getSelectionModel().getSelectedItem().getId();
                    ObservableList<Reponse> rep = FXCollections.observableArrayList(cs.ReponseSpec(r));
                    Reponses.setCellValueFactory(new PropertyValueFactory<>("Reponse"));
                    tableReponses.setItems(rep);
                    labelCours.setText(Questions.getValue().getQuestion());
                    cs.delete(x, r, u);
                    cs.deleteF(x, u);
                    cs.getZero(x, r, u);
                    cs.getZeroF(x, u);
                } catch (Exception e) {
                    System.out.println("");
                }

            });
        });

    }

    @FXML
    public void noter(ActionEvent event) {
        try {
            CoursServices cs = new CoursServices();

            LoginController lg = LoginController.getInstance();
            User u = lg.getUserc();

            NoteQuiz nq = new NoteQuiz();
            float score = cs.getNoteSpec(Cours.getValue().getId(), Questions.getValue().getId(), u.getId()).getNote();
            System.out.println("score : " + score);
            Reponse x = tableReponses.getSelectionModel().getSelectedItem();

            float note = 0;
            int nbTrue = cs.ReponseSpecTrue(x.getQ().getId());
            System.out.println(x);
            if (x.getEtat().equals("vrai")) {
                note += 1;

            } else {
                score = 0;
            }

            score += (note / nbTrue);
            nq.setId_C(x.getC().getId());
            nq.setId_q(x.getQ().getId());
            nq.setId_u(u.getId());
            nq.setNote(score);
            cs.Noter(nq);
           
            System.out.println(score);
            tableReponses.getItems().remove(x);
            float notefinal=cs.noteTotal(Cours.getValue().getId(), u.getId());
            cs.notefinal(Cours.getValue().getId(), u.getId(), notefinal);
        } catch (Exception ex) {

        }

    }


    public void AfficherCours() {
        CoursServices cs = new CoursServices();
        ObservableList<Cours> CoursList = FXCollections.observableArrayList(cs.AfficherCours());

        colcours.setCellValueFactory(new PropertyValueFactory<>("libelle"));

        tableCours.setItems(CoursList);

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

    public void TableCours() {
        AfficherCours();
        System.out.println("hi");

        iconCours.toFront();

        iconReponse.toBack();
        tablecou.setVisible(true);
        tablerep.setVisible(false);
        rep.setVisible(false);
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.2), tablecou);
        translate.setToX(896);
        translate.play();

    }

    public void AfficherTableCours() {
        iconCours.setOnMousePressed((event) -> {
            TableCours();
        });

    }

    public void TableReponse() {

        iconReponse.toFront();
        iconCours.toBack();
        tablecou.setVisible(false);
        tablerep.setVisible(true);
        rep.setVisible(true);
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.2), tablerep);
        translate.setToX(-175);
        translate.play();
    }

    public void AfficherTableReponse() {
        iconReponse.setOnMousePressed((event) -> {
            TableReponse();
        });

    }
     public void TelechargerPdf() {
        tableCours.setOnMouseClicked((r) -> {
            if (r.getButton() == MouseButton.PRIMARY) {
if(r.getClickCount()==2){
                String x = tableCours.getSelectionModel().getSelectedItem().getContract().replace("/", "////");

                Document doc = new Document(x);

                Page pdf = (Page) doc.getPages().add();
                String f = x.replaceAll("C:////Users////ASUS////Documents////NetBeansProjects////Cours////Cours////src////cours////PDF////", "");
                doc.save("C:/Users/ASUS/Desktop/" + f);
}
            }
        });
    }
}
