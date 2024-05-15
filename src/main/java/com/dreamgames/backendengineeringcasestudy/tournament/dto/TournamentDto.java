package com.dreamgames.backendengineeringcasestudy.tournament.dto;

import lombok.Data;

@Data
public class TournamentDto {
    Long id;
    String startTime;
    String endTime;
    boolean isActive;
}
