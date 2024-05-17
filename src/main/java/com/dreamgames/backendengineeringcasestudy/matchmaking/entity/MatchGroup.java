package com.dreamgames.backendengineeringcasestudy.matchmaking.entity;

import com.dreamgames.backendengineeringcasestudy.tournament.entity.Tournament;
import com.dreamgames.backendengineeringcasestudy.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.mapping.Join;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "MATCH_GROUP")
public class MatchGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MATCH_GROUP_ID")
    private Long groupId;

    @OneToMany(mappedBy = "matchGroup")
    private List<Match> matchList;

}
