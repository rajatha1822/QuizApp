package com.example.rajatha.a5minquiz;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rajatha on 28-Apr-2017.
 */

public class Question implements Parcelable {
    private String mQuestion;
    private String mTimerValue;
    private String mAnswer;
    private String mOp1;
    private String mOp2;
    private String mOp3;
    private int mID;

    public Question() {
        mQuestion = " ";
        mTimerValue = " ";
        mAnswer = " ";
        mOp1 = " ";
        mOp2 = " ";
        mOp3 = " ";
        mID = 0;
    }


    public Question(Parcel P) {
        mQuestion = P.readString();
        mTimerValue = P.readString();
        mAnswer = P.readString();
        mOp1 = P.readString();
        mOp2 = P.readString();
        mOp3 = P.readString();
        mID = P.readInt();

    }

    public void setQuestion(String mQuestion) {
        this.mQuestion = mQuestion;
    }

    public void setmTimerValue(String mTimerValue) {
        this.mTimerValue = mTimerValue;
    }

    public void setmAnswer(String mAnswer) {
        this.mAnswer = mAnswer;
    }

    public void setmOp1(String mOp1) {
        this.mOp1 = mOp1;
    }

    public void setmOp2(String mOp2) {
        this.mOp2 = mOp2;
    }

    public void setmOp3(String mOp3) {
        this.mOp3 = mOp3;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getmQuestion() {
        return mQuestion;
    }

    public String getmTimerValue() {

        return mTimerValue;
    }

    public String getmAnswer() {
        return mAnswer;
    }

    public String getmOp1() {
        return mOp1;
    }

    public String getmOp2() {
        return mOp2;
    }

    public String getmOp3() {
        return mOp3;
    }

    public int getmID() {

        return mID;
    }


    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mQuestion);
        dest.writeString(mTimerValue);
        dest.writeString(mAnswer);
        dest.writeString(mOp1);
        dest.writeString(mOp2);
        dest.writeString(mOp3);
        dest.writeInt(mID);
    }

    public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel source) {
            return new Question(source);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[0];
        }
    };
}

