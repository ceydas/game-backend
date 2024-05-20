package com.dreamgames.backendengineeringcasestudy.leaderboard.service;

import com.dreamgames.backendengineeringcasestudy.leaderboard.dto.GroupLeaderboardDto;
import com.dreamgames.backendengineeringcasestudy.matchmaking.entity.Match;
import com.dreamgames.backendengineeringcasestudy.matchmaking.enums.EnumMatchCoins;
import com.dreamgames.backendengineeringcasestudy.matchmaking.enums.EnumMatchLevel;
import com.dreamgames.backendengineeringcasestudy.matchmaking.service.entityservice.MatchmakerEntityService;
import com.dreamgames.backendengineeringcasestudy.tournament.entity.Tournament;
import com.dreamgames.backendengineeringcasestudy.tournament.exception.TournamentErrorMessage;
import com.dreamgames.backendengineeringcasestudy.tournament.exception.TournamentException;
import com.dreamgames.backendengineeringcasestudy.tournament.service.entityservice.TournamentEntityService;
import com.dreamgames.backendengineeringcasestudy.tournament_session.enums.EnumTournamentSession;
import com.dreamgames.backendengineeringcasestudy.tournament_session.exception.TournamentSessionErrorMessage;
import com.dreamgames.backendengineeringcasestudy.tournament_session.exception.TournamentSessionException;
import com.dreamgames.backendengineeringcasestudy.user.entity.User;
import com.dreamgames.backendengineeringcasestudy.user.enums.EnumCountry;
import com.dreamgames.backendengineeringcasestudy.user.enums.EnumReward;
import jakarta.transaction.Transactional;
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
    private static final String GROUP_LEADERBOARD_KEY = "leaderboard:group:";
    private static final String COUNTRY_LEADERBOARD_KEY = "leaderboard:country:";


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
        redisTemplate.opsForZSet().incrementScore(GROUP_LEADERBOARD_KEY + groupId + ":",    "user " + userId + ":country " + country, score);

        redisTemplate.opsForZSet().incrementScore(COUNTRY_LEADERBOARD_KEY + country + ":",   "user " + userId + ":group " + groupId, score);
    }

    public Set<ZSetOperations.TypedTuple<String>> getGroupLeaderboard(Long groupId) {
        return redisTemplate.opsForZSet().reverseRangeWithScores(GROUP_LEADERBOARD_KEY + groupId + ":", 0, -1);
    }

    public Long getGroupRank(Long userId){
        Tournament tournament = tournamentEntityService.findActive();
        // No active tournament
        if (tournament == null) {
            throw new TournamentException(TournamentErrorMessage.NO_ACTIVE_TOURNAMENT);
        }

        // Fetch the user's match group
        Match match = matchmakerEntityService.findByUserIdWithControl(userId);
        // User not in any match
        if (match == null) {
            throw new TournamentSessionException(TournamentSessionErrorMessage.USER_HAS_NO_TOURNAMENT_PARTICIPATION);
        }

        Long groupId = match.getMatchGroup().getGroupId();
        String country = match.getUser().getCountry().toString();

        // Construct the member identifier used in the sorted set
        String member = "user " + userId + ":country " + country;

        // Fetch the rank (0-based index)
        Long rank = redisTemplate.opsForZSet().reverseRank(GROUP_LEADERBOARD_KEY + groupId + ":", member);

        // Convert to 1-based rank
        return (rank != null) ? rank + 1 : null;
    }

    public void resetLeaderboard(){
        redisTemplate.delete(GROUP_LEADERBOARD_KEY);
        redisTemplate.delete(COUNTRY_LEADERBOARD_KEY);
    }
    public Set<ZSetOperations.TypedTuple<String>> getCountryLeaderboard(String country) {
        return redisTemplate.opsForZSet().reverseRangeWithScores(COUNTRY_LEADERBOARD_KEY + country +":", 0, -1);
    }

}
