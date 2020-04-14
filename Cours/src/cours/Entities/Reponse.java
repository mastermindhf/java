/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cours.Entities;

/**
 *
 * @author ASUS
 */
public class Reponse {

    private int id;
    private String Reponse, etat;
    private Question q;
    private Cours c;

    public Reponse() {
    }

    public Reponse(String Reponse, String etat, Question q, Cours c) {
        this.Reponse = Reponse;
        this.etat = etat;
        this.q = q;
        this.c = c;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReponse() {
        return Reponse;
    }

    public void setReponse(String Reponse) {
        this.Reponse = Reponse;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Question getQ() {
        return q;
    }

    public void setQ(Question q) {
        this.q = q;
    }

    public Cours getC() {
        return c;
    }

    public void setC(Cours c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return "Reponse{" + "id=" + id + ", Reponse=" + Reponse + ", etat=" + etat + ", q=" + q + ", c=" + c + '}';
    }
    
    
    
}
