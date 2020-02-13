package com.jeefersan.lolprojectre.util;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.jeefersan.lolprojectre.model.SummonerDetail;
import com.jeefersan.lolprojectre.model.SummonerModel;
import com.jeefersan.lolprojectre.model.gamewrapper.GameWrapper;
import com.jeefersan.lolprojectre.model.gamewrapper.participant.Participant;
import com.jeefersan.lolprojectre.model.gamewrapper.participant.Stats;
import com.jeefersan.lolprojectre.model.gamewrapper.participant_identities.Identity;
import com.jeefersan.lolprojectre.model.matchwrapper.MatchWrapper;
import com.jeefersan.lolprojectre.model.matchwrapper.matches.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SummonerRepository {
    private SummonerModel mSummonerModel;
    private List<Match> mMatchList;

    private MutableLiveData<SummonerModel> summoner = new MutableLiveData<>();
    private MutableLiveData<List<Match>> matchMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> error = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    static final CompositeDisposable disposable = new CompositeDisposable();

    private Application application;


    public RestApiService restApiService = new RestApiService();


    public SummonerRepository(Application application) {
        this.application = application;
    }

    public void update(String input) {
        isLoading.setValue(true);
        disposable.add(
                restApiService.getSummonerWrapper(input)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<SummonerModel>() {
                            @Override
                            public void onSuccess(SummonerModel sm) {
                                mSummonerModel = sm;
                                fetchMatches();
                                isLoading.setValue(false);
                                error.setValue(false);
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                                isLoading.setValue(false);
                                error.setValue(true);
                            }
                        })

        );

    }


    public void fetchMatches() {
        Call<MatchWrapper> match = restApiService.getMatchWrapper(mSummonerModel.getAccountId());
        match.enqueue(new Callback<MatchWrapper>() {
            @Override
            public void onResponse(Call<MatchWrapper> call, Response<MatchWrapper> response) {
                MatchWrapper wrapper = response.body();
                if (wrapper != null) {
                    mMatchList = wrapper.getMatches();
                    sortMatches(mMatchList);
                    matchMutableLiveData.setValue(mMatchList);
                    Log.v("LOG","first match gameId = " + mMatchList.get(0).getGameId());
                    fetchMatchDetails(mMatchList);
                }
            }

            @Override
            public void onFailure(Call<MatchWrapper> call, Throwable t) {
                Log.v("Error", "cant get matches");
            }
        });
    }

    public void fetchMatchDetails(List<Match> list) {
        for (int i=0;i<list.size();i++) {
            Match match = list.get(i);
            long gameId = match.getGameId();
            Call<GameWrapper> gameWrapperCall = restApiService.getGameWrapper(gameId);
            gameWrapperCall.enqueue(new Callback<GameWrapper>() {
                @Override
                public void onResponse(Call<GameWrapper> call, Response<GameWrapper> response) {
                    GameWrapper wrapper = response.body();
                    if (wrapper == null) {
                        return;
                    }
                    List<Identity> identityList = wrapper.getIdentitiesList();
                    int participantId;
                    for(Identity identity : identityList){
                        if(identity.getPlayer().getSummonerName().equals(mSummonerModel.getName())){
                            if(mSummonerModel.getSummonerId()==null){
                                mSummonerModel.setSummonerId(identity.getPlayer().getSummonerId());
                                fetchSummonerDetails();
                                fetchMastery();
                            }
                            participantId = identity.getParticipantId()-1;
                            Log.v("LOG","this is: " + identity.getPlayer().getSummonerName() + " with participantid " + identity.getParticipantId());
                            List<Participant> lista = wrapper.getParticipants();
                            Stats s = lista.get(participantId).getStats();
                            int k = s.getKills();
                            int d = s.getDeaths();
                            int a = s.getAssists();
                            match.setKda(k + "/" + d + "/" + a);
                            match.setWin(s.isWin());
                            Log.v("Log","match kda is " + k + "/" + d + "/" + a + " and summoner Id = : " + mSummonerModel.getSummonerId());
                            break;
                        }

                    }

                }

                @Override
                public void onFailure(Call<GameWrapper> call, Throwable t) {
                    Log.e("error","eror ");
                }
            });

        }
    }

    public void fetchSummonerDetails(){
        Call<List<SummonerDetail>> detailsCall = restApiService.getSummonerDetails(mSummonerModel.getSummonerId());
        detailsCall.enqueue(new Callback<List<SummonerDetail>>() {
            @Override
            public void onResponse(Call<List<SummonerDetail>> call, Response<List<SummonerDetail>> response) {
                List<SummonerDetail> lista = response.body();
                if(lista.isEmpty() || lista.size()==0){
                    Log.v("LOG","matchList empty");
                    return;
                }
                SummonerDetail sm = lista.get(0);
                mSummonerModel.setTier(sm.getTier());
                mSummonerModel.setRank(sm.getRank());
                mSummonerModel.setWins(sm.getWins());
                mSummonerModel.setLosses(sm.getLosses());
                mSummonerModel.setLeaguePoints(sm.getLeaguePoints());
                summoner.setValue(mSummonerModel);
                Log.v("LOG","WORKING: " + mSummonerModel.getRank() + mSummonerModel.getLosses());
            }

            @Override
            public void onFailure(Call<List<SummonerDetail>> call, Throwable t) {
                Log.v("Error","fetch summoner details has failed");
            }
        });
    }

    public void fetchMastery(){
        Call<ResponseBody> call = restApiService.getMastery(mSummonerModel.getSummonerId());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String jsonResponse;
                try {
                    jsonResponse = response.body().string();
                    JSONArray array = new JSONArray(jsonResponse);
                    JSONObject obj = array.getJSONObject(0);
                    mSummonerModel.setChampionId(obj.getInt("championId"));
                    mSummonerModel.setChampionPoints(obj.getLong("championPoints"));
                    Log.v("LOG","summoner champ n mastery: " + mSummonerModel.getChampionId() + " & " + mSummonerModel.getChampionPoints());
                    summoner.setValue(mSummonerModel);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                catch (IOException ie){
                    ie.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


    public MutableLiveData<List<Match>> getMatchMutableLiveData() {
        return matchMutableLiveData;
    }

    public MutableLiveData<SummonerModel> getSummoner() {
        return summoner;
    }

    public MutableLiveData<Boolean> getError() {
        return error;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public void sortMatches(List<Match> list) {
        list.sort((o1, o2) -> (int) ((int) o2.getTimestamp() - o1.getTimestamp()));
    }


}
