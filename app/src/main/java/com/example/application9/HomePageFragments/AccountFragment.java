package com.example.application9.HomePageFragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.application9.BottomSheets.BottomSheetColorFragment;
import com.example.application9.BottomSheets.BottomSheetDonateFragment;
import com.example.application9.R;
import com.example.application9.SettingsActivity;
import com.google.android.material.card.MaterialCardView;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.application9.MainActivity._DARK_THEME;
import static com.example.application9.MainActivity._DESIGN_COLOR;

public class AccountFragment extends Fragment {

    public static CircleImageView acc_small_image;
    @SuppressLint("StaticFieldLeak")
    public static TextView acc_full_name;
    @SuppressLint("StaticFieldLeak")
    public static LinearLayout acc_info;
    @SuppressLint("StaticFieldLeak")
    public static ImageView acc_bg_image;
    @SuppressLint("StaticFieldLeak")
    public static View acc_gradient_view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_account, container, false);

        MaterialCardView setting_fake_button = view.findViewById(R.id.setting_fake_button);
        MaterialCardView donte_fake_button = view.findViewById(R.id.donte_fake_button);
        MaterialCardView color_fake_button = view.findViewById(R.id.color_fake_button);
        MaterialCardView vk_login = view.findViewById(R.id.vk_login);

        acc_small_image = view.findViewById(R.id.acc_small_image);
        acc_full_name = view.findViewById(R.id.acc_full_name);
        acc_info = view.findViewById(R.id.acc_info);
        acc_bg_image = view.findViewById(R.id.acc_bg_image);
        acc_gradient_view = view.findViewById(R.id.acc_gradient_view);

        setting_fake_button.setOnClickListener(v -> startActivity(new Intent(getContext(), SettingsActivity.class)));

        color_fake_button.setOnClickListener(v -> {
            BottomSheetColorFragment bottomSheetColorFragment = new BottomSheetColorFragment(getContext());
            bottomSheetColorFragment.show(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), bottomSheetColorFragment.getTag());
        });

        vk_login.setOnClickListener(v -> {
//            if (_UID_G.equals("null") && _PASSWORD.equals("null")) {
//                startActivity(new Intent(getContext(), VkLoginActivity.class));
//            } else {
//
//
//                int endRadius = (int) Math.hypot(vk_login.getWidth(), vk_login.getHeight());
//                Animator anim = ViewAnimationUtils.createCircularReveal(vk_login, vk_login.getWidth()/2, vk_login.getHeight()/2, 0, endRadius);
//                anim.setDuration(2500);
//                anim.start();
//                Intent intent = new Intent(getContext(), AccountActivity.class);
//                intent.putExtra("acc_extra_full_name", acc_full_name.getText());
//                intent.putExtra("acc_extra_photo_200", _IMG_SMALL_ACC);
//                intent.putExtra("acc_extra_photo_full", _IMG_FULL_ACC);
//                startActivity(intent);
//            }
        });

        donte_fake_button.setOnClickListener(v -> {
            BottomSheetDonateFragment bottomSheetFragment = new BottomSheetDonateFragment(getContext());
            bottomSheetFragment.show(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), bottomSheetFragment.getTag());
        });

        return view;
    }


}
