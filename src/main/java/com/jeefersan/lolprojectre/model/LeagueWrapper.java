package com.jeefersan.lolprojectre.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LeagueWrapper {

    @SerializedName("tier")
    @Expose
    public String tier;

    @SerializedName("leagueId")
    @Expose
    public String leagueId;

    @SerializedName("queue")
    @Expose
    public String queue;

    @SerializedName("accountId")
    @Expose
    public String name;

    @SerializedName("entries")
    @Expose
    public List<PlayerModel> entries;


    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PlayerModel> getEntries() {
        return entries;
    }

    public void setEntries(List<PlayerModel> entries) {
        this.entries = entries;
    }


}
