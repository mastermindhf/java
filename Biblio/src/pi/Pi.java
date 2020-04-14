/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi;


import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Smirani
 */
public class Pi extends Application {
    
    public static int Id_user_connecte;
    
     private FXMLLoader loader;
    
    @Override
    public void start(Stage primaryStage) {
      try {
            Parent root = FXMLLoader
        .load(getClass().getResource("/gui/Login.fxml"));
            
            Scene scene = new Scene(root);
            
           // primaryStage.setTitle("Hello World!");
           
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
       
        //Screen screen = Screen.getPrimary();
        
           
            primaryStage.fullScreenProperty();
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
