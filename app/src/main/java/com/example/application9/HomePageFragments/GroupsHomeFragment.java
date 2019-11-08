package com.example.application9.HomePageFragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application9.AdaptersPackage.GroupListAdapter_main;
import com.example.application9.DataPackage.GroupList_main;
import com.example.application9.GroupActivity;
import com.example.application9.R;
import com.google.android.material.card.MaterialCardView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.application9.MainActivity._MAIN_URL_FOR_GROUP_NAME;
import static com.example.application9.MainActivity._SECOND_GROUP_ID;
import static com.example.application9.MainActivity._SECOND_GROUP_NAME;
import static com.example.application9.MainActivity.groupListAdapterMain;
import static com.example.application9.MainActivity.myPreferences;
import static com.example.application9.MainActivity.top_pin;
import static com.example.application9.MainActivity.top_pin_title;

public class GroupsHomeFragment extends Fragment {

    private RecyclerView recycler_view_group;
    public static MaterialCardView pin_group_bg;
    @SuppressLint("StaticFieldLeak")
    public static TextView pin_group_title;
    private List<GroupList_main> groupListMains = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_schedule_list, container, false);

        recycler_view_group = view.findViewById(R.id.recycler_view);
        pin_group_bg = view.findViewById(R.id.pin_group_bg);
        NestedScrollView scroll = view.findViewById(R.id.scroll);
        Button unpin_button = view.findViewById(R.id.unpin_button);
        pin_group_title = view.findViewById(R.id.pin_group_title);

        //top_pin.setAlpha(0);
        //top_pin.setVisibility(View.GONE);

        ThreadGetGroupNameWeek getGroupNameWeek = new ThreadGetGroupNameWeek();
        getGroupNameWeek.execute();

        scroll.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if (!_SECOND_GROUP_NAME.equals("null") && !_SECOND_GROUP_ID.equals("null")) {
                if (scrollY > 800 && top_pin.getAlpha() == 0) {
                    top_pin.setVisibility(View.VISIBLE);
                    top_pin.setAlpha(1);
                    Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.item_animation_fall_down);
                    top_pin.setAnimation(anim);
                }

                if (scrollY < 800) {
                    Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.item_animation_fall_down_out);
                    anim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            top_pin.setAlpha(0);
                            top_pin.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    top_pin.setAnimation(anim);
                }
            }
        });

        top_pin.setOnClickListener(v -> {
            if (!_SECOND_GROUP_NAME.equals("null") && !_SECOND_GROUP_ID.equals("null")) {
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(Objects.requireNonNull(getContext()));
                _SECOND_GROUP_NAME = sharedPref.getString("group_TITLE", "null");
                _SECOND_GROUP_ID = sharedPref.getString("group_ID", "null");
                Intent intent = new Intent(getContext(), GroupActivity.class);
                intent.putExtra("_TYPE", "cg");
                intent.putExtra("_TITLE", _SECOND_GROUP_NAME);
                intent.putExtra("_ID", _SECOND_GROUP_ID);
                Objects.requireNonNull(getContext()).startActivity(intent);
            }
        });

        pin_group_bg.setOnClickListener(v -> {
            if (!_SECOND_GROUP_NAME.equals("null") && !_SECOND_GROUP_ID.equals("null")) {
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(Objects.requireNonNull(getContext()));
                _SECOND_GROUP_NAME = sharedPref.getString("group_TITLE", "null");
                _SECOND_GROUP_ID = sharedPref.getString("group_ID", "null");
                Intent intent = new Intent(getContext(), GroupActivity.class);
                intent.putExtra("_TYPE", "cg");
                intent.putExtra("_TITLE", _SECOND_GROUP_NAME);
                intent.putExtra("_ID", _SECOND_GROUP_ID);
                Objects.requireNonNull(getContext()).startActivity(intent);
            }
        });

        unpin_button.setOnClickListener(v -> {
            @SuppressLint("CommitPrefEdits") SharedPreferences.Editor myEditor = myPreferences.edit();
            myEditor.putString("group_TITLE", "null");
            myEditor.putString("group_ID", "null");
            myEditor.apply();
            _SECOND_GROUP_NAME = "null";
            _SECOND_GROUP_ID = "null";

            Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.item_animation_fall_down_out);
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    pin_group_bg.setVisibility(View.GONE);
                    top_pin.setVisibility(View.GONE);
                    pin_group_title.setText(_SECOND_GROUP_NAME);
                    top_pin_title.setText(_SECOND_GROUP_NAME);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            pin_group_bg.startAnimation(anim);
        });

        if (!_SECOND_GROUP_NAME.equals("null") && !_SECOND_GROUP_ID.equals("null")) {
            pin_group_bg.setVisibility(View.VISIBLE);
            pin_group_title.setText(_SECOND_GROUP_NAME);
            top_pin_title.setText(_SECOND_GROUP_NAME);
        }

        return view;
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
            groupListAdapterMain = new GroupListAdapter_main(getContext(), groupListMains);
            recycler_view_group.setAdapter(groupListAdapterMain);
            recycler_view_group.setAlpha(0);
            recycler_view_group.animate().setDuration(250).alpha(1).start();
            //cdd.dismiss();
        }
    }

}
