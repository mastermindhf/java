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
public class date {
    private int id;
    private Date date;

    public date(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public date() {
    }

    @Override
    public String toString() {
        return "date{" + "id=" + id + ", date=" + date + '}';
    }
    
        
    
}
