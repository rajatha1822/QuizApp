package com.example.rajatha.a5minquiz;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rajatha on 28-Apr-2017.
 */

public class QuestionReaderAsync extends AsyncTask<Context,Void,ArrayList<Question>> {

    ArrayList<Question> questionList=new ArrayList<>();

    @Override
    protected ArrayList<Question> doInBackground(Context...params) {

        QuestionDatabase mhelper= new QuestionDatabase(params[0]);
        String selectQuery="SELECT  * FROM " + DataBaseContract.QuestionTable.TABLE_NAME;
        SQLiteDatabase dbase = mhelper.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Question newQuestion = new Question();

                newQuestion.setmID(cursor.getInt(0));
                newQuestion.setQuestion(cursor.getString(1));
                newQuestion.setmTimerValue(cursor.getString(2));
                newQuestion.setmAnswer(cursor.getString(3));
                newQuestion.setmOp1(cursor.getString(4));
                newQuestion.setmOp2(cursor.getString(5));
                newQuestion.setmOp3(cursor.getString(6));
                questionList.add(newQuestion);
            }while (cursor.moveToNext());

        }

        return questionList;
    }

    @Override
    protected void onPostExecute(ArrayList<Question> questions) {
        super.onPostExecute(questions);
    }
}

