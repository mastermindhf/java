/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi_1;


import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author admin
 */
public class Pi_1 extends Application {
    
    @Override
     public void start(Stage primaryStage) throws IOException {
     Parent root = FXMLLoader
        .load(getClass().getResource("SideBar.fxml"));

       
            
     
        Scene scene = new Scene(root,1000, 600);

        

  
        primaryStage.setTitle("pi_1!");
        primaryStage.setScene(scene);
        primaryStage.show();
     }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
