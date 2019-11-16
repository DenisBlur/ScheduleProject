package com.example.application9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.application9.AdaptersPackage.GroupListAdapter_main;

import static com.example.application9.MainActivity.groupListMains;
import static com.example.application9.MainActivity.resID;

public class SearchActivity extends AppCompatActivity {

    private GroupListAdapter_main mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Theme
        setTheme(resID);
        //Theme

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mAdapter = new GroupListAdapter_main(this, groupListMains);

        EditText edit_text_search = findViewById(R.id.edit_text_search);
        RecyclerView recycler_view_search = findViewById(R.id.recycler_view_search);
        recycler_view_search.setItemAnimator(new DefaultItemAnimator());
        recycler_view_search.setAdapter(mAdapter);

        edit_text_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String filt = s.toString();
                mAdapter.getFilter().filter(filt);
            }
        });

    }
}
