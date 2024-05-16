package com.dreamgames.backendengineeringcasestudy.user.entity;

import com.dreamgames.backendengineeringcasestudy.matchmaking.entity.Match;
import com.dreamgames.backendengineeringcasestudy.user.enums.EnumCountry;
import jakarta.persistence.*;

import java.util.List;

@Entity
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

    @OneToMany(mappedBy = "user")
    private List<Match> matchList;

    public Long getUserId() {
        return userId;
    }

    protected void setUserId(Long userId) {
        this.userId = userId;
    }


    public EnumCountry getCountry() {
        return country;
    }

    public void setCountry(EnumCountry country) {
        this.country = country;
    }


    public Long getCurrentCoins() {
        return currentCoins;
    }

    public void setCurrentCoins(Long currentCoins) {
        this.currentCoins = currentCoins;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }



}
