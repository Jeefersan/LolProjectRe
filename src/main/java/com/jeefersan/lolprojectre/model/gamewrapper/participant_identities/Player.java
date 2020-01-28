package com.jeefersan.lolprojectre.model.gamewrapper.participant_identities;

import com.google.gson.annotations.SerializedName;

public class Player {
    @SerializedName("accountId")
    String accountId;

    @SerializedName("summonerName")
    String summonerName;

    @SerializedName("summonerId")
    String summonerId;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    public Player(String accountId, String summonerName) {
        this.accountId = accountId;
        this.summonerName = summonerName;
    }

    public String getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
    }
}
