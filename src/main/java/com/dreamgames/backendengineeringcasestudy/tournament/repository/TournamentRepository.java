package com.dreamgames.backendengineeringcasestudy.tournament.repository;

import com.dreamgames.backendengineeringcasestudy.tournament.entity.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    public Tournament findByActiveIsTrue();
}
