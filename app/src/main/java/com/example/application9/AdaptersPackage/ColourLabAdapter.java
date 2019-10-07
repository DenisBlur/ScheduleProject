package com.example.application9.AdaptersPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.application9.DataPackage.ColourLabService_List;
import com.example.application9.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.application9.BottomSheets.BottomSheetColorFragment._COLOR_TEMP;
import static com.example.application9.BottomSheets.BottomSheetColorFragment._COLOR_TEMP_INT;
import static com.example.application9.BottomSheets.BottomSheetColorFragment.view_color;

public class ColourLabAdapter extends RecyclerView.Adapter<ColourLabAdapter.ViewHolder> {

    private List<ColourLabService_List> colorThemeLists;
    private LayoutInflater layoutInflater;
    private Context mContext;

    public ColourLabAdapter(Context mContext, List<ColourLabService_List> colorThemeLists) {
        this.mContext = mContext;
        this.colorThemeLists = colorThemeLists;
        this.layoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.recycler_item_color_lab, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ColourLabService_List labService_list = colorThemeLists.get(position);

        holder.color_name.setText(labService_list.getName_color());
        holder.color_preview.setImageResource(labService_list.getColor_name_res());
        holder.color_preview_1.setImageResource(labService_list.getColor_name_res_1());
        holder.color_preview_2.setImageResource(labService_list.getColor_name_res_2());

        Glide.with(mContext).load(labService_list.getBg_url()).into(holder.bg_image);

        holder.color_preview.setOnClickListener(v -> {
            view_color.setImageResource(labService_list.getColor_name_res());
            _COLOR_TEMP = labService_list.getCode_name();
            _COLOR_TEMP_INT = labService_list.getColor_name_res();
        });
        holder.color_preview_1.setOnClickListener(v -> {
            view_color.setImageResource(labService_list.getColor_name_res_1());
            _COLOR_TEMP = labService_list.getCode_name_1();
            _COLOR_TEMP_INT = labService_list.getColor_name_res_1();
        });
        holder.color_preview_2.setOnClickListener(v -> {
            view_color.setImageResource(labService_list.getColor_name_res_2());
            _COLOR_TEMP = labService_list.getCode_name_2();
            _COLOR_TEMP_INT = labService_list.getColor_name_res_2();
        });

    }

    @Override
    public int getItemCount() {
        return colorThemeLists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView color_preview, color_preview_1, color_preview_2;
        private ImageView bg_image;
        private TextView color_name;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            color_name = itemView.findViewById(R.id.color_name);
            color_preview = itemView.findViewById(R.id.color_preview);
            color_preview_1 = itemView.findViewById(R.id.color_preview_1);
            color_preview_2 = itemView.findViewById(R.id.color_preview_2);
            bg_image = itemView.findViewById(R.id.bg_image);

        }
    }
}
