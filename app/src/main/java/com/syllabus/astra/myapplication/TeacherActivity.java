package com.syllabus.astra.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TeacherActivity extends AppCompatActivity {
    private Spinner spinner;
    private TextView termsText;
    private List<String> termsList;
    private ArrayAdapter arrayAdapter;

    public void returnOnclick(View view){
        Intent intent=new Intent();
        intent.setClass(this,MainActivity.class);
        startActivity(intent);
    }
    public void tableOnclick(View view){
        Intent intent=new Intent();
        TextView textView=(TextView) findViewById(R.id.titleText);
        String title=(String)textView.getText();
        intent.putExtra("title",title);
        intent.setClass(this,TimetableActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        spinner = (Spinner) findViewById(R.id.termsSpinner);
        termsText = (TextView) findViewById(R.id.termsText);
        //验证码部分隐藏
        LinearLayout linearLayout=(LinearLayout) findViewById(R.id.identifyLinear);
        linearLayout.setVisibility(View.GONE);


        //设置数据源，使用arrayList填充Spinner
        termsList = new ArrayList<String>();
        termsList.add("2016-2017学年第一学期");
        termsList.add("2016-2017学年第二学期");
        //新建ArrayAdapter(定义适配器，添加资源：为下拉列表定义一个数组适配器ArrayAdapte)
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, termsList);
        // 3. 设置适配器的下拉菜单的样式
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // 4. 为Spinner下拉列表加载适配器
        spinner.setAdapter(arrayAdapter);
        // 5. 为Spinner设置监听器
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String termName = (String) arrayAdapter.getItem(position);
                termsText.setText("" + termName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}

