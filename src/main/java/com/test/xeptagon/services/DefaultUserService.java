package com.test.xeptagon.services;

import com.google.common.collect.ImmutableMap;
import com.test.xeptagon.dtos.UserDto;
import com.test.xeptagon.entities.Student;
import com.test.xeptagon.entities.User;
import com.test.xeptagon.enums.UserType;
import com.test.xeptagon.exceptions.NotAllowedToCreateUserException;
import com.test.xeptagon.exceptions.UserAlreadyExistException;
import com.test.xeptagon.exceptions.UserCreationFailedException;
import com.test.xeptagon.mappers.UserMapper;
import com.test.xeptagon.models.CreateUser;
import com.test.xeptagon.repositories.UserRepository;
import com.test.xeptagon.utils.UserUtil;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DefaultUserService implements UserService {

    private static final Map<UserType, List<UserType>> userTypeAllowedToCreateUsers = ImmutableMap.of(
            UserType.ADMINISTRATOR, List.of(UserType.INSTRUCTOR),
            UserType.INSTRUCTOR, List.of(UserType.STUDENT)
    );
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public DefaultUserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto createUser(CreateUser payload, UserDetails userDetails,
                              String password) {
        User savedUser;
        allowedToCreateUserOrThrow(userDetails, payload.getUserType());
        try {
            savedUser = userRepository.save(userMapper.toEntity(payload, password));
        } catch (DataIntegrityViolationException exception) {
            throw new UserAlreadyExistException(String.format("User with name %s already exist", payload.getUserName()));
        } catch (Exception exception) {
            throw new UserCreationFailedException(exception.getMessage());
        }
        return userMapper.toDto(savedUser, password);
    }

    @Override
    public Student createStudent(CreateUser payload, UserDetails userDetails, String password) {
        allowedToCreateUserOrThrow(userDetails, payload.getUserType());
        try {
            Student student = userMapper.toStudent(payload, password, null);
            return userRepository.save(student);
        } catch (DataIntegrityViolationException exception) {
            throw new UserAlreadyExistException(String.format("User with name %s already exist", payload.getUserName()));
        } catch (Exception exception) {
            throw new UserCreationFailedException(exception.getMessage());
        }
    }

    private void allowedToCreateUserOrThrow(UserDetails userDetails, UserType userType) {
        Optional<UserType> currentUserType = UserUtil.getUsersType(userDetails);

        if (currentUserType.isEmpty())
            throw new RuntimeException();

        List<UserType> userAllowedToCreateUserTypes = userTypeAllowedToCreateUsers.get(currentUserType.get());

        if (!userAllowedToCreateUserTypes.contains(userType))
            throw new NotAllowedToCreateUserException(String.format("User with name %s is not allowed to " +
                    "create user of type %s", userDetails.getUsername(), userType));
    }
}
