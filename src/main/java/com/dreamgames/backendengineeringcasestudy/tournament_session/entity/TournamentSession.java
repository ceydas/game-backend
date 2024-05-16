package com.dreamgames.backendengineeringcasestudy.tournament_session.entity;

import com.dreamgames.backendengineeringcasestudy.tournament.entity.Tournament;
import com.dreamgames.backendengineeringcasestudy.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class TournamentSession {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tournamentSessionId;

    private Long userId;

    private Long latestTournamentId;

    private int levelsAdvanced;

    private Timestamp joinedAt;

    private boolean didClaimReward;
}
