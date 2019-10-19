package com.example.application9.BottomSheets;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.application9.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.card.MaterialCardView;

import java.util.Objects;

import static com.example.application9.MainActivity._DARK_THEME;

public class BottomSheetUpdateAppFragment extends BottomSheetDialogFragment {

    public BottomSheetUpdateAppFragment(Context context) {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        switch (_DARK_THEME) {
            case "Light":
                setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.AppBottomSheetDialogTheme);
                break;
            case "Dark":
            case "Android":
                setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.AppBottomSheetDialogTheme_Dark);
                break;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_update_app, container, false);

        ImageView image_morning = view.findViewById(R.id.image_morning);
        ImageView image_day = view.findViewById(R.id.image_day);
        ImageView image_evening = view.findViewById(R.id.image_evening);
        ImageView image_night = view.findViewById(R.id.image_night);

        ImageView image_server_time = view.findViewById(R.id.image_server_time);
        ImageView image_server_variable = view.findViewById(R.id.image_server_variable);
        ImageView image_server_schedule = view.findViewById(R.id.image_server_schedule);

        MaterialCardView server_backdrop = view.findViewById(R.id.server_backdrop);
        MaterialCardView server_time = view.findViewById(R.id.server_time);
        MaterialCardView server_variable = view.findViewById(R.id.server_variable);
        MaterialCardView server_schedule = view.findViewById(R.id.server_schedule);

        server_backdrop.setOnClickListener(v -> {
            Uri address = Uri.parse("http://s917802v.beget.tech/server_backdrop/");
            Intent openlink = new Intent(Intent.ACTION_VIEW, address);
            startActivity(openlink);
        });

        server_time.setOnClickListener(v -> {
            Uri address = Uri.parse("http://s917802v.beget.tech/server_time_new/");
            Intent openlink = new Intent(Intent.ACTION_VIEW, address);
            startActivity(openlink);
        });

        server_variable.setOnClickListener(v -> {
            Uri address = Uri.parse("http://s917802v.beget.tech/server_variable/");
            Intent openlink = new Intent(Intent.ACTION_VIEW, address);
            startActivity(openlink);
        });

        server_schedule.setOnClickListener(v -> {
            Uri address = Uri.parse("http://83.174.201.182/cg.htm");
            Intent openlink = new Intent(Intent.ACTION_VIEW, address);
            startActivity(openlink);
        });

        Glide.with(Objects.requireNonNull(getContext())).load("http://s917802v.beget.tech/server_backdrop/images/morning.jpg").skipMemoryCache(true).into(image_morning);
        Glide.with(getContext()).load("http://s917802v.beget.tech/server_backdrop/images/daytime.jpg").skipMemoryCache(true).into(image_day);
        Glide.with(getContext()).load("http://s917802v.beget.tech/server_backdrop/images/evening.png").skipMemoryCache(true).into(image_evening);
        Glide.with(getContext()).load("http://s917802v.beget.tech/server_backdrop/images/night.jpg").skipMemoryCache(true).into(image_night);

        Glide.with(getContext()).load("https://sun9-63.userapi.com/c857624/v857624342/b0674/euHXP2Nwq8Y.jpg").skipMemoryCache(true).into(image_server_time);
        Glide.with(getContext()).load("https://sun9-62.userapi.com/c857624/v857624342/b06ac/qQpDbzaLLGE.jpg").skipMemoryCache(true).into(image_server_variable);
        Glide.with(getContext()).load("https://sun9-38.userapi.com/c857624/v857624342/b0710/HKzyaXSujYc.jpg").skipMemoryCache(true).into(image_server_schedule);

        return view;
    }
}
