package com.example.application9.HomePageFragments;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.application9.AdaptersPackage.TimeListAdapter_main;
import com.example.application9.DataPackage.TimeList_main;
import com.example.application9.MainActivity;
import com.example.application9.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static com.example.application9.MainActivity._MAIN_URL_FOR_TIMES;
import static com.example.application9.MainActivity.bottom_nav_view;
import static com.example.application9.MainActivity.hello_bitmap;
import static com.example.application9.MainActivity.hello_title;

public class TimeLineFragment extends Fragment {

    private RecyclerView recycler_view_timeline, recycler_view_timeline_sr, recycler_view_timeline_sb;
    public static List<TimeList_main> timeListMain = new ArrayList<>();
    public static List<TimeList_main> timeListMain_sr = new ArrayList<>();
    public static List<TimeList_main> timeListMain_sb = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_time_line, container, false);

        recycler_view_timeline = view.findViewById(R.id.recycler_view_time);
        recycler_view_timeline_sr = view.findViewById(R.id.recycler_view_time_sr);
        recycler_view_timeline_sb = view.findViewById(R.id.recycler_view_time_sb);

        ThreadGetTime threadGetTime = new ThreadGetTime();
        threadGetTime.execute();

        return view;
    }

    @SuppressLint("StaticFieldLeak")
    public class ThreadGetTime extends AsyncTask<Void, Void, Void> {

        String v_app_server;

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document document_FULL_HTML_CODE = Jsoup.connect(_MAIN_URL_FOR_TIMES).get();
                String document_TYPE_HTML_CODE = document_FULL_HTML_CODE.select("div.type").first().text();
                MainActivity._NOW_DAY = document_FULL_HTML_CODE.select("div.now_day").first().text();
                Element document_GET_TIME_HTML_CODE = document_FULL_HTML_CODE.select("div." + document_TYPE_HTML_CODE).first();

                v_app_server = document_FULL_HTML_CODE.select("div.vapp").first().text();

                Elements document_GET_TIME_HTML_SR = document_GET_TIME_HTML_CODE.select("div.pnPt > p");
                for (Element document_GET_TIME_P : document_GET_TIME_HTML_SR) {
                    timeListMain.add(new TimeList_main(document_GET_TIME_P.select("p").text(),0));
                }
                Elements document_GET_TIME_HTML_SB = document_GET_TIME_HTML_CODE.select("div.sr > p");
                for (Element document_GET_TIME_P : document_GET_TIME_HTML_SB) {
                    timeListMain_sr.add(new TimeList_main(document_GET_TIME_P.select("p").text(),0));
                }
                Elements document_GET_TIME_HTML_PnPt = document_GET_TIME_HTML_CODE.select("div.sb > p");
                for (Element document_GET_TIME_P : document_GET_TIME_HTML_PnPt) {
                    timeListMain_sb.add(new TimeList_main(document_GET_TIME_P.select("p").text(),0));
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
                Glide.with(Objects.requireNonNull(getContext())).load("http://s917802v.beget.tech/server_backdrop/images/night.jpg").skipMemoryCache(true).into(hello_bitmap);
                hello_title.setText("Доброй ночи!");
            } else if (times_ms >= 4 && times_ms < 12) {
                Glide.with(Objects.requireNonNull(getContext())).load("http://s917802v.beget.tech/server_backdrop/images/morning.jpg").skipMemoryCache(true).into(hello_bitmap);
                hello_title.setText("Доброе утро!");
            } else if (times_ms >= 12 && times_ms < 18) {
                Glide.with(Objects.requireNonNull(getContext())).load("http://s917802v.beget.tech/server_backdrop/images/daytime.jpg").skipMemoryCache(true).into(hello_bitmap);
                hello_title.setText("Добрый день!");
            } else if (times_ms >= 18) {
                Glide.with(Objects.requireNonNull(getContext())).load("http://s917802v.beget.tech/server_backdrop/images/evening.png").skipMemoryCache(true).into(hello_bitmap);
                hello_title.setText("Добрый вечер!");
            }

            TimeListAdapter_main timeListAdapterMain = new TimeListAdapter_main(getContext(), timeListMain);
            TimeListAdapter_main timeListAdapterMain_sr = new TimeListAdapter_main(getContext(), timeListMain_sr);
            TimeListAdapter_main timeListAdapterMain_sb = new TimeListAdapter_main(getContext(), timeListMain_sb);

            recycler_view_timeline.setAdapter(timeListAdapterMain);
            recycler_view_timeline_sr.setAdapter(timeListAdapterMain_sr);
            recycler_view_timeline_sb.setAdapter(timeListAdapterMain_sb);

            hello_bitmap.animate().setDuration(500).alpha(1).start();
            hello_title.animate().setDuration(250).setStartDelay(800).alpha(1).start();
            String n_app = Objects.requireNonNull(getContext()).getString(R.string.version_in_app);

            if (!v_app_server.equals(n_app)) {
                bottom_nav_view.getOrCreateBadge(R.id.navigation_account);
                //update_button.setVisibility(View.VISIBLE);
            }
        }
    }

}
