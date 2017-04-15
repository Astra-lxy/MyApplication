package com.syllabus.astra.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //跳转
    public void teacherOnclick(View view){
        Intent intent=new Intent();
        intent.setClass(this,TeacherActivity.class);
        startActivity(intent);
    }
    public void roomOnclick(View view){
        Intent intent=new Intent();
        intent.setClass(this,RoomActivity.class);
        startActivity(intent);
    }
    public void courseOnclick(View view){
        Intent intent=new Intent();
        intent.setClass(this,CourseActivity.class);
        startActivity(intent);
    }
}
