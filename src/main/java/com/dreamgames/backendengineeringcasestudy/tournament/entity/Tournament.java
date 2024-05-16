package com.dreamgames.backendengineeringcasestudy.tournament.entity;

import com.dreamgames.backendengineeringcasestudy.tournament_session.entity.TournamentSession;
import com.dreamgames.backendengineeringcasestudy.tournament_session.enums.EnumTournamentReward;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Tournament {

    @Id
    private Long id;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private boolean active;


}
