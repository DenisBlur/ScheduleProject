package com.example.application9.GroupPageFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application9.R;

public class FirstTab extends Fragment {

    public static RecyclerView recyclerView_first;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_group_first, container, false);

        recyclerView_first = view.findViewById(R.id.recycler_view);
//        AdView mAdView = view.findViewById(R.id.banner_view);
//        mAdView.setBlockId("R-M-457144-2");
//        mAdView.setAdSize(AdSize.BANNER_320x50);

        return view;
    }
}
