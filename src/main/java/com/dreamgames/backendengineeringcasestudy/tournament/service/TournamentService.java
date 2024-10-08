package com.dreamgames.backendengineeringcasestudy.tournament.service;
import com.dreamgames.backendengineeringcasestudy.leaderboard.service.LeaderboardService;
import com.dreamgames.backendengineeringcasestudy.tournament.converter.TournamentConverter;
import com.dreamgames.backendengineeringcasestudy.tournament.dto.TournamentDto;
import com.dreamgames.backendengineeringcasestudy.tournament.entity.Tournament;
import com.dreamgames.backendengineeringcasestudy.tournament.exception.TournamentErrorMessage;
import com.dreamgames.backendengineeringcasestudy.tournament.exception.TournamentException;
import com.dreamgames.backendengineeringcasestudy.tournament.service.entityservice.TournamentEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TournamentService {
    private final TournamentEntityService tournamentEntityService;
    LeaderboardService leaderboardService;
    @Scheduled(cron = "0 0 0 * * *", zone = "UTC") // At 00:00 UTC
    public void startTournament(){
        Tournament tournament = tournamentEntityService.startTournament(LocalDateTime.now());
    }
    @Scheduled(cron = "0 0 20 * * *", zone = "UTC") // At 20:00 UTC
    public void endTournament(){
        TournamentDto tournamentDto = this.findActive();
        Long activeTournamentId = tournamentDto.getId();
        tournamentEntityService.endTournament(activeTournamentId);
        leaderboardService.resetLeaderboard();
    }

    public TournamentDto findActive(){
        Tournament tournament = tournamentEntityService.findActive();
        if (tournament == null){
            return null;
        }
        TournamentDto tournamentDto = TournamentConverter.INSTANCE.convertToTournamentDto(tournament);
        return tournamentDto;
    }

    public boolean activeTournamentExists() {
        Tournament tournament = tournamentEntityService.findActive();
        if (tournament == null) {
            return false;
        }
        return true;
    }

    public Long findCurrentTournamentId(){
        Tournament tournament = tournamentEntityService.findActive();
        if (tournament == null){
            throw new TournamentException(TournamentErrorMessage.NO_ACTIVE_TOURNAMENT);
        }

        return tournament.getId();
    }

}
