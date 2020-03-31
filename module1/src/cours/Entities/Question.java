/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cours.Entities;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Question {
    private int id;
    private Cours cour;
    private String question;
    private Date date;

    public Question() {
    }

    public Question(Cours cour, String question, Date date) {
        this.cour = cour;
        this.question = question;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cours getCour() {
        return cour;
    }

    public void setCour(Cours cour) {
        this.cour = cour;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Question{" + "id=" + id + ", cour=" + cour + ", question=" + question + ", date=" + date + '}';
    }

   

    
    

    
 
}
