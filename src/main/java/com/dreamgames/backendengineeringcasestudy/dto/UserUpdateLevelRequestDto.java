package com.dreamgames.backendengineeringcasestudy.dto;

import lombok.Data;

@Data
public class UserUpdateLevelRequestDto {
    private Long userId;
    private int newLevel;
    private Long newCoins;
}
