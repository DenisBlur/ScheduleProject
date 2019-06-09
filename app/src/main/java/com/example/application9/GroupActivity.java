package com.example.application9;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application9.AdaptersPackage.ScheduleListAdapter_second;
import com.example.application9.DataPackage.ScheduleList_second;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GroupActivity extends AppCompatActivity {

    public static String _MAIN_URL_FOR_GROUP_WEEK_SC;
    private String group_TITLE, group_ID;
    private RecyclerView recycler_view;
    private List<ScheduleList_second> scheduleListSecond = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        //Component Initializing
        recycler_view = findViewById(R.id.recycler_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Component Initializing

        //GetIntent
        group_TITLE = getIntent().getStringExtra("group_TITLE");
        group_ID = getIntent().getStringExtra("group_ID");
        Objects.requireNonNull(getSupportActionBar()).setTitle(group_TITLE);
        toolbar.setTitle(group_TITLE);
        _MAIN_URL_FOR_GROUP_WEEK_SC = "http://83.174.201.182/" + group_ID;
        //GetIntent

        //ThreadStart
        ThreadGetWeek threadGetWeek = new ThreadGetWeek();
        threadGetWeek.execute();
        //ThreadStart
    }

    @SuppressLint("StaticFieldLeak")
    class ThreadGetWeek extends AsyncTask<Void, Void, Void> {

        //TODO deleted that
        String test;

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
                        scheduleListSecond.add(new ScheduleList_second(day_params, first_lesson, first_cabinet, first_teacher, 0));
                        for (int j = 1; j < 6; j++) {
                            if (j == 5) {
                                String lesson_name = document_TABLE_ELEMENTS.get(i + j).select("tr > td.ur > a.z1").text();
                                String cabinet_name = document_TABLE_ELEMENTS.get(i + j).select("tr > td.ur > a.z2").text();
                                String teacher_name = document_TABLE_ELEMENTS.get(i + j).select("tr > td.ur > a.z3").text();
                                scheduleListSecond.add(new ScheduleList_second(day_params, lesson_name, cabinet_name, teacher_name, 2));
                            } else {
                                String lesson_name = document_TABLE_ELEMENTS.get(i + j).select("tr > td.ur > a.z1").text();
                                String cabinet_name = document_TABLE_ELEMENTS.get(i + j).select("tr > td.ur > a.z2").text();
                                String teacher_name = document_TABLE_ELEMENTS.get(i + j).select("tr > td.ur > a.z3").text();
                                if (!lesson_name.equals("")) {
                                    scheduleListSecond.add(new ScheduleList_second(day_params, lesson_name, cabinet_name, teacher_name, 1));
                                } else {
                                    scheduleListSecond.add(new ScheduleList_second(day_params, lesson_name, cabinet_name, teacher_name, 4));
                                }
                            }
                        }
                    } else if (day_params.contains("Вс")) {
                        scheduleListSecond.add(new ScheduleList_second(day_params, "", "", "", 3));
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

            Toast.makeText(GroupActivity.this, test, Toast.LENGTH_SHORT).show();
            ScheduleListAdapter_second scheduleListAdapterSecond = new ScheduleListAdapter_second(GroupActivity.this, scheduleListSecond);
            recycler_view.setAdapter(scheduleListAdapterSecond);
        }
    }
}
