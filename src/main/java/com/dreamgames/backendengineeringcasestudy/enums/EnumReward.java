package com.dreamgames.backendengineeringcasestudy.enums;

public enum EnumReward {
    GROUP_FIRST(10_000L),
    GROUP_SECOND(5_000L),
    LEVEL_COMPLETE(25L);

    public Long reward;

    private EnumReward(Long reward){
        this.reward = reward;
    }

    public Long getReward(){
        return reward;
    }



}
