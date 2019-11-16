package com.example.application9;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityOptionsCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.PreferenceManager;

import com.bumptech.glide.Glide;
import com.example.application9.AdaptersPackage.GroupListAdapter_main;
import com.example.application9.CustomDialog.FirstDialog;
import com.example.application9.DataPackage.GroupList_main;
import com.example.application9.DataPackage.NewsUpdate_List;
import com.example.application9.DataPackage.TimeList_main;
import com.example.application9.DataPackage.VariableList;
import com.example.application9.Support.NetworkManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;
import com.yandex.mobile.ads.AdEventListener;
import com.yandex.mobile.ads.AdRequest;
import com.yandex.mobile.ads.AdSize;
import com.yandex.mobile.ads.AdView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static com.example.application9.BottomSheets.BottomSheetColorFragment._COLOR_TEMP;
import static com.example.application9.BottomSheets.BottomSheetColorFragment._COLOR_TEMP_INT;
import static com.example.application9.BottomSheets.BottomSheetColorFragment._THEME_TEMP;
import static com.example.application9.HomePageFragments.GroupsHomeFragment.onSetUpGroup;

public class MainActivity extends AppCompatActivity {

    public static String _MAIN_URL_FOR_GROUP_NAME = "http://83.174.201.182/";
    public static String _MAIN_URL_FOR_TIMES = "http://s917802v.beget.tech/server_time_new/";
    public static String _MAIN_URL_FOR_VARIABLE = "http://s917802v.beget.tech/server_variable/";
    public static String _UID_G = "null", _AID_G = "", _PASSWORD = "null";
    public static String _NOW_DAY;

    public static List<GroupList_main> groupListMains = new ArrayList<>();
    public static List<TimeList_main> timeListMain = new ArrayList<>();
    public static List<TimeList_main> timeListMain_sr = new ArrayList<>();
    public static List<TimeList_main> timeListMain_sb = new ArrayList<>();
    public static List<VariableList> variableList = new ArrayList<>();
    public static List<NewsUpdate_List> newsUpdateLists = new ArrayList<>();

    @SuppressLint("StaticFieldLeak")
    public static GroupListAdapter_main groupListAdapterMain;
    @SuppressLint("StaticFieldLeak")

    public static CoordinatorLayout main_content;
    public static String _DESIGN_COLOR;
    public static String _SITE;
    public static String _DARK_THEME;
    public static int _THEME_INT, _COLOR_INT;
    public static int resID;
    @SuppressLint("StaticFieldLeak")
    private static Activity activity;

    public static String _SECOND_GROUP_NAME, _SECOND_GROUP_ID;
    public static SharedPreferences myPreferences;

    @SuppressLint("StaticFieldLeak")
    private static Context mContext;
    @SuppressLint("StaticFieldLeak")
    public static ImageView hello_bitmap;
    @SuppressLint("StaticFieldLeak")
    public static TextView hello_title;
    private FirstDialog cdd;
    public static BottomNavigationView bottom_nav_view;
    public static final String BLOCK_ID = "R-M-457149-1";

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Setting
        myPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        _SITE = sharedPref.getString("_site_list", "first_site");

        _DESIGN_COLOR = sharedPref.getString("_design_color_list_new", "Red");
        _DARK_THEME = sharedPref.getString("_theme_lds_list_new", "Android");

        _COLOR_TEMP = _DESIGN_COLOR;
        _THEME_TEMP = _DARK_THEME;

        _THEME_INT = sharedPref.getInt("_theme_lds_list_res", R.drawable.ic_launcher_background);
        _COLOR_INT = sharedPref.getInt("_design_color_list_res", R.color.colorAccent_Red);

        _SECOND_GROUP_NAME = sharedPref.getString("group_TITLE", "null");
        _SECOND_GROUP_ID = sharedPref.getString("group_ID", "null");

        if (_SITE.equals("first_site")) {
            _MAIN_URL_FOR_GROUP_NAME = "http://83.174.201.182/";
        } else {
            _MAIN_URL_FOR_GROUP_NAME = "http://83.174.201.182:85/";
        }

        resID = getResId("AppTheme_" + _DESIGN_COLOR + "_" + _DARK_THEME, R.style.class);

        setTheme(resID);
        //Setting

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = this;

        AdView mAdView = findViewById(R.id.banner_view);
        mAdView.setBlockId(BLOCK_ID);
        mAdView.setAdSize(AdSize.BANNER_320x50);

