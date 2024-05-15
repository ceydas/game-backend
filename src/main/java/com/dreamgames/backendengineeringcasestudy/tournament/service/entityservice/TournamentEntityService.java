package com.dreamgames.backendengineeringcasestudy.tournament.service.entityservice;

import com.dreamgames.backendengineeringcasestudy.tournament.EnumTournament;
import com.dreamgames.backendengineeringcasestudy.tournament.entity.Tournament;
import com.dreamgames.backendengineeringcasestudy.tournament.exception.TournamentErrorMessage;
import com.dreamgames.backendengineeringcasestudy.tournament.exception.TournamentException;
import com.dreamgames.backendengineeringcasestudy.tournament.repository.TournamentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TournamentEntityService {
    private final TournamentRepository tournamentRepository;

    public Tournament findByIdWithControl(Long id){
        Optional<Tournament> optionalTournament = tournamentRepository.findById(id);
        if (!optionalTournament.isPresent()){
            throw new TournamentException(TournamentErrorMessage.TOURNAMENT_DOES_NOT_EXIST);
        }
        return optionalTournament.get();
    }

    public Tournament findActive(){
        /*Warning: check null handling*/
        Tournament tournament = tournamentRepository.findByActiveIsTrue();
        return tournament;
    }
    public Tournament startTournament(LocalDateTime dateTime){
        Tournament tournament = tournamentRepository.findByActiveIsTrue();

        if (tournament != null){
            throw new TournamentException(TournamentErrorMessage.TOURNAMENT_ALREADY_ACTIVE);
        }
        tournament = new Tournament();
        tournament.setStartTime(dateTime);
        tournament.setEndTime(dateTime.plusHours(EnumTournament.TOURNAMENT_DURATION.getDurationInHours()));
        tournament.setActive(true);
        return tournamentRepository.save(tournament);
    }

    public Tournament endTournament(Long id){
        Tournament tournament = tournamentRepository.findByActiveIsTrue();
        if (tournament == null){
            throw new TournamentException(TournamentErrorMessage.NO_ACTIVE_TOURNAMENT);
        }
        //todo: assert active tournament id equals id parameter
        tournament.setActive(false);
        return tournamentRepository.save(tournament);
    }
}
