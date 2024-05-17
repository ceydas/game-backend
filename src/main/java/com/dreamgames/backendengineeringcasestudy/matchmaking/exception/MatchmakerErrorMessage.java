package com.dreamgames.backendengineeringcasestudy.matchmaking.exception;

public enum MatchmakerErrorMessage {
    MINIMUM_REQUIREMENTS_NOT_MET("User doesn't meet the minimum criteria to join the tournament."),

    MATCH_GROUP_CAPACITY_INVALID("Invalid match group capacity.");


    public String message;
    MatchmakerErrorMessage(String message) {
        this.message = message;
    }
}
