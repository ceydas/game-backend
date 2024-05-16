package com.dreamgames.backendengineeringcasestudy.tournament_session.exception;

public enum TournamentSessionErrorMessage{
    USER_HAS_NO_TOURNAMENT_PARTICIPATION("User has not participated in any tournaments");


    String message;
    TournamentSessionErrorMessage(String message) {
        this.message = message;
    }
}
