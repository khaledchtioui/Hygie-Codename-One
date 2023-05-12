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
 * @author bhk
 */
public class HomeForm extends BaseForm{

    public HomeForm(Resources res) {
              //  super("Newsfeed", BoxLayout.y());
  Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        setLayout(BoxLayout.y());
                super.addSideMenu(res);

        add(new Label(""));
        Button btnAddTask = new Button("Add Task");
        Button btnListTasks = new Button("List Tasks");
        
        btnAddTask.addActionListener(e-> new AddTaskForm(this).show());
        btnListTasks.addActionListener(e-> new ListTasksForm(this).show());
        addAll(btnAddTask,btnListTasks);
        
        
    }
    
    
}