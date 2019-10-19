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
        if (viewType == 1) {
            View view = layoutInflater.inflate(R.layout.recycler_item_second_schedule_new_s, parent, false);
            return new ViewHolder(view);
        } else if (viewType == 2) {
            View view = layoutInflater.inflate(R.layout.recycler_item_second_schedule_launch, parent, false);
            return new ViewHolder(view);
        } else {
            View view = layoutInflater.inflate(R.layout.recycler_item_second_schedule_new, parent, false);
            return new ViewHolder(view);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ScheduleList_second_new listSecond = scheduleList_second_news.get(position);


        if (listSecond.getSeparate() == 0) {

            holder.schedule_second_number_name = holder.itemView.findViewById(R.id.schedule_second_number_name);
            holder.schedule_second_teacher_name = holder.itemView.findViewById(R.id.schedule_second_teacher_name);
            holder.schedule_second_lesson_name = holder.itemView.findViewById(R.id.schedule_second_lesson_name);
            holder.schedule_second_cabinet_name = holder.itemView.findViewById(R.id.schedule_second_cabinet_name);
            holder.schedule_second_time_name = holder.itemView.findViewById(R.id.schedule_second_time_name);
            holder.schedule_second_type_name = holder.itemView.findViewById(R.id.schedule_second_type_name);

            holder.schedule_second_lesson_name.setText(listSecond.getLesson_name());
            holder.schedule_second_cabinet_name.setText(listSecond.getCabinet_number());
            holder.schedule_second_teacher_name.setText(listSecond.getTeacher_name());
            holder.schedule_second_number_name.setText(Integer.toString(listSecond.getNum_schedule()));
            holder.schedule_second_time_name.setText(listSecond.getTime_lesson());
            holder.schedule_second_type_name.setText(listSecond.getType_lesson());
        } else if (listSecond.getSeparate() == 1) {

            String[] subStr_Cab, subStr_Less, subStr_Teac;
            subStr_Less = listSecond.getLesson_name().split(" ");
            subStr_Cab = listSecond.getCabinet_number().split(" ");
            subStr_Teac = listSecond.getTeacher_name().split(" ");

            holder.schedule_second_number_name = holder.itemView.findViewById(R.id.schedule_second_number_name);
            holder.schedule_second_teacher_name = holder.itemView.findViewById(R.id.schedule_second_teacher_name);
            holder.schedule_second_lesson_name = holder.itemView.findViewById(R.id.schedule_second_lesson_name);
            holder.schedule_second_cabinet_name = holder.itemView.findViewById(R.id.schedule_second_cabinet_name);
            holder.schedule_second_time_name = holder.itemView.findViewById(R.id.schedule_second_time_name);
            holder.schedule_second_type_name = holder.itemView.findViewById(R.id.schedule_second_type_name);

            holder.schedule_second_teacher_name_2 = holder.itemView.findViewById(R.id.schedule_second_teacher_name_2);
            holder.schedule_second_lesson_name_2 = holder.itemView.findViewById(R.id.schedule_second_lesson_name_2);
            holder.schedule_second_cabinet_name_2 = holder.itemView.findViewById(R.id.schedule_second_cabinet_name_2);

            holder.schedule_second_lesson_name.setText(subStr_Less[0]);
            if (subStr_Cab.length != 0) {
                holder.schedule_second_cabinet_name.setText(subStr_Cab[0]);
            }
            if (subStr_Teac.length < 4) {
                holder.schedule_second_teacher_name.setText(subStr_Teac[0]);
            } else {
                holder.schedule_second_teacher_name.setText(subStr_Teac[0] + " " + subStr_Teac[1]);

            }
            holder.schedule_second_lesson_name_2.setText(subStr_Less[0]);

            if (subStr_Cab.length == 2) {
                holder.schedule_second_cabinet_name_2.setText(subStr_Cab[1]);
            }

            if (subStr_Teac.length < 4) {
                holder.schedule_second_teacher_name_2.setText(subStr_Teac[1] + " " + subStr_Teac[2]);
            } else {
                holder.schedule_second_teacher_name_2.setText(subStr_Teac[2] + " " + subStr_Teac[3]);
            }
            holder.schedule_second_number_name.setText(Integer.toString(listSecond.getNum_schedule()));
            holder.schedule_second_time_name.setText(listSecond.getTime_lesson());
            holder.schedule_second_type_name.setText(listSecond.getType_lesson());

        } else {
            holder.schedule_second_lesson_name = holder.itemView.findViewById(R.id.schedule_second_lesson_name);
            holder.schedule_second_time_name = holder.itemView.findViewById(R.id.schedule_second_time_name);
            holder.schedule_second_lesson_name.setText(listSecond.getLesson_name());
            holder.schedule_second_time_name.setText(listSecond.getTime_lesson());
        }

    }

    @Override
    public int getItemCount() {
        return scheduleList_second_news.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView schedule_second_lesson_name_2, schedule_second_cabinet_name_2, schedule_second_teacher_name_2;
        private TextView schedule_second_type_name, schedule_second_lesson_name, schedule_second_cabinet_name, schedule_second_teacher_name, schedule_second_number_name, schedule_second_time_name;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        ScheduleList_second_new listSecond = scheduleList_second_news.get(position);
        return listSecond.getSeparate();
    }
}
