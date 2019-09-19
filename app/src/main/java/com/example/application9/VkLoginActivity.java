package com.example.application9;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.example.application9.CustomDialog.FirstDialog;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.application9.MainActivity.myPreferences;
import static com.example.application9.MainActivity.resID;

public class VkLoginActivity extends AppCompatActivity {

    public static String _TOKEN = "";
    public static String _UID = "";
    private WebView webView;
    private FirstDialog cdd;
    private CoordinatorLayout backdrop_account, backdrop_account_password;
    private LottieAnimationView allDone, allDone_pass;
    private EditText edit_uid, edit_full_name, edit_password, edit_password_login;

    private String first_name, last_name, photo_200, photo_max_orig, bdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Theme
        setTheme(resID);
        //Theme

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vk_login);

        webView = findViewById(R.id.webView);
        backdrop_account = findViewById(R.id.backdrop_account);
        backdrop_account_password = findViewById(R.id.backdrop_account_password);
        edit_uid = findViewById(R.id.edit_uid);
        edit_full_name = findViewById(R.id.edit_full_name);
        edit_password = findViewById(R.id.edit_password);
        edit_password_login = findViewById(R.id.edit_password_login);
        allDone = findViewById(R.id.allDone);
        allDone_pass = findViewById(R.id.allDone_pass);

        cdd = new FirstDialog(this);
        Objects.requireNonNull(cdd.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        String _USR = "https://oauth.vk.com/authorize?client_id=6044506\n&display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=friends&response_type=token&v=5.101&state=123456";
        webView.loadUrl(_USR);
        webView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                if (webView.getUrl().contains("access_token=")) {
                    String[] strings = webView.getUrl().split("[=, &]");
                    _TOKEN = strings[1];
                    _UID = strings[5];
                    cdd.show();

                    GetUserInfo getUserInfo = new GetUserInfo();
                    getUserInfo.execute();
                }
            }
        });
    }

    public void onCreateAccout(View view) {

        if (edit_password.length() >= 6) {
            CreateAccount createAccout = new CreateAccount();
            createAccout.execute();
        } else {
            Toast.makeText(this, "Пароль должен содержать не менее 6 символов.", Toast.LENGTH_SHORT).show();
        }
    }

    public void onLoginAccount(View view) {

        if (edit_password_login.length() >= 6) {
            LoginAccount loginAccount = new LoginAccount();
            loginAccount.execute();
        } else {
            Toast.makeText(this, "Пароль должен содержать не менее 6 символов.", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("StaticFieldLeak")
    class LoginAccount extends AsyncTask<View, View, View> {

        String body;
        boolean errors = false;

        @Override
        protected View doInBackground(View... views) {
            try {
                Document responses_check;
                responses_check = Jsoup.connect("http://s917802v.beget.tech/server_account/loginaccount.php" +
                        "?id=" + _UID +
                        "&password=" + edit_password_login.getText().toString()).get();

                body = responses_check.body().toString();
                errors = body.contains("error");

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(View view) {
            super.onPostExecute(view);
            Toast.makeText(VkLoginActivity.this, body, Toast.LENGTH_SHORT).show();
            if (!errors) {
                SharedPreferences.Editor myEditor = myPreferences.edit();
                myEditor.putString("account_id", _UID);
                myEditor.putString("account_password", edit_password_login.getText().toString());
                myEditor.apply();
                finish();
                MainActivity.onCheckLogin();
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    class CreateAccount extends AsyncTask<View, View, View> {

        String body;
        boolean errors = false;

        @Override
        protected View doInBackground(View... views) {
            try {
                Document responses_check;
                String full_name = first_name + " " + last_name;
                if (!bdate.isEmpty()) {
                    responses_check = Jsoup.connect("http://s917802v.beget.tech/server_account/createaccount.php" +
                            "?id=" + _UID +
                            "&full_name=" + full_name +
                            "&src_200px=" + photo_200 +
                            "&src_fullpx=" + photo_max_orig +
                            "&password=" + edit_password.getText().toString() +
                            "&bdate=" + bdate).get();
                } else {
                    responses_check = Jsoup.connect("http://s917802v.beget.tech/server_account/createaccount.php" +
                            "?id=" + _UID +
                            "&full_name=" + full_name +
                            "&src_200px=" + photo_200 +
                            "&src_fullpx=" + photo_max_orig +
                            "&password=" + edit_password.getText().toString() +
                            "&bdate=_no_b_date_").get();
                }

                body = responses_check.body().toString();
                errors = body.contains("error");

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(View view) {
            super.onPostExecute(view);
            Toast.makeText(VkLoginActivity.this, body, Toast.LENGTH_SHORT).show();
            Toast.makeText(VkLoginActivity.this, _UID + last_name + first_name + photo_200 + photo_max_orig + edit_password.getText().toString() + bdate, Toast.LENGTH_SHORT).show();
            if (!errors) {
                SharedPreferences.Editor myEditor = myPreferences.edit();
                myEditor.putString("account_id", _UID);
                myEditor.putString("account_password", edit_password.getText().toString());
                myEditor.apply();
                finish();
                MainActivity.onCheckLogin();
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    class GetUserInfo extends AsyncTask<View, View, View> {

        boolean login = false;

        @Override
        protected View doInBackground(View... views) {

            try {
                Document responses_check = Jsoup.connect("http://s917802v.beget.tech/server_account/checkaccount.php?id=" + _UID).get();
                String body = responses_check.body().toString();

                login = !body.contains("{create new}");

            } catch (IOException e) {
                e.printStackTrace();
            }

            if (!login) {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://api.vk.com/method/users.get?user_ids=" + _UID + "&fields=photo_200,photo_max_orig,bdate&access_token=" + _TOKEN + "&v=5.101")
                        .build();
                Response responses = null;

                try {
                    responses = client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    assert responses != null;
                    assert responses.body() != null;
                    String jsonData = responses.body().string();
                    JSONObject jsonObject = new JSONObject(jsonData);
                    first_name = jsonObject.getJSONArray("response").getJSONObject(0).getString("first_name");
                    last_name = jsonObject.getJSONArray("response").getJSONObject(0).getString("last_name");
                    photo_200 = jsonObject.getJSONArray("response").getJSONObject(0).getString("photo_200");
                    photo_max_orig = jsonObject.getJSONArray("response").getJSONObject(0).getString("photo_max_orig");
                    bdate = jsonObject.getJSONArray("response").getJSONObject(0).getString("bdate");

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(View view) {
            super.onPostExecute(view);
            if (!login) {
                allDone.playAnimation();
                edit_uid.setText(_UID);
                edit_full_name.setText(first_name + " " + last_name);
                backdrop_account.setVisibility(View.VISIBLE);
                webView.setVisibility(View.GONE);
                backdrop_account_password.setVisibility(View.GONE);
            } else {
                allDone_pass.playAnimation();
                backdrop_account.setVisibility(View.GONE);
                webView.setVisibility(View.GONE);
                backdrop_account_password.setVisibility(View.VISIBLE);
            }
            cdd.dismiss();
        }

    }

}