        final AdRequest adRequest = new AdRequest.Builder().build();

        // Регистрация слушателя для отслеживания событий, происходящих в баннерной рекламе.
        mAdView.setAdEventListener(new AdEventListener.SimpleAdEventListener() {
            @Override
            public void onAdLoaded() {

            }
        });

        // Загрузка объявления.
        mAdView.loadAd(adRequest);

        cdd = new FirstDialog(this);
        Objects.requireNonNull(cdd.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //Fragments

        //initializing all component
        hello_bitmap = findViewById(R.id.backdrop_bitmap);
        hello_title = findViewById(R.id.hello_title);

        main_content = findViewById(R.id.main_content);

        Toolbar toolbar1 = findViewById(R.id.toolbar);
        mContext = MainActivity.this;
        setSupportActionBar(toolbar1);
        bottom_nav_view = findViewById(R.id.bottom_nav_view);
        //initializing all component

        //TODO: Использовать в будущем
        //color extracting
//        Bitmap helloImage = BitmapFactory.decodeResource(mContext.getResources(), pin_group_bg.getCardBackgroundColor());
//        Palette.generateAsync(helloImage, palette -> {
//            assert palette != null;
//            assert palette.getDominantSwatch() != null;
//            pin_group_title.setBackgroundColor(palette.getDominantSwatch().getRgb());
//        });
        //color extracting

        //Toolbar and AppBar Elements
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(bottom_nav_view, navController);
        //Toolbar and AppBar Elements

        //thread start and more
        threadStart();
        //thread start and more
    }

    private void threadStart() {

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        _SITE = sharedPref.getString("_site_list", "first_site");
        assert _SITE != null;

        if (_SITE.equals("first_site")) {
            _MAIN_URL_FOR_GROUP_NAME = "http://83.174.201.182/";
        } else {
            _MAIN_URL_FOR_GROUP_NAME = "http://83.174.201.182:85/";
        }

        if (NetworkManager.isNetworkAvailable(mContext)) {

            timeListMain = new ArrayList<>();
            timeListMain_sr = new ArrayList<>();
            timeListMain_sb = new ArrayList<>();

            ThreadGetVariable threadGetVariable = new ThreadGetVariable();
            threadGetVariable.execute();

            ThreadGetNewsUpdate threadGetNewsUpdate = new ThreadGetNewsUpdate();
            threadGetNewsUpdate.execute();

            ThreadGetGroupNameWeek threadGetGroupNameWeek = new ThreadGetGroupNameWeek();
            threadGetGroupNameWeek.execute();

            ThreadGetTime threadGetTime = new ThreadGetTime();
            threadGetTime.execute();

            cdd.show();
        } else {
            Toast.makeText(mContext, "Check your network connection", Toast.LENGTH_SHORT).show();
            hello_title.animate().setDuration(500).alpha(0).start();
            hello_bitmap.animate().setDuration(250).setStartDelay(800).alpha(0).start();
        }
    }

