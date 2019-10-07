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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.preference.PreferenceManager;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.example.application9.AdaptersPackage.GroupListAdapter_main;
import com.example.application9.AdaptersPackage.TimeListAdapter_main;
import com.example.application9.CustomDialog.FirstDialog;
import com.example.application9.DataPackage.GroupList_main;
import com.example.application9.DataPackage.TimeList_main;
import com.example.application9.DataPackage.VariableList;
import com.example.application9.HomePageFragments.AccountFragment;
import com.example.application9.HomePageFragments.GroupsHomeFragment;
import com.example.application9.HomePageFragments.TimeLineFragment;
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

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.application9.BottomSheets.BottomSheetColorFragment._COLOR_TEMP;
import static com.example.application9.BottomSheets.BottomSheetColorFragment._COLOR_TEMP_INT;
import static com.example.application9.BottomSheets.BottomSheetColorFragment._THEME_TEMP;
import static com.example.application9.HomePageFragments.AccountFragment.acc_bg_image;
import static com.example.application9.HomePageFragments.AccountFragment.acc_full_name;
import static com.example.application9.HomePageFragments.AccountFragment.acc_gradient_view;
import static com.example.application9.HomePageFragments.AccountFragment.acc_info;
import static com.example.application9.HomePageFragments.AccountFragment.acc_small_image;
import static com.example.application9.HomePageFragments.GroupsHomeFragment.recycler_view_group;
import static com.example.application9.HomePageFragments.TimeLineFragment.recycler_view_timeline;
import static com.example.application9.HomePageFragments.TimeLineFragment.recycler_view_timeline_sb;
import static com.example.application9.HomePageFragments.TimeLineFragment.recycler_view_timeline_sr;

public class MainActivity extends AppCompatActivity {

    public static String _MAIN_URL_CONSTRUCTOR_C = "cg";
    public static String _MAIN_URL_FOR_GROUP_NAME = "http://83.174.201.182/";
    public static String _MAIN_URL_FOR_TIMES = "http://s917802v.beget.tech/server_time_new/";
    public static String _MAIN_URL_FOR_VARIABLE = "http://s917802v.beget.tech/server_variable/";
    public static String _MAIN_ACHIEVEMENT = "http://s917802v.beget.tech/server_account/achievement/checkachievement.php?";
    public static String _ONE_DAY_SITE_CODE;
    public static String _UID_G = "null", _AID_G = "", _PASSWORD = "null";
    public static String _IMG_SMALL_ACC = "null", _IMG_FULL_ACC = "null";
    public static String _NOW_DAY;

    @SuppressLint("StaticFieldLeak")
    public static GroupListAdapter_main groupListAdapterMain;
    @SuppressLint("StaticFieldLeak")
    public static TimeListAdapter_main timeListAdapterMain, timeListAdapterMain_sr, timeListAdapterMain_sb;

    public static CoordinatorLayout main_content;
    public static String _DESIGN_COLOR;
    public static String _SITE;
    public static String _DARK_THEME;
    public static int _THEME_INT, _COLOR_INT;
    public static boolean _ONE_DAY;
    public static int resID;
    public static List<TimeList_main> timeListMains = new ArrayList<>();
    public static List<TimeList_main> timeListMain_sr = new ArrayList<>();
    public static List<TimeList_main> timeListMain_sb = new ArrayList<>();
    public static List<VariableList> variableList = new ArrayList<>();
    @SuppressLint("StaticFieldLeak")
    private static Activity activity;
    private List<GroupList_main> groupListMains = new ArrayList<>();

    public static String _SECOND_GROUP_NAME, _SECOND_GROUP_ID;
    public static SharedPreferences myPreferences;
    @SuppressLint("StaticFieldLeak")
    public static MaterialCardView top_pin;
    @SuppressLint("StaticFieldLeak")
    public static TextView top_pin_title;

    @SuppressLint("StaticFieldLeak")
    private static Context mContext;
    private ImageView hello_bitmap;
    private TextView hello_title;
    private FirstDialog cdd;

