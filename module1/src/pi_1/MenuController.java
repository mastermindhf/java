/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi_1;

import entities.Dessert;
import entities.Jour;
import entities.Menu;
import entities.Plat;
import entities.Semaine;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.time.DayOfWeek;
import java.time.LocalDate;

import java.time.temporal.TemporalAdjusters;

import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javafx.scene.control.Label;

import javax.swing.JOptionPane;
import javax.swing.text.DateFormatter;
import services.ServiceDessert;
import services.ServiceMenu;
import services.ServicePlat;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class MenuController implements Initializable {

    @FXML
    private ComboBox<Plat> combo_menu_plat;
    @FXML
    private Button affecter_plat_menu;
    @FXML
    private ComboBox<Jour> combo_menu_jour;
    @FXML
    private ComboBox<Date> combo_menu_semaine;
    @FXML
    private ComboBox<Dessert> combo_menu_dessert;
    @FXML
    private Label datelabel;
 
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {





           ServicePlat sp = new ServicePlat();
           ServiceMenu sm = new ServiceMenu();
           ServiceDessert sd = new ServiceDessert();
            LocalDate  now = LocalDate.now();
            LocalDate  nextWed = now.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
       
            Date thisWeek=java.sql.Date.valueOf(now);
            Date nextWeek=java.sql.Date.valueOf(nextWed);

			
        try {
            List <Plat> plats = sp.read();
            List <Jour> jours = sm.read();
            List <Dessert> desserts = sd.read();
            
          
                       combo_menu_plat.getItems().addAll(plats);
                      
                       combo_menu_dessert.getItems().addAll(desserts);
                       combo_menu_semaine.getItems().addAll(thisWeek);
                       combo_menu_jour.getItems().addAll(jours);
                       combo_menu_plat.getSelectionModel().selectFirst();
                       combo_menu_semaine.getSelectionModel().selectFirst();
                       combo_menu_jour.getSelectionModel().selectFirst();
                       combo_menu_dessert.getSelectionModel().selectFirst();
                       
                     
                       
        } catch (SQLException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }

               
       
    }    

    @FXML
    private void affecter_plat(ActionEvent event) throws SQLException {
              LocalDate  now = LocalDate.now();
            LocalDate  nextWed = now.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
       
            Date thisWeek=java.sql.Date.valueOf(now);
            Date nextWeek=java.sql.Date.valueOf(nextWed);
        
        Plat e = combo_menu_plat.getValue();
         Date debut =combo_menu_semaine.getValue();
     //   Date fin= combo_menu_semTest.getValue();
        Dessert d = combo_menu_dessert.getValue();
        Jour j = combo_menu_jour.getValue();
  
                        
    
        int calories_tot=e.getCalories()+d.getCalories();
       
        Menu m = new Menu();
     
        m.setDessert(d.getId());
        m.setJour(j.getId());
        m.setPlat(e.getId());
    
        m.setCalories(calories_tot);
  
        ServiceMenu sm = new ServiceMenu();
    
        
  
  
        Semaine s = new Semaine("helloo",debut, nextWeek);
 
       
       
        
       
     
    
        
     
       
          sm.ajouter(s);
             JOptionPane.showMessageDialog(null, nextWeek);
           
    
    }

  

}
