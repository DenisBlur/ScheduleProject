package com.example.application9.HomePageFragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.application9.AdaptersPackage.NewsUpdate_Adapter;
import com.example.application9.BottomSheets.BottomSheetColorFragment;
import com.example.application9.BottomSheets.BottomSheetUpdateAppFragment;
import com.example.application9.R;
import com.example.application9.VkLoginActivity;
import com.google.android.material.card.MaterialCardView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.application9.MainActivity._MAIN_URL_FOR_GROUP_NAME;
import static com.example.application9.MainActivity._PASSWORD;
import static com.example.application9.MainActivity._SITE;
import static com.example.application9.MainActivity._UID_G;
import static com.example.application9.MainActivity.myPreferences;
import static com.example.application9.MainActivity.newsUpdateLists;

public class AccountFragment extends Fragment {

    private static CircleImageView acc_small_image;
    @SuppressLint("StaticFieldLeak")
    private static TextView acc_full_name;
    @SuppressLint("StaticFieldLeak")
    private static ImageView acc_bg_image;
    private SharedPreferences sharedPref;
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_account, container, false);

        sharedPref = PreferenceManager.getDefaultSharedPreferences(Objects.requireNonNull(getContext()));
        mContext = getContext();

        MaterialCardView setting_fake_button = view.findViewById(R.id.setting_fake_button);
        MaterialCardView review_button = view.findViewById(R.id.review_button);
        MaterialCardView color_fake_button = view.findViewById(R.id.color_fake_button);
        MaterialCardView vk_login = view.findViewById(R.id.vk_login);

        acc_small_image = view.findViewById(R.id.acc_small_image);
        acc_full_name = view.findViewById(R.id.acc_full_name);
        MaterialCardView update_button = view.findViewById(R.id.update_button);
        acc_bg_image = view.findViewById(R.id.acc_bg_image);
        RecyclerView recycler_view_news = view.findViewById(R.id.recycler_view_news);

        NewsUpdate_Adapter newsUpdate_adapter = new NewsUpdate_Adapter(mContext, newsUpdateLists);
        recycler_view_news.setAdapter(newsUpdate_adapter);

        Spinner spinner = view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Objects.requireNonNull(getContext()),
                R.array.site, R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        if (_SITE.equals("first_site")) {
            spinner.setSelection(0);
        } else {
            spinner.setSelection(1);
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                if (item.equals("Первая площадка")) {
                    SharedPreferences.Editor myEditor = myPreferences.edit();
                    myEditor.putString("_site_list", "first_site");
                    myEditor.apply();
                    _SITE = "first_site";
                    _MAIN_URL_FOR_GROUP_NAME = "http://83.174.201.182/";
                } else {
                    SharedPreferences.Editor myEditor = myPreferences.edit();
                    myEditor.putString("_site_list", "second_site");
                    myEditor.apply();
                    _SITE = "second_site";
                    _MAIN_URL_FOR_GROUP_NAME = "http://83.174.201.182:85/";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

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

        onCheckLogin();

        return view;
    }

    private void onCheckLogin() {
        _UID_G = sharedPref.getString("account_id", "null");
        _PASSWORD = sharedPref.getString("account_password", "null");

        assert _PASSWORD != null;
        if (!_UID_G.equals("null") || !_PASSWORD.equals("null")) {
            LoginAccount loginAccount = new LoginAccount();
            loginAccount.execute();
        }
    }

    @SuppressLint("StaticFieldLeak")
    class LoginAccount extends AsyncTask<View, View, View> {

        String full_name, photo_200, photo_max_orig, body;
        boolean errors = false;

        @Override
        protected View doInBackground(View... views) {
            try {
                Document responses_check;
                responses_check = Jsoup.connect("http://s917802v.beget.tech/server_account/loginaccount.php" +
                        "?id=" + _UID_G +
                        "&password=" + _PASSWORD).get();

                body = responses_check.body().toString();
                errors = body.contains("error");

                if (!errors) {
                    full_name = responses_check.select("div.full_name").text();
                    photo_200 = responses_check.select("div.src_200px").text();
                    photo_max_orig = responses_check.select("div.src_fullpx").text();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(View view) {
            super.onPostExecute(view);
            if (!errors) {
                acc_full_name.setText(full_name);
                Glide.with(mContext).load(photo_max_orig).into(acc_small_image);
                acc_bg_image.setVisibility(View.GONE);
            } else {
                Toast.makeText(mContext, "Произошла ошибка авторизации.", Toast.LENGTH_SHORT).show();
                _UID_G = "null";
                _PASSWORD = "null";
                SharedPreferences.Editor myEditor = myPreferences.edit();
                myEditor.putString("account_id", _UID_G);
                myEditor.putString("account_password", _PASSWORD);
                myEditor.apply();
            }
        }
    }

}
