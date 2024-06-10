package com.dreamgames.backendengineeringcasestudy.matchmaking.service;

import com.dreamgames.backendengineeringcasestudy.kafka.dto.EnterTournamentProducerDto;
import com.dreamgames.backendengineeringcasestudy.matchmaking.entity.Match;
import com.dreamgames.backendengineeringcasestudy.matchmaking.entity.MatchGroup;
import com.dreamgames.backendengineeringcasestudy.matchmaking.enums.EnumMatchCapacity;
import com.dreamgames.backendengineeringcasestudy.matchmaking.enums.EnumMatchCoins;
import com.dreamgames.backendengineeringcasestudy.matchmaking.service.entityservice.MatchmakerEntityService;
import com.dreamgames.backendengineeringcasestudy.tournament.entity.Tournament;
import com.dreamgames.backendengineeringcasestudy.tournament.service.entityservice.TournamentEntityService;
import com.dreamgames.backendengineeringcasestudy.tournament_session.entity.TournamentSession;
import com.dreamgames.backendengineeringcasestudy.tournament_session.service.entityservice.TournamentSessionEntityService;
import com.dreamgames.backendengineeringcasestudy.user.entity.User;
import com.dreamgames.backendengineeringcasestudy.user.enums.EnumCountry;
import com.dreamgames.backendengineeringcasestudy.user.service.UserService;
import com.dreamgames.backendengineeringcasestudy.user.service.entityservice.UserEntityService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

@Service
@RequiredArgsConstructor
public class MatchmakerService {
    private final MatchmakerEntityService matchmakerEntityService;
    private final TournamentEntityService tournamentEntityService;
    private final UserEntityService userEntityService;
    private final TournamentSessionEntityService tournamentSessionEntityService;
    private final UserService userService;
    private final int capacity = EnumMatchCapacity.TOURNAMENT_GROUP_CAPACITY.getCapacity();

    private Map<EnumCountry, ArrayList<EnterTournamentProducerDto>> countryUserMap = new HashMap<EnumCountry, ArrayList<EnterTournamentProducerDto>>(capacity);
    private final ReentrantLock lock = new ReentrantLock();

    /** Thread */
    @KafkaListener(topics = {"enter-tournament"}, groupId = "app-1")
    @Transactional(rollbackOn = Exception.class)
    public void consumeEnterTournamentTicket(EnterTournamentProducerDto dto,
                                             @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                                             @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                             @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts){


        EnumCountry currentCountry = dto.getCountry(); // each thread processes a different country since partitioning is done via country.

        lock.lock();
        try {
            System.out.println("Consuming: " + dto + " inside partition " + partition + " and thread " + Thread.currentThread().getName());
            // Add country to the map.
            countryUserMap = addToCountryUserMap(currentCountry, dto);
            // Check if we have one user from each country
            if (countryUserMap.size() == capacity) {
                enterTournamentUsersFromDifferentCountries(countryUserMap);
            }
        } finally {
            lock.unlock();
        }

    }

    @Transactional(rollbackOn = Exception.class)
    public void enterTournamentUsersFromDifferentCountries(Map<EnumCountry, ArrayList<EnterTournamentProducerDto>> userList) {

        System.out.println("Processing users: " + userList);

        HashMap<EnumCountry, EnterTournamentProducerDto> dtosToRemove = new HashMap<>();

        // Create new MatchGroup before adding in the users
        MatchGroup matchGroup = matchmakerEntityService.createAndSaveMatchGroup();

        for (Map.Entry<EnumCountry, ArrayList<EnterTournamentProducerDto>> entry : userList.entrySet()) {

            EnterTournamentProducerDto dto = entry.getValue().get(0);

            Long userId = dto.getUserId();
            EnumCountry country = dto.getCountry();

            System.out.println("Processing user: " + userId + " dto " + dto);

            User user = userEntityService.findByIdWithControl(userId);

            Tournament tournament = tournamentEntityService.findByIdWithControl(dto.getCurrentTournamentId());

            // Save new Match
            Match match = initMatchGroup(matchGroup, user, tournament);
            matchmakerEntityService.saveMatch(match);

            // Add new tournament_session
            TournamentSession tournamentSession = initTournamentSession(user, tournament);
            tournamentSessionEntityService.save(tournamentSession);

            userService.payCoins(dto.getUserId(), EnumMatchCoins.MIN_REQUIRED_COINS_TO_JOIN_TOURNAMENT.coins);

            dtosToRemove.put(country, dto);

            System.out.println("Finished processing user: " + userId + "!");

        }

        removeConsumedDtoFromCountryUserMap(dtosToRemove);


    }

    private static Match initMatchGroup(MatchGroup matchGroup, User user, Tournament tournament) {
        Match match = new Match();
        match.setTournament(tournament);
        match.setUser(user);
        match.setMatchGroup(matchGroup);
        match.setCreatedAt(LocalDateTime.now());
        return match;
    }

    private static TournamentSession initTournamentSession(User user, Tournament tournament) {
        TournamentSession tournamentSession = new TournamentSession();
        tournamentSession.setTournament(tournament);
        tournamentSession.setUser(user);
        tournamentSession.setReward(0L);
        tournamentSession.setJoinedAt(LocalDateTime.now());
        tournamentSession.setDidClaimReward(false);
        return tournamentSession;
    }

    public Map<EnumCountry, ArrayList<EnterTournamentProducerDto>> addToCountryUserMap(EnumCountry country, EnterTournamentProducerDto dto) {
        // Check if the country key exists in the map
        if (countryUserMap.containsKey(country)) {
            // Get the existing list and add the new DTO element
            countryUserMap.get(country).add(dto);
        } else {
            // Create a new list, add the DTO element, and put it in the map
            ArrayList<EnterTournamentProducerDto> newList = new ArrayList<>();
            newList.add(dto);
            countryUserMap.put(country, newList);
        }

        return countryUserMap;
    }

    private Map<EnumCountry, ArrayList<EnterTournamentProducerDto>> removeConsumedDtoFromCountryUserMap(HashMap<EnumCountry, EnterTournamentProducerDto> map) {
        // Collect countries to be removed after iteration
        List<EnumCountry> countriesToRemove = new ArrayList<>();

        for (Map.Entry<EnumCountry, EnterTournamentProducerDto> entry : map.entrySet()) {
            EnumCountry country = entry.getKey();
            EnterTournamentProducerDto dto = entry.getValue();

            // Get the list of DTOs for the current country
            ArrayList<EnterTournamentProducerDto> dtoList = countryUserMap.get(country);

            // Remove the processed DTO
            boolean removed = dtoList.remove(dto);

            // If the list is empty after removal, mark the country for removal from the map
            if (removed && dtoList.isEmpty()) {
                countriesToRemove.add(country);
            } else if (removed) {
                // Update the map with the modified list
                countryUserMap.put(country, dtoList);
            }
        }

        // Remove countries with empty lists from the map
        for (EnumCountry country : countriesToRemove) {
            countryUserMap.remove(country);
        }

        return countryUserMap;
    }

    public Match findMatchByUserId(Long id){
        return matchmakerEntityService.findByUserIdWithControl(id);
    }


    public boolean existsByUserAndTournament(User user, Tournament tournament){
        return matchmakerEntityService.existsByUserAndTournament(user, tournament);
    }
}
