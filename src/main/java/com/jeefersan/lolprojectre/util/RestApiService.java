package com.jeefersan.lolprojectre.util;

import android.util.Log;

import com.jeefersan.lolprojectre.model.Mastery;
import com.jeefersan.lolprojectre.model.SummonerDetail;
import com.jeefersan.lolprojectre.model.SummonerModel;
import com.jeefersan.lolprojectre.model.gamewrapper.GameWrapper;
import com.jeefersan.lolprojectre.model.matchwrapper.MatchWrapper;
import com.jeefersan.lolprojectre.view.MainActivity;

import java.util.List;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiService {

    private static final String BASE_URL = "https://euw1.api.riotgames.com";
    private static Retrofit retrofit = null;

    private static RestApiService instance;

    public RestApiService() {
    }

    public static ApiService getApiService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

        }
        Log.v("Logging","Retrofit created");
        return retrofit.create(ApiService.class);
    }


    public static RestApiService getInstance() {
        if (instance == null) {
            instance = new RestApiService();
        }
        return instance;
    }

    public Single<SummonerModel> getSummonerWrapper(String name){
        return getApiService().getSummoner(name, MainActivity.apiKey);
    }

    public Call<MatchWrapper> getMatchWrapper(String id){
        return getApiService().getMatchWrapper(id,0,15,MainActivity.apiKey);
    }

    public Call<GameWrapper> getGameWrapper(long matchId){
        return getApiService().getGameWrapper(matchId,MainActivity.apiKey);
    }

    public Call<List<SummonerDetail>> getSummonerDetails(String summonerId){
        return getApiService().getSummonerDetails(summonerId,MainActivity.apiKey);
    }

    public Call<ResponseBody> getMastery(String summonerId){
        return getApiService().getMastery(summonerId,MainActivity.apiKey);
    }





}
