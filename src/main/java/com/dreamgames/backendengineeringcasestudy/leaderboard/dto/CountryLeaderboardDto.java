package com.dreamgames.backendengineeringcasestudy.leaderboard.dto;

import com.dreamgames.backendengineeringcasestudy.user.enums.EnumCountry;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountryLeaderboardDto {
    EnumCountry country;
    Long userId;
    Long userScore;
    Long totalScoreByCountry;

}
