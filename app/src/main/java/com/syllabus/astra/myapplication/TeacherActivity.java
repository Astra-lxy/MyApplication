package com.syllabus.astra.myapplication;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeacherActivity extends AppCompatActivity {
    private Spinner spinner;
    private TextView termsText;
    private List<String> termsList;
    private ArrayAdapter arrayAdapter;
    private Button button;
    private ImageView identifyImageView;
    private EditText identifyEditText;
    private Spinner teacherNameSpinner;
    private EditText teacherNameEditText;
    Handler handler;

    String Sel_XNXQ; //学年学期
    String Sel_JS; //教师id
    String type = "1"; //默认格式一
    String txt_yzm; //验证码


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
        teacherNameSpinner = (Spinner) findViewById(R.id.namesSpinner);
        teacherNameEditText = (EditText) findViewById(R.id.teacherEdit);


        //网络初始化获取html和cookie
        final RequireInfomation requireInfomation = new RequireInfomation("http://211.67.107.38/jwweb/ZNPK/TeacherKBFB.aspx");

        //绑定控件
        identifyImageView = (ImageView) findViewById(R.id.identifyImage);
        identifyEditText = (EditText) findViewById(R.id.identfyEdit);
        button = (Button) findViewById(R.id.baseButton);

        //点击图片获取刷新验证码
        identifyImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Bitmap bitmap = requireInfomation.getPicture();
                        TeacherActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                identifyImageView.setImageBitmap(bitmap);
                                identifyEditText.setText("");
                            }
                        });
                    }
                }).start();
            }
        });


        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.i("handler----", "ok");
            }
        };

        //点击按钮事件，首先查询数据库，如果数据库没有，则从网络获取
        button.setOnClickListener(new View.OnClickListener() {
            String unResolvedHtml;
            @Override
            public void onClick(View view) {

                /*请求示例参数
                Sel_XNXQ:20161
                Sel_JS:0001016
                chk_bhhj:1    不含实践安排
                type:2
                txt_yzm:4549*/



                //获取各个控件的数据
                if(termsText.getText().toString().equals("2016-2017学年第一学期")) {
                    Sel_XNXQ = "20161";
                }
                else {
                    Sel_XNXQ = "20160";
                }
                txt_yzm = identifyEditText.getText().toString();
                Sel_JS = "0001016";
                final Map<String, String> httpParam = new HashMap<String, String>();
                httpParam.put("Sel_XNXQ", Sel_XNXQ);
                httpParam.put("Sel_JS", Sel_JS);
                httpParam.put("chk_bhhj", "1");
                httpParam.put("type", type);
                httpParam.put("txt_yzm", txt_yzm);


                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        unResolvedHtml = requireInfomation.getClassTableInfo(httpParam);
                        Log.i("html-----", unResolvedHtml);

                        Message message = new Message();
                        Bundle bundle = new Bundle();
                        bundle.putString("requireHtml", "ok");
                        message.setData(bundle);
                        handler.sendMessage(message);
                    }
                }).start();
            }
        });


        //验证码部分隐藏
        //LinearLayout linearLayout=(LinearLayout) findViewById(R.id.identifyLinear);
        //linearLayout.setVisibility(View.GONE);



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

        List<String> teachersNameList = new ArrayList<>();
        //此处从数据库获取教师姓名数据加入到list
        //
        ///
        //
        //

        final ArrayAdapter<String> teacherAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, teachersNameList);
        teacherAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teacherNameSpinner.setAdapter(teacherAdapter);
        teacherNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String teacherName = teacherAdapter.getItem(i);

                //通过teacherName获取teacherID并且赋值

                Sel_JS = "";
                //teacherNameEditText.setText(teacherName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}

