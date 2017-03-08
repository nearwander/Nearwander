package com.nearwander.application.nearwander;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SetupProfile extends AppCompatActivity {

    EditText input;
    Button check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_profile);

        input = (EditText) findViewById(R.id.user_profile_name);
        input = (EditText) findViewById(R.id.your_id);
        input = (EditText) findViewById(R.id.email);
        input = (EditText) findViewById(R.id.phone);
        input = (EditText) findViewById(R.id.status);
        input = (EditText) findViewById(R.id.test);
        check = (Button) findViewById(R.id.button2);

        check.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View arg0){
                if(input.getText().toString().equals("")){
                    Toast.makeText(SetupProfile.this, "Please Insert", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(SetupProfile.this,"SAVED!!",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
