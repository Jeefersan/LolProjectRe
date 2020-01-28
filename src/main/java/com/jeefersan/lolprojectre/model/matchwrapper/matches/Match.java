package com.jeefersan.lolprojectre.model.matchwrapper.matches;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Match {

    @SerializedName("platformId")
    @Expose
    String platformId;

    @SerializedName("gameId")
    @Expose
    long gameId;

    @SerializedName("champion")
    @Expose
    int champion;

    @SerializedName("queue")
    @Expose
    int queue;

    @SerializedName("timestamp")
    @Expose
    long timestamp;

    @SerializedName("role")
    @Expose
    String role;

    @SerializedName("lane")
    @Expose
    String lane;

    String kda;
    boolean isWin;
    String summonerId;

    public String getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
    }

    public String getKda() {
        return kda;
    }

    public void setKda(String kda) {
        this.kda = kda;
    }

    public boolean isWin() {
        return isWin;
    }

    public void setWin(boolean win) {
        isWin = win;
    }

    public String getWinLose(){
        if(isWin){
            return "win";
        }
        return "lose";
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public int getChampion() {
        return champion;
    }

    public void setChampion(int champion) {
        this.champion = champion;
    }

    public int getQueue() {
        return queue;
    }

    public void setQueue(int queue) {
        this.queue = queue;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLane() {
        return lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public Match(String platformId, long gameId, int champion, int queue, long timestamp, String role, String lane) {
        this.platformId = platformId;
        this.gameId = gameId;
        this.champion = champion;
        this.queue = queue;
        this.timestamp = timestamp;
        this.role = role;
        this.lane = lane;


    }
}
