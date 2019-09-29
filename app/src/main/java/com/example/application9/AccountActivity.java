package com.example.application9;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.application9.HomePageFragments.AccountFragment.acc_bg_image;
import static com.example.application9.HomePageFragments.AccountFragment.acc_small_image;
import static com.example.application9.MainActivity.resID;

public class AccountActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(resID);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        ImageView acc_cover = findViewById(R.id.acc_cover);
        TextView acc_full_name = findViewById(R.id.acc_full_name);
        CircleImageView acc_small = findViewById(R.id.acc_small);

        mContext = this;

        String full_name = getIntent().getStringExtra("acc_extra_full_name");
        String photo_200 = getIntent().getStringExtra("acc_extra_photo_200");
        String photo_full = getIntent().getStringExtra("acc_extra_photo_full");

        Glide.with(mContext).load(photo_200).into(acc_small);
        Glide.with(mContext).load(photo_full).into(acc_cover);
        acc_full_name.setText(full_name);

    }

}
