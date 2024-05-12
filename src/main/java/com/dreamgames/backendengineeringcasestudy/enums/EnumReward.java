package com.dreamgames.backendengineeringcasestudy.enums;

public enum EnumReward {

    REGISTER(5_000L),
    GROUP_FIRST(10_000L),
    GROUP_SECOND(5_000L),
    LEVEL_COMPLETE(25L);

    private Long reward;

    EnumReward(Long reward){
        this.reward = reward;
    }

    public Long getReward(){
        return reward;
    }



}
