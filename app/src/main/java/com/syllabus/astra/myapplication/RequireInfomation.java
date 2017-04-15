package com.syllabus.astra.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2017/4/14.
 */

public class RequireInfomation {

    private  String path;
    private String infomation;
    private CookieTemp cookie = new CookieTemp();
    private Bitmap bitmap;


    //除了构造方法，即获取html和cookie之外，其它方法在activity中都需要开辟一个线程来执行
    public RequireInfomation(String path) {
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
    }


    public String getHtml() {
        return infomation;
    }



    public String getClassTableInfo(String code) {
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
            String param = "Sel_XNXQ=20161&Sel_KC=660021&gs=1&txt_yzm=" + code;
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
