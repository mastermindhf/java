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

    public ReponseReclamationService() {
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
        //System.out.println(rep);
        return rep;

    }

    public ReponseReclamation recupereResultatReponse(ResultSet x) {
        ReponseReclamation q = new ReponseReclamation();
        try {
            q.setId_rep(x.getInt("id_rep"));
            q.setReprec(retournerReclamation(x.getInt("fk_id_reclamation")));
           
        } catch (SQLException ex) {

        }
        System.out.println(q);
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
        System.out.println(q);
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

    public void insert(ReponseReclamation e) {
        try {

            PreparedStatement pre = cnx.prepareStatement("INSERT INTO `pi`.`reponsereclamation`( `fk_id_reclamation`,`repondre`) VALUES ('" + e.getReprec().getId() + "','" + e.getRepondre() + "')");
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

       public void ModifierEtatTraite(reclamation c) {

        String requete = "UPDATE `reclamation` SET `Etat` = '" +"Traite"+  "' WHERE `reclamation`.`id` = '" + c.getId() + "'";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException e) {
        }
    }
        public void ModifierEtatEncours(reclamation c) {

        String requete = "UPDATE `reclamation` SET `Etat` = '" +"En cours"+  "' WHERE `reclamation`.`id` = '" + c.getId() + "'";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException e) {
        }
        
    }
        
         public void ModifierEtatEnAttente(reclamation c) {

        String requete = "UPDATE `reclamation` SET `Etat` = '" +"En Attente"+  "' WHERE `reclamation`.`id` = '" + c.getId() + "'";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException e) {
        }
        
       
       
         }}
