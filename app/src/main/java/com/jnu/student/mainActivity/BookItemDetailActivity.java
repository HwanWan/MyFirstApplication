package com.jnu.student.mainActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jnu.student.R;

public class BookItemDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_item_detail);
        //当点击确认按钮关闭当前Activity
        Button button_ok=findViewById(R.id.button_ok);

        // 给按钮添加点击响应器
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 返回数据
                 * */
                // 1.创建一个Intent对象，指定要启动的Activity
                Intent intent = new Intent();
                // 2.添加需要传递给另一个Activity的数据
                EditText bookTitle = findViewById(R.id.editText_item_name);
                intent.putExtra("bookTitle", bookTitle.getText().toString());
                setResult(Activity.RESULT_OK,intent);

                /**
                 * 关闭当前BookItemDetailActivity
                 * */
                BookItemDetailActivity.this.finish();
            }
        });
    }
}
