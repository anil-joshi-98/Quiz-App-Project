package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quizapp.fragments.LoginAdapter;
import com.example.quizapp.signins.GoogleSign_in;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class MainScreen extends AppCompatActivity {

    TabLayout tabLayout;
    private FirebaseAuth mAuth;
    ViewPager viewPager;
    TextView skip;
    ImageView gg;
    float v=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        tabLayout=findViewById(R.id.tabLayout);
        viewPager=findViewById(R.id.viewPager);
        gg=findViewById(R.id.google);
        gg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainScreen.this, GoogleSign_in.class));
            }
        });


        skip=findViewById(R.id.skipForNow);
        tabLayout.addTab(tabLayout.newTab().setText("LOGIN"));
        tabLayout.addTab(tabLayout.newTab().setText("SIGNUP"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        LoginAdapter loginAdapter=new LoginAdapter(getSupportFragmentManager(),MainScreen.this,tabLayout.getTabCount());
        viewPager.setAdapter(loginAdapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        gg.setTranslationY(300);
        gg.setAlpha(v);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainScreen.this,HomeActivity.class));
                finish();
            }
        });
        gg.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(400).start();
    }

}