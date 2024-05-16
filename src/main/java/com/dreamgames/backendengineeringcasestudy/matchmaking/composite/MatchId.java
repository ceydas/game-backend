package com.dreamgames.backendengineeringcasestudy.matchmaking.composite;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class MatchId implements Serializable {
    private Long userId;
    private Long groupId;
    private Long tournamentId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchId matchId = (MatchId) o;
        return Objects.equals(userId, matchId.userId) && Objects.equals(groupId, matchId.groupId) && Objects.equals(tournamentId, matchId.tournamentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, groupId, tournamentId);
    }
}
