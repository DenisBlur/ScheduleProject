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

import static com.example.application9.HomePageFragments.TimeLineFragment.timeListMain;
import static com.example.application9.HomePageFragments.TimeLineFragment.timeListMain_sr;

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
            if (listSecond.getDate_name().contains("Ср")) {

                scheduleListSecondNew.add(new ScheduleList_second_new(
                        "Классный час",
                        "",
                        "",
                        "КЛ. ЧАС", timeListMain_sr.get(0).getTime_support(),
                        "",
                        "",
                        2, 2));

                String[] subStr_Less, subStr_Teac;
                subStr_Less = listSecond.getCabinet_number().split(" ");
                subStr_Teac = listSecond.getTeacher_name().split(" ");
                if (subStr_Less.length >= 2 || subStr_Teac.length == 4) {
                    scheduleListSecondNew.add(new ScheduleList_second_new(
                            listSecond.getLesson_name(),
                            listSecond.getCabinet_number(),
                            listSecond.getTeacher_name(),
                            "лекция",
                            timeListMain_sr.get(1).getTime_support(),
                            listSecond.getCabinet_number_link(),
                            listSecond.getCabinet_number_link(),
                            1, 1));
                } else {
                    scheduleListSecondNew.add(new ScheduleList_second_new(
                            listSecond.getLesson_name(),
                            listSecond.getCabinet_number(),
                            listSecond.getTeacher_name(),
                            "лекция",
                            timeListMain_sr.get(1).getTime_support(),
                            listSecond.getCabinet_number_link(),
                            listSecond.getCabinet_number_link(),
                            1, 0));
                }

                for (int i = 1; i < 6; i++) {
                    if (i == 2) {
                        scheduleListSecondNew.add(new ScheduleList_second_new(
                                "Обеденный перерыв",
                                "",
                                "",
                                "ОБЕД", timeListMain_sr.get(3).getTime_support(),
                                "",
                                "",
                                2, 2));
                    }
                    String[] subStr_Less_2, subStr_Teac_2;
                    subStr_Less_2 = scheduleListSecond.get(position + i).getCabinet_number().split(" ");
                    subStr_Teac_2 = scheduleListSecond.get(position + i).getTeacher_name().split(" ");
                    if (subStr_Less_2.length >= 2 || subStr_Teac_2.length == 4) {
                        if (i >= 2) {
                            scheduleListSecondNew.add(new ScheduleList_second_new(
                                    scheduleListSecond.get(position + i).getLesson_name(),
                                    scheduleListSecond.get(position + i).getCabinet_number(),
                                    scheduleListSecond.get(position + i).getTeacher_name(),
                                    "лекция", timeListMain_sr.get(i + 2).getTime_support(),
                                    scheduleListSecond.get(position + i).getCabinet_number_link(),
                                    scheduleListSecond.get(position + i).getCabinet_number_link(),
                                    i + 1, 1));
                        } else {
                            scheduleListSecondNew.add(new ScheduleList_second_new(
                                    scheduleListSecond.get(position + i).getLesson_name(),
                                    scheduleListSecond.get(position + i).getCabinet_number(),
                                    scheduleListSecond.get(position + i).getTeacher_name(),
                                    "лекция", timeListMain_sr.get(i + 1).getTime_support(),
                                    scheduleListSecond.get(position + i).getCabinet_number_link(),
                                    scheduleListSecond.get(position + i).getCabinet_number_link(),
                                    i + 1, 1));
                        }
                    } else {
                        if (i >= 2) {
                            scheduleListSecondNew.add(new ScheduleList_second_new(
                                    scheduleListSecond.get(position + i).getLesson_name(),
                                    scheduleListSecond.get(position + i).getCabinet_number(),
                                    scheduleListSecond.get(position + i).getTeacher_name(),
                                    "лекция", timeListMain_sr.get(i + 2).getTime_support(),
                                    scheduleListSecond.get(position + i).getCabinet_number_link(),
                                    scheduleListSecond.get(position + i).getCabinet_number_link(),
                                    i + 1, 0));
                        } else {
                            scheduleListSecondNew.add(new ScheduleList_second_new(
                                    scheduleListSecond.get(position + i).getLesson_name(),
                                    scheduleListSecond.get(position + i).getCabinet_number(),
                                    scheduleListSecond.get(position + i).getTeacher_name(),
                                    "лекция", timeListMain_sr.get(i + 1).getTime_support(),
                                    scheduleListSecond.get(position + i).getCabinet_number_link(),
                                    scheduleListSecond.get(position + i).getCabinet_number_link(),
                                    i + 1, 0));
                        }
                    }
                }

                ScheduleListAdapter_second_new scheduleListAdapterSecondNew = new ScheduleListAdapter_second_new(mContext, scheduleListSecondNew);
                holder.schedule_second_recycler_view.setAdapter(scheduleListAdapterSecondNew);
                holder.schedule_second_recycler_view.setNestedScrollingEnabled(false);
            } else {
                String[] subStr_Less, subStr_Teac;
                subStr_Less = listSecond.getCabinet_number().split(" ");
                subStr_Teac = listSecond.getTeacher_name().split(" ");
                if (subStr_Less.length >= 2 || subStr_Teac.length == 4) {
                    String type_lesson;
                    if (listSecond.getLesson_name().contains("Пр")) {
                        type_lesson = "практика";
                    } else {
                        type_lesson = "лекция";
                    }
                    scheduleListSecondNew.add(new ScheduleList_second_new(
                            listSecond.getLesson_name(),
                            listSecond.getCabinet_number(),
                            listSecond.getTeacher_name(),
                            type_lesson,
                            timeListMain.get(0).getTime_support(),
                            listSecond.getCabinet_number_link(),
                            listSecond.getCabinet_number_link(),
                            1, 1));
                } else {
                    String type_lesson;
                    if (listSecond.getLesson_name().contains("Пр")) {
                        type_lesson = "практика";
                    } else {
                        type_lesson = "лекция";
                    }
                    scheduleListSecondNew.add(new ScheduleList_second_new(
                            listSecond.getLesson_name(),
                            listSecond.getCabinet_number(),
                            listSecond.getTeacher_name(),
                            type_lesson,
                            timeListMain.get(0).getTime_support(),
                            listSecond.getCabinet_number_link(),
                            listSecond.getCabinet_number_link(),
                            1, 0));
                }

                for (int i = 1; i < 6; i++) {
                    if (i == 2) {
                        scheduleListSecondNew.add(new ScheduleList_second_new(
                                "Обеденный перерыв",
                                "",
                                "",
                                "ОБЕД", timeListMain.get(2).getTime_support(),
                                "",
                                "",
                                2, 2));
                    }
                    String[] subStr_Less_2, subStr_Teac_2;
                    subStr_Less_2 = scheduleListSecond.get(position + i).getCabinet_number().split(" ");
                    subStr_Teac_2 = scheduleListSecond.get(position + i).getTeacher_name().split(" ");
                    if (subStr_Less_2.length >= 2 || subStr_Teac_2.length == 4) {
                        if (i >= 2) {
                            scheduleListSecondNew.add(new ScheduleList_second_new(
                                    scheduleListSecond.get(position + i).getLesson_name(),
                                    scheduleListSecond.get(position + i).getCabinet_number(),
                                    scheduleListSecond.get(position + i).getTeacher_name(),
                                    "лекция", timeListMain.get(i + 1).getTime_support(),
                                    scheduleListSecond.get(position + i).getCabinet_number_link(),
                                    scheduleListSecond.get(position + i).getCabinet_number_link(),
                                    i + 1, 1));
                        } else {
                            scheduleListSecondNew.add(new ScheduleList_second_new(
                                    scheduleListSecond.get(position + i).getLesson_name(),
                                    scheduleListSecond.get(position + i).getCabinet_number(),
                                    scheduleListSecond.get(position + i).getTeacher_name(),
                                    "лекция", timeListMain.get(i).getTime_support(),
                                    scheduleListSecond.get(position + i).getCabinet_number_link(),
                                    scheduleListSecond.get(position + i).getCabinet_number_link(),
                                    i + 1, 1));
                        }
                    } else {
                        if (i >= 2) {
                            scheduleListSecondNew.add(new ScheduleList_second_new(
                                    scheduleListSecond.get(position + i).getLesson_name(),
                                    scheduleListSecond.get(position + i).getCabinet_number(),
                                    scheduleListSecond.get(position + i).getTeacher_name(),
                                    "лекция", timeListMain.get(i + 1).getTime_support(),
                                    scheduleListSecond.get(position + i).getCabinet_number_link(),
                                    scheduleListSecond.get(position + i).getCabinet_number_link(),
                                    i + 1, 0));
                        } else {
                            scheduleListSecondNew.add(new ScheduleList_second_new(
                                    scheduleListSecond.get(position + i).getLesson_name(),
                                    scheduleListSecond.get(position + i).getCabinet_number(),
                                    scheduleListSecond.get(position + i).getTeacher_name(),
                                    "лекция", timeListMain.get(i).getTime_support(),
                                    scheduleListSecond.get(position + i).getCabinet_number_link(),
                                    scheduleListSecond.get(position + i).getCabinet_number_link(),
                                    i + 1, 0));
                        }
                    }
                }

                ScheduleListAdapter_second_new scheduleListAdapterSecondNew = new ScheduleListAdapter_second_new(mContext, scheduleListSecondNew);
                holder.schedule_second_recycler_view.setAdapter(scheduleListAdapterSecondNew);
                holder.schedule_second_recycler_view.setNestedScrollingEnabled(false);
            }
        }

    }

    @Override
    public int getItemCount() {
        return scheduleListSecond.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private Chip schedule_second_date_name;
        private RecyclerView schedule_second_recycler_view;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @Override
    public int getItemViewType(int position) {

        ScheduleList_second listSecond = scheduleListSecond.get(position);

        return listSecond.getType_schedule();
    }
}
