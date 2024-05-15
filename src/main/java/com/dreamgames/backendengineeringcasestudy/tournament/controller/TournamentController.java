package com.dreamgames.backendengineeringcasestudy.tournament.controller;

import com.dreamgames.backendengineeringcasestudy.tournament.dto.TournamentDto;
import com.dreamgames.backendengineeringcasestudy.tournament.service.TournamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TournamentController {
    private final TournamentService tournamentService;
    @GetMapping("/tournament/active")
    public ResponseEntity<TournamentDto> getActive(){
        TournamentDto tournamentDto = tournamentService.findActive();
        if (tournamentDto == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tournamentDto);
    }

}
