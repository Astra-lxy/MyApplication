package com.syllabus.astra.myapplication;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomActivity extends AppCompatActivity {
    private Spinner spinner;
    private TextView termsText;
    private List<String> termsList;
    private ArrayAdapter arrayAdapter;
    private Button button;
    private ImageView identifyImageView;
    private EditText identifyEditText;
    private Spinner schoolSpinner;
    private TextView schoolTextView;
    private Spinner buildingSpinner;
    private TextView buildingTextView;
    private Spinner roomSpinner;
    private TextView roomTextView;
    Handler handler;

    String Sel_XNXQ; //学期学年
    String rad_gs = "1"; //格式
    String txt_yzm; //验证码
    String Sel_XQ; //校区
    String Sel_JXL; //教学楼
    String Sel_ROOM; //教室

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        termsText = (TextView) findViewById(R.id.termsText);
        spinner = (Spinner) findViewById(R.id.termsSpinner);
        button = (Button) findViewById(R.id.baseButton);
        identifyImageView = (ImageView) findViewById(R.id.identifyImage);
        identifyEditText = (EditText) findViewById(R.id.identfyEdit);
        schoolSpinner = (Spinner) findViewById(R.id.campusSpinner);
        schoolTextView = (TextView) findViewById(R.id.campusText);
        buildingSpinner = (Spinner) findViewById(R.id.buldingsSpinner);
        buildingTextView = (TextView) findViewById(R.id.buildingsText);
        roomSpinner = (Spinner) findViewById(R.id.roomsSpinner);
        roomTextView = (TextView) findViewById(R.id.roomsText);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        };
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
                        RoomActivity.this.runOnUiThread(new Runnable() {
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


        button.setOnClickListener(new View.OnClickListener() {
            String unResolvedHtml;
            @Override
            public void onClick(View view) {
                //获取各个控件的数据
                if(termsText.getText().toString().equals("2016-2017学年第一学期")) {
                    Sel_XNXQ = "20161";
                }
                else {
                    Sel_XNXQ = "20160";
                }
                txt_yzm = identifyEditText.getText().toString();
                Sel_XQ = "1";
                Sel_JXL = "106";
                Sel_ROOM = "1060602";
                final Map<String, String> httpParam = new HashMap<String, String>();
                httpParam.put("Sel_XNXQ", Sel_XNXQ);
                httpParam.put("rad_gs", rad_gs);
                httpParam.put("txt_yzm", txt_yzm);
                httpParam.put("Sel_XQ", Sel_XQ);
                httpParam.put("Sel_JXL", Sel_JXL);
                httpParam.put("Sel_ROOM", Sel_ROOM);

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

        List<String> schoolList = new ArrayList<>();
        //从数据库查询schoolList
        final ArrayAdapter schoolAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, schoolList);
        schoolAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        schoolSpinner.setAdapter(schoolAdapter);
        schoolSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String termName = (String) schoolAdapter.getItem(position);
                schoolTextView.setText("" + termName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        List<String> buildingList = new ArrayList<>();
        //从数据库查询schoolList
        final ArrayAdapter buildingAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, buildingList);
        buildingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        buildingSpinner.setAdapter(buildingAdapter);
        buildingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String termName = (String) buildingAdapter.getItem(position);
                buildingTextView.setText("" + termName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        List<String> roomList = new ArrayList<>();
        //从数据库查询schoolList
        final ArrayAdapter roomAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, roomList);
        roomAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roomSpinner.setAdapter(roomAdapter);
        schoolSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String termName = (String) roomAdapter.getItem(position);
                roomTextView.setText("" + termName);
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
