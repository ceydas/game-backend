package com.dreamgames.backendengineeringcasestudy.user.converter;

import com.dreamgames.backendengineeringcasestudy.user.dto.UserDto;
import com.dreamgames.backendengineeringcasestudy.user.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-19T01:30:38+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
public class UserDtoConverterImpl implements UserDtoConverter {

    @Override
    public User convertToUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setUserId( userDto.getUserId() );
        user.setCountry( userDto.getCountry() );
        user.setCurrentCoins( userDto.getCurrentCoins() );
        user.setCurrentLevel( userDto.getCurrentLevel() );

        return user;
    }

    @Override
    public UserDto convertToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.userId( user.getUserId() );
        userDto.country( user.getCountry() );
        userDto.currentCoins( user.getCurrentCoins() );
        userDto.currentLevel( user.getCurrentLevel() );

        return userDto.build();
    }

    @Override
    public List<User> convertToUser(List<UserDto> userDtoList) {
        if ( userDtoList == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( userDtoList.size() );
        for ( UserDto userDto : userDtoList ) {
            list.add( convertToUser( userDto ) );
        }

        return list;
    }

    @Override
    public List<UserDto> convertToUserDtoList(List<User> userList) {
        if ( userList == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( userList.size() );
        for ( User user : userList ) {
            list.add( convertToUserDto( user ) );
        }

        return list;
    }
}
