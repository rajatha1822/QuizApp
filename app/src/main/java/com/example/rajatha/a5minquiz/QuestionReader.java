package com.example.rajatha.a5minquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class QuestionReader extends AppCompatActivity {

    QuestionDatabase mhelper;
    private ArrayList<Question> mQuestion;
    private QuestionAdapter madapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_reader);
        mQuestion=new ArrayList<Question>();
        ListView listView=(ListView)findViewById(R.id.listview);
        if(savedInstanceState==null){

            QuestionReaderAsync mAddQues=new QuestionReaderAsync();
            try {
                mQuestion=mAddQues.execute(getBaseContext()).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }

        madapter=new QuestionAdapter(this,mQuestion);
        listView.setAdapter(madapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Question mparcable=(Question) madapter.getItem(position);
                Intent parcableIntent= new Intent(QuestionReader.this,QuestionDisplay.class);
                parcableIntent.putExtra("Question",mparcable);
                startActivity(parcableIntent);

            }
        });



    }
}
