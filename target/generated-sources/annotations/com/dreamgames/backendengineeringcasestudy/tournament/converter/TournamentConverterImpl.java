package com.dreamgames.backendengineeringcasestudy.tournament.converter;

import com.dreamgames.backendengineeringcasestudy.tournament.dto.TournamentDto;
import com.dreamgames.backendengineeringcasestudy.tournament.entity.Tournament;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-15T02:59:08+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
public class TournamentConverterImpl implements TournamentConverter {

    private final DateTimeFormatter dateTimeFormatter_yyyy_MM_dd_HH_mm_ss_11333195168 = DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss" );

    @Override
    public TournamentDto convertToTournamentDto(Tournament tournament) {
        if ( tournament == null ) {
            return null;
        }

        TournamentDto tournamentDto = new TournamentDto();

        if ( tournament.getStartTime() != null ) {
            tournamentDto.setStartTime( dateTimeFormatter_yyyy_MM_dd_HH_mm_ss_11333195168.format( tournament.getStartTime() ) );
        }
        if ( tournament.getEndTime() != null ) {
            tournamentDto.setEndTime( dateTimeFormatter_yyyy_MM_dd_HH_mm_ss_11333195168.format( tournament.getEndTime() ) );
        }
        tournamentDto.setId( tournament.getId() );
        tournamentDto.setActive( tournament.isActive() );

        return tournamentDto;
    }

    @Override
    public Tournament convertToTournament(TournamentDto tournamentDto) {
        if ( tournamentDto == null ) {
            return null;
        }

        Tournament tournament = new Tournament();

        if ( tournamentDto.getStartTime() != null ) {
            tournament.setStartTime( LocalDateTime.parse( tournamentDto.getStartTime(), dateTimeFormatter_yyyy_MM_dd_HH_mm_ss_11333195168 ) );
        }
        if ( tournamentDto.getEndTime() != null ) {
            tournament.setEndTime( LocalDateTime.parse( tournamentDto.getEndTime(), dateTimeFormatter_yyyy_MM_dd_HH_mm_ss_11333195168 ) );
        }
        tournament.setActive( tournamentDto.isActive() );
        tournament.setId( tournamentDto.getId() );

        return tournament;
    }
}
