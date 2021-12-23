package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.data.AnswerAsyncResponse;
import com.example.quizapp.data.Repository;
import com.example.quizapp.model.Questions;
import com.sasank.roundedhorizontalprogress.RoundedHorizontalProgressBar;

import java.time.Clock;
import java.util.ArrayList;

public class DashBoardActivity extends AppCompatActivity {
    private CountDownTimer countDownTimer;
    private int timer=10;
    private TextView remaining,scoreId;
    private RoundedHorizontalProgressBar roundedHorizontalProgressBar;
    private CardView c1,c2,c3;
    private LinearLayout next,back;
    public int score;
    private ImageView back_btn;
    private SharedPreferences sharedPreferences;
    int qI=0,tempSc=0;
    private TextView t1,t2,t3,t4,newSc;
    private String url = "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        back_btn=findViewById(R.id.back_btn);
        newSc=findViewById(R.id.newScore);
        c2=findViewById(R.id.card1);
        c3=findViewById(R.id.card2);
        back=findViewById(R.id.backButton);
        scoreId=findViewById(R.id.scoreView);
        next=findViewById(R.id.nextButton);
        sharedPreferences=getSharedPreferences("score",Context.MODE_PRIVATE);
        score=sharedPreferences.getInt("score",0);
        roundedHorizontalProgressBar=findViewById(R.id.quizTimer);
        qI= (int) (Math.random() * 800)+0;
        scoreId.setText("Score= "+score);
        t4=findViewById(R.id.exit_from_question);
        t1=findViewById(R.id.question);
        scoreId.setText("HighScore= "+score);
        newSc.setText("Score  "+tempSc);
        newQuestion();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(qI>0) {
                    countDownTimer.cancel();
                    qI--;
                    remaining.setTextColor(Color.WHITE);
                    countDownTimer.start();
                    timer=10;
                    c3.setCardBackgroundColor(Color.WHITE);
                    c2.setCardBackgroundColor(Color.WHITE);
                    c3.setEnabled(true);
                    c2.setEnabled(true);
                    newQuestion();
                }
            }
        });
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashBoardActivity.this,HomeActivity.class));
            }
        });
        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp=timer;
                countDownTimer.cancel();
                android.app.AlertDialog.Builder builder=new AlertDialog.Builder(DashBoardActivity.this);
                builder.setMessage("Are you Sure?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        timer=10;
                        countDownTimer.start();
                    }
                });
                builder.setCancelable(false);
                builder.create();
                builder.show();
            }
        });

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    countDownTimer.cancel();
                    qI++;
                    remaining.setTextColor(Color.WHITE);
                    countDownTimer.start();
                    timer=10;
                    c3.setCardBackgroundColor(Color.WHITE);
                    c2.setCardBackgroundColor(Color.WHITE);
                    c3.setEnabled(true);
                    c2.setEnabled(true);
                    newSc.setText("Score  "+tempSc);
                    newQuestion();
                }
            });

        remaining=findViewById(R.id.remainingtime);
        countDownTimer=new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer=timer-1;
                roundedHorizontalProgressBar.setProgress(timer);
                remaining.setText(String.valueOf(timer));
                if(timer<4){
                    remaining.setTextColor(Color.RED);
                }
            }



            @Override
            public void onFinish() {
                Dialog dialog=new Dialog(DashBoardActivity.this,R.style.Dialog);
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                dialog.setContentView(R.layout.timeout_dialog);
                dialog.show();
                dialog.findViewById(R.id.try_again).setOnClickListener(v -> {
                    dialog.dismiss();

                });
                dialog.findViewById(R.id.exit_main).setOnClickListener(v -> finish());
            }
        }.start();
    }
    public void newQuestion() {
        ArrayList<Questions> ar = new Repository().getQuestions(arrayList -> {

            t1.setText(arrayList.get(qI).getQuestion());
            boolean option = arrayList.get(qI).isAnswer();
            c2.setOnClickListener(v -> {
                if (option) {
                    c2.setCardBackgroundColor(Color.GREEN);
                    tempSc+=100;
                } else {
                    c2.setCardBackgroundColor(Color.RED);
                    tempSc=tempSc>0?tempSc-50:0;
                }
                scoreId.setText("HighScore= "+score);
                newSc.setText("Score "+tempSc);
                countDownTimer.cancel();
            });
            c3.setOnClickListener(v -> {
                if (option) {
                    c3.setCardBackgroundColor(Color.RED);
                    tempSc=tempSc>0?tempSc-50:0;
                } else {
                    c3.setCardBackgroundColor(Color.GREEN);
                    tempSc+=100;
                }
                scoreId.setText("HighScore= "+score);
                c3.setEnabled(false);
                c2.setEnabled(false);
                countDownTimer.cancel();
                if(score<tempSc){
                    Toast.makeText(DashBoardActivity.this,"Now You have reached high score",Toast.LENGTH_LONG).show();
                    score=tempSc;
                    scoreId.setText("HighScore= "+score);
                    sharedPreferences.edit().putInt("score",score).apply();
                }
                newSc.setText("Score "+tempSc);
            });
        });
        if(score<tempSc){
            Toast.makeText(DashBoardActivity.this,"Now You have reached high score",Toast.LENGTH_LONG).show();
            score=tempSc;
            scoreId.setText("HighScore= "+score);
            sharedPreferences.edit().putInt("score",score).apply();
        }
    }

}