package com.example.application9.BottomSheets;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.application9.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.card.MaterialCardView;

public class BottomSheetDonateFragment extends BottomSheetDialogFragment {

    private Context mContext;

    public BottomSheetDonateFragment(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.AppBottomSheetDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bottom_sheet, container, false);

        Button copy_button = view.findViewById(R.id.copy_button);
        Button open_button = view.findViewById(R.id.open_button);
        MaterialCardView fake_button_da = view.findViewById(R.id.fake_button_da);
        MaterialCardView fake_button_sb = view.findViewById(R.id.fake_button_sb);

        fake_button_da.setOnClickListener(v -> {
            Uri address = Uri.parse("https://www.donationalerts.com/r/denistokar");
            Intent openlink = new Intent(Intent.ACTION_VIEW, address);
            startActivity(Intent.createChooser(openlink, "Browser"));
        });

        open_button.setOnClickListener(v -> {
            Uri address = Uri.parse("https://www.donationalerts.com/r/denistokar");
            Intent openlink = new Intent(Intent.ACTION_VIEW, address);
            startActivity(Intent.createChooser(openlink, "Browser"));
        });

        fake_button_sb.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("", "5469060024261726");
            clipboard.setPrimaryClip(clip);
            Toast.makeText(mContext, "Номер скопирован!", Toast.LENGTH_SHORT).show();
        });

        copy_button.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("", "5469060024261726");
            clipboard.setPrimaryClip(clip);
            Toast.makeText(mContext, "Номер скопирован!", Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}
