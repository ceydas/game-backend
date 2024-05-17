package com.dreamgames.backendengineeringcasestudy.matchmaking.enums;

public enum EnumMatchCapacity {

    TOURNAMENT_GROUP_CAPACITY(5);

    int capacity;


    EnumMatchCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}
