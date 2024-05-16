package com.dreamgames.backendengineeringcasestudy.tournament.entity;


import com.dreamgames.backendengineeringcasestudy.matchmaking.entity.Match;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "TOURNAMENT")

public class Tournament {

    @Id
    private Long id;

    @Column(name = "TOURNAMENT_START_TIME")
    private LocalDateTime startTime;

    @Column(name = "TOURNAMENT_END_TIME")
    private LocalDateTime endTime;

    private boolean active;
    @OneToMany(mappedBy = "tournament")
    private Set<Match> matchList;


}
