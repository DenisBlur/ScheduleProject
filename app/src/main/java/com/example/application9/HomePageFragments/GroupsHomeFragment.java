package com.example.application9.HomePageFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application9.GroupActivity;
import com.example.application9.R;
import com.google.android.material.card.MaterialCardView;

import java.util.Objects;

import static com.example.application9.MainActivity._SECOND_GROUP_ID;
import static com.example.application9.MainActivity._SECOND_GROUP_NAME;

public class GroupsHomeFragment extends Fragment {

    public static RecyclerView recycler_view_group;
    public static MaterialCardView pin_group_bg;
    public static TextView pin_group_title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_schedule_list, container, false);

        recycler_view_group = view.findViewById(R.id.recycler_view);
        pin_group_bg = view.findViewById(R.id.pin_group_bg);
        pin_group_title = view.findViewById(R.id.pin_group_title);

        pin_group_bg.setOnClickListener(v -> {
            if (!_SECOND_GROUP_NAME.equals("null") && !_SECOND_GROUP_ID.equals("null")) {
                Intent intent = new Intent(getContext(), GroupActivity.class);
                intent.putExtra("group_TITLE", _SECOND_GROUP_NAME);
                intent.putExtra("group_ID", _SECOND_GROUP_ID);
                Objects.requireNonNull(getContext()).startActivity(intent);
            }

        });

        if (!_SECOND_GROUP_NAME.equals("null") && !_SECOND_GROUP_ID.equals("null")) {
            pin_group_bg.setVisibility(View.VISIBLE);
            pin_group_title.setText(_SECOND_GROUP_NAME);
        }

        return view;
    }
}
