/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author farou
 */
public interface Iservice <T>{
    void insert(T e);
    void update(int id,String Nom,String Prenom,String tel,String Email,String Type,String Screenshot,String Objet,String Reclamation,Date Date);
    void delete(int id);
    List<T> displayAll();
}

