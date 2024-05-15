package com.dreamgames.backendengineeringcasestudy.tournament.service;
import com.dreamgames.backendengineeringcasestudy.tournament.converter.TournamentConverter;
import com.dreamgames.backendengineeringcasestudy.tournament.dto.TournamentDto;
import com.dreamgames.backendengineeringcasestudy.tournament.entity.Tournament;
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
    @Scheduled(cron = "0 0 0 * * *", zone = "UTC") // At 00:00 UTC
    public TournamentDto startTournament(){
        Tournament tournament = tournamentEntityService.startTournament(LocalDateTime.now());
        TournamentDto tournamentDto = TournamentConverter.INSTANCE.convertToTournamentDto(tournament);
        return tournamentDto;
    }

    @Scheduled(cron = "0 0 20 * * *", zone = "UTC") // At 20:00 UTC
    public TournamentDto endTournament(Long id){
        Tournament tournament = tournamentEntityService.findByIdWithControl(id);
        tournamentEntityService.endTournament(id);
        TournamentDto tournamentDto = TournamentConverter.INSTANCE.convertToTournamentDto(tournament);
        return tournamentDto;
    }

    public TournamentDto findActive(){
        Tournament tournament = tournamentEntityService.findActive();
        if (tournament == null){
            return null;
        }
        TournamentDto tournamentDto = TournamentConverter.INSTANCE.convertToTournamentDto(tournament);
        return tournamentDto;
    }

}
