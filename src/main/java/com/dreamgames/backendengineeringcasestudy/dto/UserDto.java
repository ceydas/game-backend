package com.dreamgames.backendengineeringcasestudy.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long userId;
    private String country;
    private Long currentCoins;
    private int currentLevel;
}
