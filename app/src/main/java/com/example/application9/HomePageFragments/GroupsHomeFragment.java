package com.example.application9.HomePageFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application9.CustomDialog.FirstDialog;
import com.example.application9.R;

public class GroupsHomeFragment extends Fragment {

    public static RecyclerView recycler_view_group;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_schedule_list, container, false);

        recycler_view_group = view.findViewById(R.id.recycler_view);

        return view;
    }
}
