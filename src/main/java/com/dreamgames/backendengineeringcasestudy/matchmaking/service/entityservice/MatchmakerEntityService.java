package com.dreamgames.backendengineeringcasestudy.matchmaking.service.entityservice;

import com.dreamgames.backendengineeringcasestudy.matchmaking.dto.MatchDto;
import com.dreamgames.backendengineeringcasestudy.matchmaking.dto.MatchGroupDto;
import com.dreamgames.backendengineeringcasestudy.matchmaking.entity.Match;
import com.dreamgames.backendengineeringcasestudy.matchmaking.entity.MatchGroup;
import com.dreamgames.backendengineeringcasestudy.matchmaking.exception.MatchmakerErrorMessage;
import com.dreamgames.backendengineeringcasestudy.matchmaking.exception.MatchmakerException;
import com.dreamgames.backendengineeringcasestudy.matchmaking.repository.MatchGroupRepository;
import com.dreamgames.backendengineeringcasestudy.matchmaking.repository.MatchRepository;
import com.dreamgames.backendengineeringcasestudy.user.entity.User;
import com.dreamgames.backendengineeringcasestudy.user.exception.UserErrorMessage;
import com.dreamgames.backendengineeringcasestudy.user.exception.UserException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatchmakerEntityService {
    private final MatchRepository matchRepository;
    private final MatchGroupRepository matchGroupRepository;

    public MatchGroup createAndSaveMatchGroup(){
        MatchGroup matchGroup = new MatchGroup();
        return matchGroupRepository.save(matchGroup);
    }

    public MatchGroup findMatchGroupByIdWithControl(Long id){

        Optional<MatchGroup> optionalMatchGroup = matchGroupRepository.findById(id);

        MatchGroup matchGroup;
        if (optionalMatchGroup.isPresent()){
            matchGroup = optionalMatchGroup.get();
        } else {
            throw new MatchmakerException(MatchmakerErrorMessage.MATCH_GROUP_NOT_FOUND);
        }
        return matchGroup;
    }

    public MatchGroup saveMatchGroup(MatchGroup matchGroup){
        return matchGroupRepository.save(matchGroup);
    }

    public Match findMatchByIdWithControl(Long id){

        Optional<Match> optionalMatch = matchRepository.findById(id);

        Match match;
        if (optionalMatch.isPresent()){
            match = optionalMatch.get();
        } else {
            throw new MatchmakerException(MatchmakerErrorMessage.MATCH_NOT_FOUND);
        }
        return match;
    }

    public Match saveMatch(Match match){
        return matchRepository.save(match);
    }


    //todo
    /*
    public Match createAndSaveMatch(){

    }

     */





}
