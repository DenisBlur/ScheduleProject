package com.example.application9.AdaptersPackage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application9.DataPackage.ScheduleList_second_new;
import com.example.application9.R;

import java.util.List;

public class ScheduleListAdapter_second_new extends RecyclerView.Adapter<ScheduleListAdapter_second_new.ViewHolder> {

    private List<ScheduleList_second_new> scheduleList_second_news;
    private LayoutInflater layoutInflater;

    ScheduleListAdapter_second_new(Context mContext, List<ScheduleList_second_new> scheduleList_second_news) {
        this.scheduleList_second_news = scheduleList_second_news;
        this.layoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.recycler_item_second_schedule_new, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ScheduleList_second_new listSecond = scheduleList_second_news.get(position);

        holder.schedule_second_lesson_name.setText(listSecond.getLesson_name());
        holder.schedule_second_cabinet_name.setText(listSecond.getCabinet_number());
        holder.schedule_second_teacher_name.setText(listSecond.getTeacher_name());
        holder.schedule_second_number_name.setText(Integer.toString(listSecond.getNum_schedule()));
        holder.schedule_second_time_name.setText(listSecond.getTime_lesson());
        holder.schedule_second_type_name.setText(listSecond.getType_lesson());

    }

    @Override
    public int getItemCount() {
        return scheduleList_second_news.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView schedule_second_type_name, schedule_second_lesson_name, schedule_second_cabinet_name, schedule_second_teacher_name, schedule_second_number_name, schedule_second_time_name;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            schedule_second_number_name = itemView.findViewById(R.id.schedule_second_number_name);
            schedule_second_teacher_name = itemView.findViewById(R.id.schedule_second_teacher_name);
            schedule_second_lesson_name = itemView.findViewById(R.id.schedule_second_lesson_name);
            schedule_second_cabinet_name = itemView.findViewById(R.id.schedule_second_cabinet_name);
            schedule_second_time_name = itemView.findViewById(R.id.schedule_second_time_name);
            schedule_second_type_name = itemView.findViewById(R.id.schedule_second_type_name);
        }
    }
}
