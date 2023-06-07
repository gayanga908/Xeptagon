package com.test.xeptagon.controllers;

import com.test.xeptagon.enums.ClassModules;
import com.test.xeptagon.enums.UserType;
import com.test.xeptagon.exceptions.ModuleNotAllowedToExecuteException;
import com.test.xeptagon.fatories.ModuleServiceFactory;
import com.test.xeptagon.models.ExecuteModule;
import com.test.xeptagon.utils.UserUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;

@RestController
public class ModuleController {

    private final ModuleServiceFactory moduleServiceFactory;

    public ModuleController(ModuleServiceFactory moduleServiceFactory) {
        this.moduleServiceFactory = moduleServiceFactory;
    }

    @GetMapping(value = "/modules")
    public ResponseEntity<Set<ClassModules>> getClassModules(HttpServletRequest httpServletRequest) {
        UserDetails userDetails = (UserDetails) httpServletRequest.getAttribute("user");
        Optional<UserType> currentUserType = UserUtil.getUsersType(userDetails);
        if (currentUserType.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        Set<ClassModules> classModules = moduleServiceFactory.getModuleService(currentUserType.get())
                .viewModule(httpServletRequest.getUserPrincipal().getName());
        return new ResponseEntity<>(classModules, HttpStatus.OK);
    }

    @PostMapping(value = "/modules")
    public ResponseEntity<String> executeModule(@RequestBody ExecuteModule payload,
                                                HttpServletRequest httpServletRequest) {
        UserDetails userDetails = (UserDetails) httpServletRequest.getAttribute("user");
        Optional<UserType> currentUserType = UserUtil.getUsersType(userDetails);
        if (currentUserType.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        String returnStr;
        try {
            returnStr =
                    moduleServiceFactory.getModuleService(currentUserType.get())
                            .executeModule(httpServletRequest.getUserPrincipal().getName(),
                                    payload.getModule());
        } catch (ModuleNotAllowedToExecuteException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(returnStr, HttpStatus.OK);
    }
}
