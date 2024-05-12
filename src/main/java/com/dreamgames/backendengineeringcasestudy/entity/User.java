package com.dreamgames.backendengineeringcasestudy.entity;

import com.dreamgames.backendengineeringcasestudy.enums.EnumCountry;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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
