package com.dreamgames.backendengineeringcasestudy.tournament_session.enums;

public enum EnumTournamentReward {
    GROUP_FIRST(10_000L),
    GROUP_SECOND(5_000L);


    Long reward;
    EnumTournamentReward(Long reward) {
        this.reward = reward;
    }
}
