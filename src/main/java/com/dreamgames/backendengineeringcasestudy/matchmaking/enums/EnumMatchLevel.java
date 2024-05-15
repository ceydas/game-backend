package com.dreamgames.backendengineeringcasestudy.matchmaking.enums;

public enum EnumMatchLevel {
    MIN_REQUIRED_LEVEL_TO_JOIN_TOURNAMENT(20);

    public int level;


    EnumMatchLevel(int level) {
        this.level = level;
    }
}
