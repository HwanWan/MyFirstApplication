package com.jnu.student.adapter;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.jnu.student.R;
import com.jnu.student.data.Book;

import java.util.ArrayList;

/**
 * 内部类：RecyclerView
 */
public class BookRecycleViewAdpater extends RecyclerView.Adapter<BookRecycleViewAdpater.ViewHolder>  {

    private ArrayList<Book> localDataSet;

    /**
     * 加载布局文件里面的控件: 将布局文件的控件与控件对象绑定
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener  {
        private final TextView title;
        private final ImageView coverResourceId;

        /**
         * 【7.创建菜单】
         * 2.在RecyclerView的适配器Adapter类的内部类ViewHolder实现View.OnCreateContextMenuListener接口
         *   并重写onCreateContextMenu()函数设置右键菜单的显示内容
         */
        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle("具体操作");
            int position = this.getAdapterPosition();
            contextMenu.add(ContextMenu.NONE, 0, position, "添加");
            contextMenu.add(ContextMenu.NONE, 1, position, "删除");
            contextMenu.add(ContextMenu.NONE, 2, position, "修改");
        }
        public ViewHolder(View bookItemview) {
            super(bookItemview);
            // 将每个项的布局文件里的控件与控件对象绑定
            title = (TextView) bookItemview.findViewById(R.id.text_view_book_title);
            coverResourceId = (ImageView) bookItemview.findViewById(R.id.image_view_book_cover);

            /**
             * 【7.创建菜单】
             * 3.事件的发生者是RecycleView控件的每一栏数据
             * 所以要在ViewHolder构造器中为RecycleView控件的每一栏数据设置监听器
             * 当在数据栏点击鼠标右键时就会响应去调用onCreateContextMenu()函数展示右键菜单
             */
            bookItemview.setOnCreateContextMenuListener(this);
        }



        public TextView getTitle() {
            return title;
        }


        public ImageView getCoverResourceId() {
            return coverResourceId;
        }


    }

    /**
     * 构造适配器：传入数据集
     */
    public BookRecycleViewAdpater(ArrayList<Book> dataSet) {
        localDataSet = dataSet;
    }

    /**
     * 加载布局文件
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.book_item_row, viewGroup, false);

        return new ViewHolder(view);
    }


    /**
     * 将控件与数据绑定
     */
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTitle().setText(localDataSet.get(position).getTitle());
        viewHolder.getCoverResourceId().setImageResource(localDataSet.get(position).getCoverResourceId());
    }

    /**
     * 返回控件数量
     */
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }



}