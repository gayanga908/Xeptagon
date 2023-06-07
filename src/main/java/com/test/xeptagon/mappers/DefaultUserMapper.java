package com.test.xeptagon.mappers;

import com.test.xeptagon.dtos.UserDto;
import com.test.xeptagon.entities.Class;
import com.test.xeptagon.entities.Student;
import com.test.xeptagon.entities.User;
import com.test.xeptagon.models.CreateUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DefaultUserMapper implements UserMapper {

    @Override
    public User toEntity(CreateUser payload, String password) {
        User user = new User();
        user.setUserName(payload.getUserName());
        user.setPassword(DigestUtils.sha256Hex(password));
        user.setUserType(payload.getUserType().toString());
        return user;
    }

    @Override
    public UserDto toDto(User user, String password) {
        return new UserDto(user.getUserName(), password, user.getUserType());
    }

    @Override
    public Student toStudent(CreateUser payload, String password, Set<Class> classes) {
        Student student = new Student();
        student.setUserName(payload.getUserName());
        student.setPassword(DigestUtils.sha256Hex(password));
        student.setUserType(payload.getUserType().toString());
        student.setClasses(classes);
        return student;
    }


}
