package com.example.application9;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.transition.Fade;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.preference.PreferenceManager;

import com.example.application9.AdaptersPackage.GroupListAdapter_main;
import com.example.application9.AdaptersPackage.TimeListAdapter_main;
import com.example.application9.CustomDialog.FirstDialog;
import com.example.application9.DataPackage.GroupList_main;
import com.example.application9.DataPackage.TimeList_main;
import com.example.application9.HomePageFragments.AccountFragment;
import com.example.application9.HomePageFragments.GroupsHomeFragment;
import com.example.application9.HomePageFragments.TimeLineFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.application9.HomePageFragments.GroupsHomeFragment.recycler_view_group;
import static com.example.application9.HomePageFragments.TimeLineFragment.recycler_view_timeline;

public class MainActivity extends AppCompatActivity {

    public static String _MAIN_URL_FOR_GROUP_NAME = "http://83.174.201.182/cg.htm";
    public static String _MAIN_URL_FOR_TIMES = "http://s917802v.bget.ru/times/";

    @SuppressLint("StaticFieldLeak")
    public static GroupListAdapter_main groupListAdapterMain;
    @SuppressLint("StaticFieldLeak")
    public static TimeListAdapter_main timeListAdapterMain;

    public static String _DESIGN_COLOR;
    public static String _SITE;
    public static boolean _ONE_DAY;
    public static boolean _DARK_THEME;
    public static int resID;

    private List<GroupList_main> groupListMains = new ArrayList<>();
    private List<TimeList_main> timeListMains = new ArrayList<>();
    private Context mContext;
    private View outlook_switch;
    private ImageView hello_bitmap;
    private TextView hello_title;
    private Float dp;
    private FirstDialog cdd;

    final Fragment fragment1 = new GroupsHomeFragment();
    final Fragment fragment2 = new TimeLineFragment();
    final Fragment fragment3 = new AccountFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Setting
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        _DESIGN_COLOR = sharedPref.getString("_design_color_list", "Orange");
        _SITE = sharedPref.getString("_site_list", "first_site");
        _ONE_DAY = sharedPref.getBoolean("_main_one_day_switch", false);
        _DARK_THEME = sharedPref.getBoolean("_design_dark_theme", false);

        //resID = getResId("AppTheme_" + _DESIGN_COLOR, R.style.class);

        if (_SITE.equals("first_site")) {
            _MAIN_URL_FOR_GROUP_NAME = "http://83.174.201.182/";
        } else {
            _MAIN_URL_FOR_GROUP_NAME = "http://83.174.201.182:85/";
        }

        if (!_DARK_THEME) {
            resID = getResId("AppTheme_" + _DESIGN_COLOR + "_Light", R.style.class);
        } else {
            resID = getResId("AppTheme_" + _DESIGN_COLOR, R.style.class);
        }
        setTheme(resID);
        //Setting

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Animation
        getWindow().setEnterTransition(new Fade());
        //Animation

