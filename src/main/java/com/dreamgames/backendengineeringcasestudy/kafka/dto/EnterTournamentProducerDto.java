package com.dreamgames.backendengineeringcasestudy.kafka.dto;

import com.dreamgames.backendengineeringcasestudy.user.enums.EnumCountry;
import lombok.Data;

@Data
public class EnterTournamentProducerDto {
    Long userId;
    EnumCountry country;
    Long currentTournamentId;
}
