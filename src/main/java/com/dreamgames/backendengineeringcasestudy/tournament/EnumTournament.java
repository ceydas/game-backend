package com.dreamgames.backendengineeringcasestudy.tournament;

public enum EnumTournament {
    TOURNAMENT_DURATION(20);

    int durationInHours;


    EnumTournament(int durationInHours) {
        this.durationInHours = durationInHours;
    }

    public int getDurationInHours() {
        return durationInHours;
    }
}
