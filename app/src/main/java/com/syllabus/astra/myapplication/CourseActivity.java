package com.syllabus.astra.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CourseActivity extends AppCompatActivity {
    private Spinner spinner;
    private TextView termsText;
    private List<String> termsList;
    private ArrayAdapter arrayAdapter;
    private Button button;
    private ImageView identifyImageView;
    private EditText identifyEditText;
    private Spinner courseSpinner;
    private TextView courseText;
    private ArrayAdapter courseAdapter;
    Handler handler;
    /*
    Sel_XNXQ:20161 学年学期
    Sel_KC:601960 课程号
    gs:1 格式一
    txt_yzm:pahc 验证码
    */
    String Sel_XNXQ;
    String Sel_KC;
    String gs = "1";
    String txt_yzm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        spinner = (Spinner) findViewById(R.id.termsSpinner);
        termsText = (TextView) findViewById(R.id.termsText);
        courseSpinner = (Spinner) findViewById(R.id.campusSpinner);
        courseText = (TextView) findViewById(R.id.coursesText);

        //验证码部分隐藏
        //LinearLayout linearLayout=(LinearLayout) findViewById(R.id.identifyLinear);
        //linearLayout.setVisibility(View.GONE);

        //网络初始化获取html和cookie
        final RequireInfomation requireInfomation = new RequireInfomation("http://211.67.107.38/jwweb/ZNPK/TeacherKBFB.aspx");

        //绑定控件
        identifyImageView = (ImageView) findViewById(R.id.identifyImage);
        identifyEditText = (EditText) findViewById(R.id.identfyEdit);
        button = (Button) findViewById(R.id.baseButton);

        identifyImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Bitmap bitmap = requireInfomation.getPicture();
                        CourseActivity.this.runOnUiThread(new Runnable() {
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
            }
        };

        button.setOnClickListener(new View.OnClickListener() {
            String unResolvedHtml;
            @Override
            public void onClick(View view) {
                if(termsText.getText().toString().equals("2016-2017学年第一学期")) {
                    Sel_XNXQ = "20161";
                }
                else {
                    Sel_XNXQ = "20160";
                }
                txt_yzm = identifyEditText.getText().toString();
                Sel_KC = "601960";
                final Map<String, String> httpParam = new HashMap<String, String>();
                httpParam.put("Sel_XNXQ", Sel_XNXQ);
                httpParam.put("Sel_KC", Sel_KC);
                httpParam.put("gs", gs);
                httpParam.put("txt_yzm", txt_yzm);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        unResolvedHtml = requireInfomation.getClassTableInfo(httpParam);
                        Message message = new Message();
                        Bundle bundle = new Bundle();
                        bundle.putString("requireHtml", "ok");
                        message.setData(bundle);
                        handler.sendMessage(message);
                    }
                }).start();
            }
        });

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

        List<String> courseList = new ArrayList<>();
        //数据库在此添加

        courseAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, courseList);
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseSpinner.setAdapter(courseAdapter);
        courseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String course = (String) courseAdapter.getItem(i);
                courseText.setText("" + course);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




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
