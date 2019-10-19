package com.example.application9.HomePageFragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application9.BottomSheets.BottomSheetColorFragment;
import com.example.application9.BottomSheets.BottomSheetUpdateAppFragment;
import com.example.application9.R;
import com.example.application9.VkLoginActivity;
import com.google.android.material.card.MaterialCardView;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.application9.MainActivity._PASSWORD;
import static com.example.application9.MainActivity._UID_G;

public class AccountFragment extends Fragment {

    public static CircleImageView acc_small_image;
    @SuppressLint("StaticFieldLeak")
    public static TextView acc_full_name;
    @SuppressLint("StaticFieldLeak")
    public static ImageView acc_bg_image;
    public static MaterialCardView update_button;
    public static RecyclerView recycler_view_news;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_account, container, false);

        MaterialCardView setting_fake_button = view.findViewById(R.id.setting_fake_button);
        MaterialCardView review_button = view.findViewById(R.id.review_button);
        MaterialCardView color_fake_button = view.findViewById(R.id.color_fake_button);
        MaterialCardView vk_login = view.findViewById(R.id.vk_login);

        acc_small_image = view.findViewById(R.id.acc_small_image);
        acc_full_name = view.findViewById(R.id.acc_full_name);
        update_button = view.findViewById(R.id.update_button);
        acc_bg_image = view.findViewById(R.id.acc_bg_image);
        recycler_view_news = view.findViewById(R.id.recycler_view_news);

        Spinner spinner = view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Objects.requireNonNull(getContext()),
                R.array.site, R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        setting_fake_button.setOnClickListener(v -> {
            BottomSheetUpdateAppFragment bottomSheetUpdateAppFragment = new BottomSheetUpdateAppFragment(getContext());
            bottomSheetUpdateAppFragment.show(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), bottomSheetUpdateAppFragment.getTag());
        });

        review_button.setOnClickListener(v -> {
            Uri address = Uri.parse("https://play.google.com/store/apps/details?id=com.bluedexon.denis.application");
            Intent openlink = new Intent(Intent.ACTION_VIEW, address);
            startActivity(openlink);
        });

        color_fake_button.setOnClickListener(v -> {
            BottomSheetColorFragment bottomSheetColorFragment = new BottomSheetColorFragment(getContext());
            bottomSheetColorFragment.show(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), bottomSheetColorFragment.getTag());
        });

        update_button.setOnClickListener(v -> {
            Uri address = Uri.parse("https://play.google.com/store/apps/details?id=com.bluedexon.denis.application");
            Intent openlink = new Intent(Intent.ACTION_VIEW, address);
            startActivity(openlink);
        });

        vk_login.setOnClickListener(v -> {
            if (_UID_G.equals("null") && _PASSWORD.equals("null")) {
                startActivity(new Intent(getContext(), VkLoginActivity.class));
            }
        });

        return view;
    }


}
