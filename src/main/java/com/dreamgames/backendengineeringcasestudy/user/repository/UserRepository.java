package com.dreamgames.backendengineeringcasestudy.user.repository;

import com.dreamgames.backendengineeringcasestudy.user.entity.User;
import com.dreamgames.backendengineeringcasestudy.user.enums.EnumCountry;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    public boolean existsByCountry(EnumCountry country);
}
