package com.dreamgames.backendengineeringcasestudy.tournament_session;

import lombok.Data;

@Data
public class TournamentSessionDto {
    private boolean didClaimReward;
    private Long pendingReward;
    private Long tournamentId;
    private Long tournamentSessionId;
    private Long userId;
}
