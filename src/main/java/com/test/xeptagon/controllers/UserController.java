package com.test.xeptagon.controllers;

import com.test.xeptagon.dtos.UserDto;
import com.test.xeptagon.models.CreateUser;
import com.test.xeptagon.services.UserService;
import com.test.xeptagon.utils.PasswordGenerateUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@Tag(name = "Users", description = "User management APIs")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUser payload, HttpServletRequest httpServletRequest) {
        UserDetails userDetails = (UserDetails) httpServletRequest.getAttribute("user");
        return new ResponseEntity<>(userService.createUser(payload, userDetails,
                PasswordGenerateUtil.generateRandomPassword()), HttpStatus.CREATED);
    }
}
