package com.jnu.student.data;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.jnu.student.R;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class DataBankTest {
    ArrayList<Book> save_books;
    ArrayList<Book> load_books;

    @Before
    public void setUp() throws Exception {
        DataBank dataBank = new DataBank();
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        // 先写入数据
        save_books = new ArrayList<>();
        save_books=new DataBank().loadShopItms(context);
        if(save_books==null || 0==save_books.size()){
            //【6.3】设置适配器
            save_books.add(new Book("创新工程实践", R.drawable.book_no_name));
            save_books.add(new Book("软件项目管理案例教程（第4版）", R.drawable.book_2));
            save_books.add(new Book("信息安全数学基础（第2版）", R.drawable.book_1));
        }
    }

    @After
    public void tearDown() throws Exception {
        DataBank dataBank = new DataBank();
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        // 再读取写入的数据
        load_books = dataBank.loadShopItms(context);

        // 判断写入的数据是否相同
        // 1.避免直接将save_books返回给load_books，而load_books不是读取到的数据
        Assert.assertNotSame(save_books,load_books);
        // 2.比较写入的数据量 & 读取到的数据量 是否相同
        Assert.assertEquals(save_books.size(),load_books.size());
        // 3.比较写入的数据内容 & 读取到的数据内容 是否相同
        for (int i = 0; i < save_books.size(); i++) {
            Assert.assertNotSame(save_books.get(i),load_books.get(i));
            Assert.assertEquals(save_books.get(i).getCoverResourceId(),load_books.get(i).getCoverResourceId());
            Assert.assertEquals(save_books.get(i).getTitle(),load_books.get(i).getTitle());
        }
    }

    @Test
    public void loadShopItms() {



    }

    @Test
    public void saveShopItms() {
    }
}