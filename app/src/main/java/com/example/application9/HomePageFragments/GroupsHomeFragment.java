package com.example.application9.HomePageFragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
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
import androidx.recyclerview.widget.RecyclerView;

import com.example.application9.GroupActivity;
import com.example.application9.R;
import com.google.android.material.card.MaterialCardView;

import java.util.Objects;

import static com.example.application9.MainActivity._SECOND_GROUP_ID;
import static com.example.application9.MainActivity._SECOND_GROUP_NAME;
import static com.example.application9.MainActivity.myPreferences;
import static com.example.application9.MainActivity.top_pin;
import static com.example.application9.MainActivity.top_pin_title;

public class GroupsHomeFragment extends Fragment {

    public static RecyclerView recycler_view_group;
    @SuppressLint("StaticFieldLeak")
    public static MaterialCardView pin_group_bg;
    @SuppressLint("StaticFieldLeak")
    public static TextView pin_group_title;
    @SuppressLint("StaticFieldLeak")
    public static NestedScrollView scroll;

    Button unpin_button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_schedule_list, container, false);

        recycler_view_group = view.findViewById(R.id.recycler_view);
        pin_group_bg = view.findViewById(R.id.pin_group_bg);
        scroll = view.findViewById(R.id.scroll);
        unpin_button = view.findViewById(R.id.unpin_button);
        pin_group_title = view.findViewById(R.id.pin_group_title);

        top_pin.setAlpha(0);
        top_pin.setVisibility(View.GONE);


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
                Intent intent = new Intent(getContext(), GroupActivity.class);
                intent.putExtra("group_TITLE", _SECOND_GROUP_NAME);
                intent.putExtra("group_ID", _SECOND_GROUP_ID);
                Objects.requireNonNull(getContext()).startActivity(intent);
            }
        });

        pin_group_bg.setOnClickListener(v -> {
            if (!_SECOND_GROUP_NAME.equals("null") && !_SECOND_GROUP_ID.equals("null")) {
                Intent intent = new Intent(getContext(), GroupActivity.class);
                intent.putExtra("group_TITLE", _SECOND_GROUP_NAME);
                intent.putExtra("group_ID", _SECOND_GROUP_ID);
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
}
