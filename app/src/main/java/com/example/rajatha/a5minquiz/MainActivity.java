package com.example.rajatha.a5minquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button QuizTaker=(Button)findViewById(R.id.QuizTaker);
        Button QuizMaster=(Button)findViewById(R.id.QuizMaster);
        QuizTaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent qtIntent= new Intent(MainActivity.this,QuizTaker.class);
                startActivity(qtIntent);

            }
        });

        QuizMaster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent qmIntent= new Intent(MainActivity.this,QuizMasterLogin.class);
                startActivity(qmIntent);
            }
        });
    }
}
