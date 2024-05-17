package com.dreamgames.backendengineeringcasestudy.matchmaking.dto;

import com.dreamgames.backendengineeringcasestudy.matchmaking.enums.EnumMatchCapacity;
import com.dreamgames.backendengineeringcasestudy.matchmaking.exception.MatchmakerErrorMessage;
import com.dreamgames.backendengineeringcasestudy.matchmaking.exception.MatchmakerException;
import lombok.Data;

@Data
public class MatchGroupDto {
    private Long[] userIds;

    public MatchGroupDto(Long[] userIds) {
        validateMatchGroupCapacity(userIds);
        this.userIds = userIds;
    }

    public Long[] getUserIds() {
        return userIds;
    }

    public void setUserIds(Long[] userIds) {
        validateMatchGroupCapacity(userIds);
        this.userIds = userIds;
    }

    public static void validateMatchGroupCapacity(Long[] userIds){
        if (userIds.length != EnumMatchCapacity.TOURNAMENT_GROUP_CAPACITY.getCapacity()) {
            throw new MatchmakerException(MatchmakerErrorMessage.MATCH_GROUP_CAPACITY_INVALID);
        }
    }
}


