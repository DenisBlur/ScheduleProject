package com.example.application9.HomePageFragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application9.AdaptersPackage.GroupListAdapter_main;
import com.example.application9.GroupActivity;
import com.example.application9.R;
import com.google.android.material.card.MaterialCardView;

import java.util.Objects;

import static com.example.application9.MainActivity._SECOND_GROUP_ID;
import static com.example.application9.MainActivity._SECOND_GROUP_NAME;
import static com.example.application9.MainActivity.groupListAdapterMain;
import static com.example.application9.MainActivity.groupListMains;
import static com.example.application9.MainActivity.myPreferences;
public class GroupsHomeFragment extends Fragment {

    public static MaterialCardView pin_group_bg;
    @SuppressLint("StaticFieldLeak")
    public static TextView pin_group_title;
    private static RecyclerView recycler_view_group;
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_schedule_list, container, false);

        mContext = getContext();
        recycler_view_group = view.findViewById(R.id.recycler_view);
        pin_group_bg = view.findViewById(R.id.pin_group_bg);
        Button unpin_button = view.findViewById(R.id.unpin_button);
        pin_group_title = view.findViewById(R.id.pin_group_title);


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

        });

        if (!_SECOND_GROUP_NAME.equals("null") && !_SECOND_GROUP_ID.equals("null")) {
            pin_group_bg.setVisibility(View.VISIBLE);
            pin_group_title.setText(_SECOND_GROUP_NAME);
        }

        onSetUpGroup();

        return view;
    }

    static public void onSetUpGroup() {

        groupListAdapterMain = new GroupListAdapter_main(mContext, groupListMains);
        recycler_view_group.setAdapter(groupListAdapterMain);

    }

}
