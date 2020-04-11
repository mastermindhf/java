/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author farou
 */
public class Home extends Application{
public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
Parent root = FXMLLoader
                .load(getClass().getResource("CrudReclamation.fxml"));

            Scene scene = new Scene(root);
            primaryStage.setTitle("SchoolNet");
              primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("z.png")));
            primaryStage.setScene(scene);
            primaryStage.show();    }
    
}
