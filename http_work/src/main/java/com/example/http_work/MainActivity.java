package com.example.http_work;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.MalformedInputException;

public class MainActivity extends AppCompatActivity {
    private String path = "http://result.eolinker.com/iYXEPGn4e9c6dafce6e5cdd23287d2bb136ee7e9194d3e9?uri=one";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String data = getData();
                Log.e("myMessage",""+data);
            }
        }).start();
    }
    public  String getData() {

            try {
                //发起网络请求
                URL url = new URL(path);
                //开启一个连接
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                if (urlConnection.getResponseCode() == 200) {
                    InputStream inputStream = urlConnection.getInputStream();
                    ByteArrayOutputStream bAOS = new ByteArrayOutputStream();
                    byte[] bytes = new byte[1024];
                    //设置初始长度
                    int length = 0;
                    while ((length = inputStream.read(bytes)) != -1) {
                        bAOS.write(bytes, 0, length);
                    }
                    String values = bAOS.toString();
                    return values;
                }
            }catch(MalformedURLException e){
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }
        return "";
    }
}
