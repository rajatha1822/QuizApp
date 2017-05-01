package com.example.rajatha.a5minquiz;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

public class Quizz extends AppCompatActivity {

    private ArrayList<Question> mQuizzQuestion;
    private ArrayList<User> mUser;
    private Question mQuestion;
    private int counter = 0;
    private TextView mQText,Timer,mScore;
    private RadioGroup Options;
    private RadioButton Op1,Op2,Op3;
    private static final String CONTINUE="Do you want to continue?";
    private static final String TIMESUP="Times UP!!!! \n Do you want to continue?";
    private Handler mhandler;
    private MyCountDownTimer myCountDownTimer;
    private int Round=1,Score=0;
    private String UserName,Password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);
        mQText = (TextView) findViewById(R.id.Quizz);
        Timer = (TextView) findViewById(R.id.Timer);
        mScore=(TextView)findViewById(R.id.Score);
        Options = (RadioGroup) findViewById(R.id.Options);
        Op1=(RadioButton)findViewById(R.id.radioButton1);
        Op2=(RadioButton)findViewById(R.id.radioButton2);
        Op3=(RadioButton)findViewById(R.id.radioButton3);
        mQuizzQuestion = new ArrayList<Question>();
        mhandler=new Handler();
        Bundle bundle = getIntent().getExtras();
        UserName = bundle.getString("UserName");
        Password = bundle.getString("Password");

        if (savedInstanceState == null) {

            QuestionReaderAsync mAddQues = new QuestionReaderAsync();
            UserReaderAsync mUserList=new UserReaderAsync();
            try {
                mQuizzQuestion = mAddQues.execute(getBaseContext()).get();
                mUser=mUserList.execute(getBaseContext()).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

         if (mUser.size()!=0){
             for (User nUser: mUser){

                 if (nUser.getmUser().equals(UserName) && nUser.getmPassword().equals(Password)){
                     Round=Integer.parseInt(nUser.getmRounds())+1;
                     Score=Integer.parseInt(nUser.getmScore());
                     mScore.setText("Rounds : " + Round
                                    +"\n" + " Score : " + Score);
                 }
             }

         }


        Collections.shuffle(mQuizzQuestion);

        if(mQuizzQuestion.size()!=0){
            mhandler.postDelayed(setQuestion,100);
        }
        Op1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCountDownTimer.cancel();
               if(mQuestion.getmAnswer().trim().equals(Op1.getText().toString().trim())){
                   StartColourAnimation(Op1);
                   Score++;
                   mScore.setText("Rounds : " + Round
                           +"\n" + " Score : " + Score);

               }else {
                   FalseColourAnimation(Op1);
               }
                Options.clearCheck();
                mhandler.postDelayed(setQuestion,1000);

            }
        });

        Op2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCountDownTimer.cancel();
                if(mQuestion.getmAnswer().trim().equals(Op2.getText().toString().trim())){
                    StartColourAnimation(Op2);
                    Score++;
                    mScore.setText("Rounds : " + Round
                            +"\n" + " Score : " + Score);
                }else {
                    FalseColourAnimation(Op2);
                }
                Options.clearCheck();
                mhandler.postDelayed(setQuestion,1000);

            }
        });

        Op3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCountDownTimer.cancel();
                if(mQuestion.getmAnswer().trim().equals(Op3.getText().toString().trim())){
                    StartColourAnimation(Op3);
                    Score++;
                    mScore.setText("Rounds : " + Round
                            +"\n" + " Score : " + Score);
                }else {
                    FalseColourAnimation(Op3);
                }
                Options.clearCheck();
                mhandler.postDelayed(setQuestion,1000);

            }
        });



    }

    @Override
    public void onBackPressed() {
        myCountDownTimer.cancel();
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        UpdateStats();
    }

    /* Method that is used to set the question for Quiz  */
    private Runnable setQuestion= new Runnable() {
        public void run() {
            if (counter < 5) {
                mQuestion = mQuizzQuestion.get(counter);
                mQText.setText(mQuestion.getmQuestion());
                Op1.setText(mQuestion.getmOp1());
                Op2.setText(mQuestion.getmOp2());
                Op3.setText(mQuestion.getmOp3());
                Long count=Long.parseLong(mQuestion.getmTimerValue().trim())*1000;
                myCountDownTimer = new MyCountDownTimer(count, 1000);
                myCountDownTimer.start();
                counter++;
            } else {
                myCountDownTimer.cancel();
                counter = 0;
                Collections.shuffle(mQuizzQuestion);
                AlertDialog();
            }
        }

    };
  /* Alert Dialog when count down timer times up */
    private void AlertDialog(){

        AlertDialog.Builder alertDialog= new AlertDialog.Builder(this);
        alertDialog.setTitle(CONTINUE);
        TextView myMsg = new TextView(this);
        myMsg.setText(CONTINUE);
        myMsg.setGravity(Gravity.CENTER_HORIZONTAL);
        myMsg.setTextSize(20);
        myMsg.setTextColor(Color.WHITE);
        alertDialog.setView(myMsg);
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
                UpdateStats();
               finish();
            }
        });
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
                Round++;
                mScore.setText("Rounds : " + Round
                        +"\n" + " Score : " + Score);
               dialog.dismiss();
                mhandler.postDelayed(setQuestion, 1000);

            }
        });
        AlertDialog dialog=alertDialog.create();
        alertDialog.show();
    }

    /* Alert Dialog when 5 questions are done */

    private void AlertDialogTime(){

        AlertDialog.Builder alertDialog= new AlertDialog.Builder(this);
        alertDialog.setTitle(CONTINUE);
        TextView myMsg = new TextView(this);
        myMsg.setText(TIMESUP);
        myMsg.setGravity(Gravity.CENTER_HORIZONTAL);
        myMsg.setTextSize(20);
        myMsg.setTextColor(Color.WHITE);
        alertDialog.setView(myMsg);
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
                UpdateStats();
                finish();
            }
        });
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
                Round++;
                mScore.setText("Rounds : " + Round
                        +"\n" + " Score : " + Score);
                dialog.dismiss();
                mhandler.postDelayed(setQuestion, 1000);
            }
        });
        AlertDialog dialog=alertDialog.create();
        alertDialog.show();
    }

    /* Animation for the Right Answer Selection */
    private void StartColourAnimation(View v){
        int colorStart =v.getSolidColor();
        int colorEnd=0xff00ff00;
        ValueAnimator colorAnim = ObjectAnimator.ofInt(v,"backgroundColor",colorStart,colorEnd);
        colorAnim.setDuration(500);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(1);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();

    }
    /* Animation for the Wrong Answer Selection */
    private void FalseColourAnimation(View v){
        int colorStart =v.getSolidColor();
        int colorEnd=0xffff0000;
        ValueAnimator colorAnim = ObjectAnimator.ofInt(v,"backgroundColor",colorStart,colorEnd);
        colorAnim.setDuration(500);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(1);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();


    }

    public void UpdateStats(){
        QuestionDatabase DP=new QuestionDatabase(getBaseContext());
        DP.UpdateUserStats(DP,UserName,Password,Score,Round);
    }



    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            Timer.setText("Seconds Remaining : " + millisUntilFinished / 1000);
        }

        @Override
        public void onFinish() {
            this.cancel();
            if (mQuestion.getmAnswer().equals(mQuestion.getmOp1())) {
                StartColourAnimation(Op1);
            } else if (mQuestion.getmAnswer().equals(mQuestion.getmOp2())) {
                StartColourAnimation(Op2);
            } else if (mQuestion.getmAnswer().equals(mQuestion.getmOp3())) {
                StartColourAnimation(Op3);

            }
            counter = 0;
            Collections.shuffle(mQuizzQuestion);
            AlertDialogTime();
        }
    }


}




