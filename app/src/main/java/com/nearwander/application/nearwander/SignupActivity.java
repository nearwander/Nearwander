package com.nearwander.application.nearwander;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    private EditText txtEmail;
    private EditText txtPassword;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        txtEmail = (EditText) findViewById(R.id.emailRegist);
        txtPassword = (EditText) findViewById(R.id.passwordRegist);
        firebaseAuth = FirebaseAuth.getInstance();

    }

    public void btnSignUpUser_Click(View v){

        final ProgressDialog progressDialog = ProgressDialog.show(SignupActivity.this, "Please wait...", "Processing", true);
        (firebaseAuth.createUserWithEmailAndPassword(txtEmail.getText().toString(), txtPassword.getText().toString())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();

                if(task.isSuccessful()){
                    Toast.makeText(SignupActivity.this, "Registration successful", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(SignupActivity.this, MainActivity.class);
                    startActivity(i);
                }else {
                    Log.e("ERROR", task.getException().toString());
                    Toast.makeText(SignupActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
