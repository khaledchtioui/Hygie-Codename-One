/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import User.gui.Questionnaire;
import User.gui.Questions;
import com.mycompany.myapp.entities.ServiceTask;

/**
 *
 * @author Khaled
 */
public class QuestionByQuiz extends Form{

    public QuestionByQuiz(Form previous , int id ) {
                System.out.println("test 1");

          setTitle("Add a new question");
          setLayout(BoxLayout.y());
        
        TextField tfquestion = new TextField("","Question");
        TextField tfpoint = new TextField("","Point");
        ComboBox<String> combotype = new ComboBox<>() ; 
        combotype.addItem("Choix Multiple")  ; 
        combotype.addItem("Choix Unique"); 
        

        
        tfpoint.setConstraint(TextField.NUMERIC) ;
                System.out.println("test 2");

        
        Button btnValider = new Button("Add task");
        
               btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                        System.out.println("test wiou");

                if ((tfquestion.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    int point  = Integer.parseInt(tfpoint.getText().toString())  ;
                    try {
                        int status=1;
                        if(combotype.getSelectedItem().equals("Choix Multiple"))
                            status=2;
                        Questions t = new Questions(tfquestion.getText().toString(),status,point,id);
                        if( ServiceTask.getInstance().addQuestion(t))
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

                
       
        
        addAll(tfquestion,combotype,tfpoint,btnValider)  ; 
        
             getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

        
    }
    
    

}
