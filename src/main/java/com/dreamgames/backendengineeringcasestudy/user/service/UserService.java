package com.dreamgames.backendengineeringcasestudy.user.service;

import com.dreamgames.backendengineeringcasestudy.leaderboard.dto.GroupLeaderboardDto;
import com.dreamgames.backendengineeringcasestudy.leaderboard.service.LeaderboardService;
import com.dreamgames.backendengineeringcasestudy.matchmaking.entity.Match;
import com.dreamgames.backendengineeringcasestudy.matchmaking.entity.MatchGroup;
import com.dreamgames.backendengineeringcasestudy.matchmaking.service.MatchmakerService;
import com.dreamgames.backendengineeringcasestudy.matchmaking.service.entityservice.MatchmakerEntityService;
import com.dreamgames.backendengineeringcasestudy.tournament.entity.Tournament;
import com.dreamgames.backendengineeringcasestudy.tournament.service.TournamentService;
import com.dreamgames.backendengineeringcasestudy.tournament.service.entityservice.TournamentEntityService;
import com.dreamgames.backendengineeringcasestudy.tournament_session.service.TournamentSessionService;
import com.dreamgames.backendengineeringcasestudy.user.converter.UserDtoConverter;
import com.dreamgames.backendengineeringcasestudy.user.dto.UserDto;
import com.dreamgames.backendengineeringcasestudy.user.dto.UserUpdateLevelRequestDto;
import com.dreamgames.backendengineeringcasestudy.user.dto.UserUpdateResponseDto;
import com.dreamgames.backendengineeringcasestudy.user.entity.User;
import com.dreamgames.backendengineeringcasestudy.user.enums.EnumLevel;
import com.dreamgames.backendengineeringcasestudy.user.enums.EnumReward;
import com.dreamgames.backendengineeringcasestudy.user.exception.UserErrorMessage;
import com.dreamgames.backendengineeringcasestudy.user.exception.UserException;
import com.dreamgames.backendengineeringcasestudy.user.service.entityservice.UserEntityService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserEntityService userEntityService;
    private final LeaderboardService leaderboardService;


    public List<UserDto> findAll() {
        List<User> userList = userEntityService.findAll();
        List<UserDto> userDtoList = UserDtoConverter.INSTANCE.convertToUserDtoList(userList);
        return userDtoList;
    }

    public UserDto createAndSave(){
        User user = userEntityService.createAndSaveUser();
        UserDto userDto = UserDtoConverter.INSTANCE.convertToUserDto(user);
        return userDto;
    }


    public UserDto findById(Long id) {
        User user = userEntityService.findByIdWithControl(id);
        UserDto userDto = UserDtoConverter.INSTANCE.convertToUserDto(user);

        return userDto;
    }


    public void delete(Long id) {

        User user = userEntityService.findByIdWithControl(id);
        userEntityService.delete(user);
    }


    @Transactional
    public UserDto updateLevel(UserUpdateLevelRequestDto updateLevelRequestDto){
        Long userId = updateLevelRequestDto.getUserId();
        int newLevel = updateLevelRequestDto.getNewLevel();
        Long newCoins = updateLevelRequestDto.getNewCoins();

        User user = userEntityService.findByIdWithControl(userId);
        user.setCurrentLevel(newLevel);
        user.setCurrentCoins(newCoins);
        userEntityService.save(user);
        UserDto userDto = UserDtoConverter.INSTANCE.convertToUserDto(user);

        return userDto;
    }

    @Transactional
    public UserDto payCoins(Long id, Long amount){
        User user = userEntityService.findByIdWithControl(id);
        Long currentCoins = user.getCurrentCoins();

        if (currentCoins < amount){
            throw new UserException(UserErrorMessage.INSUFFICIENT_COINS);
        }
        user.setCurrentCoins(currentCoins - amount);
        userEntityService.save(user);
        UserDto userDto = UserDtoConverter.INSTANCE.convertToUserDto(user);
        return userDto;
    }

    public UserDto addCoins(Long id, Long amount){
        User user = userEntityService.findByIdWithControl(id);
        Long currentCoins = user.getCurrentCoins();
        user.setCurrentCoins(currentCoins + amount);
        userEntityService.save(user);
        UserDto userDto = UserDtoConverter.INSTANCE.convertToUserDto(user);
        return userDto;
    }

    @Transactional
    public UserUpdateResponseDto completeLevel(Long id){
        User user = userEntityService.findByIdWithControl(id);

        int currentLevel = user.getCurrentLevel();
        Long currentCoins = user.getCurrentCoins();

        int newLevel = currentLevel + EnumLevel.DEFAULT_LEVEL_UP.getLevel();
        Long newCoins = currentCoins + EnumReward.COMPLETE_LEVEL.getReward();

        user.setCurrentLevel(newLevel);
        user.setCurrentCoins(newCoins);
        userEntityService.save(user);

        //todo
        // If there's an active tournament, update the leaderboard
        leaderboardService.updateUserScore(user);


        UserUpdateResponseDto updateResponseDto = UserUpdateResponseDto.builder()
                .id(id)
                .oldLevel(currentLevel)
                .oldCoins(currentCoins)
                .newCoins(newCoins)
                .newLevel(newLevel).build();

        return updateResponseDto;
    }
    public UserDto getUserDetails(Long id){
        User user = userEntityService.findByIdWithControl(id);
        UserDto userDto = UserDto.builder()
                .userId(id)
                .currentLevel(user.getCurrentLevel())
                .currentCoins(user.getCurrentCoins())
                .country(user.getCountry()).build();

        return userDto;

    }
}

