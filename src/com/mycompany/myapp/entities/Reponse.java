/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Khaled
 */
public class Reponse {
    private String reponse ;
    private int id ; 
    private boolean type ; 
    private int id_question ; 

    public Reponse() {
    }

    public Reponse(String reponse, boolean type, int id_question) {
        this.reponse = reponse;
        this.type = type;
        this.id_question = id_question;
    }

    public Reponse(int id, String toString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Reponse{" + "reponse=" + reponse + ", type=" + type + '}';
    }

    public String getReponse() {
        return reponse;
    }

    public int getId() {
        return id;
    }

    public boolean isType() {
        return type;
    }

    public int getId_question() {
        return id_question;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }

 
    public Reponse( int id,String reponse, boolean type, int id_question) {
        this.reponse = reponse;
        this.id = id;
        this.type = type;
        this.id_question = id_question;
    }
    
    
    
}
