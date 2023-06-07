package com.test.xeptagon.services;

import com.test.xeptagon.enums.ClassModules;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class DefaultModuleService implements ModuleService {

    @Override
    public Set<ClassModules> viewModule(String userName) {
        return Set.of(ClassModules.values());
    }

    @Override
    public String executeModule(String userName, ClassModules module) {
        return "Hello Module " + module.toString();
    }
}
