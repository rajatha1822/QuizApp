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
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Rajatha on 30-Apr-2017.
 */

public class UserAdapter extends BaseAdapter {

    ArrayList<User> mUserList;
    Context mContext;
    static final String DELETE="Do you want to delete this User?";

    public UserAdapter(Context context,ArrayList<User> mUser){
        mContext=context;
        mUserList=mUser;
    }

    @Override
    public int getCount() {
        return mUserList.size();
    }

    @Override
    public Object getItem(int position) {
        return mUserList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
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
              deleteUser(position);
            }
        });
        AlertDialog dialog=alertDialog.create();

        alertDialog.show();
    }
    public void deleteUser(int position){
        User mObject=(User) getItem(position);
        deleteUserDb(mObject.getmID());
        mUserList.remove(position);
        notifyDataSetChanged();
    }
    public void deleteUserDb(int position){
        QuestionDatabase mdHelper=new QuestionDatabase(mContext);
        mdHelper.DeleteUser(mdHelper,position);
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final User mNewUser=mUserList.get(position);
        View mView;
        LayoutInflater layoutInflater=(LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView==null){
            mView=layoutInflater.inflate(R.layout.user_list_view,null);


        }else {

            mView=convertView;
        }

        TextView details=(TextView)mView.findViewById(R.id.UserDetails);
        ImageButton delete=(ImageButton)mView.findViewById(R.id.deleteUser);
        delete.setFocusable(false);
        delete.setFocusableInTouchMode(false);
        delete.setTag(position);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog(position);
            }
        });
        details.setText("Username : " + mNewUser.getmUser()
                        + "\n" + "Password : " + mNewUser.getmPassword()
                        + "\n" + "Rounds : " + mNewUser.getmRounds()
                        + "\n" + "Score : " + mNewUser.getmScore());

        return mView;
    }
}
