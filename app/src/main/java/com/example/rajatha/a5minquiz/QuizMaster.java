package com.example.rajatha.a5minquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QuizMaster extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_master);
        final Button AddQuestion=(Button)findViewById(R.id.AddQuestion);
        Button ViewQuestions=(Button)findViewById(R.id.ViewQuestion);
        Button UserDetails=(Button)findViewById(R.id.ViewUser);

        AddQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mAddQ=new Intent(QuizMaster.this,AddQuestion.class);
                startActivity(mAddQ);
            }
        });


        ViewQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mQuestionReader=new Intent(QuizMaster.this,QuestionReader.class);
                startActivity(mQuestionReader);

            }
        });

        UserDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mUserDetails=new Intent(QuizMaster.this,UserDetails.class);
                startActivity(mUserDetails);
            }
        });


    }
}
