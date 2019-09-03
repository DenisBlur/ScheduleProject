package com.example.application9;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.application9.AdaptersPackage.ResultsListAdapter_main;
import com.example.application9.AdaptersPackage.ScheduleListAdapter_second;
import com.example.application9.AdaptersPackage.TabAdapter;
import com.example.application9.CustomDialog.FirstDialog;
import com.example.application9.DataPackage.ResultsList_main;
import com.example.application9.DataPackage.ScheduleList_second;
import com.example.application9.GroupPageFragments.FirstTab;
import com.example.application9.GroupPageFragments.SecondTab;
import com.google.android.material.tabs.TabLayout;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.application9.GroupPageFragments.FirstTab.recyclerView_first;
import static com.example.application9.GroupPageFragments.SecondTab.recyclerView_second;
import static com.example.application9.MainActivity._MAIN_URL_FOR_GROUP_NAME;
import static com.example.application9.MainActivity.resID;

public class GroupActivity extends AppCompatActivity {

    public static String _MAIN_URL_FOR_GROUP_WEEK_SC;
    public static String _MAIN_URL_FOR_GROUP_RESULTS_SC;
    private List<ScheduleList_second> scheduleListSecond = new ArrayList<>();
    private List<ResultsList_main> resultsListMains = new ArrayList<>();
    private FirstDialog cdd;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Theme
        setTheme(resID);
        //Theme

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        //Component Initializing
        TextView group_title = findViewById(R.id.group_title);
        //Component Initializing

