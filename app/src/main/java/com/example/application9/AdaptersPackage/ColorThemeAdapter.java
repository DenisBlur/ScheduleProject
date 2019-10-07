package com.example.application9.AdaptersPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application9.DataPackage.ColorTheme_List;
import com.example.application9.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.application9.BottomSheets.BottomSheetColorFragment._COLOR_TEMP;
import static com.example.application9.BottomSheets.BottomSheetColorFragment._COLOR_TEMP_INT;
import static com.example.application9.BottomSheets.BottomSheetColorFragment._THEME_TEMP;
import static com.example.application9.BottomSheets.BottomSheetColorFragment._THEME_TEMP_INT;
import static com.example.application9.BottomSheets.BottomSheetColorFragment.view_color;
import static com.example.application9.BottomSheets.BottomSheetColorFragment.view_theme;

public class ColorThemeAdapter extends RecyclerView.Adapter<ColorThemeAdapter.ViewHolder> {

    private List<ColorTheme_List> colorThemeLists;
    private LayoutInflater layoutInflater;

    public ColorThemeAdapter(Context mContext, List<ColorTheme_List> colorThemeLists) {
        this.colorThemeLists = colorThemeLists;
        this.layoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.recycler_view_item_color, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ColorTheme_List colorTheme_list = colorThemeLists.get(position);
        holder.color_name.setText(colorTheme_list.getName_color());
        holder.color_preview.setImageResource(colorTheme_list.getColor_name_res());
        holder.chose.setOnClickListener(v -> {
            if (colorTheme_list.getTheme() == 1) {
                view_theme.setImageResource(colorTheme_list.getColor_name_res());
                _THEME_TEMP = colorTheme_list.getCode_name();
                _THEME_TEMP_INT = colorTheme_list.getColor_name_res();
            } else {
                view_color.setImageResource(colorTheme_list.getColor_name_res());
                _COLOR_TEMP = colorTheme_list.getCode_name();
                _COLOR_TEMP_INT = colorTheme_list.getColor_name_res();
            }
        });

    }

    @Override
    public int getItemCount() {
        return colorThemeLists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView color_preview;
        private TextView color_name;
        private LinearLayout chose;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            color_name = itemView.findViewById(R.id.color_name);
            color_preview = itemView.findViewById(R.id.color_preview);
            chose = itemView.findViewById(R.id.chose);

        }
    }
}
