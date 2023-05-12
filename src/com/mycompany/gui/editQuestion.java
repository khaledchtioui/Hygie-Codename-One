/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
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
public class editQuestion extends Form{
    Questions questionn =new Questions()     ; 
    
 
    public editQuestion(Form previous ,int id) {
        System.out.println("alo");
          setTitle("Edit Question");
        setLayout(BoxLayout.y());
                 questionn = ServiceTask.getInstance().getquestion(id);
                 int type = questionn.getType()  ;


         
        TextField tfquestion = new TextField(questionn.getQuestion(),"TaskName");
          TextField tfpoint = new TextField(Integer.toString(questionn.getPoint()),"Point");
        ComboBox<String> combotype = new ComboBox<>() ; 
        combotype.addItem("Choix Multiple")  ; 
        combotype.addItem("Choix Unique"); 
        if(type==1)
        {
            combotype.setSelectedItem("Choix Unique");
        }
        else if(type==2)
        {
                        combotype.setSelectedItem("Choix Multiple");

        }
        
        Button btnValider = new Button("Update Quiz");
        
        
        
        //
        
                        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfquestion.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        
                        Questions t = new Questions(id,tfquestion.getText().toString(),type,Integer.parseInt(tfpoint.getText().toString()));
                        if( ServiceTask.getInstance().uppQuestion(t))
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

        
        //
        
        
        
        addAll(tfquestion,tfpoint,combotype,btnValider)   ; 
        
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

        
        
        
    }
    
    
    
    
}
