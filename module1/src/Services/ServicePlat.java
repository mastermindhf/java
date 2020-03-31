/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entities.Plat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import utils.ConnectionClass;

/**
 *
 * @author admin
 */
public class ServicePlat {

    private Statement ste;
    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();

    public void ajouter(Plat e) throws SQLException {

        ste = connection.createStatement();
       
        String requeteInsert = "INSERT INTO plat(calories,likes,dislikes,res,libelle,description) VALUES ('" + e.getCalories() + "','" + e.getLikes()+"','" + e.getDislikes()+"','" + e.getRes()+"','" + e.getLibelle() + "','" + e.getDescription() + "');";
        ste.executeUpdate(requeteInsert);
        JOptionPane.showMessageDialog(null, "plat ajouté avec succes");

    }

    public List<Plat> read() throws SQLException {
      List<Plat> list = new ArrayList<Plat>();
      Statement st = connection.createStatement();
      //execute query and store result in resultset
      String req = "(SELECT * FROM plat)";
      ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Plat p = new Plat();
           
            p.setCalories(rs.getInt("calories"));
            p.setDescription(rs.getString("description"));
            p.setLibelle(rs.getString("libelle"));
             p.setId(rs.getInt("id"));
            list.add(p);
        }

        return list;

    }

    public void supprimer(Plat x)  {

       
        //JOptionPane.showMessageDialog(null, "plat supp avec succes");
       try{
            ste = connection.createStatement();
        String req2 =
        "delete from plat where id=?";
        PreparedStatement ps =
        connection.prepareStatement(req2);
        ps.setInt(1, x.getId());
        ps.execute();
        //del=ste.executeUpdate("delete from materievente where refmat="+t.getRef());
       }catch (SQLException ex) {
              Logger.getLogger(Plat.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    public List<Plat> rechercher(String nomm) throws SQLException {   //recherche par libelle
        List<Plat> list = new ArrayList<Plat>();
        Statement st = connection.createStatement();
        //execute query and store result in resultset
        String req = "select * from plat where `libelle` = '" + nomm + "' ";
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Plat p = new Plat();
            p.setId(rs.getInt("id"));
            p.setCalories(rs.getInt("calories"));
            p.setDescription(rs.getString("description"));
            p.setLibelle(rs.getString("libelle"));
            list.add(p);
        }
        return list;

    }
}
