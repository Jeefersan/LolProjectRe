package com.jeefersan.lolprojectre.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jeefersan.lolprojectre.util.ApiService;
import com.jeefersan.lolprojectre.model.LeagueWrapper;
import com.jeefersan.lolprojectre.model.PlayerModel;
import com.jeefersan.lolprojectre.util.RestApiService;
import com.jeefersan.lolprojectre.view.MainActivity;

import java.util.ArrayList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class BasePlayerViewModel extends ViewModel {

    public MutableLiveData<List<PlayerModel>> players = new MutableLiveData<>();
    public MutableLiveData<Boolean> playerLoadError = new MutableLiveData<>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<>();

    private List<PlayerModel> playerList = new ArrayList<>();

    private Application mApplication;

    private String mLeague;


    public BasePlayerViewModel(Application app) {
        this.mApplication = app;
    }

    public void refresh() {
        fetchPlayers();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public List<PlayerModel> fetchPlayers() {
        ApiService apiService = RestApiService.getApiService();
        loading.setValue(true);
        Log.v("Log", "w./e " + getmLeague() + " and " + MainActivity.apiKey);
        Call<LeagueWrapper> call = apiService.getData(getmLeague(),MainActivity.apiKey);
        call.enqueue(new Callback<LeagueWrapper>() {
            @Override
            public void onResponse(Call<LeagueWrapper> call, Response<LeagueWrapper> response) {
                LeagueWrapper mLeagueWrapper = response.body();
                Log.v("Logging","log: " + call.request().url().toString());
                if (mLeagueWrapper != null && mLeagueWrapper.getEntries() != null) {
                    playerList = mLeagueWrapper.getEntries();
                    sortListByLp(playerList);
                    for (int i = 0; i < playerList.size(); i++) {
                        playerList.get(i).setTier(mLeagueWrapper.getTier());
                    }

                    players.setValue(playerList);
                }

                playerLoadError.setValue(false);
            }

            @Override
            public void onFailure(Call<LeagueWrapper> call, Throwable t) {
                playerLoadError.setValue(true);
                Log.v("Error", "fetchPlayers failed");
            }
        });
        loading.setValue(false);
        return playerList;
    }

    private static void sortListByLp(List<PlayerModel> list) {
        list.sort((o1, o2) -> o2.getLeaguePoints() - o1.getLeaguePoints());

    }

    public String getmLeague() {
        return mLeague + "leagues";
    }
}