package com.dreamgames.backendengineeringcasestudy.leaderboard.controller;

import com.dreamgames.backendengineeringcasestudy.leaderboard.service.LeaderboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class LeaderboardController {

    LeaderboardService leaderboardService;
    @GetMapping("/leaderboard/country/{country}")
    public Set<ZSetOperations.TypedTuple<String>> getCountryLeaderboard(@PathVariable String country) {
        return leaderboardService.getCountryLeaderboard();
    }
}
