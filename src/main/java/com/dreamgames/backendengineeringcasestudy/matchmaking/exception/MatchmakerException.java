package com.dreamgames.backendengineeringcasestudy.matchmaking.exception;

import com.dreamgames.backendengineeringcasestudy.matchmaking.controller.MatchmakerErrorMessage;

public class MatchmakerException extends RuntimeException{

    public MatchmakerException(MatchmakerErrorMessage matchmakerErrorMessage){

            super(matchmakerErrorMessage.toString());
        }

    }
