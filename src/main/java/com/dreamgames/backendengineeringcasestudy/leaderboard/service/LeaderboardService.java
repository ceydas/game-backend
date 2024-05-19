package com.dreamgames.backendengineeringcasestudy.leaderboard.service;

import com.dreamgames.backendengineeringcasestudy.matchmaking.enums.EnumMatchCoins;
import com.dreamgames.backendengineeringcasestudy.matchmaking.enums.EnumMatchLevel;
import com.dreamgames.backendengineeringcasestudy.tournament_session.enums.EnumTournamentSession;
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
    private static final String GROUP_LEADERBOARD_KEY = "leaderboard:group";
    private static final String COUNTRY_LEADERBOARD_KEY = "leaderboard:country";
    private static final String USER_SCORES_KEY_PREFIX = "leaderboard:user";

    public void updateUserScore(Long userId, EnumCountry country) {

        Long score = EnumTournamentSession.REWARD_LEVEL_ADVANCE.getReward();

        // Update Group Leaderboard
        redisTemplate.opsForZSet().add(GROUP_LEADERBOARD_KEY, userId + ":" + country, score);

        redisTemplate.opsForZSet().incrementScore(COUNTRY_LEADERBOARD_KEY, country.toString(), score);
    }

    public Set<ZSetOperations.TypedTuple<String>> getGroupLeaderboard() {
        return redisTemplate.opsForZSet().reverseRangeWithScores(GROUP_LEADERBOARD_KEY, 0, -1);
    }

    public Set<ZSetOperations.TypedTuple<String>> getCountryLeaderboard() {
        return redisTemplate.opsForZSet().reverseRangeWithScores(COUNTRY_LEADERBOARD_KEY, 0, -1);
    }

}
