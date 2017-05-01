package com.example.rajatha.a5minquiz;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * Created by Rajatha on 30-Apr-2017.
 */

public class UserReaderAsync extends AsyncTask<Context,Void,ArrayList<User>> {

    ArrayList<User> UserList = new ArrayList<User>();

    @Override
    protected ArrayList<User> doInBackground(Context... params) {

        QuestionDatabase mhelper = new QuestionDatabase(params[0]);
        String selectQuery = "SELECT  * FROM " + DataBaseContract.UserTable.TABLE_NAME;
        SQLiteDatabase dbase = mhelper.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                User newUser = new User();
                newUser.setmID(cursor.getInt(0));
                newUser.setmUser(cursor.getString(1));
                newUser.setmPassword(cursor.getString(2));
                newUser.setmRounds(cursor.getString(3));
                newUser.setmScore(cursor.getString(4));
                UserList.add(newUser);
            } while (cursor.moveToNext());

        }

        return UserList;
    }

    @Override
    protected void onPostExecute(ArrayList<User> users) {
        super.onPostExecute(users);
    }
}
