/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import User.gui.Message;
import User.gui.Reclamation;
import User.gui.Task;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Khaled
 */
public class ServiceRec {
    
    

    public ArrayList<Task> tasks;
    public ArrayList<Reclamation> reclamtions;
    public Reclamation reclamtionn;

    public ArrayList<Message> messages;
    public static ServiceRec instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceRec() {
        req = new ConnectionRequest();
    }

    public static ServiceRec getInstance() {
        if (instance == null) {
            instance = new ServiceRec();
        }
        return instance;
    }

    public boolean addTask(Task t) {

        String name = t.getName();
        int status =  t.getStatus();
        
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL + "create/" + status + "/" + name;

        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    public boolean addReclamation(Reclamation t) {

        String titre = t.getTitre();
        int status =  t.getStatus();
        String desc = t.getDescription();
        String type =t.getType();
        
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL +"reclamation/newjson?type="+type+"&status="+status+"&titre="+titre+"&descreption="+desc ;
        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    public boolean addMessage(Message t) {

       
        
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL +"message/newjson/"+t.getIdreclamation()+"?descriptionmes="+t.getDescriptionmes();

        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    
        public ArrayList<Reclamation> parsereclamation(String jsonText) {
        try {
            reclamtions = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Reclamation t = new Reclamation();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                t.setStatus(((int) Float.parseFloat(obj.get("status").toString())));
                    t.setTitre(obj.get("titre").toString());
                    t.setType(obj.get("type").toString());
                     t.setDescription(obj.get("description").toString());
                     t.setDate(obj.get("daterec").toString());

                
                
                
                
                reclamtions.add(t);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamtions;
    }

    
    
    public ArrayList<Task> parseTasks(String jsonText) {
        try {
            tasks = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Task t = new Task();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                t.setStatus(((int) Float.parseFloat(obj.get("status").toString())));
                if (obj.get("name") == null) {
                    t.setName("null");
                } else {
                    t.setName(obj.get("name").toString());
                }
                tasks.add(t);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return tasks;
    }

      public ArrayList<Message> parsemessages(String jsonText) {
        try {
            messages = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Message t = new Message();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                float idrec=Float.parseFloat(obj.get("scr").toString())  ; 
                t.setIdreclamation((int) idrec );
                t.setDescriptionmes(obj.get("descriptionmes").toString());
                t.setDate(obj.get("datemes").toString());
                
                
                messages.add(t);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return messages;
    }

    //
    
            public ArrayList<Message> getAllMessage() {
        String url = Statics.BASE_URL + "message/json";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                messages = parsemessages(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return messages;
    }

    
    //
      public Reclamation getreclamation(int id) {
        
        String url = Statics.BASE_URL + "reclamation/getjson/+"+id+"/"   ;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reclamtionn = parseReclamationbyid(new String(req.getResponseData()));

                req.removeResponseListener(this);
            }
        });
        
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        
        return reclamtionn;
        
        
    }
     public Reclamation parseReclamationbyid(String jsonText) {
        try {

            reclamtionn = new Reclamation();

            JSONParser j = new JSONParser();

            Map<String, Object> reclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                                      

             
                float id = Float.parseFloat(reclamationListJson.get("id").toString());

                reclamtionn.setId((int) id);

             

                   reclamtionn.setTitre(reclamationListJson.get("titre").toString())        ;
                   reclamtionn.setDescription(reclamationListJson.get("description").toString());
                   reclamtionn.setType(reclamationListJson.get("type").toString());
                   
                   reclamtionn.setStatus(((int) Float.parseFloat(reclamationListJson.get("status").toString())));
                   reclamtionn.setDate(reclamationListJson.get("daterec").toString());
                    
                
                 
              
              
                    

                    
                
            
                
                
            

        } catch (IOException ex) {
                 System.out.println("errrrrrrrrr");
        }
        

        
        return reclamtionn ;
    } 

      
    
        public ArrayList<Reclamation> getAllReclamation() {
        String url = Statics.BASE_URL + "reclamation/json";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reclamtions = parsereclamation(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reclamtions;
    }

    
    
    public ArrayList<Task> getAllTasks() {
        String url = Statics.BASE_URL + "get/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
    
    
     public boolean uppReclamation(Reclamation t) {

        
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL + "reclamation/getjson/"+t.getId()+"?type="+t.getType()+"&status="+t.getStatus()+"&description="+t.getDescription()+"&titre="+t.getType() ;

        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    
    
      public boolean suppReclamation(Reclamation t)
    {
        
         String url = Statics.BASE_URL + "reclamation/deletejson/"+t.getId();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);             }
         });
          NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
        
        
        
    }
//
       public boolean suppmessage(Message t)
    {
        
         String url = Statics.BASE_URL + "message/deletejson/"+t.getId();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);             }
         });
          NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
        
        
        
    }
//
     
    
    
    
}
