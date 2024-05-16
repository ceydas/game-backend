package com.dreamgames.backendengineeringcasestudy.user.entity;

import com.dreamgames.backendengineeringcasestudy.tournament.entity.Tournament;
import com.dreamgames.backendengineeringcasestudy.tournament_session.entity.TournamentSession;
import com.dreamgames.backendengineeringcasestudy.user.enums.EnumCountry;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "USER")
public class User {


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long userId;

    @Column(name="USER_COUNTRY", nullable=false, unique=false)
    @Enumerated(EnumType.STRING)
    private EnumCountry country;

    @Column(name="USER_COINS", nullable=false, unique=false)
    private Long currentCoins;

    @Column(name="USER_LEVEL", nullable=false, unique=false)
    private int currentLevel;



}
