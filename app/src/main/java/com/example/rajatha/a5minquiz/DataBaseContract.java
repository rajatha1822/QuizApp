package com.example.rajatha.a5minquiz;

import android.provider.BaseColumns;

/**
 * Created by Rajatha on 27-Apr-2017.
 */

public final class DataBaseContract {

    private DataBaseContract(){};


    public static class QuestionTable implements BaseColumns{

        public static final String TABLE_NAME="QuestionTable";
        public static final String COLUMN_QUESTION="Question";
        public static final String COLUMN_TIMER="TimerValue";
        public static final String COLUMN_ANSWER="Answer";
        public static final String COLUMN_OPT1="Option1";
        public static final String COLUMN_OPT2="Option2";
        public static final String COLUMN_OPT3="Option3";

    }

    public static class UserTable implements BaseColumns{


        public static final String TABLE_NAME="UserTable";
        public static final String COLUMN_USER="UserName";
        public static final String COLUMN_PASS="Password";
        public static final String COLUMN_ROUNDS="Rounds";
        public static final String COLUMN_SCORE="TotalScore";
    }
}
