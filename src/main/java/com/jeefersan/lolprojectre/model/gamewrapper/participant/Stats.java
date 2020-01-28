package com.jeefersan.lolprojectre.model.gamewrapper.participant;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stats {
    @SerializedName("win")
    @Expose
    boolean win;

    @SerializedName("kills")
    @Expose
    int kills;

    @SerializedName("deaths")
    @Expose
    int deaths;

    @SerializedName("assists")
    @Expose
    int assists;

    public Stats(boolean win, int kills, int deaths, int assists) {
        this.win = win;
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }
}

