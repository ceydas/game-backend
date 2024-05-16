package com.dreamgames.backendengineeringcasestudy.tournament_session.exception;

import com.dreamgames.backendengineeringcasestudy.user.exception.UserErrorMessage;

public class TournamentSessionException extends RuntimeException{
    public TournamentSessionException(TournamentSessionErrorMessage tournamentSessionErrorMessage){

        super(tournamentSessionErrorMessage.toString());
    }
}
