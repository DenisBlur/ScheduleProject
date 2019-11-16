package com.example.application9.HomePageFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application9.AdaptersPackage.TimeListAdapter_main;
import com.example.application9.R;

import static com.example.application9.MainActivity.timeListMain;
import static com.example.application9.MainActivity.timeListMain_sb;
import static com.example.application9.MainActivity.timeListMain_sr;

public class TimeLineFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_time_line, container, false);

        RecyclerView recycler_view_timeline = view.findViewById(R.id.recycler_view_time);
        RecyclerView recycler_view_timeline_sr = view.findViewById(R.id.recycler_view_time_sr);
        RecyclerView recycler_view_timeline_sb = view.findViewById(R.id.recycler_view_time_sb);

        TimeListAdapter_main timeListAdapterMain = new TimeListAdapter_main(getContext(), timeListMain);
        TimeListAdapter_main timeListAdapterMain_sr = new TimeListAdapter_main(getContext(), timeListMain_sr);
        TimeListAdapter_main timeListAdapterMain_sb = new TimeListAdapter_main(getContext(), timeListMain_sb);

        recycler_view_timeline.setAdapter(timeListAdapterMain);
        recycler_view_timeline_sr.setAdapter(timeListAdapterMain_sr);
        recycler_view_timeline_sb.setAdapter(timeListAdapterMain_sb);

        return view;
    }

}
