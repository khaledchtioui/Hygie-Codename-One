/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.IconHolder;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Questionnaire;
import com.mycompany.myapp.entities.Questions;
import com.mycompany.myapp.entities.Reponse;
import com.mycompany.myapp.entities.Task;
import com.mycompany.myapp.services.ServiceTask;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author bhk
 */
public class ListTasksForm extends Form {

    public ListTasksForm(Form previous) {
        setTitle("List Questionnaire");
        setLayout(BoxLayout.y());

        /*SpanLabel sp = new SpanLabel();
        sp.setText(ServiceTask.getInstance().getAllTasks().toString());
        add(sp);
         */
        ArrayList<Questionnaire> quizs = ServiceTask.getInstance().getAllQuizs();
        ArrayList<Questions> questions = ServiceTask.getInstance().getAllQuestions();

        for (Questionnaire t : quizs) {
            addElement(t);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

    public void addElement(Questionnaire quizes) {
        
        Button btn_up =new Button("update"+quizes.getQuestionnaire())  ;
        btn_up.addActionListener(e-> new editQuiz(this,quizes.getId()).show());
        Button btn_2 = new Button("Delete"+quizes.getQuestionnaire())  ; 
        btn_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

              
                     if( ServiceTask.getInstance().suppQuestionnaire(quizes))
                        {  
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                     {
                       Dialog.show("Error","Server error",new Command("OK"));

                     }
                    
              

            }
        });
        
        Button btn = new Button(quizes.getQuestionnaire());
       btn.addActionListener(e-> new QuizWithQuestion(this,quizes.getId()).show());
        addAll(btn,btn_2,btn_up);

    }

}
