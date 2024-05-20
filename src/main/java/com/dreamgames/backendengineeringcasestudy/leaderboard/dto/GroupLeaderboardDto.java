package com.dreamgames.backendengineeringcasestudy.leaderboard.dto;

import com.dreamgames.backendengineeringcasestudy.user.enums.EnumCountry;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash
@Builder
public class GroupLeaderboardDto {

    Long groupId;
    Long userId;
    EnumCountry country;
}
