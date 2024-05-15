package com.dreamgames.backendengineeringcasestudy.user.service;

import com.dreamgames.backendengineeringcasestudy.user.converter.UserDtoConverter;
import com.dreamgames.backendengineeringcasestudy.user.dto.UserDto;
import com.dreamgames.backendengineeringcasestudy.user.dto.UserUpdateLevelRequestDto;
import com.dreamgames.backendengineeringcasestudy.user.entity.User;
import com.dreamgames.backendengineeringcasestudy.user.enums.EnumLevel;
import com.dreamgames.backendengineeringcasestudy.user.exception.UserErrorMessage;
import com.dreamgames.backendengineeringcasestudy.user.exception.UserException;
import com.dreamgames.backendengineeringcasestudy.user.service.entityservice.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserEntityService userEntityService;

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

    public UserDto getCoins(Long id, Long amount){
        User user = userEntityService.findByIdWithControl(id);
        Long currentCoins = user.getCurrentCoins();
        user.setCurrentCoins(currentCoins + amount);
        userEntityService.save(user);
        UserDto userDto = UserDtoConverter.INSTANCE.convertToUserDto(user);
        return userDto;
    }

    public UserDto levelUp(Long id, int levels){
        User user = userEntityService.findByIdWithControl(id);
        int currentLevel = user.getCurrentLevel();
        user.setCurrentLevel(currentLevel + levels);
        userEntityService.save(user);
        UserDto userDto = UserDtoConverter.INSTANCE.convertToUserDto(user);
        return userDto;
    }

    public UserDto levelUp(Long id){
        User user = userEntityService.findByIdWithControl(id);
        int currentLevel = user.getCurrentLevel();
        user.setCurrentLevel(currentLevel + EnumLevel.DEFAULT_LEVEL_UP.getLevel());
        userEntityService.save(user);
        UserDto userDto = UserDtoConverter.INSTANCE.convertToUserDto(user);
        return userDto;
    }
    public UserDto getUserDetails(Long id){
        User user = userEntityService.findByIdWithControl(id);
        UserDto userDto = UserDto.builder()
                .userId(id)
                .currentLevel(user.getCurrentLevel())
                .currentCoins(user.getCurrentCoins())
                .country(user.getCountry().toString()).build();

        return userDto;

    }
}

