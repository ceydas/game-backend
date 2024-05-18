package com.dreamgames.backendengineeringcasestudy.user.service.entityservice;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;

import com.dreamgames.backendengineeringcasestudy.user.entity.User;
import com.dreamgames.backendengineeringcasestudy.user.enums.EnumCountry;
import com.dreamgames.backendengineeringcasestudy.user.enums.EnumLevel;
import com.dreamgames.backendengineeringcasestudy.user.enums.EnumReward;
import com.dreamgames.backendengineeringcasestudy.user.exception.UserErrorMessage;
import com.dreamgames.backendengineeringcasestudy.user.exception.UserException;
import com.dreamgames.backendengineeringcasestudy.user.repository.UserRepository;
import com.dreamgames.backendengineeringcasestudy.user.util.CountryPickerUtil;
import org.apache.zookeeper.Op;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserEntityServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserEntityService userEntityService;

    private User user;

    @BeforeEach
    void setUp() {

        User user = new User();
        user.setUserId(1L);
        user.setCurrentLevel(EnumLevel.START.getLevel());
        user.setCurrentCoins(EnumReward.REGISTER.getReward());
        user.setCountry(CountryPickerUtil.getRandomCountry());
    }

    @Test
    void findAllUsers_SingleUser_ShouldReturnSingleElement() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));

        List<User> result = userEntityService.findAll();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void findAll_EmptyUserList_ShouldThrowUserException() {
        when(userRepository.findAll()).thenReturn(Arrays.asList());

        UserException exception = assertThrows(UserException.class, () -> {
            userEntityService.findAll();
        });

        assertEquals(UserErrorMessage.NO_USERS.toString(), exception.getMessage());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void existsById_ValidId_ShouldReturnTrue() {
        Long id = 1L;
        when(userRepository.existsById(id)).thenReturn(true);

        boolean result = userEntityService.existsById(id);

        assertTrue(result);
        verify(userRepository, times(1)).existsById(id);
    }

    @Test
    void existsByCountry_ValidCountry_ShouldReturnTrue() {
        EnumCountry country = EnumCountry.FRANCE;
        when(userRepository.existsByCountry(country)).thenReturn(true);

        boolean result = userEntityService.existsByCountry(country);

        assertTrue(result);
        verify(userRepository, times(1)).existsByCountry(country);
    }

    @Test
    void saveUser_ValidUser_ShouldReturnSavedUser() {
        User user = mock(User.class);
        when(userRepository.save(user)).thenReturn(user);

        User result = userEntityService.save(user);

        assertNotNull(result);
        assertEquals(user, result);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void createAndSaveUser_ValidUser_ShouldReturnSavedUser() {
        User user = mock(User.class);
        user.setCurrentLevel(EnumLevel.START.getLevel());
        user.setCurrentCoins(EnumReward.REGISTER.getReward());
        user.setCountry(CountryPickerUtil.getRandomCountry());

        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userEntityService.createAndSaveUser();

        assertNotNull(result);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void findByIdWithControl_ExistingId_ShouldReturnUser() {
        User user = mock(User.class);
        // Mocking findById() to return an Optional<User>
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userEntityService.findByIdWithControl(1L);

        assertNotNull(result);
        assertEquals(user, result);
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void findByIdWithControl_NonExistingUser_ShouldThrowUserException() {
        Long id = 1L;
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        UserException exception = assertThrows(UserException.class, () -> {
            userEntityService.findByIdWithControl(id);
        });

        assertEquals(UserErrorMessage.USER_NOT_FOUND.toString(), exception.getMessage());
        verify(userRepository, times(1)).findById(id);
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userRepository).delete(user);

        userEntityService.delete(user);

        verify(userRepository, times(1)).delete(user);
    }
}

