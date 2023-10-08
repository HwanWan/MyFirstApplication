package com.jnu.student.mainActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jnu.student.R;
import com.jnu.student.adapter.BookRecycleViewAdpater;
import com.jnu.student.data.Book;

import java.util.ArrayList;

public class BookListMainActivity extends AppCompatActivity {
    private ArrayList<Book> books;
    private BookRecycleViewAdpater bookRecycleViewAdpater;
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
        RecyclerView bookItemsRecyclerView = findViewById(R.id.recycle_view_books);
        //【6.2】生成布局管理器
        bookItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //【6.3】设置适配器
        books = new ArrayList<>();
        books.add(new Book("创新工程实践", R.drawable.book_no_name));
        books.add(new Book("软件项目管理案例教程（第4版）", R.drawable.book_2));
        books.add(new Book("信息安全数学基础（第2版）", R.drawable.book_1));

        bookRecycleViewAdpater = new BookRecycleViewAdpater(books);
        bookItemsRecyclerView.setAdapter(bookRecycleViewAdpater);
        /**
         * 【7.创建菜单】
         *  1.在MainActivity类的OnCreate()中
         *   给RecycleView注册菜单 ==》 点击右键的时候就会出现上下文菜单
         */
        //1.注册菜单
        registerForContextMenu(bookItemsRecyclerView);
        /**
         * 【7.创建菜单】
         *  2.在RecyclerView的适配器Adapter类的内部类ViewHolder实现View.OnCreateContextMenuListener接口
         *   并重写onCreateContextMenu()函数设置右键菜单的显示内容
         */

        /**
         * 获取子Activity返回数据
         * */
        addItemlauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        String bookTitle = data.getStringExtra("bookTitle");
                        // 将添加的内容显示到主Activity的RecycleView中
                        books.add(new Book(bookTitle, R.drawable.book_no_name));
                        // 通知RecycleView的适配器数据内容有新增，让适配器重新刷新数据
                        bookRecycleViewAdpater.notifyItemInserted(books.size());

                    } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
                        // 处理取消操作
                    }
                }
        );

    }

    /***
     * 【7.创建菜单】
     *  3.在MainActivity类重写onContextItemSelected()，响应点击菜单项事件
     */
    ActivityResultLauncher<Intent> addItemlauncher;
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item
                .getMenuInfo();
        switch (item.getItemId()) {
            case 0:
                /**
                 *  启动另一个Activity
                 * */
                // 创建一个Intent对象，指定要启动的Activity
                Intent intent = new Intent(this, BookItemDetailActivity.class);
                // 当前Activity启动另一个Activity
                addItemlauncher.launch(intent);
                break;
            case 1:
                /**
                 * 删除功能
                 * */
                //设置暂停式提示消息：AlertDialog.Builder.setXxx().create().show()
                new AlertDialog.Builder(BookListMainActivity.this)
                        .setTitle("提示")
                        .setMessage("确认删除吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // 在这里编写点击确定按钮后的逻辑
                                // 获取当前数据的位置
                                int order = item.getOrder();
                                books.remove(order);
                                bookRecycleViewAdpater.notifyItemRemoved(order);
                            }
                        })
                        .setNegativeButton("取消", null)
                        .create().show();
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
