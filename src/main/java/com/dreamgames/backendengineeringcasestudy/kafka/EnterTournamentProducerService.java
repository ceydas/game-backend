package com.dreamgames.backendengineeringcasestudy.kafka;

import com.dreamgames.backendengineeringcasestudy.kafka.dto.EnterTournamentProducerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnterTournamentProducerService {
    private final KafkaTemplate<String,EnterTournamentProducerDto> kafkaTemplate;

    public void send(EnterTournamentProducerDto dto){
            this.kafkaTemplate.send("enter-tournament",dto);

    }

}
