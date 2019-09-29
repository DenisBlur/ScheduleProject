package com.example.application9.AdaptersPackage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application9.DataPackage.ScheduleList_second;
import com.example.application9.DataPackage.ScheduleList_second_new;
import com.example.application9.R;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

import static com.example.application9.MainActivity.timeListMains;

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
            View view = layoutInflater.inflate(R.layout.recycler_item_second_new, parent, false);
            return new ViewHolder(view);
        } else {
            View view = layoutInflater.inflate(R.layout.recycler_item_second_schedule_clear_new, parent, false);
            return new ViewHolder(view);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ScheduleList_second listSecond = scheduleListSecond.get(position);

        if (listSecond.getDate_name().contains("Пн") || listSecond.getDate_name().contains("Вт")
                || listSecond.getDate_name().contains("Ср") || listSecond.getDate_name().contains("Чт")
                || listSecond.getDate_name().contains("Пт") || listSecond.getDate_name().contains("Сб")
                || listSecond.getDate_name().contains("Вс")) {

            holder.schedule_second_recycler_view = holder.itemView.findViewById(R.id.schedule_second_recycler_view);
            holder.schedule_second_date_name = holder.itemView.findViewById(R.id.schedule_second_date_name);
            holder.schedule_second_date_name.setText(listSecond.getDate_name());

            List<ScheduleList_second_new> scheduleListSecondNew = new ArrayList<>();
            scheduleListSecondNew.add(new ScheduleList_second_new(listSecond.getLesson_name(),listSecond.getCabinet_number(),listSecond.getTeacher_name(), "лекция", timeListMains.get(0).getTime_support(), 1));
            scheduleListSecondNew.add(new ScheduleList_second_new(scheduleListSecond.get(position+1).getLesson_name(),scheduleListSecond.get(position+1).getCabinet_number(),scheduleListSecond.get(position+1).getTeacher_name(), "лекция",timeListMains.get(1).getTime_support(), 2));
            scheduleListSecondNew.add(new ScheduleList_second_new(scheduleListSecond.get(position+2).getLesson_name(),scheduleListSecond.get(position+2).getCabinet_number(),scheduleListSecond.get(position+2).getTeacher_name(), "лекция",timeListMains.get(3).getTime_support(), 3));
            scheduleListSecondNew.add(new ScheduleList_second_new(scheduleListSecond.get(position+3).getLesson_name(),scheduleListSecond.get(position+3).getCabinet_number(),scheduleListSecond.get(position+3).getTeacher_name(), "лекция",timeListMains.get(4).getTime_support(), 4));
            scheduleListSecondNew.add(new ScheduleList_second_new(scheduleListSecond.get(position+4).getLesson_name(),scheduleListSecond.get(position+4).getCabinet_number(),scheduleListSecond.get(position+4).getTeacher_name(), "лекция",timeListMains.get(5).getTime_support(), 5));
            scheduleListSecondNew.add(new ScheduleList_second_new(scheduleListSecond.get(position+5).getLesson_name(),scheduleListSecond.get(position+5).getCabinet_number(),scheduleListSecond.get(position+5).getTeacher_name(), "лекция",timeListMains.get(6).getTime_support(), 6));


            ScheduleListAdapter_second_new scheduleListAdapterSecondNew = new ScheduleListAdapter_second_new(mContext, scheduleListSecondNew);
            holder.schedule_second_recycler_view.setAdapter(scheduleListAdapterSecondNew);
            holder.schedule_second_recycler_view.setNestedScrollingEnabled(false);
        }

//        holder.schedule_second_date_name.setText(listSecond.getDate_name());
//        holder.schedule_second_teacher_name.setText(listSecond.getTeacher_name());
//        holder.schedule_second_lesson_name.setText(listSecond.getLesson_name());
//        holder.schedule_second_cabinet_name.setText(listSecond.getCabinet_number());
//        holder.schedule_second_number_name.setText(Integer.toString(listSecond.getNum_schedule()));
//
//        TimeList_main listMain;
//
//        if (!_NOW_DAY.equals("3")) {
//            switch (listSecond.getNum_schedule()) {
//                case 1: listMain = timeListMains.get(0); holder.schedule_second_time_name.setText(listMain.getTime_support()); break;
//                case 2: listMain = timeListMains.get(1); holder.schedule_second_time_name.setText(listMain.getTime_support()); break;
//                case 3: listMain = timeListMains.get(3); holder.schedule_second_time_name.setText(listMain.getTime_support()); break;
//                case 4: listMain = timeListMains.get(4); holder.schedule_second_time_name.setText(listMain.getTime_support()); break;
//                case 5: listMain = timeListMains.get(5); holder.schedule_second_time_name.setText(listMain.getTime_support()); break;
//                case 6: listMain = timeListMains.get(7); holder.schedule_second_time_name.setText(listMain.getTime_support()); break;
//                case 9: listMain = timeListMains.get(2); holder.schedule_second_time_name.setText(listMain.getTime_support()); break;
//            }
//        } else {
//            switch (listSecond.getNum_schedule()) {
//                case 1: listMain = timeListMains.get(1); holder.schedule_second_time_name.setText(listMain.getTime_support()); break;
//                case 2: listMain = timeListMains.get(2); holder.schedule_second_time_name.setText(listMain.getTime_support()); break;
//                case 3: listMain = timeListMains.get(4); holder.schedule_second_time_name.setText(listMain.getTime_support()); break;
//                case 4: listMain = timeListMains.get(5); holder.schedule_second_time_name.setText(listMain.getTime_support()); break;
//                case 5: listMain = timeListMains.get(6); holder.schedule_second_time_name.setText(listMain.getTime_support()); break;
//                case 6: listMain = timeListMains.get(7); holder.schedule_second_time_name.setText(listMain.getTime_support()); break;
//                case 9: listMain = timeListMains.get(3); holder.schedule_second_time_name.setText(listMain.getTime_support()); break;
//            }
//        }
//
//        if (listSecond.getNum_schedule() == 2) {
//            holder.bottom_view.setVisibility(View.INVISIBLE);
//
//        }

    }

    @Override
    public int getItemCount() {
        return scheduleListSecond.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private Chip schedule_second_date_name;
//        private View bottom_view;
        private RecyclerView schedule_second_recycler_view;
//        private TextView schedule_second_lesson_name, schedule_second_cabinet_name, schedule_second_teacher_name, schedule_second_number_name, schedule_second_time_name;

        ViewHolder(@NonNull View itemView) {
            super(itemView);


//            schedule_second_date_name = itemView.findViewById(R.id.schedule_second_date_name);
//            schedule_second_number_name = itemView.findViewById(R.id.schedule_second_number_name);
//            schedule_second_teacher_name = itemView.findViewById(R.id.schedule_second_teacher_name);
//            schedule_second_lesson_name = itemView.findViewById(R.id.schedule_second_lesson_name);
//            schedule_second_cabinet_name = itemView.findViewById(R.id.schedule_second_cabinet_name);
//            schedule_second_time_name = itemView.findViewById(R.id.schedule_second_time_name);
//            bottom_view = itemView.findViewById(R.id.bottom_view);

        }
    }

    @Override
    public int getItemViewType(int position) {

        ScheduleList_second listSecond = scheduleListSecond.get(position);

        return listSecond.getType_schedule();
    }
}
