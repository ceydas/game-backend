package com.dreamgames.backendengineeringcasestudy.matchmaking.repository;

import com.dreamgames.backendengineeringcasestudy.matchmaking.entity.Match;
import com.dreamgames.backendengineeringcasestudy.tournament.entity.Tournament;
import com.dreamgames.backendengineeringcasestudy.user.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface MatchRepository extends CrudRepository<Match, Long> {
    public Match findByUserUserId(Long userId);

    public boolean existsByUserAndTournament(User user, Tournament tournament);
}
