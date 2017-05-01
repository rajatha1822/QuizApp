package com.example.rajatha.a5minquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class UserDetails extends AppCompatActivity {

    QuestionDatabase mhelper;
    private ArrayList<User> mUserList;
    private UserAdapter madapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        mUserList=new ArrayList<User>();
        ListView listView=(ListView)findViewById(R.id.UserListView);
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

        madapter=new UserAdapter(this,mUserList);
        listView.setAdapter(madapter);

    }
}
