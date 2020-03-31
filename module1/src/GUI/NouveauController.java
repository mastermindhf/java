/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class NouveauController implements Initializable {

    @FXML
    private Button ajouter;
    @FXML
    private TextField libelle_plat;
    @FXML
    private TextField calories_plat;
    @FXML
    private TextArea description_plat;
    @FXML
    private Label label;
    @FXML
    private TableView<?> table_afficher_plat;
    @FXML
    private TableColumn<?, ?> libelle_plat_column;
    @FXML
    private TableColumn<?, ?> desc_plat_column;
    @FXML
    private TableColumn<?, ?> calories_plat_column;
    @FXML
    private Button load;
    @FXML
    private TextField recherche_plat_field;
    @FXML
    private Button recherche_plat_button;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void amal(ActionEvent event) {
    }

    @FXML
    private void load(ActionEvent event) {
    }

    @FXML
    private void rechercherPlat(ActionEvent event) {
    }
    
}