        //TabLayout
        ViewPager pager = findViewById(R.id.pager);
        TabLayout tabs = findViewById(R.id.tabs);
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new FirstTab(), "Расписание");
        adapter.addFragment(new SecondTab(), "Итоги");
        pager.setAdapter(adapter);
        tabs.setupWithViewPager(pager);
        //TabLayout

        //GetIntent
        mContext = GroupActivity.this;

        String group_TITLE = getIntent().getStringExtra("group_TITLE");
        String group_ID = getIntent().getStringExtra("group_ID");
        String group_ID_RESULTS = group_ID.replaceAll("cg", "vg");
        group_title.setText(group_TITLE);
        _MAIN_URL_FOR_GROUP_WEEK_SC = _MAIN_URL_FOR_GROUP_NAME + group_ID;
        _MAIN_URL_FOR_GROUP_RESULTS_SC = _MAIN_URL_FOR_GROUP_NAME + group_ID_RESULTS;
        //GetIntent

        //ThreadStart
        cdd = new FirstDialog(this);
        Objects.requireNonNull(cdd.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();

        ThreadGetWeek threadGetWeek = new ThreadGetWeek();
        threadGetWeek.execute();

        ThreadGetResults threadGetResults = new ThreadGetResults();
        threadGetResults.execute();
        //ThreadStart
    }

    @SuppressLint("StaticFieldLeak")
    class ThreadGetResults extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Document document_FULL_HTML_CODE = Jsoup.connect(_MAIN_URL_FOR_GROUP_RESULTS_SC).get();
                Element document_TABLE_HTML_CODE = document_FULL_HTML_CODE.select("table.inf").first();
                Elements document_TABLE_ELEMENTS = document_TABLE_HTML_CODE.select("tr");
                for (int i = 1; i < document_TABLE_ELEMENTS.size(); i++) {
                    Element document_TABLE_SELECTED = document_TABLE_ELEMENTS.get(i);
                    Elements document_TABLE_SELECTED_TD = document_TABLE_SELECTED.select("td");
                    String num = document_TABLE_SELECTED_TD.get(0).text();
                    String name_t = document_TABLE_SELECTED_TD.get(1).text();
                    String name_gr = document_TABLE_SELECTED_TD.get(2).text();
                    String name_l = document_TABLE_SELECTED_TD.get(4).text();
                    String hour_all = document_TABLE_SELECTED_TD.get(5).text();
                    String hour_out = document_TABLE_SELECTED_TD.get(8).text();
                    int progress = Integer.parseInt(document_TABLE_SELECTED_TD.get(12).select("img").attr("alt"));

                    resultsListMains.add(new ResultsList_main(num,name_t,name_gr,name_l,hour_all,hour_out,progress));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ResultsListAdapter_main resultsListAdapter_main = new ResultsListAdapter_main(GroupActivity.this, resultsListMains);
            recyclerView_second.setAdapter(resultsListAdapter_main);
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.item_animation_fall_down);
            recyclerView_second.startAnimation(animation);
            cdd.dismiss();
        }
    }

    @SuppressLint("StaticFieldLeak")
    class ThreadGetWeek extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Document document_FULL_HTML_CODE = Jsoup.connect(_MAIN_URL_FOR_GROUP_WEEK_SC).get();
                Element document_TABLE_HTML_CODE = document_FULL_HTML_CODE.select("table.inf").first();
                Elements document_TABLE_ELEMENTS = document_TABLE_HTML_CODE.select("tr");
                for (int i = 0; i < document_TABLE_ELEMENTS.size(); i++) {

                    Element document_TABLE_SELECTED = document_TABLE_ELEMENTS.get(i);
                    String day_params = document_TABLE_SELECTED.select("tr > td.hd").text();

                    if (day_params.contains("Сб") || day_params.contains("Пн") || day_params.contains("Вт") || day_params.contains("Ср") ||
                            day_params.contains("Чт") || day_params.contains("Пт")) {
                        String first_lesson = document_TABLE_SELECTED.select("tr > td.ur > a.z1").text();
                        String first_cabinet = document_TABLE_SELECTED.select("tr > td.ur > a.z2").text();
                        String first_teacher = document_TABLE_SELECTED.select("tr > td.ur > a.z3").text();

                        if (first_lesson.contains("Пр")) {
                            scheduleListSecond.add(new ScheduleList_second(day_params, first_lesson, first_cabinet, first_teacher, "практика",0, 1));
                        } else {
                            scheduleListSecond.add(new ScheduleList_second(day_params, first_lesson, first_cabinet, first_teacher, "лекция",0, 1));
                        }
                        for (int j = 1; j < 6; j++) {
                            if (j == 5) {
                                String lesson_name = document_TABLE_ELEMENTS.get(i + j).select("tr > td.ur > a.z1").text();
                                String cabinet_name = document_TABLE_ELEMENTS.get(i + j).select("tr > td.ur > a.z2").text();
                                String teacher_name = document_TABLE_ELEMENTS.get(i + j).select("tr > td.ur > a.z3").text();
                                if (lesson_name.contains("Пр")) {
                                    scheduleListSecond.add(new ScheduleList_second(day_params, lesson_name, cabinet_name, teacher_name, "практика",2, j+1));
                                } else {
                                    scheduleListSecond.add(new ScheduleList_second(day_params, lesson_name, cabinet_name, teacher_name, "лекция",2, j+1));
                                }
                            } else {
                                String lesson_name = document_TABLE_ELEMENTS.get(i + j).select("tr > td.ur > a.z1").text();
                                String cabinet_name = document_TABLE_ELEMENTS.get(i + j).select("tr > td.ur > a.z2").text();
                                String teacher_name = document_TABLE_ELEMENTS.get(i + j).select("tr > td.ur > a.z3").text();
                                if (!lesson_name.equals("")) {
                                    scheduleListSecond.add(new ScheduleList_second(day_params, lesson_name, cabinet_name, teacher_name, "",1, j+1));
                                } else {
                                    scheduleListSecond.add(new ScheduleList_second(day_params, lesson_name, cabinet_name, teacher_name, "",4, j+1));
                                }
                                if (j == 1) {
                                    scheduleListSecond.add(new ScheduleList_second(day_params, "Обеденный перерыв", "", "", "",5, j+1));
                                }
                            }
                        }
                    } else if (day_params.contains("Вс")) {
                        scheduleListSecond.add(new ScheduleList_second(day_params, "", "", "", "",3, 0));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ScheduleListAdapter_second scheduleListAdapterSecond = new ScheduleListAdapter_second(GroupActivity.this, scheduleListSecond);
            recyclerView_first.setAdapter(scheduleListAdapterSecond);
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.item_animation_fall_down);
            recyclerView_first.startAnimation(animation);
        }
    }
}
