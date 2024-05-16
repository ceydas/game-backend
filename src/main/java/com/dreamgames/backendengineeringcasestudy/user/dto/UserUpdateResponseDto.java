package com.dreamgames.backendengineeringcasestudy.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserUpdateResponseDto {
    Long id;
    Long oldCoins;
    Long newCoins;
    int oldLevel;

    int newLevel;
}
