package com.jeefersan.lolprojectre.util;

import com.jeefersan.lolprojectre.model.LeagueWrapper;
import com.jeefersan.lolprojectre.model.Mastery;
import com.jeefersan.lolprojectre.model.SummonerDetail;
import com.jeefersan.lolprojectre.model.SummonerModel;
import com.jeefersan.lolprojectre.model.gamewrapper.GameWrapper;
import com.jeefersan.lolprojectre.model.matchwrapper.MatchWrapper;

import java.util.List;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("lol/league/v4/{whatleague}/by-queue/RANKED_SOLO_5x5")
    Call<LeagueWrapper> getData(
            @Path("whatleague") String whatLeague,
            @Query("api_key") String apiKey
    );


    @GET("lol/summoner/v4/summoners/by-name/{summonerName}")
    Single<SummonerModel> getSummoner(
            @Path("summonerName") String summonerName,
            @Query("api_key") String apiKey
    );

    @GET("lol/match/v4/matchlists/by-account/{encryptedAccountId}")
    Call<MatchWrapper> getMatchWrapper(
            @Path("encryptedAccountId") String accountId,
            @Query("beginIndex") int beginIndex,
            @Query("endIndex") int endIndex,
            @Query("api_key") String apiKey
    );

    @GET("lol/match/v4/matches/{matchId}")
    Call<GameWrapper> getGameWrapper(
            @Path("matchId") long matchId,
            @Query("api_key") String apiKey
    );

    @GET("lol/league/v4/entries/by-summoner/{sumId}")
    Call<List<SummonerDetail>> getSummonerDetails(
            @Path("sumId") String sumId,
            @Query("api_key") String apiKey
    );

    @GET("lol/champion-mastery/v4/champion-masteries/by-summoner/{summonerId}")
    Call<ResponseBody> getMastery(
            @Path("summonerId") String summonerId,
            @Query("api_key") String apiKey
    );
}
