package com.dreamgames.backendengineeringcasestudy.converter;


import com.dreamgames.backendengineeringcasestudy.dto.UserDto;
import com.dreamgames.backendengineeringcasestudy.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN)
public interface UserDtoConverter {
    UserDtoConverter INSTANCE = Mappers.getMapper(UserDtoConverter.class);

    User convertToUser(UserDto userDto);
    UserDto convertToUserDto(User user);

    List<User> convertToUser(List<UserDto> userDtoList);

    List<UserDto> convertToUserDtoList(List<User> userList);



}
