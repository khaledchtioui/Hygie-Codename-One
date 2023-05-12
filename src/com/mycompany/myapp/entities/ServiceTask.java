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
import User.gui.Questionnaire;
import User.gui.Questions;
import User.gui.Reponse;
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
   public Questionnaire quizz ;
   public Questions questionn ; 
   public Reponse reponsee ; 
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

    public boolean suppQuestionnaire(Questionnaire t)
    {
        
         String url = Statics.BASE_URL + "questionnaire/deletejson/"+t.getId() ;
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
     public boolean suppQuestion(Questions t)
    {
        
         String url = Statics.BASE_URL + "questions/deletejson/"+t.getId();
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
     
     
     //
     public boolean suppReponse(Reponse t)
    {
        
         String url = Statics.BASE_URL + "reponse/deletejson/"+t.getId() ;
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
     //
    //
//
     
     //
                 public Reponse getreponse(int id) {
                
                
                
        
        String url = Statics.BASE_URL + "reponse/reponsejson/+"+id+"/"   ;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reponsee = parseReponsebyid(new String(req.getResponseData()));

                req.removeResponseListener(this);
            }
        });
        
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        
        return reponsee;
        
        
    }

     //
     
     //
            public Questionnaire getquiz(int id) {
                
                
                
        
        String url = Statics.BASE_URL + "questionnaire/quizIdjson/+"+id+"/"   ;
        req.setUrl(url);
        req.setPost(false);
           System.out.println("test err");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                quizz = parseQuestionnairebyid(new String(req.getResponseData()));

                req.removeResponseListener(this);
            }
        });
        
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        
        return quizz;
        
        
    }

     //
     
       public Questions getquestion(int id) {
        
        String url = Statics.BASE_URL + "questions/questionidjson/+"+id+"/"   ;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                questionn = parseQuestionbyid(new String(req.getResponseData()));

                req.removeResponseListener(this);
            }
        });
        
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        
        return questionn;
        
        
    }
       //
       
                public Questions parseQuestionbyid(String jsonText) {
        try {
           System.out.println("aloo 2 ");

            questionn = new Questions();
           System.out.println("aloo 3");

            JSONParser j = new JSONParser();
             System.out.println("aloo 4");

            Map<String, Object> questionListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println("aloo aloo");
                                      

             
                float id = Float.parseFloat(questionListJson.get("id").toString());
                 System.out.println("test err id");

                questionn.setId((int) id);

                if (questionListJson.get("question") == null) 
                {
                    
                    
                
                    questionn.setQuestion("null")                          ;

                
               
                } 
                else 
                {
               
                    
                   questionn.setQuestion(questionListJson.get("question").toString())        ;

                    
                
                 }
              
                    if(questionListJson.get("type")=="1")
                
                    questionn.setType(1) ;   
                    
                    else 
                        questionn.setType(2);

                
               
              
                    

                    
                
            
                
                
            

        } catch (IOException ex) {
                 System.out.println("errrrrrrrrr");
        }
        

        
        return questionn ;
    } 

       //
       
       
      //
     public Reponse parseReponsebyid(String jsonText) {
        try {

            reponsee = new Reponse();
            JSONParser j = new JSONParser();
            Map<String, Object> reponseListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                       System.out.println(reponseListJson);




             
                float id = Float.parseFloat(reponseListJson.get("id").toString());

                reponsee.setId((int) id);

                if (reponseListJson.get("reponsee") == null) 
                {
                    
                    
                
                    reponsee.setReponse("null")                          ;

                
               
                } 
                else 
                {
               
                    
                   reponsee.setReponse(reponseListJson.get("reponsee").toString())        ;

                    
                
                 }
                if(reponseListJson.get("type").toString().equals("true"))
                {
                reponsee.setType(true);
                }
                else
                {
                    reponsee.setType(false);
                }
                
                
            

        } catch (IOException ex) {
                 System.out.println("errrrrrrrrr");
        }
        

        
        return reponsee ;
    } 

      //
         public Questionnaire parseQuestionnairebyid(String jsonText) {
        try {
                                       System.out.println("test err 2");

            quizz = new Questionnaire();
            JSONParser j = new JSONParser();
            Map<String, Object> quizsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                                       System.out.println("test err 3");
                                       System.out.println(quizsListJson);

                                                   System.out.println("test err 4");


                                     System.out.println("test err 5");

             
                float id = Float.parseFloat(quizsListJson.get("id").toString());
                 System.out.println("test err id");

                quizz.setId((int) id);
                                          System.out.println("test err 5");

                if (quizsListJson.get("nom") == null) 
                {
                    
                    
                
                    quizz.setQuestionnaire("null")                          ;
                   System.out.println("test err 6");

                
               
                } 
                else 
                {
               
                    
                   quizz.setQuestionnaire(quizsListJson.get("nom").toString())        ;
                   System.out.println("test err 7");

                    
                
         }
                
                
            

        } catch (IOException ex) {
                 System.out.println("errrrrrrrrr");
        }
        

        
        return quizz ;
    } 
  
       
      //
         
        // 
         public boolean uppQuestionnaire(Questionnaire t) {

        String name = t.getQuestionnaire();
        
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL + "questionnaire/updateQuizJson/"+t.getId()+"?nom="+name ;

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
         //   
      //   
         public boolean uppReponse(Reponse t) {
   String type ; 
   if (t.isType()==true)
   {
       type = "true";
   }
   else
   {
       type= "false";
   }
        String name = t.getReponse();
        
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL + "reponse/updatereponsejson/"+t.getId()+"?reponse="+name+"&type="+type ;

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
     //
         //
                  public boolean uppQuestion(Questions t) {

        String name = t.getQuestion();
        
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL + "questions/Updatequestionjson/"+t.getId()+"?question="+name+"&point="+t.getPoint()+"&type="+t.getType() ;

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

         //
//    
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
    
    //
        public boolean addReponse(Reponse t) {
            int type ;
        if(t.isType()==true)
        {
         type = 1 ;    
        }
        else 
        {
            type = 0 ; 
        }
        
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL + "reponse/newjson/"+t.getId_question()+"?type="+type+"&reponse="+t.getReponse() ;

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

    //
    
    //
    public boolean addQuestion(Questions t) {

        String name = t.getQuestion();
        
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL + "questions/newjson/"+t.getId_quiz()+"?type="+t.getType()+"&question="+t.getQuestion()+"&point="+t.getPoint() ;

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
//
    

    public ArrayList<Questionnaire> parseQuestionnaire(String jsonText) {
        try {
            quizs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> quizsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                                                               System.out.println(quizsListJson);

            List<Map<String, Object>> list = (List<Map<String, Object>>) quizsListJson.get("root");
                                                               System.out.println(list);

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
