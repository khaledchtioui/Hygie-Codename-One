/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Questions;
import com.mycompany.myapp.entities.Reponse;
import com.mycompany.myapp.services.ServiceTask;
import java.util.ArrayList;

/**
 *
 * @author Khaled
 */
public class QuizWithQuestion extends Form{

    public QuizWithQuestion(Form previous,int id) {
          setTitle("QuizWithQuestion");
          setLayout(BoxLayout.y());
          
        ArrayList<Questions> questions = ServiceTask.getInstance().getAllQuestions();
        ArrayList<Reponse> reponses = ServiceTask.getInstance().getAllReponse();

       for (Questions t : questions) {
          if(t.getId_quiz()==id)
          {addElement(t);
            for(Reponse k : reponses)
            {
               if(k.getId_question()==t.getId())
                    addElementreponse(k)   ;
            }
          }
            
            
            
   }
        
        
        
       

        
        
        
    }
    
    
     
       public void addElement(Questions questions) {

        Button btn = new Button(questions.getQuestion());
        btn.addActionListener((evt) -> {
        });
        add(btn);

    }
       
       public void addElementreponse(Reponse reponses) {

        Label lbl = new Label(reponses.getReponse());
    
        add(lbl);

    }
    
    
}
