package com.dreamgames.backendengineeringcasestudy.tournament.exception;

public enum TournamentErrorMessage {
    TOURNAMENT_START_FAILURE("Tournament creation failure"),
    NO_ACTIVE_TOURNAMENT("No active tournament"),

    TOURNAMENT_DOES_NOT_EXIST("Tournament with id does not exist"),

    TOURNAMENT_ALREADY_ACTIVE("Tournament already active");

    String message;

    TournamentErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
