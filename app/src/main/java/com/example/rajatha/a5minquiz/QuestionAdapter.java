package com.example.rajatha.a5minquiz;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Rajatha on 28-Apr-2017.
 */

public class QuestionAdapter extends BaseAdapter {
 ArrayList<Question> mQuestions;
    Context mContext;
    static final String DELETE="Do you want to delete the Question?";

    public QuestionAdapter(Context context,ArrayList<Question> ques){
        mContext=context;
        mQuestions=ques;
    }

    @Override
    public int getCount() {
        return mQuestions.size();
    }

    @Override
    public Object getItem(int position) {
        return mQuestions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void deleteQuestionDb(int position){
        QuestionDatabase mdHelper=new QuestionDatabase(mContext);
        mdHelper.DeleteQuestion(mdHelper,position);
    }

    public void deleteQuestion(int position){
        Question mObject=(Question)getItem(position);
        deleteQuestionDb(mObject.getmID());
        mQuestions.remove(position);
        notifyDataSetChanged();
    }

    public ArrayList<Question> getList(){

        return new ArrayList<Question>();
    }
    public void addQuestion(Question ques){
        mQuestions.add(ques);
        notifyDataSetChanged();
    }
    public void AlertDialog(final int position){

        AlertDialog.Builder alertDialog= new AlertDialog.Builder(mContext);
        alertDialog.setTitle(DELETE);
        TextView myMsg = new TextView(mContext);
        myMsg.setText(DELETE);
        myMsg.setGravity(Gravity.CENTER_HORIZONTAL);
        myMsg.setTextSize(20);
        myMsg.setTextColor(Color.WHITE);
        alertDialog.setView(myMsg);
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
                deleteQuestion(position);
            }
        });
        AlertDialog dialog=alertDialog.create();

        alertDialog.show();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Question mNewQuestion=mQuestions.get(position);
        View mView;
        LayoutInflater layoutInflater=(LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView==null){

            mView=layoutInflater.inflate(R.layout.list_view,null);
        }
        else {

            mView=convertView;
        }
        ImageButton delete=(ImageButton) mView.findViewById(R.id.Delete);
        delete.setFocusable(false);
        delete.setFocusableInTouchMode(false);
        delete.setTag(position);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Integer index = (Integer) v.getTag();
                AlertDialog(position);
            }
        });
        TextView question =(TextView)mView.findViewById(R.id.listQ);
        question.setText(String.valueOf(position+1)+ "." + mNewQuestion.getmQuestion());
        return mView;

    }
}
