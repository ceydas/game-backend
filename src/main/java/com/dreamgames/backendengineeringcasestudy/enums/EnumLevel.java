package com.dreamgames.backendengineeringcasestudy.enums;

public enum EnumLevel {
    START(1),
    END(100);


    private int level;

    EnumLevel(int level){
        this.level = level;
    }

    public int getLevel(){
        return level;
    }

}
