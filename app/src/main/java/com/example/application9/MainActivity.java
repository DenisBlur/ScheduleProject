package com.example.application9;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.transition.Fade;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application9.AdaptersPackage.GroupListAdapter_main;
import com.example.application9.DataPackage.GroupList_main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static String _MAIN_URL_FOR_GROUP_NAME = "http://83.174.201.182/cg.htm";

    private List<GroupList_main> groupListMains = new ArrayList<>();
    private RecyclerView recycler_view;
    private Context mContext;
    private View outlook_switch, outlook_bg;
    private TextView day_button_sw, week_button_sw;
    private LinearLayout outlook_button_condition;
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
        outlook_switch = findViewById(R.id.outlook_switch);
        outlook_bg = findViewById(R.id.outlook_bg);
        day_button_sw = findViewById(R.id.day_button_sw);
        week_button_sw = findViewById(R.id.week_button_sw);
        outlook_button_condition = findViewById(R.id.outlook_button_condition);
        //initializing all component

        //Context
        mContext = MainActivity.this;
        //Context

        //thread start and more
        ThreadGetGroupName getGroupName = new ThreadGetGroupName();
        getGroupName.execute();
        //thread start and more

        //Outlooks details
        week_button_sw.setOnClickListener(v -> {
            int week_button_sw_w = week_button_sw.getWidth();
            outlook_switch.animate().translationX(0 * dp).setDuration(150).start();
            ValueAnimator anim = ValueAnimator.ofInt(outlook_switch.getMeasuredWidth(), week_button_sw_w + 24+16+8);
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
            ValueAnimator anim = ValueAnimator.ofInt(outlook_switch.getMeasuredWidth(), day_button_sw_w + 24+16+8);
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

    private void detailWith() {
        ViewGroup.LayoutParams layoutParams_bg = outlook_bg.getLayoutParams();
        layoutParams_bg.width = outlook_button_condition.getWidth();
        layoutParams_bg.height = (int) (32*dp);
        outlook_bg.setLayoutParams(layoutParams_bg);
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
                    if (group_TITLE != "") {
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
