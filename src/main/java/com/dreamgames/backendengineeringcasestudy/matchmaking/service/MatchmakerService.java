package com.dreamgames.backendengineeringcasestudy.matchmaking.service;

import com.dreamgames.backendengineeringcasestudy.kafka.dto.EnterTournamentProducerDto;
import com.dreamgames.backendengineeringcasestudy.matchmaking.entity.Match;
import com.dreamgames.backendengineeringcasestudy.matchmaking.entity.MatchGroup;
import com.dreamgames.backendengineeringcasestudy.matchmaking.enums.EnumMatchCoins;
import com.dreamgames.backendengineeringcasestudy.matchmaking.service.entityservice.MatchmakerEntityService;
import com.dreamgames.backendengineeringcasestudy.tournament.entity.Tournament;
import com.dreamgames.backendengineeringcasestudy.tournament.service.entityservice.TournamentEntityService;
import com.dreamgames.backendengineeringcasestudy.tournament_session.entity.TournamentSession;
import com.dreamgames.backendengineeringcasestudy.tournament_session.service.entityservice.TournamentSessionEntityService;
import com.dreamgames.backendengineeringcasestudy.user.entity.User;
import com.dreamgames.backendengineeringcasestudy.user.service.UserService;
import com.dreamgames.backendengineeringcasestudy.user.service.entityservice.UserEntityService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MatchmakerService {
    private final MatchmakerEntityService matchmakerEntityService;
    private final TournamentEntityService tournamentEntityService;
    private final UserEntityService userEntityService;
    private final TournamentSessionEntityService tournamentSessionEntityService;
    private final UserService userService;
    @KafkaListener(topics = {"enter-tournament"}, groupId = "app-1")
    @Transactional(rollbackOn = Exception.class)
    public void consumeEnterTournamentTicket(EnterTournamentProducerDto dto){

        User user = userEntityService.findByIdWithControl(dto.getUserId());

        Tournament tournament = tournamentEntityService.findByIdWithControl(dto.getCurrentTournamentId());
        // Create new MatchGroup
        MatchGroup matchGroup = matchmakerEntityService.createAndSaveMatchGroup();

        // Save new Match
        Match match = new Match();
        match.setTournament(tournament);
        match.setUser(user);
        match.setMatchGroup(matchGroup);
        match.setCreatedAt(LocalDateTime.now());
        matchmakerEntityService.saveMatch(match);

        // Add new tournament_session
        TournamentSession tournamentSession = new TournamentSession();
        tournamentSession.setTournament(tournament);
        tournamentSession.setUser(user);
        tournamentSession.setReward(0L);
        tournamentSession.setJoinedAt(LocalDateTime.now());
        tournamentSession.setDidClaimReward(false);
        tournamentSessionEntityService.save(tournamentSession);

        userService.payCoins(dto.getUserId(), EnumMatchCoins.MIN_REQUIRED_COINS_TO_JOIN_TOURNAMENT.coins);
        
    }

    public Match findMatchByUserId(Long id){
        return matchmakerEntityService.findByUserIdWithControl(id);
    }


    public boolean existsByUserAndTournament(User user, Tournament tournament){
        return matchmakerEntityService.existsByUserAndTournament(user, tournament);
    }
}
