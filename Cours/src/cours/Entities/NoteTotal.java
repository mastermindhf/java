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
public class NoteTotal {
    int id,cours,user,note;

    public NoteTotal(int cours, int user, int note) {
        this.cours = cours;
        this.user = user;
        this.note = note;
    }

    public NoteTotal() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCours() {
        return cours;
    }

    public void setCours(int cours) {
        this.cours = cours;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "NoteTotal{" + "id=" + id + ", cours=" + cours + ", user=" + user + ", note=" + note + '}';
    }
    
}
