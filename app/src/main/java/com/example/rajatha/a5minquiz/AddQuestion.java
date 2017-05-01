package com.example.rajatha.a5minquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddQuestion extends AppCompatActivity {
    String mQuestion,mAnswer,mTimerValue,mOpt1,mOpt2,mOpt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        final EditText Question=(EditText)findViewById(R.id.Question);
        final EditText Timer=(EditText)findViewById(R.id.TimerValue);
        final EditText Answer=(EditText)findViewById(R.id.Answer);
        final EditText Opt1=(EditText)findViewById(R.id.Opt1);
        final EditText Opt2=(EditText)findViewById(R.id.Opt2);
        final EditText Opt3=(EditText)findViewById(R.id.Opt3);
        Button Add=(Button)findViewById(R.id.Add);
        Button Delete=(Button)findViewById(R.id.Done);
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mQuestion=Question.getText().toString();
                mAnswer=Answer.getText().toString();
                mTimerValue=Timer.getText().toString();
                mOpt1=Opt1.getText().toString();
                mOpt2=Opt2.getText().toString();
                mOpt3=Opt3.getText().toString();
                QuestionDatabase mdHelper=new QuestionDatabase(getBaseContext());
                mdHelper.AddQuestionDatabase(mdHelper,mQuestion,mTimerValue,mAnswer,mOpt1,mOpt2,mOpt3);
                Toast.makeText(getBaseContext(),"Question has been added",Toast.LENGTH_SHORT).show();
                Question.setText(" ");
                Answer.setText(" ");
                Timer.setText(" ");
                Opt1.setText(" ");
                Opt2.setText(" ");
                Opt3.setText(" ");

            }
        });

    }
    public void AddToDataBase(String Question,String Answer,String Timer,String Op1,String Op2,String Op3){



    }
}
