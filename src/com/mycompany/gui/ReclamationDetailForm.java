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
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import User.gui.Message;
import User.gui.Reclamation;
import User.gui.Task;
import com.mycompany.myapp.entities.ServiceRec;
import java.util.ArrayList;

/**
 *
 * @author Khaled
 */
public class ReclamationDetailForm extends Form{

    public ReclamationDetailForm(Form previous , Reclamation r ) {
        
        setTitle("Reclamation");
        setLayout(BoxLayout.y());
        addElement(r);

 ArrayList<Message> msg = ServiceRec.getInstance().getAllMessage();
        for (Message t : msg) {
            if(t.getIdreclamation()==r.getId())
            addmessageElement(t);
        }
        addReponse(r.getId());
                    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

        
    }
    
    
    
    public void addElement(Reclamation reclamations) {

String etat ="Non Traité"  ; 
        int status = reclamations.getStatus()  ; 
        if(status==2)
            etat ="Traité"  ; 
        else if (status==1)
            etat="En Cours" ; 
            
        
        
                Label lbltitre = new Label(reclamations.getTitre());
                Label lbtype = new Label(reclamations.getType());
                Label lbdesc = new Label(reclamations.getDescription());
                Label lbdate = new Label(reclamations.getDate());
                Label lbstatus = new Label(etat);


        

        addAll(lbltitre,lbtype,lbdesc,lbdate,lbstatus);

    }
    
   
        public void addReponse(int id) {

                
               
        
                TextField tfName = new TextField("","Message");

                Button btnValider = new Button("Envoyer");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                       
                        Message t = new Message(id,tfName.getText().toString());
                        if( ServiceRec.getInstance().addMessage(t))
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

    }
    
     
    public void addmessageElement(Message mess) {


                   Button btndel = new Button("Delete Reclamation");
      btndel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
    
                  
                         if( ServiceRec.getInstance().suppmessage(mess))
                            {  
                                Dialog.show("Success","Connection accepted",new Command("OK"));
                            }else
                         {
                           Dialog.show("Error","Server error",new Command("OK"));
    
                         }
                        }
            });
        
        
                Label lbltitre = new Label(mess.getDescriptionmes());
             

        

        addAll(lbltitre,btndel);

    }
    

}
