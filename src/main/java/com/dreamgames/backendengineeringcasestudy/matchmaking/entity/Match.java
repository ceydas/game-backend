package com.dreamgames.backendengineeringcasestudy.matchmaking.entity;

import com.dreamgames.backendengineeringcasestudy.matchmaking.composite.MatchId;
import com.dreamgames.backendengineeringcasestudy.tournament.entity.Tournament;
import com.dreamgames.backendengineeringcasestudy.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "GAME_MATCH")
public class Match {

    @EmbeddedId
    @Column(name = "MATCH_ID")
    private MatchId matchId;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @MapsId("groupId")
    @JoinColumn(name = "GROUP_ID")
    private MatchGroup matchGroup;

    @ManyToOne
    @MapsId("tournamentId")
    @JoinColumn(name = "TOURNAMENT_ID")
    private Tournament tournament;

}
