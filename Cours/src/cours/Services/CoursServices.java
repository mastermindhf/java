/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cours.Services;

import cours.Entities.Cours;
import cours.Entities.Matiere;
import cours.Entities.NoteQuiz;
import cours.Entities.Question;
import cours.Entities.Reponse;
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
            System.out.println(e.getMessage());
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
            System.out.println(ex.getMessage());
           
            
        }
        return cours;
    }

    public boolean SupprimerCours(Cours c) {
        boolean x=false;
        String requete = "delete from cours where id = " + c.getId() + "";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            x=true;
            return x;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return x;
    }

    public void ModifierCours(Cours c) {

        String requete = "UPDATE `cours` SET `contract` = '" + c.getContract() + "', `niveau` = '" + c.getNiveau() + "', `id_m` = '" + c.getM().getId() + "', `Libelle` = '" + c.getLibelle() + "' WHERE `cours`.`id` = '" + c.getId() + "'";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
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
            System.out.println(ex.getMessage());
        }
        return questions;
    }

    public boolean SupprimerQuestion(Question q) {
boolean x=false;
        String requete = "delete from question where id = " + q.getId() + "";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            x=true;
            return x;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return x;
    }

    public void ModifierQuestion(Question q) {
        String requete = "update question set Question= '" + q.getQuestion() + "' , id_C=" + q.getCour().getId() + ",date='" + q.getDate() + "' where id = " + q.getId() + "";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
            System.out.println(ex.getMessage());

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
            System.out.println(ex.getMessage());

        }

        return q;
    }

    public Matiere recupereResultatM(ResultSet x) {
        Matiere q = new Matiere();
        try {
            q.setId(x.getInt("id"));
            q.setLibelle(x.getString("Libelle"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

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
            System.out.println(ex.getMessage());

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
            System.out.println(ex.getMessage());
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
            System.out.println(ex.getMessage());

        }
        return null;
    }

    public boolean AjouterReponse(Reponse r) {
        String requete = "insert into reponse (Reponse,etat,id_q,id_C) values('" + r.getReponse() + "','" + r.getEtat() + "'," + r.getQ().getId()
                + ",'" + r.getC().getId() + "')";
        boolean x = false;
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            x = true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
        return x;
    }

    public ArrayList<Reponse> AfficherReponse() {
        ArrayList<Reponse> rep = new ArrayList<>();
        String requete = "(select * from reponse)";
        try {
            Statement st = cnx.createStatement();
            ResultSet x = st.executeQuery(requete);
            while (x.next()) {
                rep.add(recupereResultatReponse(x));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
        return rep;
    }

    public Reponse recupereResultatReponse(ResultSet x) {
        Reponse r = new Reponse();
        try {
            r.setId(x.getInt("id"));
            r.setReponse(x.getString("Reponse"));
            r.setEtat(x.getString("etat"));
            r.setC(retournerCours(x.getInt("id_C")));
            r.setQ(retournerQuestion(x.getInt("id_q")));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return r;
    }

    public Question retournerQuestion(int id) {
        try {
            PreparedStatement pt = cnx.prepareStatement("select * from question where id=?");
            pt.setInt(1, id);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                return recupereResultatQ(rs);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return null;
    }
     public ArrayList<Question> QuestionSpec(int idc) {
        {
            ArrayList<Question> Q = new ArrayList<>();
            String requete = "(select * from question where id_C="+idc+")";

            try {
                Statement st = cnx.createStatement();
                ResultSet x = st.executeQuery(requete);
                while (x.next()) {
                   Question q = new Question();
                    q.setCour(retournerCours(x.getInt("id_C")));
                    q.setQuestion(x.getString("Question"));
                    q.setId(x.getInt("id"));
                    q.setDate(x.getDate("date"));
                    Q.add(q);
                    
                    
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
return Q;
        }
        
    }
      public ArrayList<Reponse> ReponseSpec(int idq) {
        {
            ArrayList<Reponse> R = new ArrayList<>();
            String requete = "(select * from reponse where id_q="+idq+")";

            try {
                Statement st = cnx.createStatement();
                ResultSet x = st.executeQuery(requete);
                while (x.next()) {
                   Reponse r = new Reponse();
                    r.setId(x.getInt("id"));
                    r.setReponse(x.getString("Reponse"));
                    r.setEtat(x.getString("etat"));
                    r.setQ(retournerQuestion(x.getInt("id_q")));
                    r.setC(retournerCours(x.getInt("id_C")));
                    R.add(r);
                    
                    
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
return R;
        }
        
    }
      public int ReponseSpecTrue(int idq) {
        {
            int count=0;
            String requete = "(select count(*) from reponse where id_q="+idq+" and etat = 'vrai')";

            try {
                Statement st = cnx.createStatement();
                ResultSet x = st.executeQuery(requete);
               while (x.next()){
                   count+=x.getInt("count(*)");
               }
               return count;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
           
return count;
        }
        
    }
      
      public void Noter(NoteQuiz n){
             String requete = "update note_quiz set id_q= '" + n.getId_q()+ "' , id_C='" + n.getId_C() + "',id_u='" + n.getId_u()+ "',note='"+n.getNote()+"' where id_q = '" + n.getId_q()+ "' and id_C='"+n.getId_C()+"' and id_u='"+n.getId_u()+"'";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
      }
 
      public NoteQuiz getNoteSpec(int idc,int idq,int idu){
            String requete = "(select * from note_quiz where id_C='"+idc+"' and id_q='"+idq+"' and id_u='"+idu+"' )";
        try {
            Statement st = cnx.createStatement();
            ResultSet x = st.executeQuery(requete);
            NoteQuiz nq=new NoteQuiz();
            while (x.next()) {
               
               nq.setId(x.getInt("id"));
               nq.setNote(x.getFloat("note")
               );
            }
            
            return nq;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
           
            
        }
          return null;
      }
      public void delete(int idc,int idq,int idu){
          String requete = "delete from note_quiz  where id_C='"+idc+"' and id_q='"+idq+"' and id_u='"+idu+"'";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
      }
      public void deleteF(int idc,int idu){
          String requete = "delete from note_total  where cours='"+idc+"'  and user='"+idu+"'";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
      }
      public void getZero(int idc,int idq,int idu){
          String requete = "insert into note_quiz (id_q,note,id_C,id_u) values ('"+idq+"',0,'"+idc+"','"+idu+"')";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
      }
       public void getZeroF(int idc,int idu){
          String requete = "insert into note_total (note,cours,user) values (0,'"+idc+"','"+idu+"')";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
      }
       public void ModifierReponse(Reponse r) {
        String requete = "update reponse set Reponse= '" + r.getReponse() + "' , id_C=" + r.getC().getId() + ",id_q='" + r.getQ().getId() + "'"+ ",etat='" + r.getEtat() + "' where id = " + r.getId() + "";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
        public ArrayList<Question> AfficherQuestionParCours(Cours c) {
        {
            ArrayList<Question> Q = new ArrayList<>();
            String requete = "(select * from question where id_C="+c.getId()+")";

            try {
                Statement st = cnx.createStatement();
                ResultSet x = st.executeQuery(requete);
                while (x.next()) {
                    Question q = new Question();
                   q.setCour(retournerCours(x.getInt("id_C")));
                   q.setDate(x.getDate("date"));
                   q.setQuestion(x.getString("Question"));
                   q.setId(x.getInt("id"));
                    Q.add(q);
                    

                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
return Q;
        }
        
    }
        public void SupprimerReponse(Reponse r){
            String requete = "delete from reponse where id = " + r.getId() + "";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        }
        
      public void notefinal(int c , int u,float note ){
           String requete = "update  note_total set note ='"+note+"' where cours='"+c+"' and user ='"+u+"' ";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
      }
        public float noteTotal(int c,int u) {
        {
            int count=0;
            String requete = "(select sum(note) from note_quiz where id_C="+c+" and id_u = "+u+")";

            try {
                Statement st = cnx.createStatement();
                ResultSet x = st.executeQuery(requete);
               while (x.next()){
                   count+=x.getInt("sum(note)");
               }
               return count;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println(count);
return count;
        }
      
}
}
