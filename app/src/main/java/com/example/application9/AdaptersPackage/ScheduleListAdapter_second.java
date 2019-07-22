package com.example.application9.AdaptersPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application9.DataPackage.ScheduleList_second;
import com.example.application9.R;
import com.google.android.material.chip.Chip;

import java.util.List;

public class ScheduleListAdapter_second extends RecyclerView.Adapter<ScheduleListAdapter_second.ViewHolder> {

    private Context mContext;
    private List<ScheduleList_second> scheduleListSecond;
    private LayoutInflater layoutInflater;

    public ScheduleListAdapter_second(Context mContext, List<ScheduleList_second> scheduleListSecond) {
        this.mContext = mContext;
        this.scheduleListSecond = scheduleListSecond;
        this.layoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = layoutInflater.inflate(R.layout.recycler_item_second_schedule_with_date, parent, false);
            return new ViewHolder(view);
        } else if (viewType == 1) {
            View view = layoutInflater.inflate(R.layout.recycler_item_second_schedule, parent, false);
            return new ViewHolder(view);
        } else if (viewType == 2) {
            View view = layoutInflater.inflate(R.layout.recycler_item_second_schedule_end, parent, false);
            return new ViewHolder(view);
        } else if (viewType == 3) {
            View view = layoutInflater.inflate(R.layout.recycler_item_second_schedule_with_date_sunday, parent, false);
            return new ViewHolder(view);
        } else if (viewType == 4) {
            View view = layoutInflater.inflate(R.layout.recycler_item_second_schedule_clear, parent, false);
            return new ViewHolder(view);
        }else {
            View view = layoutInflater.inflate(R.layout.recycler_item_second_schedule, parent, false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ScheduleList_second listSecond = scheduleListSecond.get(position);

        holder.schedule_second_date_name.setText(listSecond.getDate_name());
        holder.schedule_second_teacher_name.setText(listSecond.getTeacher_name());
        holder.schedule_second_lesson_name.setText(listSecond.getLesson_name());
        holder.schedule_second_cabinet_name.setText(listSecond.getCabinet_number());

    }

    @Override
    public int getItemCount() {
        return scheduleListSecond.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private Chip schedule_second_date_name;
        private TextView schedule_second_lesson_name, schedule_second_cabinet_name, schedule_second_teacher_name;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            schedule_second_date_name = itemView.findViewById(R.id.schedule_second_date_name);
            schedule_second_teacher_name = itemView.findViewById(R.id.schedule_second_teacher_name);
            schedule_second_lesson_name = itemView.findViewById(R.id.schedule_second_lesson_name);
            schedule_second_cabinet_name = itemView.findViewById(R.id.schedule_second_cabinet_name);

        }
    }

    @Override
    public int getItemViewType(int position) {

        ScheduleList_second listSecond = scheduleListSecond.get(position);

        if (listSecond.getType_schedule() == 0) {
            return 0;
        } else if (listSecond.getType_schedule() == 1) {
            return 1;
        } else if (listSecond.getType_schedule() == 2) {
            return 2;
        } else if (listSecond.getType_schedule() == 3) {
            return 3;
        } else if (listSecond.getType_schedule() == 4) {
            return 4;
        } else {
            return super.getItemViewType(position);
        }

    }
}
