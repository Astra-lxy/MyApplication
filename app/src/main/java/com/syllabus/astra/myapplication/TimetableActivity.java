package com.syllabus.astra.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TimetableActivity extends AppCompatActivity {

    TextView xingqi1_jie1,xingqi1_jie2,xingqi1_jie3,xingqi1_jie4,xingqi1_jie5,xingqi1_jie6;
    TextView xingqi2_jie1,xingqi2_jie2,xingqi2_jie3,xingqi2_jie4,xingqi2_jie5,xingqi2_jie6;
    TextView xingqi3_jie1,xingqi3_jie2,xingqi3_jie3,xingqi3_jie4,xingqi3_jie5,xingqi3_jie6;
    TextView xingqi4_jie1,xingqi4_jie2,xingqi4_jie3,xingqi4_jie4,xingqi4_jie5,xingqi4_jie6;
    TextView xingqi5_jie1,xingqi5_jie2,xingqi5_jie3,xingqi5_jie4,xingqi5_jie5,xingqi5_jie6;
    TextView xingqi6_jie1,xingqi6_jie2,xingqi6_jie3,xingqi6_jie4,xingqi6_jie5,xingqi6_jie6;
    TextView xingqi7_jie1,xingqi7_jie2,xingqi7_jie3,xingqi7_jie4,xingqi7_jie5,xingqi7_jie6;

    //返回按钮
    public void returnOnclick(View view){
        TimetableActivity.this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetalbe);
        //获取标题填入
        Intent intent=getIntent();
        String title=intent.getStringExtra("title");
        TextView titleTextView=(TextView) findViewById(R.id.titleText);
        titleTextView.setText(title);


        xingqi1_jie1 = (TextView) findViewById(R.id.xingqi1_jie1);
        xingqi1_jie2 = (TextView) findViewById(R.id.xingqi1_jie2);
        xingqi1_jie3 = (TextView) findViewById(R.id.xingqi1_jie3);
        xingqi1_jie4 = (TextView) findViewById(R.id.xingqi1_jie4);
        xingqi1_jie5 = (TextView) findViewById(R.id.xingqi1_jie5);
        xingqi1_jie6 = (TextView) findViewById(R.id.xingqi1_jie6);

        xingqi2_jie1 = (TextView) findViewById(R.id.xingqi2_jie1);
        xingqi2_jie2 = (TextView) findViewById(R.id.xingqi2_jie2);
        xingqi2_jie3 = (TextView) findViewById(R.id.xingqi2_jie3);
        xingqi2_jie4 = (TextView) findViewById(R.id.xingqi2_jie4);
        xingqi2_jie5 = (TextView) findViewById(R.id.xingqi2_jie5);
        xingqi2_jie6 = (TextView) findViewById(R.id.xingqi2_jie6);

        xingqi3_jie1 = (TextView) findViewById(R.id.xingqi3_jie1);
        xingqi3_jie2 = (TextView) findViewById(R.id.xingqi3_jie2);
        xingqi3_jie3 = (TextView) findViewById(R.id.xingqi3_jie3);
        xingqi3_jie4 = (TextView) findViewById(R.id.xingqi3_jie4);
        xingqi3_jie5 = (TextView) findViewById(R.id.xingqi3_jie5);
        xingqi3_jie6 = (TextView) findViewById(R.id.xingqi3_jie6);

        xingqi4_jie1 = (TextView) findViewById(R.id.xingqi4_jie1);
        xingqi4_jie2 = (TextView) findViewById(R.id.xingqi4_jie2);
        xingqi4_jie3 = (TextView) findViewById(R.id.xingqi4_jie3);
        xingqi4_jie4 = (TextView) findViewById(R.id.xingqi4_jie4);
        xingqi4_jie5 = (TextView) findViewById(R.id.xingqi4_jie5);
        xingqi4_jie6 = (TextView) findViewById(R.id.xingqi4_jie6);

        xingqi5_jie1 = (TextView) findViewById(R.id.xingqi5_jie1);
        xingqi5_jie2 = (TextView) findViewById(R.id.xingqi5_jie2);
        xingqi5_jie3 = (TextView) findViewById(R.id.xingqi5_jie3);
        xingqi5_jie4 = (TextView) findViewById(R.id.xingqi5_jie4);
        xingqi5_jie5 = (TextView) findViewById(R.id.xingqi5_jie5);
        xingqi5_jie6 = (TextView) findViewById(R.id.xingqi5_jie6);

        xingqi6_jie1 = (TextView) findViewById(R.id.xingqi6_jie1);
        xingqi6_jie2 = (TextView) findViewById(R.id.xingqi6_jie2);
        xingqi6_jie3 = (TextView) findViewById(R.id.xingqi6_jie3);
        xingqi6_jie4 = (TextView) findViewById(R.id.xingqi6_jie4);
        xingqi6_jie5 = (TextView) findViewById(R.id.xingqi6_jie5);
        xingqi6_jie6 = (TextView) findViewById(R.id.xingqi6_jie6);


        //每个数组表示的是每天（从第一节到第八节课）的课程，每个数组的长度必须是8，没课的要设为“”
        String []xingqi1 = new String[]{
                "毛泽东思想与中国特色社会主义概论,严微微,3#301",
                "微机原理与接口技术,李晶晶,6#303",
                "汇编语言,王克成,12#404",
                "",
                "数据结构,刘春霞,5#211",
                "Flash与图像处理,李静,信息楼204",
                "",
                ""};
        String []xingqi2 = new String[]{
                "高等数学,魏淑芬,5#102",
                "",
                "微机原理与接口技术,李晶晶,6#303",
                "",
                "嵌入式系统开发与应用,王克成,12#404",
                "Android系统开发,王克成,12#404",
                "软件测试,王克成,12#404",
                "体育,王克成,12#404"};
        String []xingqi3 = new String[]{
                "Flash与图像处理,李静,信息楼204",
                "毛泽东思想与中国特色社会主义概论,严微微,3#301",
                "汇编语言,王克成,12#404",
                "计算机组成原理,夏春梅,8#203",
                "",
                "高等数学,魏淑芬,5#102",
                "编译原理,李强,6#404",
                "微机原理与接口技术,李晶晶,6#303"};
        String []xingqi4 = new String[]{
                "Flash与图像处理,李静,信息楼204",
                "数据结构,刘春霞,5#211",
                "",
                "毛泽东思想与中国特色社会主义概论,严微微,3#301",
                "汇编语言,王克成,12#404",
                "计算机组成原理,夏春梅,8#203",
                "",
                "微机原理与接口技术,李晶晶,6#303"};
        String []xingqi5 = new String[]{
                "",
                "汇编语言,王克成,12#404",
                "计算机组成原理,夏春梅,8#203",
                "编译原理,李强,6#404",
                "数据结构,刘春霞,5#211",
                "高等数学,魏淑芬,5#102",
                "毛泽东思想与中国特色社会主义概论,严微微,3#301",
                ""};
        String []xingqi6 = new String[]{
                "高等数学,魏淑芬,5#102",
                "编译原理,李强,6#404",
                "",
                "",
                "",
                "",
                "",
                ""};

        if(xingqi1[0]!=null&&(!xingqi1[0].equals(""))){//星期1第1节
            xingqi1_jie1.setText(xingqi1[0]);
            xingqi1_jie1.setBackgroundResource(R.drawable.bg_11);
        }else{
            xingqi1_jie1.setText(xingqi1[0]);
            xingqi1_jie1.setBackgroundColor(Color.TRANSPARENT);
        }
        if(xingqi1[1]!=null&&(!xingqi1[1].equals(""))){//星期1第2节
            xingqi1_jie2.setText(xingqi1[1]);
            xingqi1_jie2.setBackgroundResource(R.drawable.bg_12);
        }else{
            xingqi1_jie2.setText(xingqi1[1]);
            xingqi1_jie2.setBackgroundColor(Color.TRANSPARENT);
        }
        if(xingqi1[2]!=null&&(!xingqi1[2].equals(""))){//星期1第3节
            xingqi1_jie3.setText(xingqi1[2]);
            xingqi1_jie3.setBackgroundResource(R.drawable.bg_13);
        }else{
            xingqi1_jie3.setText(xingqi1[2]);
            xingqi1_jie3.setBackgroundColor(Color.TRANSPARENT);
        }
        if(xingqi1[3]!=null&&(!xingqi1[3].equals(""))){//星期1第4节
            xingqi1_jie4.setText(xingqi1[3]);
            xingqi1_jie4.setBackgroundResource(R.drawable.bg_14);
        }else{
            xingqi1_jie4.setText(xingqi1[3]);
            xingqi1_jie4.setBackgroundColor(Color.TRANSPARENT);
        }
        if(xingqi1[4]!=null&&(!xingqi1[4].equals(""))){//星期1第5节
            xingqi1_jie5.setText(xingqi1[4]);
            xingqi1_jie5.setBackgroundResource(R.drawable.bg_15);
        }else{
            xingqi1_jie5.setText(xingqi1[4]);
            xingqi1_jie5.setBackgroundColor(Color.TRANSPARENT);
        }
        if(xingqi1[5]!=null&&(!xingqi1[5].equals(""))){//星期1第6节
            xingqi1_jie6.setText(xingqi1[5]);
            xingqi1_jie6.setBackgroundResource(R.drawable.bg_16);
        }else{
            xingqi1_jie6.setText(xingqi1[5]);
            xingqi1_jie6.setBackgroundColor(Color.TRANSPARENT);
        }



        if(xingqi2[0]!=null&&(!xingqi2[0].equals(""))){//星期2第1节
            xingqi2_jie1.setText(xingqi2[0]);
            xingqi2_jie1.setBackgroundResource(R.drawable.bg_15);
        }else{
            xingqi2_jie1.setText(xingqi2[0]);
            xingqi2_jie1.setBackgroundColor(Color.TRANSPARENT);
        }
        if(xingqi2[1]!=null&&(!xingqi2[1].equals(""))){//星期2第2节
            xingqi2_jie2.setText(xingqi2[1]);
            xingqi2_jie2.setBackgroundResource(R.drawable.bg_16);
        }else{
            xingqi2_jie2.setText(xingqi2[1]);
            xingqi2_jie2.setBackgroundColor(Color.TRANSPARENT);
        }
        if(xingqi2[2]!=null&&(!xingqi2[2].equals(""))){//星期2第3节
            xingqi2_jie3.setText(xingqi2[2]);
            xingqi2_jie3.setBackgroundResource(R.drawable.bg_17);
        }else{
            xingqi2_jie3.setText(xingqi2[2]);
            xingqi2_jie3.setBackgroundColor(Color.TRANSPARENT);
        }
        if(xingqi2[3]!=null&&(!xingqi2[3].equals(""))){//星期2第4节
            xingqi2_jie4.setText(xingqi2[3]);
            xingqi2_jie4.setBackgroundResource(R.drawable.bg_18);
        }else{
            xingqi2_jie4.setText(xingqi2[3]);
            xingqi2_jie4.setBackgroundColor(Color.TRANSPARENT);
        }
        if(xingqi2[4]!=null&&(!xingqi2[4].equals(""))){//星期2第5节
            xingqi2_jie5.setText(xingqi2[4]);
            xingqi2_jie5.setBackgroundResource(R.drawable.bg_11);
        }else{
            xingqi2_jie5.setText(xingqi2[4]);
            xingqi2_jie5.setBackgroundColor(Color.TRANSPARENT);
        }
        if(xingqi2[5]!=null&&(!xingqi2[5].equals(""))){//星期2第6节
            xingqi2_jie6.setText(xingqi2[5]);
            xingqi2_jie6.setBackgroundResource(R.drawable.bg_12);
        }else{
            xingqi2_jie6.setText(xingqi2[5]);
            xingqi2_jie6.setBackgroundColor(Color.TRANSPARENT);
        }



        if(xingqi3[0]!=null&&(!xingqi3[0].equals(""))){//星期3第1节
            xingqi3_jie1.setText(xingqi3[0]);
            xingqi3_jie1.setBackgroundResource(R.drawable.bg_14);
        }else{
            xingqi3_jie1.setText(xingqi3[0]);
            xingqi3_jie1.setBackgroundColor(Color.TRANSPARENT);
        }
        if(xingqi3[1]!=null&&(!xingqi3[1].equals(""))){//星期3第2节
            xingqi3_jie2.setText(xingqi3[1]);
            xingqi3_jie2.setBackgroundResource(R.drawable.bg_13);
        }else{
            xingqi3_jie2.setText(xingqi3[1]);
            xingqi3_jie2.setBackgroundColor(Color.TRANSPARENT);
        }
        if(xingqi3[2]!=null&&(!xingqi3[2].equals(""))){//星期3第3节
            xingqi3_jie3.setText(xingqi3[2]);
            xingqi3_jie3.setBackgroundResource(R.drawable.bg_12);
        }else{
            xingqi3_jie3.setText(xingqi3[2]);
            xingqi3_jie3.setBackgroundColor(Color.TRANSPARENT);
        }
        if(xingqi3[3]!=null&&(!xingqi3[3].equals(""))){//星期3第4节
            xingqi3_jie4.setText(xingqi3[3]);
            xingqi3_jie4.setBackgroundResource(R.drawable.bg_11);
        }else{
            xingqi3_jie4.setText(xingqi3[3]);
            xingqi3_jie4.setBackgroundColor(Color.TRANSPARENT);
        }
        if(xingqi3[4]!=null&&(!xingqi3[4].equals(""))){//星期3第5节
            xingqi3_jie5.setText(xingqi3[4]);
            xingqi3_jie5.setBackgroundResource(R.drawable.bg_18);
        }else{
            xingqi3_jie5.setText(xingqi3[4]);
            xingqi3_jie5.setBackgroundColor(Color.TRANSPARENT);
        }
        if(xingqi3[5]!=null&&(!xingqi3[5].equals(""))){//星期3第6节
            xingqi3_jie6.setText(xingqi3[5]);
            xingqi3_jie6.setBackgroundResource(R.drawable.bg_17);
        }else{
            xingqi3_jie6.setText(xingqi3[5]);
            xingqi3_jie6.setBackgroundColor(Color.TRANSPARENT);
        }


        if(xingqi4[0]!=null&&(!xingqi4[0].equals(""))){//星期4第1节
            xingqi4_jie1.setText(xingqi4[0]);
            xingqi4_jie1.setBackgroundResource(R.drawable.bg_18);
        }else{
            xingqi4_jie1.setText(xingqi4[0]);
            xingqi4_jie1.setBackgroundColor(Color.TRANSPARENT);
        }
        if(xingqi4[1]!=null&&(!xingqi4[1].equals(""))){//星期4第2节
            xingqi4_jie2.setText(xingqi4[1]);
            xingqi4_jie2.setBackgroundResource(R.drawable.bg_17);
        }else{
            xingqi4_jie2.setText(xingqi4[1]);
            xingqi4_jie2.setBackgroundColor(Color.TRANSPARENT);
        }
        if(xingqi4[2]!=null&&(!xingqi4[2].equals(""))){//星期4第3节
            xingqi4_jie3.setText(xingqi4[2]);
            xingqi4_jie3.setBackgroundResource(R.drawable.bg_16);
        }else{
            xingqi4_jie3.setText(xingqi4[2]);
            xingqi4_jie3.setBackgroundColor(Color.TRANSPARENT);
        }
        if(xingqi4[3]!=null&&(!xingqi4[3].equals(""))){//星期4第4节
            xingqi4_jie4.setText(xingqi4[3]);
            xingqi4_jie4.setBackgroundResource(R.drawable.bg_15);
        }else{
            xingqi4_jie4.setText(xingqi4[3]);
            xingqi4_jie4.setBackgroundColor(Color.TRANSPARENT);
        }
        if(xingqi4[4]!=null&&(!xingqi4[4].equals(""))){//星期4第5节
            xingqi4_jie5.setText(xingqi4[4]);
            xingqi4_jie5.setBackgroundResource(R.drawable.bg_14);
        }else{
            xingqi4_jie5.setText(xingqi4[4]);
            xingqi4_jie5.setBackgroundColor(Color.TRANSPARENT);
        }
        if(xingqi4[5]!=null&&(!xingqi4[5].equals(""))){//星期4第6节
            xingqi4_jie6.setText(xingqi4[5]);
            xingqi4_jie6.setBackgroundResource(R.drawable.bg_13);
        }else{
            xingqi4_jie6.setText(xingqi4[5]);
            xingqi4_jie6.setBackgroundColor(Color.TRANSPARENT);
        }



        if(xingqi5[0]!=null&&(!xingqi5[0].equals(""))){//星期5第1节
            xingqi5_jie1.setText(xingqi5[0]);
            xingqi5_jie1.setBackgroundResource(R.drawable.bg_15);
        }else{
            xingqi5_jie1.setText(xingqi5[0]);
            xingqi5_jie1.setBackgroundColor(Color.TRANSPARENT);
        }
        if(xingqi5[1]!=null&&(!xingqi5[1].equals(""))){//星期5第2节
            xingqi5_jie2.setText(xingqi5[1]);
            xingqi5_jie2.setBackgroundResource(R.drawable.bg_15);
        }else{
            xingqi5_jie2.setText(xingqi5[1]);
            xingqi5_jie2.setBackgroundColor(Color.TRANSPARENT);
        }
        if(xingqi5[2]!=null&&(!xingqi5[2].equals(""))){//星期5第3节
            xingqi5_jie3.setText(xingqi5[2]);
            xingqi5_jie3.setBackgroundResource(R.drawable.bg_11);
        }else{
            xingqi5_jie3.setText(xingqi5[2]);
            xingqi5_jie3.setBackgroundColor(Color.TRANSPARENT);
        }
        if(xingqi5[3]!=null&&(!xingqi5[3].equals(""))){//星期5第4节
            xingqi5_jie4.setText(xingqi5[3]);
            xingqi5_jie4.setBackgroundResource(R.drawable.bg_12);
        }else{
            xingqi5_jie4.setText(xingqi5[3]);
            xingqi5_jie4.setBackgroundColor(Color.TRANSPARENT);
        }
        if(xingqi5[4]!=null&&(!xingqi5[4].equals(""))){//星期5第5节
            xingqi5_jie5.setText(xingqi5[4]);
            xingqi5_jie5.setBackgroundResource(R.drawable.bg_13);
        }else{
            xingqi5_jie5.setText(xingqi5[4]);
            xingqi5_jie5.setBackgroundColor(Color.TRANSPARENT);
        }
        if(xingqi5[5]!=null&&(!xingqi5[5].equals(""))){//星期5第6节
            xingqi5_jie6.setText(xingqi5[5]);
            xingqi5_jie6.setBackgroundResource(R.drawable.bg_17);
        }else{
            xingqi5_jie6.setText(xingqi5[5]);
            xingqi5_jie6.setBackgroundColor(Color.TRANSPARENT);
        }



        if(xingqi6[0]!=null&&(!xingqi6[0].equals(""))){//星期6第1节
            xingqi6_jie1.setText(xingqi6[0]);
            xingqi6_jie1.setBackgroundResource(R.drawable.bg_11);
        }else{
            xingqi6_jie1.setText(xingqi6[0]);
            xingqi6_jie1.setBackgroundColor(Color.TRANSPARENT);
        }
        if(xingqi6[1]!=null&&(!xingqi6[1].equals(""))){//星期6第2节
            xingqi6_jie2.setText(xingqi6[1]);
            xingqi6_jie2.setBackgroundResource(R.drawable.bg_12);
        }else{
            xingqi6_jie2.setText(xingqi6[1]);
            xingqi6_jie2.setBackgroundColor(Color.TRANSPARENT);
        }
        if(xingqi6[2]!=null&&(!xingqi6[2].equals(""))){//星期6第3节
            xingqi6_jie3.setText(xingqi6[2]);
            xingqi6_jie3.setBackgroundResource(R.drawable.bg_15);
        }else{
            xingqi6_jie3.setText(xingqi6[2]);
            xingqi6_jie3.setBackgroundColor(Color.TRANSPARENT);
        }
        if(xingqi6[3]!=null&&(!xingqi6[3].equals(""))){//星期6第4节
            xingqi6_jie4.setText(xingqi6[3]);
            xingqi6_jie4.setBackgroundResource(R.drawable.bg_13);
        }else{
            xingqi6_jie4.setText(xingqi6[3]);
            xingqi6_jie4.setBackgroundColor(Color.TRANSPARENT);
        }
        if(xingqi6[4]!=null&&(!xingqi6[4].equals(""))){//星期6第5节
            xingqi6_jie5.setText(xingqi6[4]);
            xingqi6_jie5.setBackgroundResource(R.drawable.bg_18);
        }else{
            xingqi6_jie5.setText(xingqi6[4]);
            xingqi6_jie5.setBackgroundColor(Color.TRANSPARENT);
        }
        if(xingqi6[5]!=null&&(!xingqi6[5].equals(""))){//星期6第6节
            xingqi6_jie6.setText(xingqi6[5]);
            xingqi6_jie6.setBackgroundResource(R.drawable.bg_14);
        }else{
            xingqi6_jie6.setText(xingqi6[5]);
            xingqi6_jie6.setBackgroundColor(Color.TRANSPARENT);
        }


    }

}



