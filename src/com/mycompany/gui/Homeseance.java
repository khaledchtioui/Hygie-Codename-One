/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author sayed
 */
public class Homeseance extends BaseForm {

    public Homeseance(Resources res) {
        
         Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        setLayout(BoxLayout.y());
                super.addSideMenu(res);
        
     //   super("Home", BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddTask = new Button("Add Seance");
        Button btnListTasks = new Button("List Seances");
        btnAddTask.addActionListener(e-> new AddSeance(this).show());
        btnListTasks.addActionListener(e-> new ShowSeance(this).show());
        add(btnAddTask);
        add(btnListTasks);
        
        
        
        
        
        
    }
    
    
    
    
}
