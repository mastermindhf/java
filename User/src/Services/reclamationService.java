/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Connection.DateSource;
import Entities.reclamation;
import Entities.ReponseReclamation;
import GUI.MailApp;
import GUI.SendMail;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 *
 * @author farou
 */
public class reclamationService implements Iservice <reclamation >{
  private Connection cnx;
    private Statement st;
    private PreparedStatement pre;
   
    public reclamationService(){
    cnx = DateSource.getInstance().getConnection();
    }
    @Override
    public void insert(reclamation e) {
        ReponseReclamation r= new ReponseReclamation();
          try {
             
             String sql="INSERT INTO `pi`.`reclamation` ( `Nom`, `Prenom`, `Tel`,`Email`,`Type`,`Screenshot`,`Objet`,`Reclamation`,`Date`,`Etat`) VALUES ('"+e.getNom()+"','"+e.getPrenom()+"','"+e.getTel()+"','"+e.getEmail()+"','"+e.getType()+"','"+e.getScreenshot()+"','"+e.getObjet()+"','"+e.getReclamation()+"','"+java.time.LocalDate.now()+"','"+e.getEtat()+"');";
            
             PreparedStatement pre=cnx.prepareStatement(sql);
             
                pre.executeUpdate();
        
         } catch (SQLException ex) {
             Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

  

    @Override
    public void delete(int id) {
      try {
          PreparedStatement pt = cnx.prepareStatement("delete from reclamation where id =?");
          pt.setInt(1,id);
          pt.execute();
      } catch (SQLException ex) {
          Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

    @Override
    public List<reclamation> displayAll() {
        
       String req ="select * from reclamation";
        List<reclamation> list =new ArrayList<>();
         try {
             st=cnx.createStatement();
             ResultSet rs =st.executeQuery(req);
               while(rs.next())
               {  
                   
       
 list.add(new reclamation(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6), "file:C:\\3a16\\Image\\"+rs.getString("Screenshot"), rs.getString(8), rs.getString(9), rs.getDate(10),rs.getString(11)));
               
               }
         } catch (SQLException ex) {
             Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
         }
        return list;  
    }
    
    public reclamation recupereResultat(ResultSet x) {
        reclamation l = new reclamation();
        try {          
                l.setId(x.getInt("id"));
                l.setNom(x.getString("Nom"));
                l.setPrenom(x.getString("Prenom"));
                l.setTel(x.getString("Tel"));
              l.setEmail(x.getString("Email"));
                l.setObjet(x.getString("Objet"));
                l.setReclamation(x.getString("Reclamation"));
                l.setScreenshot("file:C:\\3a16\\Image\\"+x.getString("Screenshot"));
                
        } catch (SQLException ex) {

        }

        return l;
    }
    
    public List<reclamation> recherche_event(int id) {
            List<reclamation> arr=new ArrayList<>();
            PreparedStatement pre;
         try {
             pre = cnx.prepareStatement("SELECT * FROM reclamation WHERE  id LIKE ? ;");
              pre.setInt(1,id);
        ResultSet rs=pre.executeQuery();
        while (rs.next()) {                
                
       
          
         
        arr.add(new reclamation(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getDate(10),rs.getString(11)));
    
     }
         } catch (SQLException ex) {
             Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        
       
    return arr;
              
    }  
      public void update(int id,String Nom,String Prenom,String tel,String Email,String Type,String Screenshot,String Objet,String Reclamation,Date Date) {
      try {
             PreparedStatement pt = cnx.prepareStatement("update  reclamation set Nom = ?,Prenom = ?,Tel=?,Email=?,Type=?,Screenshot=?,Objet=?,Reclamation=?,Date=? where id = ?");
             
             pt.setString(1,Nom);
             pt.setString(2,Prenom);
      pt.setString(3, tel);
             pt.setString(4,Email);
             pt.setString(5,Type);
             pt.setString(6,Screenshot);
             pt.setString(7,Objet);
             pt.setString(8,Reclamation);
              pt.setString(9, "file:C:\\3a16\\Image\\"+Screenshot);
               pt.setInt(10,id);
               System.out.println("Reclamation Modifiée");
                 pt.executeUpdate();
                  
         } catch (SQLException ex) {
             Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
         }

    }
   /* public void ModifierReclamation(reclamation c) {

        String requete = "UPDATE `reclamation` SET `Nom` = '" + c.getNom()+ "', `Prenom` = '" + c.getPrenom()+ "', `Tel` = '" + c.getTel() + "', `Email` = '" + c.getEmail()+ "', `Type` = '" + c.getType()+ "', `Screenshot` = '" + c.getScreenshot()+ "', `Objet` = '" + c.getObjet()+ "', `Reclamation` = '" + c.getReclamation()+ "', `Date` = '" + java.time.LocalDate.now()+ "' WHERE `cours`.`id` = '" + c.getId() + "'";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException e) {
        }
    }*/
public void update(reclamation l) {

        String requete = "UPDATE reclamation SET Nom = '" + l.getNom() + "', Prenom = '" + l.getPrenom()+ "', Tel = '" + l.getTel()+ "', Email = '" + l.getEmail() + "', Type = '" + l.getTel()+ "', Screenshot = '" + l.getScreenshot()+ "', Objet = '" + l.getObjet()+ "', Reclamation = '" + l.getReclamation()+ "', Date = '" + java.time.LocalDate.now()+ "' WHERE reclamation.`id` = '" + l.getId() + "'";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
           displayAll();
        } catch (SQLException e) {
        }
        
    }
    
}
    
