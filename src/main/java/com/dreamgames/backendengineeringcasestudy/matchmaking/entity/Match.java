package com.dreamgames.backendengineeringcasestudy.matchmaking.entity;

import com.dreamgames.backendengineeringcasestudy.matchmaking.composite.MatchId;
import com.dreamgames.backendengineeringcasestudy.tournament.entity.Tournament;
import com.dreamgames.backendengineeringcasestudy.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "GAME_MATCH")
public class Match {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="MATCH_ID")
    private Long matchId;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    private MatchGroup matchGroup;

    @ManyToOne
    @JoinColumn(name = "TOURNAMENT_ID")
    private Tournament tournament;

    private LocalDateTime createdAt;

}
