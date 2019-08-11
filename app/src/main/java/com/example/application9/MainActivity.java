package com.example.application9;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.transition.Fade;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application9.AdaptersPackage.GroupListAdapter_main;
import com.example.application9.AdaptersPackage.TimeListAdapter_main;
import com.example.application9.DataPackage.GroupList_main;
import com.example.application9.DataPackage.TimeList_main;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static String _MAIN_URL_FOR_GROUP_NAME = "http://83.174.201.182/cg.htm";
    public static String _MAIN_URL_FOR_TIMES = "http://s917802v.bget.ru/times/";

    private List<GroupList_main> groupListMains = new ArrayList<>();
    private List<TimeList_main> timeListMains = new ArrayList<>();
    private RecyclerView recycler_view, recycler_view_time;
    private Context mContext;
    private View outlook_switch, outlook_bg;
    private TextView day_button_sw, week_button_sw;
    private LinearLayout outlook_button_condition;
    private ImageView hello_bitmap;
    private Toolbar toolbar;
    private TextView hello_title;
    private Float dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Animation
        getWindow().setEnterTransition(new Fade());
        //Animation

        //
        dp = getApplicationContext().getResources().getDisplayMetrics().density;
        //

        //initializing all component
        recycler_view = findViewById(R.id.recycler_view);
        recycler_view_time = findViewById(R.id.recycler_view_time);
        outlook_switch = findViewById(R.id.outlook_switch);
        outlook_bg = findViewById(R.id.outlook_bg);
        day_button_sw = findViewById(R.id.day_button_sw);
        week_button_sw = findViewById(R.id.week_button_sw);
        outlook_button_condition = findViewById(R.id.outlook_button_condition);
        hello_bitmap = findViewById(R.id.hello_bitmap);
        hello_title = findViewById(R.id.hello_title);
        NestedScrollView fake_fragment_schedule = findViewById(R.id.fake_fragment_schedule);
        NestedScrollView fake_fragment_timeline = findViewById(R.id.fake_fragment_timeline);
        NestedScrollView fake_fragment_account = findViewById(R.id.fake_fragment_account);
        BottomNavigationView bottom_nav_view = findViewById(R.id.bottom_nav_view);
        toolbar = findViewById(R.id.toolbar);
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

        //Get Time EX
        toolbar.setTitle("Hello!");
        //Get Time EX

        //Toolbar and AppBar Elements
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.settings) {
                startActivity(new Intent(mContext, SettingActivity.class));
            }
            return false;
        });

        bottom_nav_view.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId())
            {
                case R.id.schedule: fake_fragment_schedule.setVisibility(View.VISIBLE); fake_fragment_timeline.setVisibility(View.GONE); fake_fragment_account.setVisibility(View.GONE); return true;
                case R.id.timeline: fake_fragment_schedule.setVisibility(View.GONE); fake_fragment_timeline.setVisibility(View.VISIBLE); fake_fragment_account.setVisibility(View.GONE); return true;
                case R.id.account: fake_fragment_schedule.setVisibility(View.GONE); fake_fragment_timeline.setVisibility(View.GONE); fake_fragment_account.setVisibility(View.VISIBLE); return true;

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
        week_button_sw.setOnClickListener(v -> {
            int week_button_sw_w = week_button_sw.getWidth();
            outlook_switch.animate().translationX(0 * dp).setDuration(150).start();
            ValueAnimator anim = ValueAnimator.ofInt(outlook_switch.getMeasuredWidth(), week_button_sw_w + 24 + 16 + 8);
            anim.addUpdateListener(valueAnimator -> {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = outlook_switch.getLayoutParams();
                layoutParams.width = val;
                outlook_switch.setLayoutParams(layoutParams);
            });
            anim.setDuration(150);
            anim.start();
            detailWith();
        });

        day_button_sw.setOnClickListener(v -> {
            int day_button_sw_w = day_button_sw.getWidth();
            outlook_switch.animate().translationX(day_button_sw_w).setDuration(150).start();
            ValueAnimator anim = ValueAnimator.ofInt(outlook_switch.getMeasuredWidth(), day_button_sw_w + 24 + 16 + 8);
            anim.addUpdateListener(valueAnimator -> {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = outlook_switch.getLayoutParams();
                layoutParams.width = val;
                outlook_switch.setLayoutParams(layoutParams);
            });
            anim.setDuration(150);
            anim.start();

        });
        //Outlooks details

        detailWith();
    }

    private void toolbarColorChange(){
        //color extracting
        Bitmap helloImage = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.night);
        Palette.generateAsync(helloImage, palette -> {
            assert palette != null;
            assert palette.getDominantSwatch() != null;
            toolbar.setBackgroundColor(palette.getDominantSwatch().getRgb());
            toolbar.setTitleTextColor(palette.getDominantSwatch().getBodyTextColor());
        });
        //color extracting
    }


    private void detailWith() {
        ViewGroup.LayoutParams layoutParams_bg = outlook_bg.getLayoutParams();
        layoutParams_bg.width = outlook_button_condition.getWidth();
        layoutParams_bg.height = (int) (32 * dp);
        outlook_bg.setLayoutParams(layoutParams_bg);
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
                Element document_GET_TIME_HTML_CODE = document_FULL_HTML_CODE.select("div."+document_TYPE_HTML_CODE).first();

                if (document_NOW_DAY_HTML_CODE.equals("3")) {
                    Elements document_GET_TIME_HTML_PnPt = document_GET_TIME_HTML_CODE.select("div.sr > p");
                    for (Element document_GET_TIME_P: document_GET_TIME_HTML_PnPt) {
                        timeListMains.add(new TimeList_main(document_GET_TIME_P.select("p").text()));
                    }
                } else if (document_NOW_DAY_HTML_CODE.equals("6")) {
                    Elements document_GET_TIME_HTML_PnPt = document_GET_TIME_HTML_CODE.select("div.sb > p");
                    for (Element document_GET_TIME_P: document_GET_TIME_HTML_PnPt) {
                        timeListMains.add(new TimeList_main(document_GET_TIME_P.select("p").text()));
                    }
                } else {
                    Elements document_GET_TIME_HTML_PnPt = document_GET_TIME_HTML_CODE.select("div.pnPt > p");
                    for (Element document_GET_TIME_P: document_GET_TIME_HTML_PnPt) {
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
                case "morning": hello_bitmap.setImageResource(R.drawable.morning); hello_title.setText("Доброе утро!"); break;
                case "daytime": hello_bitmap.setImageResource(R.drawable.daytime); hello_title.setText("Добрый день!"); break;
                case "evening": hello_bitmap.setImageResource(R.drawable.evening); hello_title.setText("Добрый вечер!"); break;
                case "night": hello_bitmap.setImageResource(R.drawable.night); hello_title.setText("Доброй ночи!"); break;

            }
            toolbarColorChange();
            TimeListAdapter_main timeListAdapterMain = new TimeListAdapter_main(mContext, timeListMains);
            recycler_view_time.setAdapter(timeListAdapterMain);
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class ThreadGetGroupName extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document document_FULL_HTML_CODE = Jsoup.connect(_MAIN_URL_FOR_GROUP_NAME).get();
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
            GroupListAdapter_main groupListAdapterMain = new GroupListAdapter_main(mContext, groupListMains);
            recycler_view.setAdapter(groupListAdapterMain);
        }
    }
}
