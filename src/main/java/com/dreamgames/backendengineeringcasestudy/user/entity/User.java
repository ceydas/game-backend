package com.dreamgames.backendengineeringcasestudy.user.entity;

import com.dreamgames.backendengineeringcasestudy.matchmaking.entity.Match;
import com.dreamgames.backendengineeringcasestudy.tournament_session.entity.TournamentSession;
import com.dreamgames.backendengineeringcasestudy.user.enums.EnumCountry;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "USER")
public class User {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="USER_ID")
    private Long userId;

    @Column(name="USER_COUNTRY", nullable=false, unique=false)
    @Enumerated(EnumType.STRING)
    private EnumCountry country;

    @Column(name="USER_COINS", nullable=false, unique=false)
    private Long currentCoins;

    @Column(name="USER_LEVEL", nullable=false, unique=false)
    private int currentLevel;


    @OneToMany(mappedBy = "user")
    private List<Match> matchList;

    @OneToOne(mappedBy = "user")
    private TournamentSession latestTournamentSession;

}
