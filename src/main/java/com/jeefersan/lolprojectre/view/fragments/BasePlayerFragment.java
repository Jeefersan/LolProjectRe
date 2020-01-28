package com.jeefersan.lolprojectre.view.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.jeefersan.lolprojectre.model.PlayerModel;
import com.jeefersan.lolprojectre.R;
import com.jeefersan.lolprojectre.util.PlayerListAdapter;
import com.jeefersan.lolprojectre.viewmodel.BasePlayerViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BasePlayerFragment extends Fragment {
    private View mRootView;

    private BasePlayerViewModel mViewModel;
    private PlayerListAdapter adapter;
    private List<PlayerModel> playerModelList;

    @BindView(R.id.list)
    RecyclerView playerList;

    @BindView(R.id.list_error)
    TextView listError;

    @BindView(R.id.loading_view)
    ProgressBar loadingView;

    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.empty)
    TextView empty;


    public BasePlayerFragment(BasePlayerViewModel mViewModel) {
        this.mViewModel = mViewModel;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        playerModelList = new ArrayList<>();

        mViewModel.refresh();

        adapter = new PlayerListAdapter(playerModelList,getContext());


        observerViewModel();
        Log.v("LOG","onCreate: " + getClass().toString());

    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_layout, container, false);

        ButterKnife.bind(this, mRootView);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mViewModel.refresh();
                refreshLayout.setRefreshing(false);
            }
        });

        playerList.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        playerList.setAdapter(adapter);
        observerViewModel();
        return mRootView;
    }



    private void observerViewModel() {
        mViewModel.players.observe(this, new Observer<List<PlayerModel>>() {
            @Override
            public void onChanged(List<PlayerModel> playerModels) {
                if (playerModels != null && !playerModels.isEmpty()) {
                    playerList.setVisibility(View.VISIBLE);
                    playerModelList = playerModels;
                    adapter.updatePlayers(playerModels);
                }

                if(playerModels.isEmpty()){
                    empty.setVisibility(View.VISIBLE);
                }
            }
        });
        mViewModel.playerLoadError.observe(this, isError -> {
            if (isError != null) {
                listError.setVisibility(isError ? View.VISIBLE : View.GONE);
            }
        });

        mViewModel.loading.observe(this, isLoading -> {
            if (isLoading != null) {
                loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if (isLoading) {
                    listError.setVisibility(View.GONE);
                    playerList.setVisibility(View.GONE);
                }
            }
        });
    }
}
