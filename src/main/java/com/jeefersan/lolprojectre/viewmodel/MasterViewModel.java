package com.jeefersan.lolprojectre.viewmodel;

import android.app.Application;

import com.jeefersan.lolprojectre.model.PlayerModel;

import java.util.List;

public class MasterViewModel extends BasePlayerViewModel {
    public final String mLeague = "master";

    public MasterViewModel(Application app) {
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
