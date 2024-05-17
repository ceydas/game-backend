package com.dreamgames.backendengineeringcasestudy.tournament_session.service;

import com.dreamgames.backendengineeringcasestudy.tournament.entity.Tournament;
import com.dreamgames.backendengineeringcasestudy.tournament.service.TournamentService;
import com.dreamgames.backendengineeringcasestudy.tournament_session.entity.TournamentSession;
import com.dreamgames.backendengineeringcasestudy.tournament_session.service.entityservice.TournamentSessionEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TournamentSessionService {

    private final TournamentService tournamentService;
    private final TournamentSessionEntityService tournamentSessionEntityService;


    public boolean userHasEnteredAnyTournaments(Long id){
        return tournamentSessionEntityService.userHasEnteredAnyTournaments(id);
    }

    public boolean userHasClaimedReward(Long id){
        return tournamentSessionEntityService.userHasClaimedReward(id);
    }
}
