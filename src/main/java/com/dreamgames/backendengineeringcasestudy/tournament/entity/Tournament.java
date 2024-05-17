package com.dreamgames.backendengineeringcasestudy.tournament.entity;


import com.dreamgames.backendengineeringcasestudy.matchmaking.entity.Match;
import com.dreamgames.backendengineeringcasestudy.tournament_session.entity.TournamentSession;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "TOURNAMENT")

public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TOURNAMENT_START_TIME", nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime startTime;

    @Column(name = "TOURNAMENT_END_TIME", nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime endTime;

    @Column(name = "IS_ACTIVE", columnDefinition = "BOOLEAN DEFAULT TRUE", nullable = false)
    private boolean active;

    @OneToMany(mappedBy = "tournament")
    private Set<Match> matchList;

    @OneToMany(mappedBy = "tournament")
    private List<TournamentSession> latestTournamentSession;

}
