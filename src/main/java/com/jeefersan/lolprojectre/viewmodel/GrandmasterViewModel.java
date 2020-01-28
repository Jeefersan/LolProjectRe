package com.jeefersan.lolprojectre.viewmodel;

import android.app.Application;

import com.jeefersan.lolprojectre.model.PlayerModel;

import java.util.List;

public class GrandmasterViewModel extends BasePlayerViewModel {
    private final String mLeague = "grandmaster";

    public GrandmasterViewModel(Application app) {
        super(app);
    }

    @Override
    public void refresh() {
        super.refresh();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    @Override
    public List<PlayerModel> fetchPlayers() {
        return super.fetchPlayers();
    }

    @Override
    public String getmLeague() {
        return mLeague+"leagues";
    }
}
