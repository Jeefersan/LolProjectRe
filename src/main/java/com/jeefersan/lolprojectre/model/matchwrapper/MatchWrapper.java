package com.jeefersan.lolprojectre.model.matchwrapper;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jeefersan.lolprojectre.model.matchwrapper.matches.Match;

import java.util.List;

public class MatchWrapper {
    @SerializedName("matches")
    @Expose
    List<Match> matches;

    public MatchWrapper(List<Match> matches) {
        this.matches = matches;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
}
