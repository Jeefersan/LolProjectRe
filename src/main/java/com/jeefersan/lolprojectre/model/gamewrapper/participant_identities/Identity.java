package com.jeefersan.lolprojectre.model.gamewrapper.participant_identities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Identity {
    @SerializedName("participantId")
    @Expose
    int participantId;

    @SerializedName("player")
    @Expose
    Player player;

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Identity(int participantId, Player player) {
        this.participantId = participantId;
        this.player = player;
    }
}
