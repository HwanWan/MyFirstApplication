package com.jnu.student.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jnu.student.R;
import com.jnu.student.data.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * 内部类：RecyclerView
 */
public class RecycleViewBookAdpater extends RecyclerView.Adapter<RecycleViewBookAdpater.ViewHolder> {

    private ArrayList<Book> localDataSet;

    /**
     * 加载布局文件里面的控件
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final ImageView coverResourceId;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            title = (TextView) view.findViewById(R.id.text_view_book_title);
            coverResourceId = (ImageView) view.findViewById(R.id.image_view_book_cover);
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
    public RecycleViewBookAdpater(ArrayList<Book> dataSet) {
        localDataSet = dataSet;
    }

    /**
     * 加载布局文件
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.shop_item_row, viewGroup, false);

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
