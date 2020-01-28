package com.jeefersan.lolprojectre.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SummonerDetail {

    @SerializedName("leagueId")
    @Expose
    String leagueId;
    @SerializedName("queueType")
    @Expose
    String queueType;
    @SerializedName("tier")
    @Expose
    String tier;
    @SerializedName("rank")
    @Expose
    String rank;
    @SerializedName("summonerId")
    @Expose
    String summonerId;
    @SerializedName("summonerName")
    @Expose
    String summonerName;
    @SerializedName("leaguePoints")
    @Expose
    Integer leaguePoints;
    @SerializedName("wins")
    @Expose
    Integer wins;
    @SerializedName("losses")
    @Expose
    Integer losses;


    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public String getQueueType() {
        return queueType;
    }

    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    public Integer getLeaguePoints() {
        return leaguePoints;
    }

    public void setLeaguePoints(Integer leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getLosses() {
        return losses;
    }

    public void setLosses(Integer losses) {
        this.losses = losses;
    }


}