package com.dreamgames.backendengineeringcasestudy.tournament.dto;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash
public class TournamentDto {
    Long id;
    String startTime;
    String endTime;
    boolean isActive;
}
