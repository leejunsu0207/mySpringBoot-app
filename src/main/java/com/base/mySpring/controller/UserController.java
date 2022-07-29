package com.base.mySpring.controller;

import com.base.mySpring.dto.UserDto;
import com.base.mySpring.entity.Account;
import com.base.mySpring.entity.User;
import com.base.mySpring.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public Long create(@RequestBody UserDto userDto){
        return userService.insertUser(userDto);
    }

    @GetMapping("/{email}")
    public UserDto getUser(@PathVariable String email){
        return userService.selectUserByEmail(email);
    }

    @GetMapping("")
    public List<UserDto> getUsers(){
        return userService.selectUsers();
    }


}
