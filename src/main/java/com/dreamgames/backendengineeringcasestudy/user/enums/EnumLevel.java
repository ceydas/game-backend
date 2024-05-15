package com.dreamgames.backendengineeringcasestudy.user.enums;

public enum EnumLevel {
    START(1),
    DEFAULT_LEVEL_UP(1);


    private int level;

    EnumLevel(int level){
        this.level = level;
    }

    public int getLevel(){
        return level;
    }

}
