package com.jnu.student;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**
         * 【1】onCreate()的第一句必须调用父类的onCreate()函数
         * */
        super.onCreate(savedInstanceState);
        /**
         * 【2】setContentView()：将窗口的布局设置为布局文件的布局
         * R表示res包、layout表示在layout目录、activity_main为布局文件名
         * 布局文件为==》 res/layout/activity_main.xml
         *
         */
        setContentView(R.layout.activity_main);
    }
}
