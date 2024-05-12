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

    public UserDto create(){
        //TODO: Who's responsible for creating an instance of 'user'? -> Probably userentityservice?
        User user = userEntityService.create();
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



    public UserDto updateLevel(UserUpdateLevelRequestDto userUpdateLevelRequestDto) {

        Long userId = userUpdateLevelRequestDto.getUserId();

        boolean doesExist = userEntityService.existsById(userId);
        if (!doesExist){
            throw new UserException(UserErrorMessage.USER_NOT_FOUND);
        }

        //TODO: add the mapping, level update request dto does not include the country field.
        //User user = UserDtoConverter.INSTANCE.convertToUser(userUpdateLevelRequestDto);
        //user = userEntityService.save(user);

        //UserDto userDto = UserDtoConverter.INSTANCE.convertToUserDto(user);

        return userDto;
    }
}
