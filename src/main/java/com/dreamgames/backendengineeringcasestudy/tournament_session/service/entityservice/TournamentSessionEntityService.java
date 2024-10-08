package com.dreamgames.backendengineeringcasestudy.tournament_session.service.entityservice;

import com.dreamgames.backendengineeringcasestudy.tournament.entity.Tournament;
import com.dreamgames.backendengineeringcasestudy.tournament_session.TournamentSessionRepository;
import com.dreamgames.backendengineeringcasestudy.tournament_session.entity.TournamentSession;
import com.dreamgames.backendengineeringcasestudy.tournament_session.exception.TournamentSessionErrorMessage;
import com.dreamgames.backendengineeringcasestudy.tournament_session.exception.TournamentSessionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TournamentSessionEntityService {

    private final TournamentSessionRepository tournamentSessionRepository;

    public TournamentSession save(TournamentSession tournamentSession){
        return tournamentSessionRepository.save(tournamentSession);
    }

    /** This method assumes that the user has entered a tournament before.
    * Else, it will throw an exception. **/
    public TournamentSession getLatestTournamentSessionByUserId(Long id){
        boolean hasEnteredTournament = userHasEnteredAnyTournaments(id);

        if (!hasEnteredTournament){
            throw new TournamentSessionException(TournamentSessionErrorMessage.USER_HAS_NO_TOURNAMENT_PARTICIPATION);
        }

        TournamentSession latestTournamentSession = tournamentSessionRepository.findByUserUserId(id);
        return latestTournamentSession;
    }

    public boolean userHasClaimedReward(Long id){
        if (!userHasEnteredAnyTournaments(id)){
            return false;
        }
        return findDidClaimRewardByUserId(id);
    }
    public boolean userHasEnteredAnyTournaments(Long id){
        return tournamentSessionRepository.existsByUserUserId(id);
    }

    public boolean findDidClaimRewardByUserId(Long id){
        TournamentSession tournamentSession = tournamentSessionRepository.findByUserUserId(id);

        if (tournamentSession == null){
            throw new TournamentSessionException(TournamentSessionErrorMessage.USER_HAS_NO_TOURNAMENT_PARTICIPATION);
        }

        return tournamentSession.isDidClaimReward();
    }
}
