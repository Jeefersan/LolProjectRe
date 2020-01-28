package com.jeefersan.lolprojectre.model.gamewrapper;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jeefersan.lolprojectre.model.gamewrapper.participant_identities.Identity;
import com.jeefersan.lolprojectre.model.gamewrapper.participant.Participant;

import java.util.List;

public class GameWrapper {

    @SerializedName("participantIdentities")
    @Expose
    List<Identity> identitiesList;

    @SerializedName("participants")
    @Expose
    List<Participant> participants;

    public List<Identity> getIdentitiesList() {
        return identitiesList;
    }

    public void setIdentitiesList(List<Identity> identitiesList) {
        this.identitiesList = identitiesList;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public GameWrapper(List<Identity> identitiesList, List<Participant> participants) {
        this.identitiesList = identitiesList;
        this.participants = participants;
    }
}
