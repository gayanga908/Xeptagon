package com.test.xeptagon.controllers;

import com.test.xeptagon.dtos.UserDto;
import com.test.xeptagon.models.CreateUser;
import com.test.xeptagon.services.UserService;
import com.test.xeptagon.utils.PasswordGenerateUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/users")
    public UserDto createUser(@RequestBody CreateUser payload, HttpServletRequest httpServletRequest) {
        UserDetails userDetails = (UserDetails) httpServletRequest.getAttribute("user");
        return userService.createUser(payload, userDetails,
                PasswordGenerateUtil.generateRandomPassword());
    }
}
