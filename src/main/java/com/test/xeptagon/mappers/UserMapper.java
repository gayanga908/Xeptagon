package com.test.xeptagon.mappers;

import com.test.xeptagon.dtos.UserDto;
import com.test.xeptagon.entities.Class;
import com.test.xeptagon.entities.Student;
import com.test.xeptagon.entities.User;
import com.test.xeptagon.models.CreateUser;

import java.util.Set;

public interface UserMapper {
    User toEntity(CreateUser payload, String password);
    UserDto toDto(User user, String password);
    Student toStudent(CreateUser payload, String password, Set<Class> classes);
}
