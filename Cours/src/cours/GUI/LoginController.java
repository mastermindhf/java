/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cours.GUI;

import cours.Entities.User;
import cours.Services.MyServices;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private User loggedUser;

    private static LoginController instance;

    public static final Map<Integer, User> USERS = new HashMap<>();
    @FXML
    private Button btnlogin;

    User UserConneter;

    public static int Id_user_connecte;
    @FXML
    private TextField labelusername;
    @FXML
    private TextField labelpassword;
    @FXML
    private Button signup;

    public LoginController() {
        instance = this;
    }

    public static LoginController getInstance() {
        return instance;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLabelpassword(String labelpassword) {
        this.labelpassword.setText(labelpassword);
    }

    private void gotoLogin() {
        try {
            loadWindow(getClass().getResource("Login.fxml"), "Dashboard", null);

        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }

    public static void loadWindow(URL loc, String title, Stage parentStage) {
        try {
            Parent parent = FXMLLoader.load(loc);
            Stage stage = null;
            if (parentStage != null) {
                stage = parentStage;
            } else {
                stage = new Stage(StageStyle.DECORATED);
            }
            Scene scene = new Scene(parent);

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void userLogout() {
        loggedUser = null;
        gotoLogin();
    }

    @FXML
    public boolean handleLoginButtonAction(ActionEvent event) throws IOException {

        MyServices myServices = new MyServices();
        String mdp = labelpassword.getText();
        String username = labelusername.getText();

        String errorMessage = "";

        if (username == null || username.length() == 0) {
            errorMessage += "Username invalide \n";
        }

        if (mdp == null || mdp.length() == 0) {
            errorMessage += "Mot de passe invalide \n";
        }

        if (errorMessage.length() != 0) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error valeur");
            alert.setHeaderText("Invalide input");
            alert.setContentText(errorMessage);
            alert.show();
        }

        Boolean pas = myServices.verifierpassword(mdp, username);

        if (myServices.chercherUtilisateurBylogin(username) && pas == true/*BCrypt.checkpw(pword, user.getPassword())*/) {

            if (myServices.Gettype(username).equals("a:1:{i:0;s:5:\"ADMIN\";}")) {
                Parent root = FXMLLoader
                        .load(getClass().getResource("Quiz.fxml"));

                Scene scene = new Scene(root);
                Stage s = new Stage();
                s.setTitle("SchoolNet");
                s.setScene(scene);

                s.initStyle(StageStyle.TRANSPARENT);
                scene.setFill(Color.TRANSPARENT);

                s.show();
                Stage stage = (Stage) btnlogin.getScene().getWindow();
                
                stage.close();
             
     

            }
            return true;
        }

        return false;
    }
public User getUserc(){
    MyServices myServices = new MyServices();
    return myServices.chercherUtilisateurByUsername(labelusername.getText());
}
}
