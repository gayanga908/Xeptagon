package com.test.xeptagon.controllers;

import com.test.xeptagon.exceptions.ClassCreationFailedException;
import com.test.xeptagon.exceptions.ClassNameAlreadyUsedException;
import com.test.xeptagon.exceptions.UserAlreadyExistException;
import com.test.xeptagon.models.CreateClass;
import com.test.xeptagon.services.ClassService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@Tag(name = "Class", description = "Class management APIs")
@RestController
public class ClassController {

    private final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @PostMapping(value = "/classes")
    public ResponseEntity<String> createClass(@RequestBody CreateClass payload,
                                             HttpServletRequest httpServletRequest) {
        UserDetails userDetails = (UserDetails) httpServletRequest.getAttribute("user");
        try {
            return new ResponseEntity<>(classService.createClass(payload, userDetails), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException exception) {
            if (exception.getMessage().contains("users")) {
                throw new UserAlreadyExistException("Cannot create the students. One or more user/users " +
                        "with provided names already exist");
            }
            throw new ClassNameAlreadyUsedException(String.format("Class with name %s already exist",
                    payload.getClassName()));
        } catch (Exception exception) {
            throw new ClassCreationFailedException(exception.getMessage());
        }

    }

}
