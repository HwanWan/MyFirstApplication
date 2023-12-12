package com.jnu.student.mainActivity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.jnu.student.Fragment.BookItemFragment;
import com.jnu.student.R;
import com.jnu.student.adapter.PageViewFragmentAdapter;
/**
 * 使用 TabLayout+ViewPager2+Fragment 实现 屏幕滑动 效果
 * */
public class ViewPagerMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pageview);
        // 【1】为MainActivity布局文件中的ViewPager2控件绑定FragmentStateAdapter适配器
        ViewPager2 ViewPager_main = findViewById(R.id.viewpager2_main);
        ViewPager_main.setAdapter(new PageViewFragmentAdapter(getSupportFragmentManager(),getLifecycle()));

        // 【2】将MainActivity布局文件中的导航栏TableLayout & 屏幕滑块ViewPager2控件绑定
        TabLayout tableLayout_header = findViewById(R.id.tableLayout_header);
        new TabLayoutMediator(tableLayout_header,ViewPager_main,new TabLayoutMediator.TabConfigurationStrategy(){
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0: // 第一个滑块的标题栏
                        tab.setText(R.string.tab_caption_0_bookItem);
                        break;
                    case 1: // 第二个滑块的标题栏
                        tab.setText(R.string.tab_caption_1_map);
                        break;
                    case 2: // 第三个滑块的标题栏
                        tab.setText(R.string.tab_caption_2_browser);
                        break;
                    case 3: // 第三个滑块的标题栏
                        tab.setText("Clock");
                        break;
                    case 4: // 第三个滑块的标题栏
                        tab.setText("Game");
                        break;
                }

            }
        }).attach();
    }

}