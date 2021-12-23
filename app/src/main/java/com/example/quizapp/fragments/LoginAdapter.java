package com.example.quizapp.fragments;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class LoginAdapter extends FragmentPagerAdapter {
    private Context context;
    int totalTab;

    public LoginAdapter(@NonNull FragmentManager fm, Context context, int totalTab) {
        super(fm);
        this.context = context;
        this.totalTab = totalTab;
    }

    @Override
    public int getCount() {
        return totalTab;
    }

    public Fragment getItem(int position){
        switch (position){
            case 0:
                LoginFragment loginFragment=new LoginFragment();
                return loginFragment;
            case 1:
                SignUpFragment signUpFragment=new SignUpFragment();
                return signUpFragment;
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Sign In";
            case 1:
                return "Sign Up";
            default:
                return null;
        }
    }
}
