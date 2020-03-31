/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DB.DataSource;
import entity.Type;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Smirani
 */
public class TypeService {
    
    Connection cn = DataSource.getInstance().getCnx();
    
    public boolean AjouterType(Type t) {
       
        String requete = "insert into type (libelle) values('" + t.getLibelle()+"')";
        System.out.println(requete);
        boolean x = false;
        try {
            Statement st = cn.createStatement();
            st.executeUpdate(requete);
            x=true;
           // etat.setText("Données Insérées avec success");
           // etat.setTextFill(Color.GREEN);
            //refresh();
        } catch (SQLException e) {
            //etat.setText("Impossible d'insérer ces données");
            //etat.setTextFill(Color.RED);
           
        }
return x;
    }
    public ArrayList<Type> AfficherType(){
         ArrayList<Type> types= new ArrayList<>();
        String requete = "(select * from type)";
        try {
            
            Statement st = cn.createStatement();
            ResultSet x = st.executeQuery(requete);
            while (x.next()) {
                Type t = new Type();
                t.setIdL(x.getInt("idL"));
                t.setLibelle(x.getString("libelle"));
                types.add(t);

              

            }
            //System.out.println(cours);
        } catch (SQLException ex) {
            System.out.println("ok");
        }
        return types;
    }
    public void SupprimerType(Type t){
        
        String requete = "delete from type where idL = " + t.getIdL() + "";
            try {
                Statement st = cn.createStatement();
                st.executeUpdate(requete);
                

            } catch (SQLException e) {
               
            }
    }
    public void ModifierType(Type c){
         String requete = "update type set libelle = '" + c.getLibelle() +  "' where idL = " + c.getIdL() + "";
        try {
            Statement st = cn.createStatement();
            st.executeUpdate(requete);
          

        } catch (SQLException e) {
            
        }
    }
    
}
