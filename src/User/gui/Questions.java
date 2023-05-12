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
public class Questions {
      private int id ; 
    private String question  ; 
   private int type ; 
    private int point ; 
    private int id_quiz ;

    public Questions(String question, int type, int point, int id_quiz) {
        this.question = question;
        this.type = type;
        this.point = point;
        this.id_quiz = id_quiz;
    }

    public Questions(int id, String question, int type, int point, int id_quiz) {
        this.id = id;
        this.question = question;
        this.type = type;
        this.point = point;
        this.id_quiz = id_quiz;
    }

    public Questions(int id, String question, int type, int point) {
        this.id = id;
        this.question = question;
        this.type = type;
        this.point = point;
    }

    public Questions() {
    }

    @Override
    public String toString() {
        return "Questions{" + "question=" + question + ", type=" + type + ", point=" + point + '}';
    }

    public void setId_quiz(int id_quiz) {
        this.id_quiz = id_quiz;
    }

    public int getId_quiz() {
        return id_quiz;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public int getType() {
        return type;
    }

    public int getPoint() {
        return point;
    }
    
    
    


}
