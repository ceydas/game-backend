package com.dreamgames.backendengineeringcasestudy.matchmaking.repository;

import com.dreamgames.backendengineeringcasestudy.matchmaking.entity.Match;
import org.springframework.data.repository.CrudRepository;

public interface MatchRepository extends CrudRepository<Match, Long> {
}
