package com.dreamgames.backendengineeringcasestudy.matchmaking.controller;

import com.dreamgames.backendengineeringcasestudy.matchmaking.enums.EnumMatchCoins;
import com.dreamgames.backendengineeringcasestudy.matchmaking.enums.EnumMatchLevel;
import com.dreamgames.backendengineeringcasestudy.matchmaking.exception.MatchmakerException;
import com.dreamgames.backendengineeringcasestudy.tournament.EnumTournament;
import com.dreamgames.backendengineeringcasestudy.tournament.service.TournamentService;
import com.dreamgames.backendengineeringcasestudy.user.dto.UserDto;
import com.dreamgames.backendengineeringcasestudy.user.entity.User;
import com.dreamgames.backendengineeringcasestudy.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MatchmakerController {

    UserService userService;
    TournamentService tournamentService;
    @PostMapping("/tournament/enter/{id}")
    public ResponseEntity<UserDto> enterTournament(@PathVariable Long id){
        UserDto userDto = userService.getUserDetails(id);
        validateEnterTournament(userDto);

        userService.payCoins(id, EnumMatchCoins.MIN_REQUIRED_COINS_TO_JOIN_TOURNAMENT.coins);

        //todo message queue: user enter tournament ticket is issued and queued

        return ResponseEntity.ok(userDto);
    }

    /*
    @PutMapping("/tournament/{tid}/claim-reward/{id}")
    public ResponseEntity<UserDto> claimReward(@PathVariable Long tid, @PathVariable Long id){
        //todo
    }

     */

    private static void validateEnterTournament(UserDto userDto) {
        int userLevel = userDto.getCurrentLevel();
        Long userCoins = userDto.getCurrentCoins();
        /* Check if user meets the min. requirements to join a tournament */
        if ((userLevel < EnumMatchLevel.MIN_REQUIRED_LEVEL_TO_JOIN_TOURNAMENT.level) || (userCoins < EnumMatchCoins.MIN_REQUIRED_COINS_TO_JOIN_TOURNAMENT.coins)){
            throw new MatchmakerException(MatchmakerErrorMessage.MINIMUM_REQUIREMENTS_NOT_MET);
        }

        // todo : check if user claimed their reward.
    }
}
