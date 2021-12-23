package com.example.quizapp.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class SignUpFragment extends Fragment {
    FirebaseAuth mAuth;
    EditText email,pass,pass2;
    Button btn;
    String mail,p1,p2;
    ProgressDialog progressDialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.signup_xml, container, false);
        email = v.findViewById(R.id.emailIdSign);
        pass = v.findViewById(R.id.password2);
        pass2 = v.findViewById(R.id.confirm_password);
        mail = email.getText().toString();
        btn = v.findViewById(R.id.signUp);
        p1 = pass.getText().toString();
        p2 = pass2.getText().toString();
        progressDialog=new ProgressDialog(getContext());
        mAuth=FirebaseAuth.getInstance();
        btn.setOnClickListener(v1 -> {
            progressDialog.setMessage("Creating account...");
            progressDialog.show();
            registerNewUser();
        });
        return v;
    }

    private void registerNewUser() {

//        mAuth.createUserWithEmailAndPassword(mail, p1)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            Intent intent = new Intent(getContext(), HomeActivity.class);
//                            startActivity(intent);
//                        }
//                        else {
//                            Toast.makeText(getContext(),"error",Toast.LENGTH_LONG).show();
//                        }
//                        progressDialog.dismiss();
//                    }
//                });
    }


}
