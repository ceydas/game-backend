package com.dreamgames.backendengineeringcasestudy.service.entityservice;

import com.dreamgames.backendengineeringcasestudy.dto.UserDto;
import com.dreamgames.backendengineeringcasestudy.entity.User;
import com.dreamgames.backendengineeringcasestudy.enums.EnumCountry;
import com.dreamgames.backendengineeringcasestudy.enums.EnumLevel;
import com.dreamgames.backendengineeringcasestudy.enums.EnumReward;
import com.dreamgames.backendengineeringcasestudy.exception.UserErrorMessage;
import com.dreamgames.backendengineeringcasestudy.exception.UserException;
import com.dreamgames.backendengineeringcasestudy.repository.UserRepository;
import com.dreamgames.backendengineeringcasestudy.util.CountryPickerUtil;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserEntityService {
    private final UserRepository userRepository;
    public List<User> findAll(){
        return userRepository.findAll();
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

    public User create(){
        User user = new User();
        user.setCurrentLevel(EnumLevel.START.getLevel());
        user.setCurrentCoins(EnumReward.REGISTER.getReward());
        user.setCountry(CountryPickerUtil.getRandomCountry());
        //TODO: See the implementation of 'save' :
        //  if (this.entityInformation.isNew(entity)) {
        //            this.em.persist(entity);
        //            return entity;
        //        } else {
        //            return this.em.merge(entity);
        //        }
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

