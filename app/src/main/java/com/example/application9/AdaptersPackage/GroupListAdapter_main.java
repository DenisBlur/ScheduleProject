package com.example.application9.AdaptersPackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Group;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application9.DataPackage.GroupList_main;
import com.example.application9.GroupActivity;
import com.example.application9.R;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

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
            intent.putExtra("group_TITLE", listMain.getGroup_TITLE());
            intent.putExtra("group_ID", listMain.getGroup_ID());
            mContext.startActivity(intent);
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
