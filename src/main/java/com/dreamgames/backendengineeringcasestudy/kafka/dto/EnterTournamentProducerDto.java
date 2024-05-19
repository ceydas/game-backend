package com.dreamgames.backendengineeringcasestudy.kafka.dto;

import com.dreamgames.backendengineeringcasestudy.user.enums.EnumCountry;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnterTournamentProducerDto {
    private Long userId;
    private EnumCountry country;
    private Long currentTournamentId;
}
