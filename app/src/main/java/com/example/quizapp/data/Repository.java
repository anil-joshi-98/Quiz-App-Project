package com.example.quizapp.data;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.quizapp.controller.AppController;
import com.example.quizapp.model.Questions;

import org.json.JSONException;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Repository {
    ArrayList<Questions> ar=new ArrayList<>();
    String url = "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";
    public ArrayList<Questions> getQuestions(AnswerAsyncResponse answer){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET,url,null,response -> {
            for(int i=0;i<response.length();i++){
                try {
                    Questions questions=new Questions(response.getJSONArray(i).getString(0),response.getJSONArray(i).getBoolean(1));
                    ar.add(questions);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if(answer!=null){
                answer.isNowDone(ar);
            }
        },error -> {

        });
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
        return ar;
    }
}
