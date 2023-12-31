package com.jnu.student.Fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.jnu.student.R;
import com.jnu.student.adapter.BookRecycleViewAdpater;
import com.jnu.student.data.Book;
import com.jnu.student.data.DataBank;
import com.jnu.student.mainActivity.BookItemDetailActivity;
import com.jnu.student.mainActivity.BookListMainActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookItemFragment extends Fragment {
    private ArrayList<Book> books;
    ActivityResultLauncher<Intent> addItemlauncher;
    ActivityResultLauncher<Intent> updateItemlauncher;
    private BookRecycleViewAdpater bookRecycleViewAdpater;





    /***
     * 【7.创建菜单】
     *  3.在MainActivity类重写onContextItemSelected()，响应点击菜单项事件
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item
                .getMenuInfo();
        switch (item.getItemId()) {
            case 0:
                /**
                 *  启动子Activity显示添加数据项页面
                 * */
                // 创建一个Intent对象，指定要启动的Activity
                Intent intentSave = new Intent(BookItemFragment.this.getContext(), BookItemDetailActivity.class);
                // 当前Activity启动另一个Activity
                addItemlauncher.launch(intentSave);
                break;
            case 1:
                /**
                 * 删除功能
                 * */
                //设置暂停式提示消息：AlertDialog.Builder.setXxx().create().show()
                new AlertDialog.Builder(BookItemFragment.this.getContext())
                        .setTitle("提示")
                        .setMessage("确认删除吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // 在这里编写点击确定按钮后的逻辑
                                // 获取当前数据的位置
                                int order = item.getOrder();
                                books.remove(order);
                                bookRecycleViewAdpater.notifyItemRemoved(order);
                                new DataBank().saveShopItms(BookItemFragment.this.getContext(),books);
                            }
                        })
                        .setNegativeButton("取消", null)
                        .create().show();
                break;
            case 2:
                /**
                 *  启动子Activity显示修改数据项页面
                 * */
                // 创建一个Intent对象，指定要启动的Activity
                Intent intentUpdate = new Intent(BookItemFragment.this.getContext(), BookItemDetailActivity.class);
                // 在Intent对象中传入当前数据项信息，由该Intent对象传给子Activity ==》 子Activity类就能展示原本数据项信息
                int order = item.getOrder();
                intentUpdate.putExtra("bookTitle",books.get(order).getTitle());
                intentUpdate.putExtra("position",order);
                // 当前Activity启动另一个Activity
                updateItemlauncher.launch(intentUpdate);
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

    public BookItemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment BookItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BookItemFragment newInstance() {
        BookItemFragment fragment = new BookItemFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_book_item, container, false);
        /**
         * 【6】创建RecyclerView
         * */
        //【6.1】加载RecyclerView控件
        RecyclerView bookItemsRecyclerView = rootView.findViewById(R.id.recycle_view_books);
        bookItemsRecyclerView.setLongClickable(true);

        //【6.2】生成布局管理器
        bookItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        /**
         * 使用序列化读取文件内容
         * */
        books=new DataBank().loadShopItms(this.getContext());
        if(books==null || 0==books.size()){
            //【6.3】设置适配器
            books.add(new Book("创新工程实践", R.drawable.book_no_name));
            books.add(new Book("软件项目管理案例教程（第4版）", R.drawable.book_2));
            books.add(new Book("信息安全数学基础（第2版）", R.drawable.book_1));
        }

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
                        Log.d("Serializable","调用了addItemlauncher的registerForActivityResult()方法");
                        Intent data = result.getData();
                        String bookTitle = data.getStringExtra("bookTitle");
                        // 将添加的内容显示到主Activity的RecycleView中
                        books.add(new Book(bookTitle, R.drawable.book_no_name));
                        // 通知RecycleView的适配器数据内容有新增，让适配器重新刷新数据
                        bookRecycleViewAdpater.notifyItemInserted(books.size());
                        // 将新数据写入到文件中
                        new DataBank().saveShopItms(this.getContext(),books);
                    } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
                        // 处理取消操作
                    }
                }
        );

        /**
         * 修改数据项：
         *  Step1.数据项的长按菜单中，修改菜单项的响应数据启动子Activity页面，并把当前菜单项数据传递给子Activity显示
         *  Step2.子Activity再将修改好的数据项内容传递过来
         *  Step3.ActivityResultLauncher对象再将新数据项信息写入到文件里
         * */
        updateItemlauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Log.d("Serializable","调用了updateItemlauncher的registerForActivityResult()方法");
                        Intent data = result.getData();
                        String bookTitle = data.getStringExtra("bookTitle");
                        int position = data.getIntExtra("position", -1);
                        Book book = books.get(position);
                        book.SetTitle(bookTitle);
                        // 刷新页面
                        bookRecycleViewAdpater.notifyItemChanged(position);
                        // 将修改的数据写入到文件中
                        new DataBank().saveShopItms(BookItemFragment.this.getContext(),books);

                    } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
                        // 处理取消操作
                    }
                }
        );
        return rootView;
    }
}