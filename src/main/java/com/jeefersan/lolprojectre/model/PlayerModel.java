package com.jeefersan.lolprojectre.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayerModel {
    @SerializedName("summonerName")
    @Expose
    String summonerName;
    @SerializedName("summonerId")
    @Expose
    String summonerId;
    @SerializedName("rank")
    @Expose
    String rank;

    String tier;

    @SerializedName("leaguePoints")
    @Expose
    int leaguePoints;
    @SerializedName("wins")
    @Expose
    int wins;
    @SerializedName("losses")
    @Expose
    int losses;
    @SerializedName("hotStreak")
    @Expose
    boolean hotStreak;

    public PlayerModel(String summonerName, String summonerId, String rank, int leaguePoints, int wins, int losses, boolean hotStreak) {
        this.summonerName = summonerName;
        this.summonerId = summonerId;
        this.rank = rank;
        this.leaguePoints = leaguePoints;
        this.wins = wins;
        this.losses = losses;
        this.hotStreak = hotStreak;
    }

    public String getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
    }

    public boolean isHotStreak() {
        return hotStreak;
    }

    public void setHotStreak(boolean hotStreak) {
        this.hotStreak = hotStreak;
    }


    public String getSummonerName() {
        return summonerName;
    }

    public String getRank() {
        return rank;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public String getTier() {
        return tier;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public void setLeaguePoints(int leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getLeaguePoints() {
        return leaguePoints;
    }

    public double getWinRate() {
        double sum = wins + losses;
        return (wins / sum) * 100;
    }

    public int showWinRate() {
        return (int) getWinRate();
    }
}
