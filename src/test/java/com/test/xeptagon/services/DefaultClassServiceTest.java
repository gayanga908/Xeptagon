package com.test.xeptagon.services;

import com.test.xeptagon.entities.User;
import com.test.xeptagon.enums.ClassModules;
import com.test.xeptagon.enums.UserType;
import com.test.xeptagon.mappers.ClassMapper;
import com.test.xeptagon.models.CreateClass;
import com.test.xeptagon.repositories.ClassRepository;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

@SpringBootTest
public class DefaultClassServiceTest {

    @Mock
    private UserService userService;
    @Mock
    private ClassRepository classRepository;
    @Mock
    private ClassMapper classMapper;

    @InjectMocks
    private DefaultClassService classService;

    @Test
    public void test_createClass() {
        CreateClass createClass = new CreateClass();
        createClass.setClassName("className");
        createClass.setModules(List.of(ClassModules.FACE_DETECT));
        createClass.setStudents(List.of("student"));
        assertNotNull(classService.createClass(createClass, Mockito.mock(UserDetails.class)));


    }
}
