package com.syllabus.astra.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.syllabus.astra.myapplication.util.Teacher;
import com.syllabus.astra.myapplication.util.TeacherList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2017/4/14.
 */

public class RequireInfomation {

    private  String path;
    private String infomation = "";
    private CookieTemp cookie = new CookieTemp();
    private Bitmap bitmap;
    CountDownLatch countDownLatch;


    //除了构造方法，即获取html和cookie之外，其它方法在activity中都需要开辟一个线程来执行
    public RequireInfomation(String path) {
        countDownLatch = new CountDownLatch(1);
        this.path = path;
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(RequireInfomation.this.path);
                    connection = (HttpURLConnection) url.openConnection();

                    InputStream inputStream = connection.getInputStream();
                    cookie.setCookie(connection.getHeaderField("Set-Cookie"));
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "gb2312"));
                    final StringBuffer stringBuffer = new StringBuffer();
                    String line = null;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuffer.append(line);
                    }
                    Log.i("requireinfomation:", RequireInfomation.this.toString());

                        infomation = stringBuffer.toString();

                    countDownLatch.countDown();


                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    connection.disconnect();
                }
            }
        }).start();
    }


    public String getHtml() {
        return infomation;
    }




    public String getClassTableInfo(Map<String, String> map) {
        HttpURLConnection connection = null;
        OutputStream outputStream = null;
        BufferedReader bufferedReader = null;
        StringBuffer result = new StringBuffer();
        try {
            URL url = new URL(path);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setDoInput(true);
            //设置请求头信息
            connection.setRequestProperty("Cookie", cookie.getCookie());
            connection.setRequestProperty("Refer", path);
            //设置请求参数
            StringBuffer params = new StringBuffer();

            Set<Map.Entry<String, String>> set = map.entrySet();
            for(Map.Entry entry : set) {
                params.append(entry.getKey() + "=" + entry.getValue() + "&");
            }
            String param = params.substring(0, params.length() - 2);

            connection.connect();
            outputStream = connection.getOutputStream();
            outputStream.write(param.getBytes());
            outputStream.flush();
            if(connection.getResponseCode() == 200) {
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "gb2312"));
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }



    //获取验证码
    public Bitmap getPicture() {
        HttpURLConnection connection = null;
        try {
            URL url = new URL("http://211.67.107.38/jwweb/sys/ValidateCode.aspx");
            connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public List<TeacherList> getTeacherInfoFromNet() {

        /*try {
            countDownLatch.await();
            Log.i("requireInfomation1:", this.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i("informationString***", infomation);*/
        Document dname = null;
        try {
            dname = Jsoup.connect("http://218.62.46.130/jwmis/ZNPK/Private/List_JS.aspx?xnxq=20161&t=29").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements script = dname.select("script");
        Document dname1 = Jsoup.parse(script.toString());
        String data  = dname1.data();
        Log.i("dname1****", dname1.toString());
        Document dname2 = Jsoup.parse(data);
        Log.i("dname2****", dname2.toString());
        Elements script1 = dname2.getElementsByTag("option");
        List<TeacherList> list = new ArrayList<>();
        TeacherList teacher;
        for (Element e1 : script1) {
            teacher = new TeacherList();
            teacher.setId(e1.attr("value"));
            teacher.setName(e1.text());
            Log.i("text***", e1.text());
            list.add(teacher);
        }
        return list;

    }

    public List<Teacher> getCourseByTeacherFromNet(String tableHtml) {
        Document dInfo = Jsoup.parse(tableHtml);
        Elements eInfo=dInfo.select("table").select("tr");
        class ClassInfo1{
            private String week;
            private String lesson;
            private String info;

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getLesson() {
                return lesson;
            }

            public void setLesson(String lesson) {
                this.lesson = lesson;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }
        }
        ArrayList<ClassInfo1> list = new ArrayList<ClassInfo1>();
        for (int i = 5; i <eInfo.size(); i++) {
            Elements eInfo1=eInfo.get(i).select("td");
            if(eInfo1.text().toString().equals("注1：")){
                break;
            }
            if(eInfo1.size()==9) {
                for (int j = 2; j < eInfo1.size(); j++) {
                    if(eInfo1.get(j).text().length()!=0){
                        ClassInfo1 classInfo1=new ClassInfo1();
                        classInfo1.setWeek(String.valueOf(j-1));
                        classInfo1.setLesson(String.valueOf(i-4));
                        classInfo1.setInfo(eInfo1.get(j).text());
                        list.add(classInfo1);
                    }
                }
            }else {
                for (int j = 1; j < eInfo1.size(); j++) {
                    if(eInfo1.get(j).text().length()!=0){
                        ClassInfo1 classInfo1=new ClassInfo1();
                        classInfo1.setWeek(String.valueOf(j));
                        classInfo1.setLesson(String.valueOf(i-4));
                        classInfo1.setInfo(eInfo1.get(j).text());
                        list.add(classInfo1);
                    }
                }
            }
        }
        List<Teacher> courselist = new ArrayList<>();
        for(int i = 0; i < 5; i ++){
            Teacher teacher = new Teacher();
            courselist.add(teacher);
        }
        for(ClassInfo1 classInfo1 : list) {
            int lesson = Integer.valueOf(classInfo1.getLesson());
            int week = Integer.valueOf(classInfo1.getWeek());
            if(week == 1) {
                courselist.get(lesson).setMon(classInfo1.getInfo());
            }
            if(week == 2) {
                courselist.get(lesson).setTues(classInfo1.getInfo());
            }
            if(week == 3) {
                courselist.get(lesson).setWed(classInfo1.getInfo());
            }
            if(week == 4) {
                courselist.get(lesson).setThur(classInfo1.getInfo());
            }
            if(week == 5) {
                courselist.get(lesson).setFri(classInfo1.getInfo());
            }
            if(week == 6) {
                courselist.get(lesson).setSat(classInfo1.getInfo());
            }
            if(week == 7) {
                courselist.get(lesson).setSun(classInfo1.getInfo());
            }

        }


        return courselist;
    }



    /*//获取html和cookie
    public void getInfomation() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL("http://211.67.107.38/jwweb/ZNPK/KBFB_LessonSel.aspx");
                    connection = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    cookie.setCookie(connection.getHeaderField("Set-Cookie"));
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "gb2312"));
                    final StringBuffer stringBuffer = new StringBuffer();
                    String line = null;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuffer.append(line);
                    }
                    infomation = stringBuffer.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    connection.disconnect();
                }
            }
        }).start();
    }*/
}
