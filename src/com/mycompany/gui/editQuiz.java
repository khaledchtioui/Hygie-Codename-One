package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import User.gui.Questionnaire;
import com.mycompany.myapp.entities.ServiceTask;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Khaled
 */
public class editQuiz extends Form{
    
       public Questionnaire quizz ;


    public editQuiz(Form previous ,int id) {
         setTitle("Edit Quiz");
        setLayout(BoxLayout.y());
        System.out.println("test");
        
                 quizz = ServiceTask.getInstance().getquiz(id);
                         System.out.println("test2");


         
        TextField tfName = new TextField(quizz.getQuestionnaire(),"TaskName");
        Button btnValider = new Button("Update Quiz");
                btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        
                        Questionnaire t = new Questionnaire(id,tfName.getText().toString());
                        if( ServiceTask.getInstance().uppQuestionnaire(t))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });

        
    
        addAll(tfName,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
        
        
        
    
    
    
}
