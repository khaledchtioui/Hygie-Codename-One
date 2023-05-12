/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User.gui;

/**
 *
 * @author Khaled
 */
public class Message {
    
    
    private int id ; 
    private int idreclamation ; 
    private String descriptionmes ; 
    private String date ;  

    public Message(int id, int idreclamation, String descriptionmes, String date) {
        this.id = id;
        this.idreclamation = idreclamation;
        this.descriptionmes = descriptionmes;
        this.date = date;
    }

    public Message(int idreclamation, String descriptionmes) {
        this.idreclamation = idreclamation;
        this.descriptionmes = descriptionmes;
    }

    public Message(int idreclamation, String descriptionmes, String date) {
        this.idreclamation = idreclamation;
        this.descriptionmes = descriptionmes;
        this.date = date;
    }

    public Message() {
    }

    @Override
    public String toString() {
        return "Message{" + "id=" + id + ", idreclamation=" + idreclamation + ", descriptionmes=" + descriptionmes + ", date=" + date + '}';
    }

    public int getId() {
        return id;
    }

    public int getIdreclamation() {
        return idreclamation;
    }

    public String getDescriptionmes() {
        return descriptionmes;
    }

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdreclamation(int idreclamation) {
        this.idreclamation = idreclamation;
    }

    public void setDescriptionmes(String descriptionmes) {
        this.descriptionmes = descriptionmes;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    

    
    
    
    
    
}
