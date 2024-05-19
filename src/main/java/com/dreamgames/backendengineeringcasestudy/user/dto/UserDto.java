package com.dreamgames.backendengineeringcasestudy.user.dto;

import com.dreamgames.backendengineeringcasestudy.user.enums.EnumCountry;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDto {
    private Long userId;
    private EnumCountry country;
    private Long currentCoins;
    private int currentLevel;
}
