package com.dreamgames.backendengineeringcasestudy.user.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.dreamgames.backendengineeringcasestudy.leaderboard.service.LeaderboardService;
import com.dreamgames.backendengineeringcasestudy.matchmaking.enums.EnumMatchCoins;
import com.dreamgames.backendengineeringcasestudy.matchmaking.service.MatchmakerService;
import com.dreamgames.backendengineeringcasestudy.matchmaking.service.entityservice.MatchmakerEntityService;
import com.dreamgames.backendengineeringcasestudy.tournament.service.TournamentService;
import com.dreamgames.backendengineeringcasestudy.tournament.service.entityservice.TournamentEntityService;
import com.dreamgames.backendengineeringcasestudy.tournament_session.service.TournamentSessionService;
import com.dreamgames.backendengineeringcasestudy.user.dto.UserDto;
import com.dreamgames.backendengineeringcasestudy.user.dto.UserUpdateLevelRequestDto;
import com.dreamgames.backendengineeringcasestudy.user.dto.UserUpdateResponseDto;
import com.dreamgames.backendengineeringcasestudy.user.entity.User;
import com.dreamgames.backendengineeringcasestudy.user.enums.EnumCountry;
import com.dreamgames.backendengineeringcasestudy.user.enums.EnumLevel;
import com.dreamgames.backendengineeringcasestudy.user.enums.EnumReward;
import com.dreamgames.backendengineeringcasestudy.user.exception.UserErrorMessage;
import com.dreamgames.backendengineeringcasestudy.user.exception.UserException;
import com.dreamgames.backendengineeringcasestudy.user.repository.UserRepository;
import com.dreamgames.backendengineeringcasestudy.user.service.entityservice.UserEntityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserServiceTest {

    @Mock
    private UserEntityService userEntityService;
    @Mock
    private  LeaderboardService leaderboardService;


    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findAllUsers_ShouldReturnUserDtoList() {
        User user1 = new User();
        user1.setUserId(1L);
        user1.setCurrentCoins(5000L);
        user1.setCurrentLevel(2);
        user1.setCountry(EnumCountry.TURKEY);
        User user2 = new User();
        user1.setUserId(1L);
        user1.setCurrentCoins(5000L);
        user1.setCurrentLevel(21);
        user1.setCountry(EnumCountry.GERMANY);

        List<User> userList = Arrays.asList(user1, user2);
        when(userEntityService.findAll()).thenReturn(userList);

        List<UserDto> userDtoList = userService.findAll();

        assertEquals(2, userDtoList.size());
        verify(userEntityService, times(1)).findAll();
    }

    @Test
    public void createAndSaveUser_ShouldSaveUser() {
        Long amount = 100L;
        Long userId = 1L;
        User user = new User();
        user.setUserId(userId);
        user.setCurrentCoins(amount);
        user.setCurrentLevel(1);
        user.setCurrentCoins(100L);
        user.setCountry(EnumCountry.UNITED_STATES);
        when(userEntityService.createAndSaveUser()).thenReturn(user);

        UserDto userDto = userService.createAndSave();

        assertNotNull(userDto);
        assertEquals(user.getUserId(), userDto.getUserId());
        verify(userEntityService, times(1)).createAndSaveUser();
    }

    @Test
    public void findUserById_ExistingId_ShouldReturnUser() {
        Long userId = 1L;
        User user = new User();
        user.setUserId(userId);
        user.setCurrentCoins(100L);
        user.setCurrentLevel(1);
        user.setCountry(EnumCountry.TURKEY);
        when(userEntityService.findByIdWithControl(userId)).thenReturn(user);

        UserDto userDto = userService.findById(userId);

        assertNotNull(userDto);
        assertEquals(userId, userDto.getUserId());
        verify(userEntityService, times(1)).findByIdWithControl(userId);
    }

    @Test
    public void deleteUser_ShouldDeleteUser() {
        Long userId = 1L;
        User user = new User();
        user.setUserId(1L);
        user.setCurrentCoins(5000L);
        user.setCurrentLevel(2);
        user.setCountry(EnumCountry.TURKEY);
        when(userEntityService.findByIdWithControl(userId)).thenReturn(user);

        userService.delete(userId);
        verify(userEntityService, times(1)).delete(user);
    }

    @Test
    public void updateUserLevel_ShouldUpdateLevel() {
        UserUpdateLevelRequestDto updateRequest = new UserUpdateLevelRequestDto();
        updateRequest.setUserId(1L);
        updateRequest.setNewLevel(20);
        updateRequest.setNewCoins(5000L);

        Long userId = 1L;
        User user = new User();
        user.setUserId(userId);
        user.setCurrentLevel(1);
        user.setCurrentCoins(150L);
        user.setCountry(EnumCountry.UNITED_STATES);

        when(userEntityService.findByIdWithControl(userId)).thenReturn(user);

        UserDto userDto = userService.updateLevel(updateRequest);

        assertNotNull(userDto);
        assertEquals(updateRequest.getNewLevel(), userDto.getCurrentLevel());
        assertEquals(updateRequest.getNewCoins(), userDto.getCurrentCoins());
        verify(userEntityService, times(1)).save(user);
    }

    @Test
    public void payCoins_SufficientCoins_ShouldDecreaseCoins() {
        Long userId = 1L;
        Long amount = 50L;
        User user = new User();
        user.setUserId(userId);
        user.setCurrentCoins(amount);
        user.setCurrentLevel(1);
        user.setCurrentCoins(100L);
        user.setCountry(EnumCountry.TURKEY);

        when(userEntityService.findByIdWithControl(userId)).thenReturn(user);

        UserDto userDto = userService.payCoins(userId, amount);

        assertNotNull(userDto);
        assertEquals(50L, userDto.getCurrentCoins());
        verify(userEntityService, times(1)).save(user);
    }

    @Test
    public void payCoins_InsufficientCoins_ShouldTrowUserException() {
        Long userId = 1L;
        Long amount = 150L;
        User user = new User();
        user.setUserId(userId);
        user.setCurrentCoins(amount);
        user.setCurrentLevel(1);
        user.setCurrentCoins(100L);
        user.setCountry(EnumCountry.TURKEY);

        when(userEntityService.findByIdWithControl(userId)).thenReturn(user);

        assertThrows(UserException.class, () -> userService.payCoins(userId, amount));
        verify(userEntityService, never()).save(user);
    }

    @Test
    public void addCoins_ValidUser_ShouldAddCoins() {
        Long userId = 1L;
        Long amount = 50L;
        User user = new User();
        user.setUserId(userId);
        user.setCurrentCoins(amount);
        user.setCurrentLevel(1);
        user.setCurrentCoins(100L);
        user.setCountry(EnumCountry.TURKEY);
        when(userEntityService.findByIdWithControl(userId)).thenReturn(user);

        UserDto userDto = userService.addCoins(userId, amount);

        assertNotNull(userDto);
        assertEquals(150L, userDto.getCurrentCoins());
        verify(userEntityService, times(1)).save(user);
    }

    @Test
    public void completeLevel_ShouldAddCoinsAndLevel() {
        Long userId = 1L;
        Long amount = 100L;
        int currentLevel = 1;
        User user = new User();
        user.setUserId(userId);
        user.setCurrentCoins(amount);
        user.setCurrentLevel(currentLevel);
        user.setCurrentCoins(100L);
        user.setCountry(EnumCountry.UNITED_STATES);

        int levelUp = EnumLevel.DEFAULT_LEVEL_UP.getLevel();
        Long increaseCoins = EnumReward.COMPLETE_LEVEL.getReward();
        Long expectedCoins = amount + increaseCoins;
        int expectedLevel = currentLevel + levelUp;

        when(userEntityService.findByIdWithControl(userId)).thenReturn(user);

        UserUpdateResponseDto responseDto = userService.completeLevel(userId);

        assertNotNull(responseDto);
        assertEquals(expectedLevel, responseDto.getNewLevel());
        assertEquals(expectedCoins, responseDto.getNewCoins());
        verify(userEntityService, times(1)).save(user);
    }

    @Test
    public void getUserDetails_ValidUser_ShouldGetUserDetails() {
        Long userId = 1L;
        Long amount = 100L;
        User user = new User();
        user.setUserId(userId);
        user.setCurrentCoins(amount);
        user.setCurrentLevel(1);
        user.setCurrentCoins(100L);
        user.setCountry(EnumCountry.UNITED_STATES);

        when(userEntityService.findByIdWithControl(userId)).thenReturn(user);

        UserDto userDto = userService.getUserDetails(userId);

        assertNotNull(userDto);
        assertEquals(userId, userDto.getUserId());
        assertEquals(1, userDto.getCurrentLevel());
        assertEquals(100L, userDto.getCurrentCoins());
        verify(userEntityService, times(1)).findByIdWithControl(userId);
    }
}
