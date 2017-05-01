package com.example.rajatha.a5minquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class QuestionDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_display);
        TextView Question=(TextView)findViewById(R.id.mQ);

        Intent intent=getIntent();
        Question mQuestion=intent.getParcelableExtra("Question");
        Question.setText(mQuestion.getmQuestion()
                +"\n\n" + "Answer: " + mQuestion.getmAnswer()
                +"\n\n" + "Timer Value: " + mQuestion.getmTimerValue()
                +"\n\n" + "1:" + mQuestion.getmOp1()
                +"\n\n" + "2:" + mQuestion.getmOp2()
                +"\n\n" + "3:" + mQuestion.getmOp3());
    }
}
