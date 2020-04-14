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
public class NoteQuiz {
    private int id,id_q,id_C,id_u;
    private float note;

    public NoteQuiz(int id_q, int id_C, int id_u, float note) {
        this.id_q = id_q;
        this.id_C = id_C;
        this.id_u = id_u;
        this.note = note;
    }

    public NoteQuiz() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_q() {
        return id_q;
    }

    public void setId_q(int id_q) {
        this.id_q = id_q;
    }

    public int getId_C() {
        return id_C;
    }

    public void setId_C(int id_C) {
        this.id_C = id_C;
    }

    public int getId_u() {
        return id_u;
    }

    public void setId_u(int id_u) {
        this.id_u = id_u;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "NoteQuiz{" + "id=" + id + ", id_q=" + id_q + ", id_C=" + id_C + ", id_u=" + id_u + ", note=" + note + '}';
    }
    
    
}
