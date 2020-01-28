package com.jeefersan.lolprojectre.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mastery {

    @SerializedName("championId")
    @Expose
    private Integer championId;
    @SerializedName("championLevel")
    @Expose
    private Integer championLevel;
    @SerializedName("championPoints")
    @Expose
    private Integer championPoints;
    @SerializedName("chestGranted")
    @Expose
    private Boolean chestGranted;
    @SerializedName("tokensEarned")
    @Expose
    private Integer tokensEarned;
    @SerializedName("summonerId")
    @Expose
    private String summonerId;

    public Integer getChampionId() {
        return championId;
    }

    public void setChampionId(Integer championId) {
        this.championId = championId;
    }

    public Integer getChampionLevel() {
        return championLevel;
    }

    public void setChampionLevel(Integer championLevel) {
        this.championLevel = championLevel;
    }

    public Integer getChampionPoints() {
        return championPoints;
    }

    public void setChampionPoints(Integer championPoints) {
        this.championPoints = championPoints;
    }

    public Boolean getChestGranted() {
        return chestGranted;
    }

    public void setChestGranted(Boolean chestGranted) {
        this.chestGranted = chestGranted;
    }

    public Integer getTokensEarned() {
        return tokensEarned;
    }

    public void setTokensEarned(Integer tokensEarned) {
        this.tokensEarned = tokensEarned;
    }

    public String getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
    }

    public Mastery(Integer championId, Integer championLevel, Integer championPoints, Boolean chestGranted, Integer tokensEarned, String summonerId) {
        this.championId = championId;
        this.championLevel = championLevel;
        this.championPoints = championPoints;
        this.chestGranted = chestGranted;
        this.tokensEarned = tokensEarned;
        this.summonerId = summonerId;
    }
}
