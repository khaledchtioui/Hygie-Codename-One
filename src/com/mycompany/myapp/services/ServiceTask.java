/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Questionnaire;
import com.mycompany.myapp.entities.Questions;
import com.mycompany.myapp.entities.Reponse;
import com.mycompany.myapp.entities.Task;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceTask {

    public ArrayList<Questionnaire> quizs   ;
  public ArrayList<Questions> questions ;  
 public ArrayList<Reponse> reponse    ;   

    public static ServiceTask instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceTask() {
        req = new ConnectionRequest();
    }

    public static ServiceTask getInstance() {
        if (instance == null) {
            instance = new ServiceTask();
        }
        return instance;
    }

    public void suppQuestionnaire(Questionnaire t)
    {
        int id = t.getId() ;
    }
    
    
    public boolean addQuestionnaire(Questionnaire t) {

        String name = t.getQuestionnaire();
        
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL + "questionnaire/newjson?nom="+name ;

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

    public ArrayList<Questionnaire> parseQuestionnaire(String jsonText) {
        try {
            quizs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> quizsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) quizsListJson.get("root");
            for (Map<String, Object> obj : list) {
                Questionnaire t = new Questionnaire();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                if (obj.get("nom") == null) 
                {
                    
                    
                
                    t.setQuestionnaire("null")                          ;
                    
                
               
                } 
                else 
                {
               
                    
                    t.setQuestionnaire(obj.get("nom").toString())        ;
                
                    
                }
                
                quizs.add(t) ;
                
            }

        } catch (IOException ex) {
                 System.out.println(ex.getMessage());
        }
        return quizs ;
    } 
//
    
      public ArrayList<Questions> parseQuestions(String jsonText) {
        try {
            questions = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> questionsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) questionsListJson.get("root");
            for (Map<String, Object> obj : list) {
                Questions t = new Questions();
                float id = Float.parseFloat(obj.get("id").toString());
                float point = Float.parseFloat(obj.get("point").toString());
                float type = Float.parseFloat(obj.get("id").toString())  ;
                float idquiz = Float.parseFloat(obj.get("src1").toString())  ;
                
                t.setType((int) type );
                t.setPoint((int) point );
                t.setId_quiz((int) idquiz );

                t.setId((int) id);
                if (obj.get("question") == null) 
                {
                    
                    
                
                    t.setQuestion("null")                          ;
                    
                
               
                } 
                else 
                {
               
                    
                    t.setQuestion(obj.get("question").toString())        ;
                
                    
                }
               
                
                questions.add(t) ;
                
            }

        } catch (IOException ex) {
                 System.out.println(ex.getMessage());
        }
        return questions ;
    } 

    
    
    public ArrayList<Reponse> parseReponses(String jsonText) {
        try {
            reponse = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> reponseListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) reponseListJson.get("root");
            for (Map<String, Object> obj : list) {
                Reponse t = new Reponse();

                float id = Float.parseFloat(obj.get("id").toString());
                float idquestion = Float.parseFloat(obj.get("scr1").toString())  ;
                
           
                t.setId((int) id);
              t.setId_question((int) idquestion );
                t.setType(true);
                if (obj.get("reponsee") == null) 
                {
                    
                    
                
                    t.setReponse("null")                          ;
                    
                
               
                } 
                else 
                {
               
                    
                    t.setReponse(obj.get("reponsee").toString())        ;
                
                    
                }
               
                
                reponse.add(t) ;
                
            }

        } catch (IOException ex) {
                 System.out.println(ex.getMessage());
        }
        return reponse ;
    } 

    
    public ArrayList<Questionnaire> getAllQuizs() {
        
        String url = Statics.BASE_URL + "questionnaire/json"   ;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                quizs = parseQuestionnaire(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        
        return quizs;
        
        
    }
    //
     public ArrayList<Questions> getAllQuestions() {
        
        String url = Statics.BASE_URL + "questions/json"   ;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                questions = parseQuestions(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        
        return questions;
        
        
    }
    
    //
    
      public ArrayList<Reponse> getAllReponse() {
        
        String url = Statics.BASE_URL + "reponse/json"   ;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reponse = parseReponses(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        
        return reponse;
        
        
    }
    
    
    
    
    
    
    
}
