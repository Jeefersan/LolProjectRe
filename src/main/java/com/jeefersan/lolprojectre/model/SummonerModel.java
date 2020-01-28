package com.jeefersan.lolprojectre.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jeefersan.lolprojectre.model.matchwrapper.matches.Match;

import java.util.List;

public class SummonerModel {
    public SummonerModel() {
    }

    @SerializedName("id")
    @Expose
    String userId;

    @SerializedName("accountId")
    @Expose
    String accountId;

    @SerializedName("puuid")
    @Expose
    String puuid;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("profileIconId")
    @Expose
    String profileIconId;

    @SerializedName("revisionDate")
    @Expose
    long revisionDate;

    @SerializedName("summonerLevel")
    @Expose
    int summonerLevel;

    private String tier;
    private String rank;
    private int leaguePoints;
    private int wins;
    private int losses;
    private String summonerId;
    private int championId;
    private long championPoints;


    public String getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
    }

    public SummonerModel(String id, String accountId, String puuid, String name, String profileIconId, long revisionDate, int summonerLevel) {
        this.userId = id;
        this.accountId = accountId;
        this.puuid = puuid;
        this.name = name;
        this.profileIconId = profileIconId;
        this.revisionDate = revisionDate;
        this.summonerLevel = summonerLevel;
    }

    public String showMasteryPoints(){
        String s = "";
        long mp = championPoints;
        if(mp>=100000){
            mp /= 1000;
            s+= mp+"k";
        }
        return s;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public int getChampionId() {
        return championId;
    }

    public void setChampionId(int championId) {
        this.championId = championId;
    }

    public long getChampionPoints() {
        return championPoints;
    }

    public void setChampionPoints(long championPoints) {
        this.championPoints = championPoints;
    }

    public String getPuuid() {
        return puuid;
    }

    public void setPuuid(String puuid) {
        this.puuid = puuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(String profileIconId) {
        this.profileIconId = profileIconId;
    }

    public long getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(long revisionDate) {
        this.revisionDate = revisionDate;
    }

    public int getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(int summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    public String getTier() {
        if(tier==null){
            return "Unranked";
        }
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

    public int getLeaguePoints() {
        return leaguePoints;
    }

    public String showLeaguePoints(){
        if(leaguePoints==0 && tier==null){
            return "";
        }
        return leaguePoints+" lp";
    }

    public void setLeaguePoints(int leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public double getWinRate(){
        double sum = wins+losses;
        return (wins/sum) * 100;
    }

    public int showWinRate(){
        return (int) getWinRate();
    }
}


