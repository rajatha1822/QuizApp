package com.example.rajatha.a5minquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class QuizMasterLogin extends AppCompatActivity {

    String User;
    String mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_master_login);

        final EditText UserName=(EditText)findViewById(R.id.LUsername);
        final EditText Password=(EditText)findViewById(R.id.LPassword);
        Button Login=(Button)findViewById(R.id.Login1);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User=UserName.getText().toString();
                mPassword=Password.getText().toString();

                if(User.equals("root") && mPassword.equals("root")){

                  Intent master=new Intent(QuizMasterLogin.this,QuizMaster.class);
                    startActivity(master);

                }else
                {

                    Toast.makeText(getBaseContext(),"Wrong Credential!!!!!",Toast.LENGTH_SHORT).show();

                }
            }
        });



    }
}
