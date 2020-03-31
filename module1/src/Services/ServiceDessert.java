/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entities.Dessert;
import entities.Jour;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.ConnectionClass;

/**
 *
 * @author admin
 */
public class ServiceDessert {
     private Statement ste;
    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();
     public List<Dessert> read() throws SQLException {
      List<Dessert> list = new ArrayList<Dessert>();
      Statement st = connection.createStatement();
      //execute query and store result in resultset
      String req = "(SELECT * FROM dessert)";
      ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Dessert d = new Dessert();
           
            d.setId(rs.getInt("id"));
            d.setLibelle(rs.getString("libelle"));
            d.setCalories(rs.getInt("calories"));
          
            list.add(d);
        }

        return list;

    }
    
    
}
