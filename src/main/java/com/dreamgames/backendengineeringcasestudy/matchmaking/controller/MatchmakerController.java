package com.dreamgames.backendengineeringcasestudy.matchmaking.controller;

import com.dreamgames.backendengineeringcasestudy.kafka.EnterTournamentProducerService;
import com.dreamgames.backendengineeringcasestudy.kafka.dto.EnterTournamentProducerDto;
import com.dreamgames.backendengineeringcasestudy.matchmaking.entity.MatchGroup;
import com.dreamgames.backendengineeringcasestudy.matchmaking.enums.EnumMatchCoins;
import com.dreamgames.backendengineeringcasestudy.matchmaking.enums.EnumMatchLevel;
import com.dreamgames.backendengineeringcasestudy.matchmaking.exception.MatchmakerErrorMessage;
import com.dreamgames.backendengineeringcasestudy.matchmaking.exception.MatchmakerException;
import com.dreamgames.backendengineeringcasestudy.matchmaking.service.MatchmakerService;
import com.dreamgames.backendengineeringcasestudy.tournament.dto.TournamentDto;
import com.dreamgames.backendengineeringcasestudy.tournament.exception.TournamentErrorMessage;
import com.dreamgames.backendengineeringcasestudy.tournament.exception.TournamentException;
import com.dreamgames.backendengineeringcasestudy.tournament.service.TournamentService;
import com.dreamgames.backendengineeringcasestudy.tournament_session.service.TournamentSessionService;
import com.dreamgames.backendengineeringcasestudy.user.dto.UserDto;
import com.dreamgames.backendengineeringcasestudy.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MatchmakerController {

    private final UserService userService;
    private final TournamentService tournamentService;
    private final TournamentSessionService tournamentSessionService;
    private final EnterTournamentProducerService enterTournamentProducerService;


    @PostMapping("/tournament/enter/{id}")
    @Transactional(rollbackOn = Exception.class)
    public ResponseEntity<UserDto> enterTournament(@PathVariable Long id){
        UserDto userDto = userService.getUserDetails(id);
        validateEnterTournament(userDto);

        Long currentTournamentId = tournamentService.findCurrentTournamentId();
        EnterTournamentProducerDto enterTournamentProducerDto = new EnterTournamentProducerDto(
                id,
                userDto.getCountry(),
                currentTournamentId
        );
        enterTournamentProducerService.send(enterTournamentProducerDto);
        return ResponseEntity.ok(userDto);
    }


    @Transactional
    public void validateEnterTournament(UserDto userDto) {
        Long userId = userDto.getUserId();
        int userLevel = userDto.getCurrentLevel();
        Long userCoins = userDto.getCurrentCoins();

        /* Check if there are any active tournaments */
        TournamentDto currentTournament = tournamentService.findActive();
        if (currentTournament == null){
            throw new TournamentException(TournamentErrorMessage.NO_ACTIVE_TOURNAMENT);
        }

        /* Check if user has any prior tournaments */
        boolean userHasEnteredAnyTournaments = tournamentSessionService.userHasEnteredAnyTournaments(userId);
        /* Check if user claimed their reward. */
        boolean userHasClaimedReward = tournamentSessionService.userHasClaimedReward(userId);


        /* If user did have any prior tournaments and left their rewards unclaimed, throw an error
        they cannot join!
         */
        if (userHasEnteredAnyTournaments && !userHasClaimedReward ){
            throw new MatchmakerException(MatchmakerErrorMessage.USER_NOT_CLAIMED_REWARD);
        }

        /* Check if user meets the min. requirements to join a tournament */
        if (userLevel < EnumMatchLevel.MIN_REQUIRED_LEVEL_TO_JOIN_TOURNAMENT.level){
            throw new MatchmakerException(MatchmakerErrorMessage.LEVEL_NOT_SUFFICIENT);
        }
        if (userCoins < EnumMatchCoins.MIN_REQUIRED_COINS_TO_JOIN_TOURNAMENT.coins){
            throw new MatchmakerException(MatchmakerErrorMessage.NOT_ENOUGH_COINS);
        }

    }
}
