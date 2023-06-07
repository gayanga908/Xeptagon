package com.test.xeptagon.services;

import com.test.xeptagon.entities.Student;
import com.test.xeptagon.enums.UserType;
import com.test.xeptagon.mappers.ClassMapper;
import com.test.xeptagon.models.CreateClass;
import com.test.xeptagon.models.CreateUser;
import com.test.xeptagon.repositories.ClassRepository;
import com.test.xeptagon.utils.PasswordGenerateUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DefaultClassService implements ClassService {

    private final UserService userService;
    private final ClassRepository classRepository;
    private final ClassMapper classMapper;

    public DefaultClassService(UserService userService, ClassRepository classRepository, ClassMapper classMapper) {
        this.userService = userService;
        this.classRepository = classRepository;
        this.classMapper = classMapper;
    }

    @Override
    @Transactional
    public String createClass(CreateClass payload, UserDetails userDetails) {
        String password = PasswordGenerateUtil.generateRandomPassword();
        Set<Student> students = createStudents(payload.getStudents(), userDetails, password);
        classRepository.save(classMapper.toEntity(payload, students));
        return password;
    }

    private Set<Student> createStudents(List<String> studentNames,
                                        UserDetails userDetails, String password) {
        return studentNames.stream().map(studentName -> {
            CreateUser student = new CreateUser(studentName, UserType.STUDENT);
            return userService.createStudent(student, userDetails, password);
        }).collect(Collectors.toSet());

    }
}
