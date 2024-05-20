package com.dreamgames.backendengineeringcasestudy.leaderboard.controller;

import com.dreamgames.backendengineeringcasestudy.leaderboard.service.LeaderboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class LeaderboardController {

    LeaderboardService leaderboardService;

    /**
     * GetGroupRankRequest: This request retrieves the player's rank for any tournament.
     * GetGroupLeaderboardRequest: This request fetches the leaderboard data of a
     * tournament group.
     * GetCountryLeaderboardRequest: This request retrieves the leaderboard data of the
     * countries for a tournament.
     */

    /**
     * GetCountryLeaderboardRequest
     */
    @GetMapping("/leaderboard/country/{country}")
    public Set<ZSetOperations.TypedTuple<String>> getCountryLeaderboard(@PathVariable String country) {
        return leaderboardService.getCountryLeaderboard();
    }

    @GetMapping("/leaderboard/group/{groupId}")
    public Set<ZSetOperations.TypedTuple<String>> getGroupLeaderboard(@PathVariable Long groupId) {
        return leaderboardService.getGroupLeaderboard();
    }


}
