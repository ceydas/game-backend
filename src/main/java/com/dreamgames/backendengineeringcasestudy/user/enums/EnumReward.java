package com.dreamgames.backendengineeringcasestudy.user.enums;

public enum EnumReward {

    REGISTER(5_000L),

    COMPLETE_LEVEL(25L);

    private Long reward;

    EnumReward(Long reward){
        this.reward = reward;
    }

    public Long getReward(){
        return reward;
    }



}
