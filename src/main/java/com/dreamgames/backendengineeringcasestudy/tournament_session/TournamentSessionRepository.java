package com.dreamgames.backendengineeringcasestudy.tournament_session;

import com.dreamgames.backendengineeringcasestudy.tournament_session.entity.TournamentSession;
import org.springframework.data.repository.CrudRepository;

public interface TournamentSessionRepository extends CrudRepository<TournamentSession, Long> {

    public boolean existsByUserId(Long userId);
    public TournamentSession findByUserId(Long userId);

}
