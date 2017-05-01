package com.example.rajatha.a5minquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QuizTaker extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_taker);

        Button Login = (Button) findViewById(R.id.LoginTaker);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(QuizTaker.this,QuizTakerLogin.class);
                startActivity(login);
            }
        });

        Button Signin =(Button)findViewById(R.id.SignIn);

        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Signin = new Intent(QuizTaker.this,QuizTakerRegister.class);
                startActivity(Signin);
            }
        });

    }
}
