package com.example.application9;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.preference.PreferenceManager;

import com.example.application9.AdaptersPackage.GroupListAdapter_main;
import com.example.application9.CustomDialog.FirstDialog;
import com.example.application9.DataPackage.GroupList_main;
import com.example.application9.DataPackage.TimeList_main;
import com.example.application9.DataPackage.VariableList;
import com.example.application9.Support.NetworkManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.application9.HomePageFragments.GroupsHomeFragment.onSetUpGroup;

public class MainActivity extends AppCompatActivity {

    public static String _MAIN_URL_FOR_GROUP_NAME = "http://46.191.196.21/";

    public static List<GroupList_main> groupListMains = new ArrayList<>();
    public static List<VariableList> variableList = new ArrayList<>();

    public static CoordinatorLayout main_content;
    public static String _DESIGN_COLOR;
    public static int  _COLOR_INT;
    public static int resID;
    @SuppressLint("StaticFieldLeak")
    private static Activity activity;

    public static String _SECOND_GROUP_NAME, _SECOND_GROUP_ID;
    public static SharedPreferences myPreferences;
    @SuppressLint("StaticFieldLeak")
    public static GroupListAdapter_main groupListAdapterMain;

    @SuppressLint("StaticFieldLeak")
    private static Context mContext;
    @SuppressLint("StaticFieldLeak")
    public static ImageView hello_bitmap;
    @SuppressLint("StaticFieldLeak")
    public static TextView hello_title;
    private FirstDialog cdd;

    public static List<TimeList_main> timeListMain = new ArrayList<>();
    public static List<TimeList_main> timeListMain_sb = new ArrayList<>();
    public static List<TimeList_main> timeListMain_sr = new ArrayList<>();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Setting
        myPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        _DESIGN_COLOR = sharedPref.getString("_design_color_list_new", "Red");
        _COLOR_INT = sharedPref.getInt("_design_color_list_res", R.color.colorAccent_Red);

        _SECOND_GROUP_NAME = sharedPref.getString("group_TITLE", "null");
        _SECOND_GROUP_ID = sharedPref.getString("group_ID", "null");

        _MAIN_URL_FOR_GROUP_NAME = "http://46.191.196.21/";

        resID = getResId("AppTheme_" + _DESIGN_COLOR + "_Light", R.style.class);

        setTheme(resID);
        //Setting

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Установка времени без сервера
        timeListMain.add(new TimeList_main("08.30 – 10.05",0));
        timeListMain.add(new TimeList_main("10.15 – 11.50",0));
        timeListMain.add(new TimeList_main("45 минут",1));
        timeListMain.add(new TimeList_main("12.35 – 14.10",0));
        timeListMain.add(new TimeList_main("14.20 – 15.50",0));
        timeListMain.add(new TimeList_main("16.00 – 17.30",0));
        timeListMain.add(new TimeList_main("17.40 – 19.10",0));
        timeListMain.add(new TimeList_main("16.00 – 17.30",0));


        timeListMain_sb.add(new TimeList_main("08.30 – 10.05",0));
        timeListMain_sb.add(new TimeList_main("10.15 – 11.50",0));
        timeListMain_sb.add(new TimeList_main("45 минут",1));
        timeListMain_sb.add(new TimeList_main("12.35 – 14.10",0));
        timeListMain_sb.add(new TimeList_main("14.20 – 15.50",0));
        timeListMain_sb.add(new TimeList_main("16.00 – 17.30",0));
        timeListMain_sb.add(new TimeList_main("14.20 – 15.50",0));
        timeListMain_sb.add(new TimeList_main("17.40 – 19.10",0));

        timeListMain_sr.add(new TimeList_main("08.30 – 10.05",0));
        timeListMain_sr.add(new TimeList_main("10.15 – 11.50",0));
        timeListMain_sr.add(new TimeList_main("45 минут",1));
        timeListMain_sr.add(new TimeList_main("12.35 – 14.10",0));
        timeListMain_sr.add(new TimeList_main("14.20 – 15.50",0));
        timeListMain_sr.add(new TimeList_main("14.20 – 15.50",0));
        timeListMain_sr.add(new TimeList_main("16.00 – 17.30",0));
        timeListMain_sr.add(new TimeList_main("17.40 – 19.10",0));

        variableList.add(new VariableList("","0","",""));
        variableList.add(new VariableList("","1","",""));
        variableList.add(new VariableList("","6","",""));
        variableList.add(new VariableList("","4","",""));
        variableList.add(new VariableList("","6","",""));
        variableList.add(new VariableList("","9","",""));
        variableList.add(new VariableList("","13","",""));
        variableList.add(new VariableList("","12","",""));
        // Установка времени без сервера


        //Диалог загрузки
        activity = this;
        cdd = new FirstDialog(this);
        Objects.requireNonNull(cdd.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //Диалог загрузки

        //Инициализация компонетнов
        hello_bitmap = findViewById(R.id.backdrop_bitmap);
        main_content = findViewById(R.id.main_content);
        mContext = MainActivity.this;
        //Инициализация компонетнов

        //Запуск потоков
        threadStart();
        //Запуск потоков
    }

    private void threadStart() {

        //Проверка доступа к интернету
        if (NetworkManager.isNetworkAvailable(mContext)) {

            //Запуск потоков
            ThreadGetGroupNameWeek threadGetGroupNameWeek = new ThreadGetGroupNameWeek();
            threadGetGroupNameWeek.execute();
            //Запуск потоков

            //Запуск окна загрузки
            cdd.show();
            //Запуск окна загрузки
        } else {
            Toast.makeText(mContext, "Check your network connection", Toast.LENGTH_SHORT).show();
        }
        //Проверка доступа к интернету
    }

    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    //Поток для получения групп
    @SuppressLint("StaticFieldLeak")
    public class ThreadGetGroupNameWeek extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            groupListMains = new ArrayList<>();
            try {
                Document document_FULL_HTML_CODE = Jsoup.connect(_MAIN_URL_FOR_GROUP_NAME + "cg.htm").get();
                Element document_TABLE_HTML_CODE = document_FULL_HTML_CODE.select("table.inf").first();
                Elements document_TABLE_ELEMENTS = document_TABLE_HTML_CODE.select("tr");
                for (Element document_TABLE_SELECTED : document_TABLE_ELEMENTS) {
                    String group_TITLE = document_TABLE_SELECTED.select("tr > td.ur > a.z0").text();
                    String group_ID = document_TABLE_SELECTED.select("tr > td.ur > a.z0").attr("href");
                    if (!group_TITLE.equals("")) {
                        groupListMains.add(new GroupList_main(group_TITLE, group_ID, ""));
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
            onSetUpGroup();
            //Отмена Диалога загрузки
            cdd.dismiss();
        }
    }
    //Поток для получения групп

}
