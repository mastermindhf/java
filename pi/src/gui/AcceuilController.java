/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Smirani
 */
public class AcceuilController implements Initializable {

    private FXMLLoader loader;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void espaceBiblio(MouseEvent event) {
        try{
            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AfficheBiblioFront.fxml"));
            TypeController controller = new TypeController();
            loader.load();
            Scene scene = new Scene(loader.getRoot());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            
        }catch(IOException ex) {
            System.out.println(ex.getMessage());
        }        
    }
    
    @FXML
    private void exitApplication(MouseEvent event) {
        Platform.exit();
    }
    
}
