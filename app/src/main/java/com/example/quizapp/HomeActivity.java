package com.example.quizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.signins.GoogleSign_in;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.material.snackbar.Snackbar;

public class HomeActivity extends AppCompatActivity {
    CardView quiz,exit,share,rate;
    Button btn;
    TextView welcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        rate=findViewById(R.id.rateCard);
        welcome=findViewById(R.id.welcomeBack);
        share=findViewById(R.id.share_card);
        quiz=findViewById(R.id.quizCard);
        exit=findViewById(R.id.exit_card);
        share.setEnabled(false);
        rate.setEnabled(false);
        btn=findViewById(R.id.signOutHere);
        welcome.setText("Welcome  "+new GoogleSign_in().userName());
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,DashBoardActivity.class));
                finish();
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(HomeActivity.this);
                builder.setMessage("Are you Sure").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                }).setNegativeButton("No",null);
                builder.show();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(GoogleSign_in.mGoogleSignInClient!=null) {
                    GoogleSign_in.mGoogleSignInClient.signOut();
                }
                Toast.makeText(HomeActivity.this, "Signed Out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HomeActivity.this,MainScreen.class));
            }
        });
    }
}