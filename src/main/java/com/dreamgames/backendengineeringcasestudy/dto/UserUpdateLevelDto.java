package com.dreamgames.backendengineeringcasestudy.dto;

import lombok.Data;

@Data
public class UserUpdateLevelDto {
    private Long userId;
    private int newLevel;
    private Long newCoins;
}
