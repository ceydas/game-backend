package com.dreamgames.backendengineeringcasestudy.matchmaking.exception;

public class MatchmakerException extends RuntimeException{

    public MatchmakerException(MatchmakerErrorMessage matchmakerErrorMessage){

            super(matchmakerErrorMessage.toString());
        }

    }
