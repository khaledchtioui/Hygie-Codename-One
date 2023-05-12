/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Khaled
 */
public class HomeRec  extends BaseForm{

    public HomeRec(Resources res) {
        
         Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        setLayout(BoxLayout.y());
                super.addSideMenu(res);
        
         
        Button btnAddTask = new Button("Add Reclamation");
        Button btnListTasks = new Button("List Tasks");
        
       btnAddTask.addActionListener(e-> new addReclamation(this).show());
       btnListTasks.addActionListener(e-> new ListReclamationForm(this).show());
       addAll(btnAddTask,btnListTasks);

        
    }

 
    
    
}
