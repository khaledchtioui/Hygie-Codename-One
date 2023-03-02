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
public class Questionnaire {
    private int id ; 
    private String questionnaire  ; 

    public Questionnaire(String questionnaire) {
        this.questionnaire = questionnaire;
    }

    public Questionnaire() {
    }

    public int getId() {
        return id;
    }

    public String getQuestionnaire() {
        return questionnaire;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuestionnaire(String questionnaire) {
        this.questionnaire = questionnaire;
    }

    @Override
    public String toString() {
        return "Questionnaire{ questionnaire=" + questionnaire + '}';
    }
    
    
    
    
    
    
}
