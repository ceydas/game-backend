package com.dreamgames.backendengineeringcasestudy.service;

import com.dreamgames.backendengineeringcasestudy.controller.UserController;
import com.dreamgames.backendengineeringcasestudy.converter.UserDtoConverter;
import com.dreamgames.backendengineeringcasestudy.dto.UserDto;
import com.dreamgames.backendengineeringcasestudy.dto.UserUpdateLevelRequestDto;
import com.dreamgames.backendengineeringcasestudy.entity.User;
import com.dreamgames.backendengineeringcasestudy.exception.UserErrorMessage;
import com.dreamgames.backendengineeringcasestudy.exception.UserException;
import com.dreamgames.backendengineeringcasestudy.service.entityservice.UserEntityService;
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
}
