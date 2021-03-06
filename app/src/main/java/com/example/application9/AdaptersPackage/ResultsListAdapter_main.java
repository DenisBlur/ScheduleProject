package com.example.application9.AdaptersPackage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.example.application9.DataPackage.ResultsList_main;
import com.example.application9.R;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class ResultsListAdapter_main extends RecyclerView.Adapter<ResultsListAdapter_main.ViewHolder> {

    private List<ResultsList_main> resultsListMains;
    private LayoutInflater layoutInflater;

    public ResultsListAdapter_main(Context mContext, List<ResultsList_main> resultsList_mains) {
        this.resultsListMains = resultsList_mains;
        this.layoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.recycler_item_second_group, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ResultsList_main main = resultsListMains.get(position);
        holder.name_t.setText(main.getNum() + " " + main.getNamet_t());
        holder.name_l.setText(main.getName_l());
        holder.hour_all.setText("Всего часов: " + main.getHour_all());
        holder.hour_out.setText("Остаток: " + main.getHour_out());
        holder.ending.setText("Окончание: " + main.getEnding());
        holder.progressBar.setProgress(main.getProgress());
    }

    @Override
    public int getItemCount() {
        return resultsListMains.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView name_t, name_l, hour_all, hour_out, ending;
        RoundCornerProgressBar progressBar;
        MaterialCardView container;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_t = itemView.findViewById(R.id.name_t);
            name_l = itemView.findViewById(R.id.name_l);
            hour_all = itemView.findViewById(R.id.hour_all);
            hour_out = itemView.findViewById(R.id.hour_out);
            ending = itemView.findViewById(R.id.ending);
            progressBar = itemView.findViewById(R.id.progressBar);
            container = itemView.findViewById(R.id.container);
        }
    }
}
