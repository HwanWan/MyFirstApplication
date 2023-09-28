package com.jnu.student;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**
         * 【1】onCreate()的第一句必须调用父类的onCreate()函数
         * */
        super.onCreate(savedInstanceState);
        /**
         * 【2】setContentView()：将窗口的布局设置为布局文件的布局
         */
        /**
         *【2.1】setContentView()的形参为布局文件名: 通过xml文件设置布局
         *    R表示res包、layout表示在layout目录、activity_main为布局文件名
         *    布局文件为==》 res/layout/activity_main.xml
         */
        setContentView(R.layout.activity_main);
        /**
         *【2.2】setContentView()的形参为布局对象: 通过java代码设置布局
         */
        RelativeLayout relativeLayout = new RelativeLayout( this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        //设置布局中的控件居中显示
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        //创建TextView控件
        TextView textView = new TextView(this);
        //设置TextView的文字内容
        textView.setText("Hello world");
        //设置TextView的文字颜色
        textView.setTextColor(Color.GREEN);
        //设置TextView的文字大小
        textView.setTextSize(50);
        //添加TextView对象和TextView的布局属性
        relativeLayout.addView(textView,params) ;
        //创建布局
        //setContentView(relativeLayout);

        /**
         * 【3】 获取贷源:getResources()
         */
        //获取字将串资源
        String string = getResources().getText(R.string.app_name).toString();
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
        /**
         * 【4】根据id获取资源: findViewById(id名)
         */
        TextView text = findViewById(R.id.text_view_hello_world);
        text.setText("你好Android");
        text.setTextSize((float)50);
    }
}
