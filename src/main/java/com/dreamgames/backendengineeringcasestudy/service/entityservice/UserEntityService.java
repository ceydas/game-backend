package com.dreamgames.backendengineeringcasestudy.service.entityservice;

import com.dreamgames.backendengineeringcasestudy.dto.UserDto;
import com.dreamgames.backendengineeringcasestudy.entity.User;
import com.dreamgames.backendengineeringcasestudy.enums.EnumCountry;
import com.dreamgames.backendengineeringcasestudy.exception.UserErrorMessage;
import com.dreamgames.backendengineeringcasestudy.exception.UserException;
import com.dreamgames.backendengineeringcasestudy.repository.UserRepository;
import jakarta.persistence.MappedSuperclass;
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
    public User save(User userEntity){
        return userRepository.save(userEntity);
    }

    public User findByIdWithControl(Long id){

        Optional<User> optionalEntity = userRepository.findById(id);

        User userEntity;
        if (optionalEntity.isPresent()){
            userEntity = optionalEntity.get();
        } else {
            throw new UserException(UserErrorMessage.USER_NOT_FOUND);
        }
        return userEntity;
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}