    @SuppressLint("StaticFieldLeak")
    private static MaterialCardView ac_bg_main;
    private static CircleImageView ac_preview;
    @SuppressLint("StaticFieldLeak")
    private static TextView ac_title, ac_main_text;

    final Fragment fragment1 = new GroupsHomeFragment();
    final Fragment fragment2 = new TimeLineFragment();
    final Fragment fragment3 = new AccountFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;

    public static final String BLOCK_ID = "R-M-457144-1";

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Setting
        myPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        _SITE = sharedPref.getString("_site_list", "first_site");
        _ONE_DAY = sharedPref.getBoolean("_main_one_day_switch", false);

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

        //Fragments
        fm.beginTransaction().add(R.id.fragment_container, fragment3, "3").hide(fragment3).commit();
        fm.beginTransaction().add(R.id.fragment_container, fragment2, "2").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.fragment_container, fragment1, "1").commit();

        cdd = new FirstDialog(this);
        Objects.requireNonNull(cdd.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //Fragments

        //initializing all component
        hello_bitmap = findViewById(R.id.backdrop_bitmap);
        hello_title = findViewById(R.id.hello_title);
        top_pin = findViewById(R.id.top_pin);
        top_pin_title = findViewById(R.id.top_pin_title);

        main_content = findViewById(R.id.main_content);

        ac_bg_main = findViewById(R.id.ac_bg_main);
        ac_preview = findViewById(R.id.ac_preview);
        ac_title = findViewById(R.id.ac_title);
        ac_main_text = findViewById(R.id.ac_main_text);


        Toolbar toolbar1 = findViewById(R.id.toolbar);
        mContext = MainActivity.this;
        setSupportActionBar(toolbar1);
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

        //Toolbar and AppBar Elements
        bottom_nav_view.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.schedule:
                    if (active != fragment1) {
                        fm.beginTransaction().setCustomAnimations(R.animator.fragment_anim_in, R.animator.fragment_anim_out).hide(active).show(fragment1).commit();
                    }
                    active = fragment1;
                    return true;
                case R.id.timeline:
                    if (active != fragment2) {
                        fm.beginTransaction().setCustomAnimations(R.animator.fragment_anim_in, R.animator.fragment_anim_out).hide(active).show(fragment2).commit();
                    }
                    active = fragment2;
                    return true;
                case R.id.account:
                    if (active != fragment3) {
                        fm.beginTransaction().setCustomAnimations(R.animator.fragment_anim_in, R.animator.fragment_anim_out).hide(active).show(fragment3).commit();
                    }
                    active = fragment3;
                    return true;
            }
            return false;
        });
        //Toolbar and AppBar Elements

        //thread start and more
        threadStart();
        //thread start and more
    }

    private void threadStart() {
        timeListMains = new ArrayList<>();
        LottieAnimationView lottieAnimationView = findViewById(R.id.no_internet);
        if (NetworkManager.isNetworkAvailable(mContext)) {
            if (!_ONE_DAY) {
                ThreadGetGroupNameWeek getGroupNameWeek = new ThreadGetGroupNameWeek();
                getGroupNameWeek.execute();
            } else {
                ThreadGetGroupNameOneDay getGroupNameOneDay = new ThreadGetGroupNameOneDay();
                getGroupNameOneDay.execute();
            }

            ThreadGetTime threadGetTime = new ThreadGetTime();
            threadGetTime.execute();

            ThreadGetVariable threadGetVariable = new ThreadGetVariable();
            threadGetVariable.execute();

            cdd.show();
            lottieAnimationView.setVisibility(View.GONE);
        } else {
            Toast.makeText(mContext, "Check your network connection", Toast.LENGTH_SHORT).show();
            lottieAnimationView.setVisibility(View.VISIBLE);
            hello_title.animate().setDuration(500).alpha(0).start();
            hello_bitmap.animate().setDuration(250).setStartDelay(800).alpha(0).start();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void onCheckTheme() {
        if (!_DARK_THEME.equals(_THEME_TEMP) || !_DESIGN_COLOR.equals(_COLOR_TEMP_INT)) {
            activity.finish();
            mContext.startActivity(new Intent(mContext, MainActivity.class), ActivityOptions.makeScaleUpAnimation(main_content, 0, 0, main_content.getWidth(), main_content.getHeight()).toBundle());

        }
    }

    public static void onCheckLogin() {

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(mContext);
        _UID_G = sharedPref.getString("account_id", "null");
        _PASSWORD = sharedPref.getString("account_password", "null");

        assert _PASSWORD != null;
        if (!_UID_G.equals("null") || !_PASSWORD.equals("null")) {
            LoginAccount loginAccount = new LoginAccount();
            loginAccount.execute();
        }
    }

    @SuppressLint("StaticFieldLeak")
    static
    class LoginAccount extends AsyncTask<View, View, View> {

        String full_name, photo_200, photo_max_orig, body;
        boolean errors = false;

        @Override
        protected View doInBackground(View... views) {
            try {
                Document responses_check;
                responses_check = Jsoup.connect("http://s917802v.beget.tech/server_account/loginaccount.php" +
                        "?id=" + _UID_G +
                        "&password=" + _PASSWORD).get();

                body = responses_check.body().toString();
                errors = body.contains("error");

                if (!errors) {
                    full_name = responses_check.select("div.full_name").text();
                    photo_200 = responses_check.select("div.src_200px").text();
                    photo_max_orig = responses_check.select("div.src_fullpx").text();
                    _IMG_SMALL_ACC = photo_200;
                    _IMG_FULL_ACC = photo_max_orig;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(View view) {
            super.onPostExecute(view);
            if (!errors) {
                acc_info.setVisibility(View.VISIBLE);
                acc_gradient_view.setVisibility(View.VISIBLE);
                acc_full_name.setText(full_name);
                Glide.with(mContext).load(photo_max_orig).into(acc_bg_image);
                Glide.with(mContext).load(photo_max_orig).into(acc_small_image);
                acc_bg_image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            } else {
                Toast.makeText(mContext, "Произошла ошибка авторизации.", Toast.LENGTH_SHORT).show();
                _UID_G = "null";
                _PASSWORD = "null";
                SharedPreferences.Editor myEditor = myPreferences.edit();
                myEditor.putString("account_id", _UID_G);
                myEditor.putString("account_password", _PASSWORD);
                myEditor.apply();
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    public static class AchievementCheck extends AsyncTask<View, View, View> {

        String body, name, description, preview;
        boolean errors, get = false;

        @Override
        protected View doInBackground(View... views) {

            Document responses_check = null;
            try {
                responses_check = Jsoup.connect(_MAIN_ACHIEVEMENT +
                        "id=" + _AID_G +
                        "&user_id=" + _UID_G).get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            assert responses_check != null;
            body = responses_check.body().toString();
            get = body.contains("congratulate");
            errors = body.contains("error");

            if (get) {
                name = responses_check.select("div.name").text();
                description = responses_check.select("div.description").text();
                preview = responses_check.select("div.preview").text();
            }

            return null;
        }

        @Override
        protected void onPostExecute(View view) {
            super.onPostExecute(view);

            Animation anim1 = AnimationUtils.loadAnimation(mContext, R.anim.item_animation_fall_down_out);
            anim1.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            ac_bg_main.setAnimation(anim1);
            ac_bg_main.startAnimation(anim1);

            if (errors) {
                Toast.makeText(mContext, body, Toast.LENGTH_SHORT).show();
            } else {
                if (get) {
                    Toast.makeText(mContext, "Достижение получено!", Toast.LENGTH_SHORT).show();
                    ac_title.setText(name);
                    ac_main_text.setText(description);
                    Glide.with(mContext).load(preview).into(ac_preview);
                    Animation anim = AnimationUtils.loadAnimation(mContext, R.anim.item_animation_fall_down);
                    anim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            ac_bg_main.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    ac_bg_main.startAnimation(anim);
                }
            }
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
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document document_FULL_HTML_CODE = Jsoup.connect(_MAIN_URL_FOR_TIMES).get();
                String document_TYPE_HTML_CODE = document_FULL_HTML_CODE.select("div.type").first().text();
                _NOW_DAY = document_FULL_HTML_CODE.select("div.now_day").first().text();
                Element document_GET_TIME_HTML_CODE = document_FULL_HTML_CODE.select("div." + document_TYPE_HTML_CODE).first();

                Elements document_GET_TIME_HTML_SR = document_GET_TIME_HTML_CODE.select("div.pnPt > p");
                for (Element document_GET_TIME_P : document_GET_TIME_HTML_SR) {
                    timeListMains.add(new TimeList_main(document_GET_TIME_P.select("p").text()));
                }
                Elements document_GET_TIME_HTML_SB = document_GET_TIME_HTML_CODE.select("div.sr > p");
                for (Element document_GET_TIME_P : document_GET_TIME_HTML_SB) {
                    timeListMain_sr.add(new TimeList_main(document_GET_TIME_P.select("p").text()));
                }
                Elements document_GET_TIME_HTML_PnPt = document_GET_TIME_HTML_CODE.select("div.sb > p");
                for (Element document_GET_TIME_P : document_GET_TIME_HTML_PnPt) {
                    timeListMain_sb.add(new TimeList_main(document_GET_TIME_P.select("p").text()));
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

            timeListAdapterMain = new TimeListAdapter_main(mContext, timeListMains);
            timeListAdapterMain_sr = new TimeListAdapter_main(mContext, timeListMain_sr);
            timeListAdapterMain_sb = new TimeListAdapter_main(mContext, timeListMain_sb);

            recycler_view_timeline.setAdapter(timeListAdapterMain);
            recycler_view_timeline_sr.setAdapter(timeListAdapterMain_sr);
            recycler_view_timeline_sb.setAdapter(timeListAdapterMain_sb);

            hello_bitmap.animate().setDuration(500).alpha(1).start();
            hello_title.animate().setDuration(250).setStartDelay(800).alpha(1).start();
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class ThreadGetGroupNameOneDay extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document document_FULL_HTML_CODE = Jsoup.connect(_MAIN_URL_FOR_GROUP_NAME + _MAIN_URL_CONSTRUCTOR_C + ".htm").get();
                _ONE_DAY_SITE_CODE = document_FULL_HTML_CODE.html();
                Element document_TABLE_HTML_CODE = document_FULL_HTML_CODE.select("table.inf").first();
                Elements document_TABLE_ELEMENTS = document_TABLE_HTML_CODE.select("tr");
                for (Element document_TABLE_SELECTED : document_TABLE_ELEMENTS) {
                    String group_TITLE = document_TABLE_SELECTED.select("tr > td.hd > a.hd").text();
                    String group_ID = document_TABLE_SELECTED.select("tr > td.hd > a.hd").attr("href");
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
            groupListAdapterMain = new GroupListAdapter_main(mContext, groupListMains);
            recycler_view_group.setAdapter(groupListAdapterMain);
            cdd.dismiss();
        }

    }

    @SuppressLint("StaticFieldLeak")
    public class ThreadGetGroupNameWeek extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document document_FULL_HTML_CODE = Jsoup.connect(_MAIN_URL_FOR_GROUP_NAME + _MAIN_URL_CONSTRUCTOR_C + ".htm").get();
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
            groupListAdapterMain = new GroupListAdapter_main(mContext, groupListMains);
            recycler_view_group.setAdapter(groupListAdapterMain);
            recycler_view_group.setAlpha(0);
            recycler_view_group.animate().setDuration(250).alpha(1).start();
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
            //TODO: Проверка логина
//            onCheckLogin();
        }
    }

}
