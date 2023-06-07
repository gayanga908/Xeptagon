package com.test.xeptagon.services;

import com.test.xeptagon.enums.ClassModules;

import java.util.Set;

public interface ModuleService {
    Set<ClassModules> viewModule(String userName);

    String executeModule(String userName, ClassModules module);
}
