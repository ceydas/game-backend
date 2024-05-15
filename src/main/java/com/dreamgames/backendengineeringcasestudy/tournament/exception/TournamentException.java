package com.dreamgames.backendengineeringcasestudy.tournament.exception;

public class TournamentException extends RuntimeException{
    public TournamentException(TournamentErrorMessage tournamentErrorMessage){

        super(tournamentErrorMessage.toString());
    }
}
