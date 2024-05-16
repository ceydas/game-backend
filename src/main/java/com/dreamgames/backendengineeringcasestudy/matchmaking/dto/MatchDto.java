package com.dreamgames.backendengineeringcasestudy.matchmaking.dto;

import lombok.Data;

@Data
public class MatchDto {
    Long matchId;
    Long groupId;
    Long[] userIds;
}
