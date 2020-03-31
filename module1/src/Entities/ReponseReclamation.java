/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author farou
 */
public class ReponseReclamation {
    
    private int id_rep;
  
    private reclamation reprec;

    public ReponseReclamation(int id_rep, reclamation reprec) {
        this.id_rep = id_rep;
        this.reprec = reprec;
    }

    public int getId_rep() {
        return id_rep;
    }

    public void setId_rep(int id_rep) {
        this.id_rep = id_rep;
    }

    public reclamation getReprec() {
        return reprec;
    }

    public void setReprec(reclamation reprec) {
        this.reprec = reprec;
    }

    public ReponseReclamation() {
    }

    @Override
    public String toString() {
        return "ReponseReclamation{" + "id_rep=" + id_rep + ", reprec=" + reprec + '}';
    }


  
}
