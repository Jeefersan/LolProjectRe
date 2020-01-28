package com.jeefersan.lolprojectre.view.fragments;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jeefersan.lolprojectre.R;
import com.jeefersan.lolprojectre.model.SummonerModel;
import com.jeefersan.lolprojectre.util.MatchAdapter;
import com.jeefersan.lolprojectre.util.Util;
import com.jeefersan.lolprojectre.view.MainActivity;
import com.jeefersan.lolprojectre.viewmodel.SearchViewModel;
import com.jeefersan.lolprojectre.model.matchwrapper.matches.*;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SearchFragment extends Fragment {

    private SearchViewModel mViewModel;
    private MatchAdapter mMatchAdapter;
    private LinearLayoutManager mLayoutManager;
    private View view;

    private Unbinder unbinder;

    private SummonerModel summoner;
    private List<Match> matchList;

    @OnClick(R.id.search_button)
    public void onClick(){
        observerViewModel(input.getText().toString());
    }


    @BindView(R.id.loading)
    ProgressBar loadingView;

    @BindView(R.id.error)
    TextView error;

    @BindView(R.id.editText)
    EditText input;

    @BindView(R.id.frameLayout2)
    FrameLayout frameLayout;

    @BindView(R.id.imageView3)
    ImageView profile;

    @BindView(R.id.imageView2)
    ImageView rankView;

    @BindView(R.id.most_played_champ)
    ImageView mostPlayedImage;

    @BindView(R.id.search_button)
    ImageButton searchButton;

    @BindView(R.id.opgg)
    ImageButton Opgg;

    @BindView(R.id.mostplayedchampname)
    TextView mostPlayedChamp;

    @BindView(R.id.masterypoints)
    TextView masteryPoints;

    @BindView(R.id.summonerName)
    TextView summonerName;

    @BindView(R.id.textView)
    TextView tier;

    @BindView(R.id.textView17)
    TextView rank;

    @BindView(R.id.textView18)
    TextView lp;

    @BindView(R.id.textView13)
    TextView amountWins;

    @BindView(R.id.textView15)
    TextView percentWinrate;

    @BindView(R.id.textView16)
    TextView amountLevel;

    @BindView(R.id.recycler_view)
    RecyclerView matchHistory;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);


    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.search_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    private void observerViewModel(String input) {

        mViewModel.getSummoner(input).observe(this, new Observer<SummonerModel>() {
            @Override
            public void onChanged(SummonerModel summonerModel) {
                if (summonerModel != null && !summonerModel.getName().isEmpty()) {
                    summoner = summonerModel;
                    updateView();
                }
            }
        });
        mViewModel.getMatches().observe(this, new Observer<List<Match>>() {
            @Override
            public void onChanged(List<Match> matches) {
                if(matches!=null){
                    matchList = matches;
                    updateMatchHistory();
                }
            }
        });
        mViewModel.getError().observe(this, isError -> {
            if(isError!=null){
                error.setVisibility(isError ? View.VISIBLE : View.GONE);
                frameLayout.setVisibility(View.INVISIBLE);
            }
        });
        mViewModel.isLoading().observe(this, isLoading -> {
            if(isLoading!=null){
                loadingView.setVisibility(isLoading?View.VISIBLE:View.GONE);
                if(isLoading){
                    error.setVisibility(View.GONE);
                }
            }
        });
    }

    private void updateView() {
        frameLayout.setVisibility(View.VISIBLE);
        summonerName.setText(Util.displaySummonerName(summoner.getName()));
        Glide.with(this).load(Util.getProfileIconUrl(summoner.getProfileIconId())).into(profile);


        Log.v("LOG","tier: " + summoner.getTier());
        @Nullable
        int rankId = getResources().getIdentifier(summoner.getTier().toLowerCase(), "drawable", getContext().getPackageName());
        int mostPlayedChampId = getResources().getIdentifier(Util.champIdtoName(summoner.getChampionId()).toLowerCase() + "_loading", "drawable", getContext().getPackageName());

        rankView.setImageResource(rankId);
        mostPlayedImage.setImageResource(mostPlayedChampId);

        Opgg.setOnClickListener(v -> {
            String url = MainActivity.opGg + summoner.getName().replaceAll("\\s", "");
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            getContext().startActivity(intent);
        });
        mostPlayedChamp.setText(Util.champIdtoName(summoner.getChampionId()));
        masteryPoints.setText(summoner.showMasteryPoints());
        lp.setText(summoner.showLeaguePoints());
        percentWinrate.setText(summoner.showWinRate() + "%");
        amountWins.setText(""+summoner.getWins());
        amountLevel.setText(""+summoner.getSummonerLevel());
        tier.setText(summoner.getTier());
        rank.setText(summoner.getRank());

    }

    private void updateMatchHistory(){
       mLayoutManager = new LinearLayoutManager(getContext());

        mMatchAdapter = new MatchAdapter(matchList, getContext());
        matchHistory.setLayoutManager(mLayoutManager);
        matchHistory.setAdapter(mMatchAdapter);
        matchHistory.setHasFixedSize(false);
    }


}


