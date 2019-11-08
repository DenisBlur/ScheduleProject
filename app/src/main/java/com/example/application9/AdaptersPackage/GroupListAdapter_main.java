package com.example.application9.AdaptersPackage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application9.DataPackage.GroupList_main;
import com.example.application9.GroupActivity;
import com.example.application9.R;
import com.google.android.material.card.MaterialCardView;
import com.irozon.alertview.AlertActionStyle;
import com.irozon.alertview.AlertStyle;
import com.irozon.alertview.AlertTheme;
import com.irozon.alertview.AlertView;
import com.irozon.alertview.objects.AlertAction;

import java.util.List;

import static com.example.application9.HomePageFragments.GroupsHomeFragment.pin_group_bg;
import static com.example.application9.HomePageFragments.GroupsHomeFragment.pin_group_title;
import static com.example.application9.MainActivity._DARK_THEME;
import static com.example.application9.MainActivity._SECOND_GROUP_ID;
import static com.example.application9.MainActivity._SECOND_GROUP_NAME;
import static com.example.application9.MainActivity.myPreferences;
import static com.example.application9.MainActivity.top_pin_title;

public class GroupListAdapter_main extends RecyclerView.Adapter<GroupListAdapter_main.ViewHolder> {

    private Context mContext;
    private List<GroupList_main> groupListMains;
    private LayoutInflater layoutInflater;

    public GroupListAdapter_main(Context mContext, List<GroupList_main> groupListMains) {
        this.mContext = mContext;
        this.groupListMains = groupListMains;
        this.layoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.recycler_item_main_group_title, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GroupList_main listMain = groupListMains.get(position);
        holder.group_title.setText(listMain.getGroup_TITLE());
        holder.group_card.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, GroupActivity.class);
            intent.putExtra("_TYPE", "cg");
            intent.putExtra("_TITLE", listMain.getGroup_TITLE());
            intent.putExtra("_ID", listMain.getGroup_ID());
            mContext.startActivity(intent);
        });
        holder.group_card.setOnLongClickListener(v -> {

            _SECOND_GROUP_NAME = listMain.getGroup_TITLE();
            _SECOND_GROUP_ID = listMain.getGroup_ID();

            AlertView alert = new AlertView(listMain.getGroup_TITLE(), "Вы уверены что хотите закрепить группу?", AlertStyle.DIALOG);
            alert.addAction(new AlertAction("Закрепить", AlertActionStyle.POSITIVE, action -> {
                @SuppressLint("CommitPrefEdits") SharedPreferences.Editor myEditor = myPreferences.edit();
                myEditor.putString("group_TITLE", _SECOND_GROUP_NAME);
                myEditor.putString("group_ID", _SECOND_GROUP_ID);
                myEditor.apply();
                pin_group_bg.setVisibility(View.VISIBLE);
                pin_group_title.setText(_SECOND_GROUP_NAME);
                top_pin_title.setText(_SECOND_GROUP_NAME);
                pin_group_bg.setVisibility(View.VISIBLE);
                pin_group_bg.setAlpha(1);
                Animation anim = AnimationUtils.loadAnimation(mContext, R.anim.item_animation_fall_down);
                pin_group_bg.setAnimation(anim);
            }));
            alert.addAction(new AlertAction("Нет", AlertActionStyle.NEGATIVE, action -> {
            }));
            switch (_DARK_THEME) {
                case "Light":
                    alert.setTheme(AlertTheme.LIGHT);
                    break;
                case "Dark":
                case "Android":
                    alert.setTheme(AlertTheme.DARK);
                    break;
            }

            alert.show((AppCompatActivity) mContext);

            return false;
        });
    }

    @Override
    public int getItemCount() {
        return groupListMains.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView group_title;
        private MaterialCardView group_card;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            group_card = itemView.findViewById(R.id.group_card);
            group_title = itemView.findViewById(R.id.group_title);

        }
    }

}
