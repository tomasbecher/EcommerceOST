package com.ost.ecommerce.user.controller;

import com.ost.ecommerce.user.controller.dto.UserCreateDto;
import com.ost.ecommerce.user.controller.dto.UserDto;
import com.ost.ecommerce.user.controller.dto.UserProfileDto;
import com.ost.ecommerce.user.controller.mapper.UserMapper;
import com.ost.ecommerce.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public ResponseEntity<UserDto> create(@RequestBody UserCreateDto userCreateDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new UserDto(
                        userService.create(
                                userMapper.fromUserCreateDto(userCreateDto)
                        ).getId()
                )
        );
    }

    @GetMapping("/profile")
    public ResponseEntity<UserProfileDto> profile(){
        return ResponseEntity.status(HttpStatus.OK).body(
                userMapper.toUserProfileDto(
                        userService.getUserProfile()
                )
        );
    }
}
