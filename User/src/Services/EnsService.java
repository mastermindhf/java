/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Connection.DateSource;
import Entities.Ens;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author farou
 */
public class EnsService {
      private Connection cnx;
    private Statement st;
    private PreparedStatement pre;
     public EnsService(){
    cnx = DateSource.getInstance().getConnection();
    }
    
      public List<Ens> displayAll() {
        
       String req ="SELECT * FROM `user` WHERE roles LIKE '%\"ENSEIGNANT\"%'";
        List<Ens> list =new ArrayList<>();
         try {
             st=cnx.createStatement();
             ResultSet rs =st.executeQuery(req);
               while(rs.next())
               {  
                   
       
 list.add(new Ens(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email")));
               }
         } catch (SQLException ex) {
             Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
         }
        return list;  
    }
    
}
