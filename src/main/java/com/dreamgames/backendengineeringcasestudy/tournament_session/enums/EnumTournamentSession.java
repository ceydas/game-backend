package com.dreamgames.backendengineeringcasestudy.tournament_session.enums;

public enum EnumTournamentSession {
    REWARD_LEVEL_ADVANCE(1L);

    Long reward;

    EnumTournamentSession(Long reward) {
        this.reward = reward;
    }
}
