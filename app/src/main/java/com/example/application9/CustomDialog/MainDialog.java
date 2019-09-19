package com.example.application9.CustomDialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.application9.R;

import static com.example.application9.HomePageFragments.GroupsHomeFragment.pin_group_bg;
import static com.example.application9.HomePageFragments.GroupsHomeFragment.pin_group_title;
import static com.example.application9.MainActivity._SECOND_GROUP_ID;
import static com.example.application9.MainActivity._SECOND_GROUP_NAME;
import static com.example.application9.MainActivity.myPreferences;
import static com.example.application9.MainActivity.top_pin_title;

public class MainDialog extends Dialog implements View.OnClickListener {
    public Activity c;
    public Dialog d;

    public MainDialog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_pin);
        Button yes = findViewById(R.id.positive_button);
        Button no = findViewById(R.id.negative_button);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.positive_button:
                @SuppressLint("CommitPrefEdits") SharedPreferences.Editor myEditor = myPreferences.edit();
                myEditor.putString("group_TITLE", _SECOND_GROUP_NAME);
                myEditor.putString("group_ID", _SECOND_GROUP_ID);
                myEditor.apply();
                pin_group_bg.setVisibility(View.VISIBLE);
                pin_group_title.setText(_SECOND_GROUP_NAME);
                top_pin_title.setText(_SECOND_GROUP_NAME);
                pin_group_bg.setVisibility(View.VISIBLE);
                pin_group_bg.setAlpha(1);
                Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.item_animation_fall_down);
                pin_group_bg.setAnimation(anim);
                dismiss();
                break;
            case R.id.negative_button:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }

}
