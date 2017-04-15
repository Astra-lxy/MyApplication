package com.syllabus.astra.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        //验证码部分隐藏
        LinearLayout linearLayout=(LinearLayout) findViewById(R.id.identifyLinear);
        linearLayout.setVisibility(View.GONE);
    }
    public void returnOnclick(View view){
        Intent intent=new Intent();
        intent.setClass(this,MainActivity.class);
        startActivity(intent);
    }
    public void tableOnclick(View view){
        Intent intent=new Intent();
        intent.setClass(this,TimetableActivity.class);
        TextView textView=(TextView) findViewById(R.id.titleText);
        String title=(String)textView.getText();
        intent.putExtra("title",title);
        startActivity(intent);
    }
}
