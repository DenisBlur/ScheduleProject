package com.example.application9.HomePageFragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application9.BottomSheetDonateFragment;
import com.example.application9.CustomDialog.FirstDialog;
import com.example.application9.R;
import com.example.application9.SettingsActivity;
import com.google.android.material.card.MaterialCardView;

import java.util.Objects;

public class AccountFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_account, container, false);

        MaterialCardView setting_fake_button = view.findViewById(R.id.setting_fake_button);
        MaterialCardView donte_fake_button = view.findViewById(R.id.donte_fake_button);

        setting_fake_button.setOnClickListener(v -> startActivity(new Intent(getContext(), SettingsActivity.class)));
        donte_fake_button.setOnClickListener(v -> {
//            BottomSheetDonateFragment bottomSheetFragment = new BottomSheetDonateFragment(getContext());
//            bottomSheetFragment.show(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), bottomSheetFragment.getTag());
        });

        return view;
    }

}