    public void onSearch(View view){

        Intent intent = new Intent(this, SearchActivity.class);
        TextView textView_S = findViewById(R.id.edit_text_search);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, textView_S, "search_edit");
        startActivity(intent, options.toBundle());

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void onCheckTheme() {
        if (!_DARK_THEME.equals(_THEME_TEMP) || !_DESIGN_COLOR.equals(_COLOR_TEMP_INT)) {
            activity.finish();
            mContext.startActivity(new Intent(mContext, MainActivity.class), ActivityOptions.makeScaleUpAnimation(main_content, 0, 0, main_content.getWidth(), main_content.getHeight()).toBundle());

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_toolbar_menu, menu);
        MenuItem item = menu.findItem(R.id.refresh);

        item.setOnMenuItemClickListener(v -> {
            threadStart();
            return true;
        });

        return true;
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

        String v_app_server;

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document document_FULL_HTML_CODE = Jsoup.connect(_MAIN_URL_FOR_TIMES).get();
                String document_TYPE_HTML_CODE = document_FULL_HTML_CODE.select("div.type").first().text();
                _NOW_DAY = document_FULL_HTML_CODE.select("div.now_day").first().text();
                Element document_GET_TIME_HTML_CODE = document_FULL_HTML_CODE.select("div." + document_TYPE_HTML_CODE).first();

                v_app_server = document_FULL_HTML_CODE.select("div.vapp").first().text();

                Elements document_GET_TIME_HTML_SR = document_GET_TIME_HTML_CODE.select("div.pnPt > p");
                for (Element document_GET_TIME_P : document_GET_TIME_HTML_SR) {
                    timeListMain.add(new TimeList_main(document_GET_TIME_P.select("p").text(), 0));
                }
                Elements document_GET_TIME_HTML_SB = document_GET_TIME_HTML_CODE.select("div.sr > p");
                for (Element document_GET_TIME_P : document_GET_TIME_HTML_SB) {
                    timeListMain_sr.add(new TimeList_main(document_GET_TIME_P.select("p").text(), 0));
                }
                Elements document_GET_TIME_HTML_PnPt = document_GET_TIME_HTML_CODE.select("div.sb > p");
                for (Element document_GET_TIME_P : document_GET_TIME_HTML_PnPt) {
                    timeListMain_sb.add(new TimeList_main(document_GET_TIME_P.select("p").text(), 0));
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Date currentDate = new Date();
            DateFormat timeFormat = new SimpleDateFormat("k", Locale.getDefault());
            int times_ms = Integer.parseInt(timeFormat.format(currentDate));

            if (times_ms >= 0 && times_ms < 4) {
                Glide.with(mContext).load("http://s917802v.beget.tech/server_backdrop/images/night.jpg").skipMemoryCache(true).into(hello_bitmap);
                hello_title.setText("Доброй ночи!");
            } else if (times_ms >= 4 && times_ms < 12) {
                Glide.with(mContext).load("http://s917802v.beget.tech/server_backdrop/images/morning.jpg").skipMemoryCache(true).into(hello_bitmap);
                hello_title.setText("Доброе утро!");
            } else if (times_ms >= 12 && times_ms < 18) {
                Glide.with(mContext).load("http://s917802v.beget.tech/server_backdrop/images/daytime.jpg").skipMemoryCache(true).into(hello_bitmap);
                hello_title.setText("Добрый день!");
            } else if (times_ms >= 18) {
                Glide.with(mContext).load("http://s917802v.beget.tech/server_backdrop/images/evening.png").skipMemoryCache(true).into(hello_bitmap);
                hello_title.setText("Добрый вечер!");
            }

            hello_bitmap.animate().setDuration(500).alpha(1).start();
            hello_title.animate().setDuration(250).setStartDelay(800).alpha(1).start();
            String n_app = mContext.getString(R.string.version_in_app);

            if (!v_app_server.equals(n_app)) {
                bottom_nav_view.getOrCreateBadge(R.id.navigation_account);
                //update_button.setVisibility(View.VISIBLE);
            }
        }
    }

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
            cdd.dismiss();
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class ThreadGetVariable extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document document_FULL_HTML_CODE = Jsoup.connect(_MAIN_URL_FOR_VARIABLE).get();
                Element document_TABLE_HTML_CODE = document_FULL_HTML_CODE.select("tbody.table_sior").first();
                Elements document_TABLE_ELEMENTS = document_TABLE_HTML_CODE.select("tr");
                for (Element document_TABLE_SELECTED : document_TABLE_ELEMENTS) {
                    String name = document_TABLE_SELECTED.select("tr > td.mdl-data-table__cell--non-numeric").text();
                    String value = document_TABLE_SELECTED.select("tr > td.value").text();
                    String responsible = document_TABLE_SELECTED.select("tr > td.responsible").text();
                    String type = document_TABLE_SELECTED.select("tr > td.type_s").text();

                    variableList.add(new VariableList(name, value, responsible, type));

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class ThreadGetNewsUpdate extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                newsUpdateLists = new ArrayList<>();
                Document document_FULL_HTML_CODE = Jsoup.connect("http://s917802v.beget.tech/server_account/news.php").get();
                Elements document_GET_TIME_HTML_SR = document_FULL_HTML_CODE.select("div.news");
                for (Element document_GET_TIME_P : document_GET_TIME_HTML_SR) {
                    newsUpdateLists.add(new NewsUpdate_List(document_GET_TIME_P.select("div.title").text(), document_GET_TIME_P.select("div.image").text(), document_GET_TIME_P.select("div.news_text").text(), document_GET_TIME_P.select("div.progress").text(), document_GET_TIME_P.select("div.stage").text()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }
    }

}
