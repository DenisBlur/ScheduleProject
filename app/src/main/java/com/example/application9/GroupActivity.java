package com.example.application9;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
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
import static com.example.application9.MainActivity.variableList;

public class GroupActivity extends AppCompatActivity {

    public static String _MAIN_URL_FOR_GROUP_WEEK_SC;
    public static String _MAIN_URL_FOR_GROUP_RESULTS_SC;
    private List<ScheduleList_second> scheduleListSecond = new ArrayList<>();
    private List<ResultsList_main> resultsListMains = new ArrayList<>();
    private FirstDialog cdd;

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
        String _TYPE = getIntent().getStringExtra("_TYPE");
        String _TITLE = getIntent().getStringExtra("_TITLE");
        String _ID = getIntent().getStringExtra("_ID");
        String _ID_RESULTS = null;

        switch (_TYPE) {
            case "cg":
                _ID_RESULTS = _ID.replaceAll(_TYPE, "vg");
                break;
            case "cp":
                _ID_RESULTS = _ID.replaceAll(_TYPE, "vp");
                break;
            case "ca":
                _ID_RESULTS = _ID.replaceAll(_TYPE, "va");
                break;
        }

        group_title.setText(_TITLE);
        _MAIN_URL_FOR_GROUP_WEEK_SC = _MAIN_URL_FOR_GROUP_NAME + _ID;
        _MAIN_URL_FOR_GROUP_RESULTS_SC = _MAIN_URL_FOR_GROUP_NAME + _ID_RESULTS;
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
                    String num = document_TABLE_SELECTED_TD.get(Integer.parseInt(variableList.get(0).getValue())).text();
                    String name_t = document_TABLE_SELECTED_TD.get(Integer.parseInt(variableList.get(1).getValue())).text();
                    String name_gr = document_TABLE_SELECTED_TD.get(Integer.parseInt(variableList.get(2).getValue())).text();
                    String name_l = document_TABLE_SELECTED_TD.get(Integer.parseInt(variableList.get(3).getValue())).text();
                    String hour_all = document_TABLE_SELECTED_TD.get(Integer.parseInt(variableList.get(4).getValue())).text();
                    String hour_out = document_TABLE_SELECTED_TD.get(Integer.parseInt(variableList.get(5).getValue())).text();
                    String ending = document_TABLE_SELECTED_TD.get(Integer.parseInt(variableList.get(7).getValue())).text();
                    int progress = Integer.parseInt(document_TABLE_SELECTED_TD.get(Integer.parseInt(variableList.get(6).getValue())).select("img").attr("alt"));

                    resultsListMains.add(new ResultsList_main(num, name_t, name_gr, name_l, hour_all, hour_out, ending, progress));
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

                    String first_lesson = document_TABLE_SELECTED.select("tr > td.ur > a.z1").text();
                    String first_cabinet = document_TABLE_SELECTED.select("tr > td.ur > a.z2").text();
                    String first_cabinet_link = document_TABLE_SELECTED.select("tr > td.ur > a.z2").attr("href");
                    String first_teacher = document_TABLE_SELECTED.select("tr > td.ur > a.z3").text();
                    String first_teacher_link = document_TABLE_SELECTED.select("tr > td.ur > a.z3").attr("href");

                    if (day_params.contains("Сб") || day_params.contains("Пн") || day_params.contains("Вт") || day_params.contains("Ср") ||
                            day_params.contains("Чт") || day_params.contains("Пт") || day_params.contains("Вс")) {
                        scheduleListSecond.add(new ScheduleList_second(day_params, first_lesson, first_cabinet, first_cabinet_link, first_teacher_link, first_teacher, "", 0, 0));
                    } else {
                        scheduleListSecond.add(new ScheduleList_second(day_params, first_lesson, first_cabinet, first_cabinet_link, first_teacher_link, first_teacher, "", 1, 0));
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
        }
    }

}
