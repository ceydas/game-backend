package com.dreamgames.backendengineeringcasestudy.leaderboard.dto;

import com.dreamgames.backendengineeringcasestudy.user.enums.EnumCountry;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

@Data
@Builder
@RedisHash
public class CountryLeaderboardDto {
    EnumCountry country;
    Long userId;

}
