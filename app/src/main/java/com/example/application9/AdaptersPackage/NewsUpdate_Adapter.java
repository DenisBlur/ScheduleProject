package com.example.application9.AdaptersPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.bumptech.glide.Glide;
import com.example.application9.DataPackage.NewsUpdate_List;
import com.example.application9.DataPackage.TimeList_main;
import com.example.application9.R;

import java.util.ArrayList;
import java.util.List;

public class NewsUpdate_Adapter extends RecyclerView.Adapter<NewsUpdate_Adapter.ViewHolder> {

    private List<NewsUpdate_List> newsUpdateLists;
    private Context mContext;
    private LayoutInflater layoutInflater;

    public NewsUpdate_Adapter(Context mContext, List<NewsUpdate_List> newsUpdateLists) {
        this.mContext = mContext;
        this.newsUpdateLists = newsUpdateLists;
        this.layoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.recycler_item_news_update, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NewsUpdate_List list = newsUpdateLists.get(position);

        Glide.with(mContext).load(list.getImage()).into(holder.news_backdrop);
        holder.news_title.setText(list.getTitle());
        holder.stage.setText(list.getStage());
        holder.progressBar.setProgress(Float.parseFloat(list.getProgress()));

        String[] separate_news_text;
        separate_news_text = list.getText().split("-");
        List<TimeList_main> timeListMains = new ArrayList<>();
        for (String s : separate_news_text) {
            timeListMains.add(new TimeList_main(s,1));
        }
        TimeListAdapter_main timeListAdapterMain_2 = new TimeListAdapter_main(mContext, timeListMains);
        holder.recycler_view.setAdapter(timeListAdapterMain_2);
        holder.recycler_view.setNestedScrollingEnabled(false);


    }

    @Override
    public int getItemCount() {
        return newsUpdateLists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView news_backdrop;
        private TextView news_title, stage;
        private RecyclerView recycler_view;
        private RoundCornerProgressBar progressBar;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            news_backdrop = itemView.findViewById(R.id.news_backdrop);
            recycler_view = itemView.findViewById(R.id.recycler_view);
            progressBar = itemView.findViewById(R.id.progressBar);
            news_title = itemView.findViewById(R.id.news_title);
            stage = itemView.findViewById(R.id.stage);

        }
    }
}
