package com.example.rajatha.a5minquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class QuizTakerLogin extends AppCompatActivity {
    String User;
    String mPassword;
    private ArrayList<User> mUserList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_taker_login);
        final EditText UserName=(EditText)findViewById(R.id.LUsername);
        final EditText Password=(EditText)findViewById(R.id.LPassword);
        Button Login=(Button)findViewById(R.id.Login1);




        if(savedInstanceState==null){

            UserReaderAsync mUser=new UserReaderAsync();
            try {
                mUserList=mUser.execute(getBaseContext()).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User=UserName.getText().toString();
                mPassword=Password.getText().toString();
                boolean loginstatus=false;
                    if (mUserList.size()!=0){
                        for(User name : mUserList) {
                            if (name.getmUser().equals(User) && name.getmPassword().equals(mPassword) ) {

                                    loginstatus=true;
                            }
                        }

                        if(loginstatus){
                            Toast.makeText(getBaseContext(),"Welcome "+ User,Toast.LENGTH_LONG).show();
                            Intent master = new Intent(QuizTakerLogin.this, Quizz.class);
                            Bundle b = new Bundle();
                            b.putString("UserName",User);
                            b.putString("Password",mPassword);
                            master.putExtras(b);
                            startActivity(master);
                        }
                        else {

                            Toast.makeText(getBaseContext(),"Login failed",Toast.LENGTH_LONG).show();

                        }
                    }

            }
        });




    }
}
