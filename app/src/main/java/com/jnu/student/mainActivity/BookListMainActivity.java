package com.jnu.student.mainActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jnu.student.R;
import com.jnu.student.adapter.RecycleViewBookAdpater;
import com.jnu.student.data.Book;

import java.util.ArrayList;

public class BookListMainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**
         * 【1】onCreate()的第一句必须调用父类的onCreate()函数
         * */
        super.onCreate(savedInstanceState);
        /**
         * 【2】setContentView()：将窗口的布局设置为布局文件的布局
         */
        setContentView(R.layout.activity_main_recycleview);

        /**
         * 【6】创建RecyclerView
         * */
        //【6.1】加载RecyclerView控件
        RecyclerView maimRecyclerView = findViewById(R.id.recycle_view_books);
        //【6.2】生成布局管理器
        maimRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //【6.3】设置适配器
        ArrayList<Book> books = new ArrayList<>();
        for (int i=0;i<20;i++){
            books.add(new Book("创新工程实践", R.drawable.book_no_name));
            books.add(new Book("软件项目管理案例教程（第4版）", R.drawable.book_2));
            books.add(new Book("信息安全数学基础（第2版）", R.drawable.book_1));
        }
        RecycleViewBookAdpater recycleViewBookAdpater = new RecycleViewBookAdpater(books);
        maimRecyclerView.setAdapter(recycleViewBookAdpater);

        /**
         * 【7】给RecycleView注册菜单
         * */
        //1.注册菜单
        registerForContextMenu(maimRecyclerView);




    }

    /**
     * 【7.2】显示菜单
     * */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("具体操作");
        menu.add(0, 0, Menu.NONE, "添加");
        menu.add(0, 1, Menu.NONE, "删除");
        menu.add(0, 2, Menu.NONE, "修改");
    }
    /***
     * 【7.3】响应点击菜单
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item
                .getMenuInfo();
        Toast.makeText(this,"点击了菜单项",Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }

    public ArrayList<Book> getLocalDataSet() {
        return localDataSet;
    }

    private ArrayList<Book> localDataSet;



}
