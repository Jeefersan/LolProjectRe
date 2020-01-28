package com.jeefersan.lolprojectre.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.jeefersan.lolprojectre.model.PlayerModel;
import com.jeefersan.lolprojectre.R;
import com.jeefersan.lolprojectre.view.MainActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.MatchViewHolder> {

    private List<PlayerModel> players;
    Context mContext;

    public PlayerListAdapter(List<PlayerModel> players, Context c) {
        this.players = players;
        this.mContext = c;
    }

    @NonNull
    @Override
    public MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        return new MatchViewHolder(view, mContext);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchViewHolder holder, int position) {
        holder.bind(players.get(position));
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public void updatePlayers(List<PlayerModel> newPlayers) {
        players.clear();
        players.addAll(newPlayers);
        notifyDataSetChanged();
    }

    class MatchViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.summonername)
        TextView summonerName;

        @BindView(R.id.tier)
        TextView tier;

        @BindView(R.id.rank)
        TextView rank;

        @BindView(R.id.winrate)
        TextView winrate;

        @BindView(R.id.lp)
        TextView lp;

        @BindView(R.id.imageButton2)
        ImageButton imageButton;

        @BindView(R.id.profile)
        ImageView tierImage;

        Context viewContext;

        public MatchViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.viewContext = context;
            ButterKnife.bind(this, itemView);
        }

        void bind(PlayerModel player) {
            summonerName.setText(player.getSummonerName());
            tier.setText(player.getTier());
            rank.setText(player.getRank());
            winrate.setText(player.showWinRate() + "%");
            lp.setText(player.getLeaguePoints() + "lp");
            int resId = mContext.getResources().getIdentifier(player.getTier().toLowerCase(),"drawable",mContext.getPackageName());
            tierImage.setImageResource(resId);
            imageButton.setOnClickListener(v -> {
                String url = MainActivity.opGg + player.getSummonerName().replaceAll("\\s", "");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                viewContext.startActivity(intent);
            });
        }

    }
}