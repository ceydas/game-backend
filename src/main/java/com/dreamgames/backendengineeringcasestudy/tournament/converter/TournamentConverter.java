package com.dreamgames.backendengineeringcasestudy.tournament.converter;

import com.dreamgames.backendengineeringcasestudy.tournament.dto.TournamentDto;
import com.dreamgames.backendengineeringcasestudy.tournament.entity.Tournament;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.Map;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN)
public interface TournamentConverter {
    TournamentConverter INSTANCE = Mappers.getMapper(TournamentConverter.class);
    @Mapping(target = "startTime", source = "startTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "endTime", source = "endTime", dateFormat = "yyyy-MM-dd HH:mm:ss" )
    public TournamentDto convertToTournamentDto(Tournament tournament);

    @Mapping(target = "startTime", source = "startTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "endTime", source = "endTime", dateFormat = "yyyy-MM-dd HH:mm:ss" )
    public Tournament convertToTournament(TournamentDto tournamentDto);

}
