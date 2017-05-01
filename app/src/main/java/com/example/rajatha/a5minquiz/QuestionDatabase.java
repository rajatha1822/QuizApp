package com.example.rajatha.a5minquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Sampler;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Rajatha on 27-Apr-2017.
 */

public class QuestionDatabase extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION=2;
    public static final String DATABASE_NAME="5MinQuiz1";
    private static final String TAG="Question Database";
    private static final int  ZERO=0;
    private static final int  ONE=1;
    private static final String SQL_CREATE_ENTRIES="CREATE TABLE " + DataBaseContract.QuestionTable.TABLE_NAME + " (" +
            DataBaseContract.QuestionTable._ID + " INTEGER PRIMARY KEY," +
            DataBaseContract.QuestionTable.COLUMN_QUESTION + " TEXT," +
            DataBaseContract.QuestionTable.COLUMN_TIMER + " TEXT," +
            DataBaseContract.QuestionTable.COLUMN_ANSWER + " TEXT," +
            DataBaseContract.QuestionTable.COLUMN_OPT1 + " TEXT," +
            DataBaseContract.QuestionTable.COLUMN_OPT2 + " TEXT," +
            DataBaseContract.QuestionTable.COLUMN_OPT3 + " TEXT);";

    private static final String SQL_CREATE_USERTABLE="CREATE TABLE " + DataBaseContract.UserTable.TABLE_NAME + " (" +
            DataBaseContract.UserTable._ID + " INTEGER PRIMARY KEY," +
            DataBaseContract.UserTable.COLUMN_USER + " TEXT," +
            DataBaseContract.UserTable.COLUMN_PASS + " TEXT," +
            DataBaseContract.UserTable.COLUMN_ROUNDS + " TEXT," +
            DataBaseContract.UserTable.COLUMN_SCORE + " TEXT);";

    private static final String SQL_DELETE_ENTRIES="DROP TABLE IF EXISTS " + DataBaseContract.QuestionTable.TABLE_NAME;
    private static final String SQL_DELETE_USERTABLE="DROP TABLE IF EXISTS " + DataBaseContract.UserTable.TABLE_NAME;

    public QuestionDatabase(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_ENTRIES);
        Log.i(TAG,"Question Table created ");
        db.execSQL(SQL_CREATE_USERTABLE);
        Log.i(TAG,"User Table created ");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_DELETE_USERTABLE);
        onCreate(db);
    }

    public void AddQuestionDatabase(QuestionDatabase mDbHelper,String Question,String Timer,String Answer,String OPT1,String OPT2,String OPT3){
        Log.i(TAG,"You are adding the Question");
        SQLiteDatabase db=mDbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(DataBaseContract.QuestionTable.COLUMN_QUESTION,Question);
        values.put(DataBaseContract.QuestionTable.COLUMN_TIMER,Timer);
        values.put(DataBaseContract.QuestionTable.COLUMN_ANSWER,Answer);
        values.put(DataBaseContract.QuestionTable.COLUMN_OPT1,OPT1);
        values.put(DataBaseContract.QuestionTable.COLUMN_OPT2,OPT2);
        values.put(DataBaseContract.QuestionTable.COLUMN_OPT3,OPT3);
        long newRowId=db.insert(DataBaseContract.QuestionTable.TABLE_NAME,null,values);
    }

    public void DeleteQuestion(QuestionDatabase mDbHelper,int Id){
        Log.i(TAG,"You are deleteing the Question");
        SQLiteDatabase db=mDbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + DataBaseContract.QuestionTable.TABLE_NAME + " WHERE _ID = " + Id);


    }

    public void DeleteUser(QuestionDatabase mDbHelper,int Id){

        SQLiteDatabase db=mDbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + DataBaseContract.UserTable.TABLE_NAME + " WHERE _ID = " + Id);
    }

    public void RegisterUser(QuestionDatabase mDbHelper,String User,String Password){
        Log.i(TAG,"User is registered");
        SQLiteDatabase db=mDbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(DataBaseContract.UserTable.COLUMN_USER,User);
        values.put(DataBaseContract.UserTable.COLUMN_PASS,Password);
        values.put(DataBaseContract.UserTable.COLUMN_ROUNDS,ZERO);
        values.put(DataBaseContract.UserTable.COLUMN_SCORE,ZERO);
        long newRowId=db.insert(DataBaseContract.UserTable.TABLE_NAME,null,values);

    }

    public void UpdateUserStats(QuestionDatabase DOP,String name, String pass,int stat,int rnd){
        SQLiteDatabase SQ= DOP.getWritableDatabase();
        String selection = DataBaseContract.UserTable.COLUMN_USER + " LIKE ? AND "+ DataBaseContract.UserTable.COLUMN_PASS + " LIKE ?";
        String args[]={name,pass};
        ContentValues values= new ContentValues();
        values.put(DataBaseContract.UserTable.COLUMN_SCORE,stat);
        values.put(DataBaseContract.UserTable.COLUMN_ROUNDS,rnd);
        SQ.update(DataBaseContract.UserTable.TABLE_NAME,values,selection,args);
    }

}