        //Fragments
        fm.beginTransaction().add(R.id.fragment_container, fragment3, "3").hide(fragment3).commit();
        fm.beginTransaction().add(R.id.fragment_container, fragment2, "2").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.fragment_container, fragment1, "1").commit();

        cdd = new FirstDialog(this);
        Objects.requireNonNull(cdd.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();
        //Fragments

        //
        dp = getApplicationContext().getResources().getDisplayMetrics().density;
        //

        //initializing all component
        outlook_switch = findViewById(R.id.outlook_switch);
        TextView day_button_sw = findViewById(R.id.day_button_sw);
        TextView week_button_sw = findViewById(R.id.week_button_sw);
        hello_bitmap = findViewById(R.id.backdrop_bitmap);
        hello_title = findViewById(R.id.hello_title);
        BottomNavigationView bottom_nav_view = findViewById(R.id.bottom_nav_view);
        //initializing all component

        //TODO: Использовать в будущем
        //color extracting
//        Bitmap helloImage = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.night);
//        Palette.generateAsync(helloImage, palette -> {
//            assert palette != null;
//            assert palette.getDominantSwatch() != null;
//            fake_fragment_schedule.setBackgroundColor(palette.getDominantSwatch().getRgb());
//        });
        //color extracting

        //Context
        mContext = MainActivity.this;
        //Context


        //Toolbar and AppBar Elements

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottom_nav_view.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.schedule:
                    fm.beginTransaction().hide(active).show(fragment1).commit();
                    active = fragment1;
                    return true;
                case R.id.timeline:
                    fm.beginTransaction().hide(active).show(fragment2).commit();
                    active = fragment2;
                    return true;
                case R.id.account:
                    fm.beginTransaction().hide(active).show(fragment3).commit();
                    active = fragment3;
                    return true;
            }
            return false;
        });
        //Toolbar and AppBar Elements

        //thread start and more
        ThreadGetGroupName getGroupName = new ThreadGetGroupName();
        getGroupName.execute();

        ThreadGetTime threadGetTime = new ThreadGetTime();
        threadGetTime.execute();
        //thread start and more

        //Outlooks details
        week_button_sw.setOnClickListener(v -> outlook_switch.animate().translationX(0 * dp).setDuration(150).start());

        day_button_sw.setOnClickListener(v -> outlook_switch.animate().translationX(96 * dp).setDuration(150).start());
        //Outlooks details
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

    @SuppressLint("StaticFieldLeak")
    public class ThreadGetTime extends AsyncTask<Void, Void, Void> {

        String document_TIME_IMAGE_HTML_CODE;

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document document_FULL_HTML_CODE = Jsoup.connect(_MAIN_URL_FOR_TIMES).get();
                String document_TYPE_HTML_CODE = document_FULL_HTML_CODE.select("div.type").first().text();
                String document_NOW_DAY_HTML_CODE = document_FULL_HTML_CODE.select("div.now_day").first().text();
                document_TIME_IMAGE_HTML_CODE = document_FULL_HTML_CODE.select("div.time_image").first().text();
                Element document_GET_TIME_HTML_CODE = document_FULL_HTML_CODE.select("div." + document_TYPE_HTML_CODE).first();

                if (document_NOW_DAY_HTML_CODE.equals("3")) {
                    Elements document_GET_TIME_HTML_PnPt = document_GET_TIME_HTML_CODE.select("div.sr > p");
                    for (Element document_GET_TIME_P : document_GET_TIME_HTML_PnPt) {
                        timeListMains.add(new TimeList_main(document_GET_TIME_P.select("p").text()));
                    }
                } else if (document_NOW_DAY_HTML_CODE.equals("6")) {
                    Elements document_GET_TIME_HTML_PnPt = document_GET_TIME_HTML_CODE.select("div.sb > p");
                    for (Element document_GET_TIME_P : document_GET_TIME_HTML_PnPt) {
                        timeListMains.add(new TimeList_main(document_GET_TIME_P.select("p").text()));
                    }
                } else {
                    Elements document_GET_TIME_HTML_PnPt = document_GET_TIME_HTML_CODE.select("div.pnPt > p");
                    for (Element document_GET_TIME_P : document_GET_TIME_HTML_PnPt) {
                        timeListMains.add(new TimeList_main(document_GET_TIME_P.select("p").text()));
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

            switch (document_TIME_IMAGE_HTML_CODE) {
                case "morning":
                    hello_bitmap.setImageResource(R.drawable.morning);
                    hello_title.setText("Доброе утро!");
                    break;
                case "daytime":
                    hello_bitmap.setImageResource(R.drawable.daytime);
                    hello_title.setText("Добрый день!");
                    break;
                case "evening":
                    hello_bitmap.setImageResource(R.drawable.evening);
                    hello_title.setText("Добрый вечер!");
                    break;
                case "night":
                    hello_bitmap.setImageResource(R.drawable.night);
                    hello_title.setText("Доброй ночи!");
                    break;

            }
            timeListAdapterMain = new TimeListAdapter_main(mContext, timeListMains);
            recycler_view_timeline.setAdapter(timeListAdapterMain);
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class ThreadGetGroupName extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document document_FULL_HTML_CODE = Jsoup.connect(_MAIN_URL_FOR_GROUP_NAME  + "cg.htm").get();
                Element document_TABLE_HTML_CODE = document_FULL_HTML_CODE.select("table.inf").first();
                Elements document_TABLE_ELEMENTS = document_TABLE_HTML_CODE.select("tr");
                for (Element document_TABLE_SELECTED : document_TABLE_ELEMENTS) {
                    String group_TITLE = document_TABLE_SELECTED.select("tr > td.ur > a.z0").text();
                    String group_ID = document_TABLE_SELECTED.select("tr > td.ur > a.z0").attr("href");
                    if (!group_TITLE.equals("")) {
                        groupListMains.add(new GroupList_main(group_TITLE, group_ID));
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
            groupListAdapterMain = new GroupListAdapter_main(mContext, groupListMains);
            recycler_view_group.setAdapter(groupListAdapterMain);
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.item_animation_fall_down);
            recycler_view_group.startAnimation(animation);
            cdd.dismiss();
        }
    }
}
