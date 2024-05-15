package com.dreamgames.backendengineeringcasestudy.user.service.entityservice;

import com.dreamgames.backendengineeringcasestudy.user.entity.User;
import com.dreamgames.backendengineeringcasestudy.user.enums.EnumCountry;
import com.dreamgames.backendengineeringcasestudy.user.enums.EnumLevel;
import com.dreamgames.backendengineeringcasestudy.user.enums.EnumReward;
import com.dreamgames.backendengineeringcasestudy.user.exception.UserErrorMessage;
import com.dreamgames.backendengineeringcasestudy.user.exception.UserException;
import com.dreamgames.backendengineeringcasestudy.user.repository.UserRepository;
import com.dreamgames.backendengineeringcasestudy.user.util.CountryPickerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserEntityService {
    private final UserRepository userRepository;
    public List<User> findAll(){
        List<User> userList = userRepository.findAll();
        if (userList.isEmpty()){
            throw new UserException(UserErrorMessage.NO_USERS);
        }
        return userList;
    }

    public boolean existsById(Long id){
        return userRepository.existsById(id);
    }

    public boolean existsByCountry(EnumCountry country){
        return userRepository.existsByCountry(country);
    }
    public User save(User user){
        return userRepository.save(user);
    }

    public static User create(){
        User user = new User();

        user.setCurrentLevel(EnumLevel.START.getLevel());
        user.setCurrentCoins(EnumReward.REGISTER.getReward());
        user.setCountry(CountryPickerUtil.getRandomCountry());

        return user;
    }

    public User createAndSaveUser(){
        User user = create();
        return userRepository.save(user);

    }



    public User findByIdWithControl(Long id){

        Optional<User> optionalUser = userRepository.findById(id);

        User user;
        if (optionalUser.isPresent()){
            user = optionalUser.get();
        } else {
            throw new UserException(UserErrorMessage.USER_NOT_FOUND);
        }
        return user;
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}

