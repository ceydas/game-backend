package com.dreamgames.backendengineeringcasestudy.user.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDto {
    private Long userId;
    private String country;
    private Long currentCoins;
    private int currentLevel;
}
