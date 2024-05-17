package com.dreamgames.backendengineeringcasestudy.matchmaking.service.entityservice;

import com.dreamgames.backendengineeringcasestudy.matchmaking.dto.MatchGroupDto;
import com.dreamgames.backendengineeringcasestudy.matchmaking.entity.MatchGroup;
import com.dreamgames.backendengineeringcasestudy.matchmaking.repository.MatchGroupRepository;
import com.dreamgames.backendengineeringcasestudy.matchmaking.repository.MatchRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatchmakerEntityService {
    private final MatchRepository matchRepository;
    private final MatchGroupRepository matchGroupRepository;

    /*
    @Transactional
    public MatchGroup saveMatchGroup(MatchGroupDto matchGroupDto){
        Long[] userIds = matchGroupDto.getUserIds();
        for (Long userId : userIds){

        }
    }

     */

}
