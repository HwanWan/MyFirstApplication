package com.jnu.student.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.jnu.student.Fragment.BookItemFragment;
import com.jnu.student.Fragment.BrowserFragment;
import com.jnu.student.Fragment.ClockViewFragment;
import com.jnu.student.Fragment.GameViewFragment;
import com.jnu.student.Fragment.TencentMapFragment;

/**
 * 将Fragment 与 FragmentStateAdapter适配器绑定
 * */
public class PageViewFragmentAdapter extends FragmentStateAdapter {

    public PageViewFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: // 第一个滑块绑定BookItemFragment
                return BookItemFragment.newInstance();
            case 1:
                return TencentMapFragment.newInstance();
            case 2: // 第二个滑块绑定BrowserFragment
                return BrowserFragment.newInstance();
            case 3: // 第三个滑块绑定ClockViewFragment
                return ClockViewFragment.newInstance();
            case 4: // 第三个滑块绑定GameViewFragment
                return GameViewFragment.newInstance();
        }
        return BookItemFragment.newInstance();
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
