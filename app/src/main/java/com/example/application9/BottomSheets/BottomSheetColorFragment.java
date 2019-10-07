package com.example.application9.BottomSheets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application9.AdaptersPackage.ColorThemeAdapter;
import com.example.application9.AdaptersPackage.ColourLabAdapter;
import com.example.application9.DataPackage.ColorTheme_List;
import com.example.application9.DataPackage.ColourLabService_List;
import com.example.application9.MainActivity;
import com.example.application9.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.application9.MainActivity._COLOR_INT;
import static com.example.application9.MainActivity._DARK_THEME;
import static com.example.application9.MainActivity._THEME_INT;
import static com.example.application9.MainActivity.myPreferences;

public class BottomSheetColorFragment extends BottomSheetDialogFragment {

    @SuppressLint("StaticFieldLeak")
    public static CircleImageView view_theme;
    @SuppressLint("StaticFieldLeak")
    public static CircleImageView view_color;
    public static String _THEME_TEMP;
    public static String _COLOR_TEMP;
    public static int _THEME_TEMP_INT, _COLOR_TEMP_INT;
    private Context mContext;

    public BottomSheetColorFragment(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        switch (_DARK_THEME) {
            case "Light":
                setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.AppBottomSheetDialogTheme);
                break;
            case "Dark":
                setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.AppBottomSheetDialogTheme_Dark);
                break;
            case "Android":
                setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.AppBottomSheetDialogTheme_DayNight);
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_color, container, false);

        RecyclerView recycler_view_theme = view.findViewById(R.id.recycler_view_theme);
        RecyclerView recycler_view_color = view.findViewById(R.id.recycler_view_color);
        RecyclerView recycler_view_color_or = view.findViewById(R.id.recycler_view_color_or);

        view_theme = view.findViewById(R.id.preview_theme);
        view_color = view.findViewById(R.id.preview_color);
        Button apply = view.findViewById(R.id.apply);

        view_theme.setImageResource(_THEME_TEMP_INT);
        view_color.setImageResource(_COLOR_TEMP_INT);

        List<ColorTheme_List> colorThemeLists_0 = new ArrayList<>();
        List<ColorTheme_List> colorThemeLists_1 = new ArrayList<>();
        List<ColourLabService_List> colorThemeLists_3 = new ArrayList<>();

        //Theme
        colorThemeLists_0.add(new ColorTheme_List("Светлая", "Light", R.color.theme_white, 1));
        colorThemeLists_0.add(new ColorTheme_List("Темная", "Dark", R.color.theme_black, 1));
        colorThemeLists_0.add(new ColorTheme_List("Система", "Android", R.drawable.ic_launcher_background, 1));
        //Theme

        //Color
        colorThemeLists_1.add(new ColorTheme_List("Red", "Red", R.color.colorAccent_Red, 0));
        colorThemeLists_1.add(new ColorTheme_List("Pink", "Pink", R.color.colorAccent_Pink, 0));
        colorThemeLists_1.add(new ColorTheme_List("Purple", "Purple", R.color.colorAccent_Purple, 0));
        colorThemeLists_1.add(new ColorTheme_List("Indigo", "Indigo", R.color.colorAccent_Indigo, 0));
        colorThemeLists_1.add(new ColorTheme_List("Teal", "Teal", R.color.colorAccent_Teal, 0));
        colorThemeLists_1.add(new ColorTheme_List("Green", "Green", R.color.colorAccent_Green, 0));
        colorThemeLists_1.add(new ColorTheme_List("Yellow", "Yellow", R.color.colorAccent_Yellow, 0));
        colorThemeLists_1.add(new ColorTheme_List("Deep Orange", "DeepOrange", R.color.colorAccent_deepOrange, 0));
        //Color

        //Color OR
        colorThemeLists_3.add(new ColourLabService_List(
                "Old Rope",
                "OR1",
                "OR2",
                "OR3",
                "https://sun9-38.userapi.com/c854324/v854324775/113f16/t5GiB5HbEMc.jpg",
                R.color.colorAccent_OR1,
                R.color.colorAccent_OR2,
                R.color.colorAccent_OR3));
        colorThemeLists_3.add(new ColourLabService_List(
                "Terracotta Sunrise",
                "TS1",
                "TS2",
                "TS3",
                "https://sun9-33.userapi.com/c854324/v854324775/113f3e/YbEGmB9Z7nw.jpg",
                R.color.colorAccent_TS1,
                R.color.colorAccent_TS2,
                R.color.colorAccent_TS3));
//        colorThemeLists_3.add(new ColourLabService_List(
//                "Clay Cave",
//                "CC1",
//                "CC2",
//                "CC3",
//                "https://sun9-68.userapi.com/c854324/v854324775/113f2a/RtGOlJu9Edo.jpg",
//                R.color.colorAccent_CC1,
//                R.color.colorAccent_CC2,
//                R.color.colorAccent_CC3));
        //Color OR

        //RecyclerView
        ColorThemeAdapter colorThemeAdapter_0 = new ColorThemeAdapter(mContext, colorThemeLists_0);
        ColorThemeAdapter colorThemeAdapter_1 = new ColorThemeAdapter(mContext, colorThemeLists_1);
        ColourLabAdapter labAdapter = new ColourLabAdapter(mContext, colorThemeLists_3);


        recycler_view_theme.setAdapter(colorThemeAdapter_0);
        recycler_view_color.setAdapter(colorThemeAdapter_1);
        recycler_view_color_or.setAdapter(labAdapter);
        //RecyclerView

        apply.setOnClickListener(v -> {
            @SuppressLint("CommitPrefEdits") SharedPreferences.Editor myEditor = myPreferences.edit();
            myEditor.putString("_design_color_list_new", _COLOR_TEMP);
            myEditor.putString("_theme_lds_list_new", _THEME_TEMP);
            myEditor.putInt("_design_color_list_res", _COLOR_TEMP_INT);
            myEditor.putInt("_theme_lds_list_res", _THEME_TEMP_INT);
            myEditor.apply();
            dismiss();
            MainActivity.onCheckTheme();
        });

        return view;

    }
}
