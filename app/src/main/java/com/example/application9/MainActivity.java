package com.example.application9;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.animation.Interpolator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application9.AdaptersPackage.GroupListAdapter_main;
import com.example.application9.DataPackage.GroupList_main;
import com.google.android.material.bottomappbar.BottomAppBar;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Animation
        getWindow().setEnterTransition(new Fade());
        //Animation

        //bottomAppbar
        BottomAppBar bottomAppBar = findViewById(R.id.bar);
        setSupportActionBar(bottomAppBar);
        //bottomAppbar

        //initializing all component
        recycler_view = findViewById(R.id.recycler_view);
        //initializing all component

        //Context
        mContext = MainActivity.this;
        //Context

        //thread start and more
        ThreadGetGroupName getGroupName = new ThreadGetGroupName();
        getGroupName.execute();
        //thread start and more

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
