package com.dreamgames.backendengineeringcasestudy.matchmaking.controller;

public enum MatchmakerErrorMessage {
    MINIMUM_REQUIREMENTS_NOT_MET("User doesn't meet the minimum criteria to join the tournament.");


    public String message;
    MatchmakerErrorMessage(String message) {
        this.message = message;
    }
}
