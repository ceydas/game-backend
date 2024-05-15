package com.dreamgames.backendengineeringcasestudy.matchmaking.enums;

public enum EnumMatchCoins {
    MIN_REQUIRED_COINS_TO_JOIN_TOURNAMENT(1_000L);
    
    public Long coins;


    EnumMatchCoins(Long coins) {
        this.coins = coins;
    }
}
