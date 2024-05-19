package com.dreamgames.backendengineeringcasestudy.kafka;

import com.dreamgames.backendengineeringcasestudy.kafka.dto.EnterTournamentProducerDto;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnterTournamentProducerService {
    private final KafkaTemplate<String,EnterTournamentProducerDto> kafkaTemplate;

    public void send(EnterTournamentProducerDto dto){
        String topic = "enter-tournament";
        String key = dto.getCountry().toString();
        this.kafkaTemplate.send(topic,key, dto);

    }

}
