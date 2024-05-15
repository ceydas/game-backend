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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MatchmakerController {

    UserService userService;
    TournamentService tournamentService;
    @PostMapping("/tournament/enter")
    public ResponseEntity enterTournament(@RequestParam(name = "user") Long userId){
        UserDto userDto = userService.getUserDetails(userId);
        validateEnterTournament(userDto);

        userService.payCoins(userId, EnumMatchCoins.MIN_REQUIRED_COINS_TO_JOIN_TOURNAMENT.coins);

        //todo message queue: user enter tournament ticket is issued and queued

        return ResponseEntity.ok(userDto);

    }

    private static void validateEnterTournament(UserDto userDto) {
        int userLevel = userDto.getCurrentLevel();
        Long userCoins = userDto.getCurrentCoins();
        /* Check if user meets the min. requirements to join a tournament */
        if ((userLevel < EnumMatchLevel.MIN_REQUIRED_LEVEL_TO_JOIN_TOURNAMENT.level) || (userCoins < EnumMatchCoins.MIN_REQUIRED_COINS_TO_JOIN_TOURNAMENT.coins)){
            throw new MatchmakerException(MatchmakerErrorMessage.MINIMUM_REQUIREMENTS_NOT_MET);
        }
    }
}
