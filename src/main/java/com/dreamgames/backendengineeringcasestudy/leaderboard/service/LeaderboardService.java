package com.dreamgames.backendengineeringcasestudy.leaderboard.service;

import com.dreamgames.backendengineeringcasestudy.leaderboard.dto.GroupLeaderboardDto;
import com.dreamgames.backendengineeringcasestudy.matchmaking.entity.Match;
import com.dreamgames.backendengineeringcasestudy.matchmaking.enums.EnumMatchCoins;
import com.dreamgames.backendengineeringcasestudy.matchmaking.enums.EnumMatchLevel;
import com.dreamgames.backendengineeringcasestudy.matchmaking.service.entityservice.MatchmakerEntityService;
import com.dreamgames.backendengineeringcasestudy.tournament.entity.Tournament;
import com.dreamgames.backendengineeringcasestudy.tournament.service.entityservice.TournamentEntityService;
import com.dreamgames.backendengineeringcasestudy.tournament_session.enums.EnumTournamentSession;
import com.dreamgames.backendengineeringcasestudy.user.entity.User;
import com.dreamgames.backendengineeringcasestudy.user.enums.EnumCountry;
import com.dreamgames.backendengineeringcasestudy.user.enums.EnumReward;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class LeaderboardService {
    private final RedisTemplate<String, String> redisTemplate;
    private final TournamentEntityService tournamentEntityService;
    private final MatchmakerEntityService matchmakerEntityService;
    private static final String GROUP_LEADERBOARD_KEY = "leaderboard:group";
    private static final String COUNTRY_LEADERBOARD_KEY = "leaderboard:country";
    private static final String USER_SCORES_KEY_PREFIX = "leaderboard:user";

    public void updateUserScore(User user) {
        Long userId = user.getUserId();
        // Check if there's an active tournament
        Tournament tournament = tournamentEntityService.findActive();
        if (tournament == null) return; // Do not update if there's no active tournament

        // Check if user is in this tournament
        boolean userInCurrentTournament = matchmakerEntityService.existsByUserAndTournament(user, tournament);
        if (!userInCurrentTournament) return; // Do not update if user's not in tournament

        Match match = matchmakerEntityService.findByUserIdWithControl(userId);
        Long groupId = match.getMatchGroup().getGroupId();
        String country = user.getCountry().toString();

        Long score = EnumTournamentSession.REWARD_LEVEL_ADVANCE.getReward();

        // Update Group Leaderboard
        redisTemplate.opsForZSet().add(GROUP_LEADERBOARD_KEY, groupId + ":" + userId + ":" + country, score);

        redisTemplate.opsForZSet().incrementScore(COUNTRY_LEADERBOARD_KEY, country + ":" + userId, score);
    }

    public Set<ZSetOperations.TypedTuple<String>> getGroupLeaderboard() {
        return redisTemplate.opsForZSet().reverseRangeWithScores(GROUP_LEADERBOARD_KEY, 0, -1);
    }

    public Set<ZSetOperations.TypedTuple<String>> getCountryLeaderboard() {
        return redisTemplate.opsForZSet().reverseRangeWithScores(COUNTRY_LEADERBOARD_KEY, 0, -1);
    }

}
