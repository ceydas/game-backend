package com.dreamgames.backendengineeringcasestudy.matchmaking.service;

import com.dreamgames.backendengineeringcasestudy.kafka.dto.EnterTournamentProducerDto;
import com.dreamgames.backendengineeringcasestudy.matchmaking.dto.MatchGroupDto;
import com.dreamgames.backendengineeringcasestudy.matchmaking.service.entityservice.MatchmakerEntityService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatchmakerService {
    private final MatchmakerEntityService matchmakerEntityService;

    @KafkaListener(topics = {"enter-tournament"}, groupId = "app-1")
    public void consumeEnterTournamentTicket(EnterTournamentProducerDto dto){
        System.out.println(dto);
    }

}
