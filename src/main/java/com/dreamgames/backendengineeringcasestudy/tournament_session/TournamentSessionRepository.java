package com.dreamgames.backendengineeringcasestudy.tournament_session;

import com.dreamgames.backendengineeringcasestudy.tournament_session.entity.TournamentSession;
import com.dreamgames.backendengineeringcasestudy.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TournamentSessionRepository extends CrudRepository<TournamentSession, Long> {

    public boolean existsByUserUserId(Long userId);

    public TournamentSession findByUserUserId(Long id);

}
