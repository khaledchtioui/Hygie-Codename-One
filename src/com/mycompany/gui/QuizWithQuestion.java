/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import User.gui.Questions;
import User.gui.Reponse;
import com.mycompany.myapp.entities.ServiceTask;
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
                 addQuestion(id);
   
        
        
                           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());


        
        
        
    }
    
    public void addQuestion(int  x)
    {
                Button btnaddques = new Button("Add Question")  ; 
          btnaddques.addActionListener(e-> new QuestionByQuiz(this,x).show());
        add(btnaddques)  ; 
    }
   
   public void addreponse(int x)
   {
            Button btnreponse = new Button("AddResponse")  ; 
          btnreponse.addActionListener(e-> new AddReponsebyquestion(this,x).show());

        add(btnreponse)  ; 
       
   }
     
       public void addElement(Questions questions) {

        Button btneditquestion = new Button("Edit question")  ; 

            Button btn_2 = new Button("Delete"+questions.getQuestion())  ; 
            btn_2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
    
                  
                         if( ServiceTask.getInstance().suppQuestion(questions))
                            {  
                                Dialog.show("Success","Connection accepted",new Command("OK"));
                            }else
                         {
                           Dialog.show("Error","Server error",new Command("OK"));
    
                         }
                        }
            });
          
          
          
       
          btneditquestion.addActionListener(e-> new editQuestion(this,questions.getId()).show());

          Label lbl = new   Label(questions.getQuestion());
      //0  btn.addActionListener((evt) -> {
       // });
        addAll(lbl,btn_2,btneditquestion);


    }
       
       public void addElementreponse(Reponse reponses) {
            Button btn_edit =new Button ("edit"+reponses.getReponse())  ; 
            Button btn_2 = new Button("Delete"+reponses.getReponse())  ; 
            btn_2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
    
                  
                         if( ServiceTask.getInstance().suppReponse(reponses))
                            {  
                                Dialog.show("Success","Connection accepted",new Command("OK"));
                            }else
                         {
                           Dialog.show("Error","Server error",new Command("OK"));
    
                         }
                        }
            });
          
          btn_edit.addActionListener(e-> new editreponse(this,reponses.getId()).show());

     
        Label lbl = new Label(reponses.getReponse());
    
        addAll(lbl,btn_2,btn_edit);

    }
    
    
}
