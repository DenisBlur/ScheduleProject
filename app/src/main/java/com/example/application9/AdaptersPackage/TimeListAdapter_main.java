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
import com.example.application9.R;

import java.util.List;

public class TimeListAdapter_main extends RecyclerView.Adapter<TimeListAdapter_main.ViewHolder> {

    private List<TimeList_main> timeListMains;
    private Context mContext;
    private LayoutInflater layoutInflater;

    public TimeListAdapter_main(Context mContext, List<TimeList_main> timeListMains) {
        this.timeListMains = timeListMains;
        this.mContext = mContext;
        this.layoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.recycler_item_main_time, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TimeList_main main = timeListMains.get(position);
        holder.text_time.setText(main.getTime_support());
        if (position != 2) {
            if (position < 2) {
                holder.text_title.setText(position + 1 + " пара");
            } else {
                holder.text_title.setText(position + " пара");
            }
        } else {
            holder.text_title.setText("Обеденный перерыв");
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
            text_title = itemView.findViewById(R.id.text_title);
        }
    }
}
