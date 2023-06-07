package com.test.xeptagon.services;

import com.test.xeptagon.dtos.UserDto;
import com.test.xeptagon.entities.Student;
import com.test.xeptagon.models.CreateUser;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    UserDto createUser(CreateUser payload, UserDetails userDetails, String password);
    Student createStudent(CreateUser payload, UserDetails userDetails, String password);
}
