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

    @OneToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", nullable = false, unique = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "TOURNAMENT_ID", referencedColumnName = "id", nullable = false)
    private Tournament tournament;

    @Column(columnDefinition = "INT DEFAULT 0", nullable = false)
    private Long reward;

    private Timestamp joinedAt;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE", nullable = false)
    private boolean didClaimReward;
}
