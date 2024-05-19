package com.dreamgames.backendengineeringcasestudy.kafka.dto;

import com.dreamgames.backendengineeringcasestudy.user.enums.EnumCountry;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnterTournamentProducerDto {
    Long userId;
    EnumCountry country;
    Long currentTournamentId;
}
