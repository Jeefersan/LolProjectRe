package com.jeefersan.lolprojectre.model.gamewrapper.participant;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Participant {
    @SerializedName("participantId")
    @Expose
    int participantId;

    @SerializedName("stats")
    @Expose
    Stats stats;

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public Participant(int participantId, Stats stats) {
        this.participantId = participantId;
        this.stats = stats;
    }
}
