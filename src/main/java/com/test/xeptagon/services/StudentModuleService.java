package com.test.xeptagon.services;

import com.test.xeptagon.entities.Student;
import com.test.xeptagon.enums.ClassModules;
import com.test.xeptagon.exceptions.ModuleNotAllowedToExecuteException;
import com.test.xeptagon.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentModuleService implements ModuleService {

    private final UserRepository userRepository;

    public StudentModuleService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Set<ClassModules> viewModule(String userName) {
        Student student = (Student) userRepository.findUserByUserName(userName);
        return student.getClasses().stream().flatMap(aClass -> aClass.getClassModules().stream()).collect(Collectors.toSet());
    }

    @Override
    public String executeModule(String userName, ClassModules module) {
        Student student = (Student) userRepository.findUserByUserName(userName);
        Set<ClassModules> classModules = student.getClasses().stream()
                .flatMap(aClass -> aClass.getClassModules().stream()).collect(Collectors.toSet());
        if (!classModules.contains(module)) {
            throw new ModuleNotAllowedToExecuteException(String.format("Student does not have access to the" +
                    " %s module", module.toString()));
        }
        return "Hello Module " + module.toString();
    }
}
