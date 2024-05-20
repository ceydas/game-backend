package com.dreamgames.backendengineeringcasestudy.user.dto;

import com.dreamgames.backendengineeringcasestudy.user.enums.EnumCountry;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

@Builder
@Data
@RedisHash
public class UserDto {
    private Long userId;
    private EnumCountry country;
    private Long currentCoins;
    private int currentLevel;
}
