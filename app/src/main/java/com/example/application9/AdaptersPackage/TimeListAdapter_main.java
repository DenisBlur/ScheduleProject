package com.example.application9.AdaptersPackage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application9.DataPackage.TimeList_main;
import com.example.application9.MainActivity;
import com.example.application9.R;

import java.util.List;

public class TimeListAdapter_main extends RecyclerView.Adapter<TimeListAdapter_main.ViewHolder> {

    private List<TimeList_main> timeListMains;
    private LayoutInflater layoutInflater;

    public TimeListAdapter_main(Context mContext, List<TimeList_main> timeListMains) {
        this.timeListMains = timeListMains;
        this.layoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = layoutInflater.inflate(R.layout.recycler_item_update_list, parent, false);
            return new ViewHolder(view);
        } else {
            View view = layoutInflater.inflate(R.layout.recycler_item_main_time, parent, false);
            return new ViewHolder(view);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TimeList_main main = timeListMains.get(position);

        if (main.getType() != 1) {
            holder.text_title = holder.itemView.findViewById(R.id.text_title);
            holder.text_time.setText(main.getTime_support());
            if (!MainActivity._NOW_DAY.equals("3")) {
                if (position != 2) {
                    if (position < 2) {
                        holder.text_title.setText(position + 1 + " пара");
                    } else {
                        holder.text_title.setText(position + " пара");
                    }
                } else {
                    holder.text_title.setText("Обеденный перерыв");
                }
            } else {
                if (position != 3) {
                    if (position < 3) {
                        if (position == 0) {
                            holder.text_title.setText("Классный час");
                        } else {
                            holder.text_title.setText(position + " пара");
                        }
                    } else {
                        holder.text_title.setText(position - 1 + " пара");
                    }
                } else {
                    holder.text_title.setText("Обеденный перерыв");
                }
            }
        } else {
            holder.text_time.setText(main.getTime_support());
        }
//        switch (position) {
//            case 0: holder.text_title
//        }
    }

    @Override
    public int getItemCount() {
        return timeListMains.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView text_time, text_title;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            text_time = itemView.findViewById(R.id.text_time);
        }
    }

    @Override
    public int getItemViewType(int position) {
        TimeList_main main = timeListMains.get(position);
        return main.getType();
    }
}
