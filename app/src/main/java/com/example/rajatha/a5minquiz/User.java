package com.example.rajatha.a5minquiz;

/**
 * Created by Rajatha on 30-Apr-2017.
 */

public class User {
    private String mUser;
    private String mPassword;
    private String mRounds;
    private String mScore;
    private int mID;

    public User (){

        mUser=" ";
        mPassword=" ";
        mRounds=" ";
        mScore=" ";
        mID=0;
    }

    public void setmUser(String mUser){
        this.mUser=mUser;
    }
    public void setmPassword(String mPassword){
        this.mPassword=mPassword;
    }
    public void setmRounds(String mRounds){
        this.mRounds=mRounds;
    }

    public void setmScore(String mScore){
        this.mScore=mScore;
    }
    public void setmID(int mID){
        this.mID=mID;
    }

    public String getmUser(){
        return mUser;
    }
    public String getmPassword(){
        return mPassword;
    }
    public String getmRounds(){
        return mRounds;
    }
    public String getmScore(){
        return mScore;
    }

    public int getmID(){
        return mID;
    }

}
