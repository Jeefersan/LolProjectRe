package com.jeefersan.lolprojectre.util;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jeefersan.lolprojectre.R;
import com.jeefersan.lolprojectre.model.matchwrapper.matches.Match;

import java.util.List;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MatchViewhHolder> {

    private List<Match> matches;
    Context context;


    public static class MatchViewhHolder extends RecyclerView.ViewHolder {
        public TextView role;
        public ImageView champion;
        public TextView champName;
        public TextView kda;
        public ImageView winLose;
        public TextView date;

        public MatchViewhHolder(View itemView) {
            super(itemView);
            role = itemView.findViewById(R.id.role);
            champion = itemView.findViewById(R.id.champ);
            kda = itemView.findViewById(R.id.kda);
            winLose = itemView.findViewById(R.id.winlose);
            date = itemView.findViewById(R.id.date);
            champName = itemView.findViewById(R.id.champName);
        }
    }

    public MatchAdapter(List<Match> matchList, Context c) {
        this.matches = matchList;
        this.context = c;
    }

    @NonNull
    @Override
    public MatchViewhHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_item, parent, false);
        MatchViewhHolder mvh = new MatchViewhHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MatchViewhHolder holder, int position) {
        Match currentItem = matches.get(position);

        int champId = context.getResources().getIdentifier(Util.champIdtoName(currentItem.getChampion()).toLowerCase(), "drawable", context.getPackageName());
//        Log.v("champId", "" + champId + " currentItem.champ = " + currentItem.getChampion());
        int winLoseId = context.getResources().getIdentifier(currentItem.getWinLose(), "drawable", context.getPackageName());
        holder.champion.setImageResource(champId);
        holder.role.setText(currentItem.getLane().substring(0, 3));
        holder.kda.setText(currentItem.getKda());
        holder.winLose.setImageResource(winLoseId);
        holder.date.setText(Util.getDate(currentItem.getTimestamp()));
        holder.champName.setText(Util.champIdtoName(currentItem.getChampion()));
    }


    @Override
    public int getItemCount() {
        return matches.size();
    }
}

