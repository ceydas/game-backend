package com.dreamgames.backendengineeringcasestudy.tournament_session.entity;

import com.dreamgames.backendengineeringcasestudy.tournament.entity.Tournament;
import com.dreamgames.backendengineeringcasestudy.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TournamentSession {


    @Id
    private Long tournamentId;

    private Tournament tournament;

    private User user;

    public void setId(Long id) {
        this.tournamentId = id;
    }

    public Long getId() {
        return tournamentId;
    }
}
