package com.dreamgames.backendengineeringcasestudy.user.controller;

import com.dreamgames.backendengineeringcasestudy.user.dto.UserDto;
import com.dreamgames.backendengineeringcasestudy.user.dto.UserUpdateLevelRequestDto;
import com.dreamgames.backendengineeringcasestudy.user.dto.UserUpdateResponseDto;
import com.dreamgames.backendengineeringcasestudy.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping("/user-progress/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        UserDto userDto = userService.findById(id);
        return ResponseEntity.ok(userDto);
    }

    //TODO: Pagination
    @GetMapping("/user-progress/users")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> userDtoList = userService.findAll();
        return ResponseEntity.ok(userDtoList);
    }

    @PostMapping("/user-progress/users/register")
    public ResponseEntity<UserDto> createUser(){
        UserDto userDto = userService.createAndSave();
        return ResponseEntity.ok(userDto);
    }

    @PutMapping("/user-progress/users/")
    public ResponseEntity<UserDto> updateUserLevelAndCoins(@RequestBody UserUpdateLevelRequestDto updateLevelRequestDto){
        UserDto userDto = userService.updateLevel(updateLevelRequestDto);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping("/user-progress/users/{id}/complete-level")
    public ResponseEntity<UserUpdateResponseDto> completeLevel(@PathVariable Long id){
        UserUpdateResponseDto updateResponseDto = userService.completeLevel(id);
        return ResponseEntity.ok(updateResponseDto);
    }

}
