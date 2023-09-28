package com.jnu.student.mainActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.jnu.student.R;
import com.jnu.student.adapter.RecycleViewBookAdpater;
import com.jnu.student.data.Book;

import java.util.ArrayList;

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

//        /**
//         *【2.2】setContentView()的形参为布局对象: 通过java代码设置布局
//         */
//        RelativeLayout relativeLayout = new RelativeLayout( this);
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
//                RelativeLayout.LayoutParams.WRAP_CONTENT,
//                RelativeLayout.LayoutParams.WRAP_CONTENT);
//        //设置布局中的控件居中显示
//        params.addRule(RelativeLayout.CENTER_IN_PARENT);
//        //创建TextView控件
//        TextView textView = new TextView(this);
//        //设置TextView的文字内容
//        textView.setText("Hello world");
//        //设置TextView的文字颜色
//        textView.setTextColor(Color.GREEN);
//        //设置TextView的文字大小
//        textView.setTextSize(50);
//        //添加TextView对象和TextView的布局属性
//        relativeLayout.addView(textView,params) ;
//        //创建布局
//        //setContentView(relativeLayout);
//
//        /**
//         * 【3】 获取贷源:getResources()
//         */
//        //获取字将串资源
//        String string = getResources().getText(R.string.app_name,"你好Android").toString();
//        Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
//        /**
//         * 【4】根据id获取资源: findViewById(id名)
//         */
//        TextView text = findViewById(R.id.text_view_hello_world);
//        text.setTextSize((float)30);
//
//
//        /**
//         * 【5】设置button控件的响应事件: 设置点击事件的监听者setOnClickListener（）
//         * */
//        Button button = (Button)findViewById(R.id.button_change_text);
//        /**
//         * 【5.1】setOnClickListener()的形参为匿名类
//         * button.setOnClickListener(new View.OnClickListener() {
//         *     @Override
//         *     public void onClick(View view) {
//         *         MyOnClick(view);
//         *     }
//         * });
//         * */
//
//        /**
//         * 【5.2】button.setOnClickListener()的形参可以使用Lambda表达式来简化匿名内部类View.OnClickListener的写法
//         * button.setOnClickListener(this);
//         * */
//        button.setOnClickListener(v -> {
//            // 响应事件的代码
//            MyOnClick(v);
//        });
//        /**
//         * 【5.3】MainActivity类实现View.OnClickListener接口，setOnClickListener()的形参为当前MainActivity对象this
//         * */
//        button.setOnClickListener(this);


    }
//    /**
//     * 【5.3】MainActivity类实现View.OnClickListener接口，setOnClickListener()的形参为当前MainActivity对象this
//     * */
//    @Override
//    public void onClick(View view) {
//        /**
//         * 【8】设置日志：Log.i/v/w/e()
//         * */
//        Log.i("MainActivity","开始执行button控件的onclick响应函数");
//        MyOnClick(view);
//        /**
//         * 【6】设置短暂提示消息：Toast.makeText().show()
//         * */
//        Toast.makeText(MainActivity.this,"交换了文本内容",Toast.LENGTH_LONG).show();
//        /**
//         * 【7】设置暂停式提示消息：AlertDialog.Builder.setXxx().create().show()
//         * */
//        new AlertDialog.Builder(MainActivity.this)
//                .setTitle("提示标题")
//                .setMessage("已交换文本内容")
//                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // 在这里编写点击确定按钮后的逻辑
//                    }
//                })
//                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // 在这里编写点击取消按钮后的逻辑
//                    }
//                })
//                .create().show();
//        Log.i("MainActivity","结束执行button控件的onclick响应函数");
//
//    }
//    /**
//     * 【5.4】在布局文件中将button控件绑定自定义函数
//     * */
//    public void MyOnClick(View view) {
//        TextView text_change_Hello = findViewById(R.id.text_change_Hello);
//         TextView text_change_JNU = findViewById(R.id.text_change_JNU);
//         CharSequence textHello = text_change_Hello.getText();
//         text_change_Hello.setText(text_change_JNU.getText());
//         text_change_JNU.setText(textHello);
//    }
}
