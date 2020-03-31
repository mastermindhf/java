/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cours.Services;

import cours.Entities.Cours;
import cours.Entities.Matiere;
import cours.Entities.Question;
import cours.Entities.date;
import cours.Utils.Cnx;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class CoursServices {

    Connection cnx = Cnx.getInstance().getConnection();

    /**
     *
     */
    /**
     *
     * @param c
     * @return
     */
    public boolean AjouterCours(Cours c) {
        String requete = "insert into cours (libelle,niveau,id_m,contract) values('" + c.getLibelle() + "','" + c.getNiveau() + "'," + c.getM()
                .getId() + ",'" + c.getContract() + "')";
        boolean x = false;
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            x = true;
        } catch (SQLException e) {
        }
        return x;
    }

    public ArrayList<Cours> AfficherCours() {
        ArrayList<Cours> cours = new ArrayList<>();
        String requete = "(select * from cours)";
        try {
            Statement st = cnx.createStatement();
            ResultSet x = st.executeQuery(requete);
            while (x.next()) {
                cours.add(recupereResultat(x));
            }
        } catch (SQLException ex) {
            System.out.println("ok");
        }
        return cours;
    }

    public void SupprimerCours(Cours c) {

        String requete = "delete from cours where id = " + c.getId() + "";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException e) {
        }
    }

    public void ModifierCours(Cours c) {

        String requete = "UPDATE `cours` SET `contract` = '" + c.getContract() + "', `niveau` = '" + c.getNiveau() + "', `id_m` = '" + c.getM().getId() + "', `Libelle` = '" + c.getLibelle() + "' WHERE `cours`.`id` = '" + c.getId() + "'";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException e) {
        }
    }

    public boolean AjouterQuestion(Question q) {
        String requete = "insert into question (id_C,Question,date) values('" + q.getCour().getId() + "','" + q.getQuestion() + "','" + q.getDate() + "')";
        boolean x = false;
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            x = true;
        } catch (SQLException e) {
        }
        return x;
    }

    public ArrayList<Question> AfficherQuestion() {
        ArrayList<Question> questions = new ArrayList<>();
        String requete = "(SELECT * FROM question )";

        try {
            Statement st = cnx.createStatement();
            ResultSet x = st.executeQuery(requete);
            while (x.next()) {
                questions.add(recupereResultatQ(x));
            }
        } catch (SQLException ex) {
        }
        return questions;
    }

    public void SupprimerQuestion(Question q) {

        String requete = "delete from question where id = " + q.getId() + "";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException e) {
        }
    }

    public void ModifierQuestion(Question q) {
        String requete = "update question set Question= '" + q.getQuestion() + "' , id_C=" + q.getCour().getId() + ",date='" + q.getDate() + "' where id = " + q.getId() + "";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException e) {
        }
    }

    public Cours recupereResultat(ResultSet x) {
        Cours c = new Cours();
        try {
            c.setId(x.getInt("id"));
            c.setLibelle(x.getString("libelle"));
            c.setNiveau(x.getString("niveau"));
            c.setM(retournerMatiere(x.getInt("id_m")));
            c.setContract(x.getString("contract"));
        } catch (SQLException ex) {

        }

        return c;
    }

    public Question recupereResultatQ(ResultSet x) {
        Question q = new Question();
        try {
            q.setId(x.getInt("id"));
            q.setCour(retournerCours(x.getInt("id_C")));
            q.setQuestion(x.getString("Question"));
            q.setDate(x.getDate("date"));

        } catch (SQLException ex) {

        }

        return q;
    }

    public Matiere recupereResultatM(ResultSet x) {
        Matiere q = new Matiere();
        try {
            q.setId(x.getInt("id"));
            q.setLibelle(x.getString("Libelle"));

        } catch (SQLException ex) {

        }

        return q;
    }

    public Matiere retournerMatiere(int id) {
        try {
            PreparedStatement pt = cnx.prepareStatement("select * from matiere where id=?");
            pt.setInt(1, id);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                return recupereResultatM(rs);
            }
        } catch (SQLException ex) {

        }
        return null;
    }

    public ArrayList<Matiere> AfficherMatiere() {
        ArrayList<Matiere> matiere = new ArrayList<>();
        String requete = "(SELECT * FROM matiere )";

        try {
            Statement st = cnx.createStatement();
            ResultSet x = st.executeQuery(requete);
            while (x.next()) {
                matiere.add(recupereResultatM(x));
            }
        } catch (SQLException ex) {
        }
        return matiere;
    }

    public Cours retournerCours(int id) {
        try {
            PreparedStatement pt = cnx.prepareStatement("select * from cours where id=?");
            pt.setInt(1, id);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                return recupereResultat(rs);
            }
        } catch (SQLException ex) {

        }
        return null;
    }

    public boolean ajouterDate(date q) {
        String requete = "insert into date (date) values('" + q.getDate() + "')";
        boolean x = false;
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            x = true;
        } catch (SQLException e) {
        }
        return x;
    }

}
