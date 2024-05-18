package com.dreamgames.backendengineeringcasestudy.user.repository;

import com.dreamgames.backendengineeringcasestudy.user.entity.User;
import com.dreamgames.backendengineeringcasestudy.user.enums.EnumCountry;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;


public interface UserRepository extends JpaRepository<User, Long> {
    public boolean existsByCountry(EnumCountry country);
    @Lock(LockModeType.OPTIMISTIC)
    public User findByUserId(Long id);
}
