/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Connection.DateSource;
import Entities.reclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Entities.ReponseReclamation;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author farou
 */
public class ReponseReclamationService {
    private Connection cnx;
    private Statement st;
    private PreparedStatement pre;
   
    public ReponseReclamationService(){
    cnx = DateSource.getInstance().getConnection();
    }
   
    
    public ArrayList<ReponseReclamation> AfficherReponseReclamation() {
        ArrayList<ReponseReclamation> rep = new ArrayList<>();
        String requete = "(SELECT * FROM reponsereclamation )";

        try {
            Statement st = cnx.createStatement();
            ResultSet x = st.executeQuery(requete);
            while (x.next()) {
                rep.add(recupereResultatReponse(x));
            }
        } catch (SQLException ex) {
        }
        return rep;
    }
    public ReponseReclamation recupereResultatReponse(ResultSet x) {
        ReponseReclamation q = new ReponseReclamation();
        try {
            q.setId_rep(x.getInt("id_rep"));
            q.setReprec(retournerReclamation(x.getInt("fk_id_reclamation")));
          
            
        } catch (SQLException ex) {

        }

        return q;
    }
    public reclamation recupereResultatReclamation(ResultSet x) {
        reclamation q = new reclamation();
        try {
            q.setId(x.getInt("id"));
          q.setNom(x.getString("Nom"));
           
             q.setPrenom(x.getString("Prenom"));
             q.setTel(x.getString("Tel"));
           
            q.setEmail(x.getString("Email"));
             q.setType(x.getString("Type"));
               q.setScreenshot(x.getString("Screenshot"));
                 q.setObjet(x.getString("Objet"));
                 q.setReclamation(x.getString("Reclamation"));
             
                 
                 
                 q.setDate(x.getDate("Date"));
                 
                       q.setEtat(x.getString("Etat"));
                       
        } catch (SQLException ex) {

        }

        return q;
    }
    
    public reclamation retournerReclamation(int id) {
        try {
            PreparedStatement pt = cnx.prepareStatement("select * from reclamation where id=?");
            pt.setInt(1, id);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                return recupereResultatReclamation(rs);
            }
        } catch (SQLException ex) {

        }
        return null;
    }
    
 
}

    
   /* public Map<reclamation, ReponseReclamation> readMessage() throws SQLException {
      //  int iduser = u.getIdUser();
        List<reclamation> arr = new ArrayList<>();
        List<ReponseReclamation> arr1 = new ArrayList<>();

        Map<reclamation, ReponseReclamation> mapM = new HashMap<>();
        st = cnx.createStatement();
        ResultSet rs = st.executeQuery("select * from reclamation INNER JOIN reponsereclamation ON reclamation.id = reponsereclamation.fk_id_reclamation   ");
        while (rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("Nom");
          

String prenom = rs.getString("Prenom");
String Tel = rs.getString("Tel");
String Email = rs.getString("Email");
String Type = rs.getString("Type");
String Screeshot = rs.getString("Screeshot");
String objet = rs.getString("Objet");
String Date = rs.getString("Date");
String rec = rs.getString("Reclamation");
           
   reclamation rc =new reclamation(id, nom, prenom, Tel, Email, Type, Screeshot, objet, Email, Date);
   int id_message = rs.getInt("id_rep");
   String Etat=rs.getString("Etat");
   int id_fk_rec=rs.getInt("fk_id_reclamation");
   
            ReponseReclamation m = new ReponseReclamation(id_message, Etat, id_fk_rec);

            arr1.add(m);
          

            arr.add(rc);
            mapM.put(rc, m);

        }
        return mapM;
    }
}
*/
//select r.Nom Etat from reclamation r INNER JOIN reponsereclamation ON id = reponsereclamation.fk_id_reclamation