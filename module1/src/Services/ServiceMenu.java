/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entities.Dessert;
import entities.Jour;
import entities.Menu;
import entities.Plat;
import entities.Semaine;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.time.Clock.system;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import utils.ConnectionClass;
import utils.*;

/**
 *
 * @author admin
 */
public class ServiceMenu {
    
    private Statement ste;
    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();
    
   public void ajouter(Semaine e) throws SQLException {

        ste = connection.createStatement();
        String requeteInsert = "INSERT INTO semaine(debut,libelle,fin) VALUES ('"+e.getDateDeb()+"','"+e.getLibelle()+"','"+e.getDateFin()+"' )";
       
        ste.executeUpdate(requeteInsert);
       

    }
      public void affecter( Menu m ) throws SQLException {

        ste = connection.createStatement();
  
     
       String requeteInsert = "INSERT INTO menu (semaine,plat,dessert,jour,calories) VALUES ('"+m.getSemaine()+"', '"+m.getPlat()+"','"+m.getDessert()+"','"+m.getJour()+"','"+m.getCalories()+"');";
       ste.executeUpdate(requeteInsert);
       JOptionPane.showMessageDialog(null,"ok");
        
    
    }
    
        public List<Jour> read() throws SQLException {
      List<Jour> list = new ArrayList<Jour>();
      Statement st = connection.createStatement();
      //execute query and store result in resultset
      String req = "(SELECT * FROM jourdelasemaine)";
      ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Jour j = new Jour();
           
            j.setId(rs.getInt("id"));
            j.setLibelle(rs.getString("libelle"));
          
            list.add(j);
        }

        return list;

    }
    
        
         public List<Semaine> read_sem() throws SQLException {
      List<Semaine> list = new ArrayList<Semaine>();
      Statement st = connection.createStatement();

   
      //execute query and store result in resultset
      String req = "(SELECT  *FROM semaine) ";
      ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Semaine j = new Semaine();
           
            j.setId(rs.getInt("id"));
            j.setLibelle(rs.getString("libelle"));
          
            list.add(j);
        }

        return list;

    }
    public boolean existe(Menu m ) throws SQLException {   //recherche par libelle
        List<Menu> list = new ArrayList<>();
        Statement st = connection.createStatement();
        //execute query and store result in resultset
        String req = "select * from menu ";

        ResultSet rs = st.executeQuery(req);
       
        while (rs.next()) {
        
    
           Menu p = new Menu();
            p.setId(rs.getInt("id"));
            p.setCalories(rs.getInt("calories"));
               p.setDessert(rs.getInt("dessert"));
                  p.setJour(rs.getInt("jour"));
                     p.setPlat(rs.getInt("plat"));
                        p.setSemaine(rs.getInt("semaine"));
                        
                        
              
           
                list.add(p);
                  
                  
                  
              
            
        }
      
     
  
    
       
         
     if(list.contains(m))
     {
         return true;
     }
         
            
            
        return false;
     

    

    }
}