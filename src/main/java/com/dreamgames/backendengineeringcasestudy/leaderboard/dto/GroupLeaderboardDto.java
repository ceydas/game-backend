package com.dreamgames.backendengineeringcasestudy.leaderboard.dto;

import com.dreamgames.backendengineeringcasestudy.user.enums.EnumCountry;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class GroupLeaderboardDto {

    Long userId;
    EnumCountry country;
    Long userTournamentScore;
}
