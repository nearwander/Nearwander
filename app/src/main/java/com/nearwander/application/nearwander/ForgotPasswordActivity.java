package com.nearwander.application.nearwander;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    private static final String TAG = "FORGOTPASSWORD_ACTIVITY";
    private EditText txtEmail;
    private Button btnForgot;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
    }

    public void btnForgotPasswordUser_Click(View v){

        txtEmail = (EditText) findViewById(R.id.forgotEmail);
        firebaseAuth = FirebaseAuth.getInstance();
        String emailAddress = txtEmail.getText().toString();

        firebaseAuth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Email sent.");
                            Toast.makeText(ForgotPasswordActivity.this, "Email sent.", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.d(TAG, "Error.");
                            Toast.makeText(ForgotPasswordActivity.this, "Email is not registered.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
