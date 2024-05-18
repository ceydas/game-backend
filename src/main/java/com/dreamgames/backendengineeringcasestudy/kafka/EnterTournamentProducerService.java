package com.dreamgames.backendengineeringcasestudy.kafka;

import com.dreamgames.backendengineeringcasestudy.kafka.dto.EnterTournamentProducerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnterTournamentProducerService {
    private final KafkaTemplate<String,Object> kafkaTemplate;

    public void send(EnterTournamentProducerDto dto){
        this.kafkaTemplate.send("enter-tournament",dto.toString());
        System.out.println("Message is sent to kafka ");
    }

}
