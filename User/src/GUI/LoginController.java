/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.User;
import Services.MyServices;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author farou
 */
public class LoginController implements Initializable {

    @FXML
    private TextField labelusername;
    @FXML
    private TextField labelpassword;
    @FXML
    private Button signup;

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
       
    public LoginController() {
        instance = this;
    }

    public static LoginController getInstance() {
        return instance;
    }
    
    
      public User getLoggedUser() {
        return loggedUser;
    }
      
   
        public JFXTextField getLabelusername() {
        return (JFXTextField) labelusername;
    }

    public void setLabelusername(String labelusername) {
        this.labelusername.setText(labelusername);
    }

    public JFXPasswordField getLabelpassword() {
        return (JFXPasswordField) labelpassword;
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
        // TODO
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
    
    
                 public void userLogout(){
        loggedUser = null;
        gotoLogin();
    }

    @FXML
    private boolean handleLoginButtonAction(ActionEvent event) {
        
         MyServices myServices=new MyServices();
        String mdp=labelpassword.getText();
        String username=labelusername.getText();
        
 
         String errorMessage = "";

        if (username == null || username.length() == 0) {
            errorMessage += "Username invalide \n";
        }

        if (mdp  == null || mdp.length() == 0) {
            errorMessage += "Mot de passe invalide \n";
        }

        if (errorMessage.length() != 0) {

       Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error valeur");
            alert.setHeaderText("Invalide input");
            alert.setContentText(errorMessage);
            alert.show();
        } else {

           
           
      
        
       
        Boolean pas=myServices.verifierpassword(mdp, username);
          
       if (myServices.chercherUtilisateurBylogin(username) && pas==true/*BCrypt.checkpw(pword, user.getPassword())*/) {

          if (myServices.Gettype(username).equals("a:1:{i:0;s:5:\"ADMIN\";}")) {
                             System.out.println("admiiin");
                             
                    
        PauseTransition pauseTransition = new PauseTransition();
        pauseTransition.setDuration(Duration.seconds(3));
        pauseTransition.setOnFinished(ev -> {
                System.out.println("hello Admin");
                     
                /*************Pour Recupere ili connecter********************/
                
                User userConnecter=myServices.chercherUtilisateurByUsername(username);
                
                
                
                          System.out.println(userConnecter);
               
              
              
             
                loadWindow(getClass().getResource("AdminMenu.fxml"), "Dashboard", null);
                
                labelusername.getScene().getWindow().hide();
                    Notifications n = Notifications.create()
                        .title("Bienvenue")
                        .text("Vous étes connecté en tant que Administrateur!")
                        .graphic(null)
                        .position(Pos.TOP_CENTER)
                        .hideAfter(Duration.seconds(5));
           });
               pauseTransition.play();
            }
          
          
          if (myServices.Gettype(username).equals("a:1:{i:0;s:10:\"ENSEIGNANT\";}")) {
                                
        PauseTransition pauseTransition = new PauseTransition();
        pauseTransition.setDuration(Duration.seconds(3));
        pauseTransition.setOnFinished(ev -> {
                System.out.println("hello Membre");
                  User userConnecter=myServices.chercherUtilisateurByUsername(username);
              loggedUser = User.of(userConnecter.getId());
                loadWindow(getClass().getResource(""), "Dashboard", null);
                labelusername.getScene().getWindow().hide();
                Notifications n = Notifications.create()
                        .title("Bienvenue")
                        .text("Vous étes connecté en tant que ens!")
                        .graphic(null)
                        .position(Pos.TOP_CENTER)
                        .hideAfter(Duration.seconds(5));
                n.showInformation();
           
               });
               pauseTransition.play();
            }
          
          
           if (myServices.Gettype(username).equals("a:1:{i:0;s:6:\"PARENT\";}")) {
             
       
        PauseTransition pauseTransition = new PauseTransition();
        pauseTransition.setDuration(Duration.seconds(3));
        pauseTransition.setOnFinished(ev -> {
       

 
  
                System.out.println("hello Partenaire");
                  User userConnecter=myServices.chercherUtilisateurByUsername(username);
              loggedUser = User.of(userConnecter.getId());
                loadWindow(getClass().getResource("/pidev/bonplan/GUI/UserInterface.fxml"), "Dashboard", null);
                   
                labelusername.getScene().getWindow().hide();
           Notifications n = Notifications.create()
                        .title("Bienvenue")
                        .text("Vous étes connecté en tant que Parent!")
                        .graphic(null)
                        .position(Pos.TOP_CENTER)
                        .hideAfter(Duration.seconds(5));
                n.showInformation();
           
                  });
           pauseTransition.play();
            }       
       
      return true;
      
       }else
       {
       
              Notifications n = Notifications.create()
                        .title("Bienvenue")
                        .text("Username ou mot de passe invalide!")
                        .graphic(null)
                        .position(Pos.TOP_CENTER)
                        .hideAfter(Duration.seconds(5));
                n.showInformation();
           
         return false;
       
       }
         }
         return true;
}
}