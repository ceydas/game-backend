package com.dreamgames.backendengineeringcasestudy.matchmaking.exception;

public enum MatchmakerErrorMessage {
    NOT_ENOUGH_COINS("User doesn't have enough coins to join the tournament"),

    LEVEL_NOT_SUFFICIENT("User level is insufficient to join the tournament"),

    MATCH_GROUP_CAPACITY_INVALID("Invalid match group capacity."),

    USER_NOT_CLAIMED_REWARD("User has not claimed pending reward, they cannot enter a new tournament."),

    MATCH_GROUP_NOT_FOUND("Match group not found"),
    MATCH_NOT_FOUND("Match not found")
    ;


    public String message;
    MatchmakerErrorMessage(String message) {
        this.message = message;
    }
}
