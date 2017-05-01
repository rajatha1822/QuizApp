package com.example.rajatha.a5minquiz;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class QuizTakerRegister extends AppCompatActivity {

    Button Register1;
    EditText Ruser, Rpass, Rconf;
    String user_name,user_pass,con_pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_taker_register);

        Ruser = (EditText) findViewById(R.id.RUsername);
        Rpass = (EditText) findViewById(R.id.RPassword);
        Rconf = (EditText) findViewById(R.id.RCPassword);
        Register1 = (Button) findViewById(R.id.Register1);

        Register1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_name=Ruser.getText().toString();
                user_pass=Rpass.getText().toString();
                con_pass=Rconf.getText().toString();
                if(!(user_pass.equals(con_pass))){
                    Toast.makeText(getBaseContext(),"Passwords are not matching",Toast.LENGTH_SHORT).show();
                    Ruser.setText("");
                    Rpass.setText("");
                    Rconf.setText("");
                }
                else{

                    QuestionDatabase DB =new QuestionDatabase(getBaseContext());
                    DB.RegisterUser(DB,user_name,user_pass);
                    Toast.makeText(getBaseContext(),"Registration is successful",Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });
    }
    }

