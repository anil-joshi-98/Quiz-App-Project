package com.example.quizapp.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.quizapp.HomeActivity;
import com.example.quizapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment {
    EditText email,pass;
    String mail,password;
    Button btn;
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    View v;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.login_xml,container,false);
        email=v.findViewById(R.id.emailId);
        pass=v.findViewById(R.id.password);
        btn=v.findViewById(R.id.idSignIn);
        mail=email.getText().toString();
        password=pass.getText().toString();
        mAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(getContext());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Signing in...");
                progressDialog.create();
                progressDialog.show();
                signIn();
            }
        });
        return v;
    }

    private void signIn() {
//        mAuth.signInWithEmailAndPassword(mail, password)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(getContext(), "Login successful!", Toast.LENGTH_LONG).show();
//                            Intent intent = new Intent(getContext(), HomeActivity.class);
//                            startActivity(intent);
//                        }
//                        else {
//                            Toast.makeText(getContext(), "Login failed! Please try again later", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });
        progressDialog.dismiss();
    }

}
